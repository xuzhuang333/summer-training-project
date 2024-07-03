package com.example.back.Controller;

import com.example.back.Entity.Yonghu;
import com.example.back.beans.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = "选课接口")
public class SelectingCourses {
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/selectingcourses1")
    @ApiOperation(value = "写数据给第一页选课界面")
    public JsonResult<ArrayList<FirstSelectingCourse>> selectingcourses1(@RequestBody IdForPost id){
        JsonResult<ArrayList<FirstSelectingCourse>> result = new JsonResult<>();
        String student_college;
        String all = "ALL";
        //获取学生的学院
        log.info("该学生ID:{}",id);
        try {
            student_college = jdbc.queryForObject("SELECT college FROM yonghu where id = ?",String.class,id.getId());
            log.info("该学生学院为：{}",student_college);
        } catch (DataAccessException e) {
            result.setCode(201);
            log.error("未找到学院数据");
            e.printStackTrace();
            return result;
        }
        //获取该学院需要的课名、课号、课学分、状态
        ArrayList<FirstSelectingCourse> arrayList;
        try {
            arrayList = (ArrayList<FirstSelectingCourse>) jdbc.query("SELECT DISTINCT course_name, course_num, credit, CASE WHEN (SELECT COUNT(*) FROM course c2 WHERE c2.course_name = c1.course_name AND c2.state = 1) = (SELECT COUNT(*) FROM course c3 WHERE c3.course_name = c1.course_name) THEN 1 ELSE 0 END AS state FROM course c1 WHERE college = ? OR college = ?",new BeanPropertyRowMapper<>(FirstSelectingCourse.class),student_college,all);
            result.setResult(arrayList);
            result.setCode(200);
            log.info("查到该学院的课程为：{}",arrayList);
        } catch (DataAccessException e) {
            result.setCode(202);
            log.error("未查到该学院的课程");
            e.printStackTrace();
        }


        return result;

    }


    @PostMapping("/selectingcourses2")
    @ApiOperation(value = "写数据给第二页选课界面")
    public JsonResult<ArrayList<SecondSelectingCourse>> selectingcourses2(@RequestBody CourseForPost courseForPost){
        JsonResult<ArrayList<SecondSelectingCourse>> result = new JsonResult<>();
        //获取该课程的所有老师教授的班级
        log.info("传入的课程信息：{}",courseForPost);
        ArrayList<SecondSelectingCourse> arrayList;
        try {
            arrayList = (ArrayList<SecondSelectingCourse>) jdbc.query("SELECT cs.course_name, cs.course_id, cs.course_num, cs.course_teacher, cs.course_room, cs.max_capacity, cs.now_capacity, CASE WHEN has_state_3.count_state_3 > 0 AND cs.state != 3 THEN 2 ELSE cs.state END AS state FROM ( SELECT c.course_name, c.course_id, c.course_num, c.course_teacher, c.course_room, c.max_capacity, c.now_capacity, CASE WHEN cr.student_id = ? AND c.course_id = cr.course_id THEN 3 ELSE c.state END AS state FROM course c LEFT JOIN courserelation cr ON c.course_name = cr.course_name AND cr.student_id = ? WHERE c.course_num = ? AND c.course_name = ? ) AS cs LEFT JOIN ( SELECT COUNT(*) AS count_state_3 FROM ( SELECT c.course_name, c.course_id, c.course_num, c.course_teacher, c.course_room, c.max_capacity, c.now_capacity, CASE WHEN cr.student_id = ? AND c.course_id = cr.course_id THEN 3 ELSE c.state END AS state FROM course c LEFT JOIN courserelation cr ON c.course_name = cr.course_name WHERE c.course_num = ? AND c.course_name = ? ) AS cs_inner WHERE cs_inner.state = 3 ) AS has_state_3 ON 1=1;",new BeanPropertyRowMapper<>(SecondSelectingCourse.class),courseForPost.getStudent_id(),courseForPost.getStudent_id(),courseForPost.getCourse_num(),courseForPost.getCourse_name(),courseForPost.getStudent_id(),courseForPost.getCourse_num(),courseForPost.getCourse_name());
            log.info("获取到该课程下所有老师的课：{}",arrayList);
            result.setResult(arrayList);
            result.setCode(200);
        } catch (DataAccessException e) {
            result.setCode(201);
            e.printStackTrace();
            log.error("获取该课程下所有老师的课失败");
        }

        return result;

    }

    @PostMapping("/selectingcourses3")
    @ApiOperation(value = "选课后操作数据库")
    public JsonResult<String> selectingcourses3(@RequestBody CourseForPost courseForPost){
        JsonResult<String> result = new JsonResult<>();
        int state = 1;
        String course_name;//课程名字
        int teacher_id;//老师id
        //获取该课程的所有老师教授的班级
        log.info("传入的课程id：{}",courseForPost);
        try {
            state = jdbc.queryForObject("SELECT state FROM course WHERE course_id = ?",Integer.class,courseForPost.getCourse_id());
        } catch (DataAccessException e) {
            result.setCode(201);
            result.setResult("无法得到课程状态");
            e.printStackTrace();
            return result;
        }
        if (state == 0){
            try {
                jdbc.update("UPDATE course SET now_capacity = now_capacity + 1, state = CASE WHEN now_capacity  = max_capacity THEN 1 ELSE 0 END WHERE course_id = ? AND now_capacity < max_capacity",courseForPost.getCourse_id());
                course_name = jdbc.queryForObject("SELECT course_name FROM course WHERE course_id = ?",String.class,courseForPost.getCourse_id());
                teacher_id = jdbc.queryForObject("SELECT teacher_id FROM course WHERE course_id = ?",int.class,courseForPost.getCourse_id());
                log.info("课程号：{}",course_name);
                log.info("老师ID：{}",teacher_id);
                try {
                    jdbc.update("INSERT INTO courserelation (course_id, course_name, teacher_id, student_id) VALUES (?, ?, ?, ?)",courseForPost.getCourse_id(),course_name,teacher_id,courseForPost.getStudent_id());
                    result.setCode(200);
                    result.setResult("选课成功");
                    return result;
                } catch (DataAccessException e) {
                    result.setCode(203);
                    result.setResult("你已经选过这门课了");
                    e.printStackTrace();
                    return result;
                }
            } catch (DataAccessException e) {
                result.setCode(202);
                result.setResult("选课出现异常，请联系管理员");
                e.printStackTrace();
                return result;
            }
        }
        else{
            result.setCode(203);
            result.setResult("该课程已满");
            return result;
        }
    }
    @PostMapping("/showCourses")
    @ApiOperation(value = "退课信息展示端口")
    public JsonResult<ArrayList<Course>> showCourses(@RequestBody IdForPost id){
        JsonResult<ArrayList<Course>> result = new JsonResult<>();
        //获取选过的所有的课程信息
        log.info("传入的学生id：{}",id);
        ArrayList<Course> arrayList;
        try {
            arrayList = (ArrayList<Course>) jdbc.query("SELECT course_name, course_num, course_teacher, course_id FROM course WHERE course_id IN ( SELECT course_id FROM courserelation WHERE student_id = ? )",new BeanPropertyRowMapper<>(Course.class),id.getId());
            log.info("获取到该学生已选的课：{}",arrayList);
            result.setResult(arrayList);
            result.setCode(200);
        } catch (DataAccessException e) {
            result.setCode(201);
            e.printStackTrace();
            log.info("获取到已选的课失败");
        }
        return result;
    }

    @PostMapping("/dropCourses")
    @ApiOperation(value = "退课端口")
    public JsonResult<String> dropCourses(@RequestBody CourseForDrop courseForDrop){
        JsonResult<String> result = new JsonResult<>();
        //获取选过的所有的课程信息
        log.info("传入的退课数据：{}",courseForDrop);
        try {
            jdbc.update("UPDATE course SET now_capacity = now_capacity - 1 WHERE course_id = ?",courseForDrop.getCourse_id());
            log.info("更新course表now_capacity成功");
            try {
                jdbc.update("DELETE FROM courserelation WHERE course_id = ? AND student_id = ?",courseForDrop.getCourse_id(),courseForDrop.getStudent_id());
                result.setCode(200);
                result.setResult("退课成功");
                log.info("从cr表删除成功");
                return(result);
            } catch (DataAccessException e) {
                result.setCode(201);
                result.setResult("退课失败");
                log.error("从cr表删除失败");
                e.printStackTrace();
                return result;
            }
        } catch (DataAccessException e) {
            result.setCode(201);
            result.setResult("退课失败");
            log.error("更新course表now_capacity失败");
            e.printStackTrace();
            return(result);
        }
    }



}
