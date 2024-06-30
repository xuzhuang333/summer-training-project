package com.soft.back.controller;

import com.soft.back.beans.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/doActivate")//激活
    public JsonResult doActivate(@RequestBody Yonghu yonghu){
        //检验是否收到数据
        log.info(yonghu.toString());
        //判断账号与初始密码是否匹配
        Yonghu user= null;
        try {
            user = jdbc.queryForObject("select * from yonghu where id=? and password=?",
                    new BeanPropertyRowMapper<>(Yonghu.class), yonghu.getId(), yonghu.getPassword());
            //说明正常
            //修改激活状态
            if(user.getActivate()!=1){
                try {
                    int update=jdbc.update("update yonghu set activate=? where id=?",
                            1,yonghu.getId());
                    //返回更新后的用户信息
                    user = jdbc.queryForObject("select * from yonghu where id = ?",
                            new BeanPropertyRowMapper<>(Yonghu.class), yonghu.getId());

                    return new JsonResult(user);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                    JsonResult<String> result=new JsonResult<>();
                    result.setCode(201);
                    result.setResult("激活失败");
                    return result;
                }
            }else{
                JsonResult<String> result=new JsonResult<>();
                result.setCode(201);
                result.setResult("用户已激活");
                return result;
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            //说明异常，有错误
            JsonResult<String> result=new JsonResult<>();
            result.setCode(201);
            result.setResult("用户名或初始密码错误");
            return result;
        }
    }

    @GetMapping("/notices")
    public JsonResult notices(){
        System.out.println("公告");
        JsonResult res = new JsonResult();
        try {
            List<Notice> dys=null;
            dys =  jdbc.query("select notice_title,DATE(time) as time,content from notice",new BeanPropertyRowMapper<>(Notice.class));
            res.setResult(dys);//数据
            res.setCode(200);//代码  成功0
            return res;
        } catch (DataAccessException e) {
            res.setResult("出错了"+e.getMessage());//数据
            res.setCode(201);//代码  出错
            return res;
        }
    }

    @GetMapping("/courses")
    public JsonResult courses(){
        System.out.println("课程信息");
        JsonResult res = new JsonResult();
        try {
            List<Course> dys=null;
            dys =  jdbc.query("select * from course",new BeanPropertyRowMapper<>(Course.class));
            res.setResult(dys);//数据
            res.setCode(200);//代码  成功0
            return res;
        } catch (DataAccessException e) {
            res.setResult("出错了"+e.getMessage());//数据
            res.setCode(201);//代码  出错
            return res;
        }
    }

    @PostMapping("/ChoiceCourse")//选课
    public JsonResult ChoiceCourse(@RequestBody Courserelation courserelation) {
        System.out.println(courserelation);
        Integer A;
        JsonResult jsonResult = new JsonResult();
        Course course=null;
        course=jdbc.queryForObject("select * from course where course_id=?",
                new BeanPropertyRowMapper<>(Course.class),courserelation.getCourseId());

        Courserelation user=new Courserelation();
        //检查该学生是否已选该门课程
        try {
            user=jdbc.queryForObject("select * from courserelation where course_name=? and student_id=?",
                    new BeanPropertyRowMapper<>(Courserelation.class),courserelation.getCourseName(),courserelation.getStudentId());
        } catch (DataAccessException e) {
            e.printStackTrace();
            user=null;
        }
        if(user==null){
            //当前容量小于最大容量则能选课
            if(course.getNowCapacity()< course.getMaxCapacity()){
                try {
                    A=jdbc.queryForObject("select teacher_id from teacher where name=?",
                            Integer.class,courserelation.getTeacherName());
                    //插入选课信息
                    jdbc.update("INSERT INTO courserelation(course_id,course_name,teacher_id,student_id)VALUES(?,?,?,?)",
                            courserelation.getCourseId(),courserelation.getCourseName(),A,courserelation.getStudentId());
                    //课程当前容量+1
                    int update=jdbc.update("update course set now_capacity=?",
                            course.getNowCapacity()+1);
                    //获取最新课程信息
                    course = jdbc.queryForObject("select * from course where course_id = ?",
                            new BeanPropertyRowMapper<>(Course.class), course.getCourseId());
                    jsonResult.setCode(200);
                    jsonResult.setResult(course);
                } catch (DataAccessException E) {
                    E.printStackTrace();
                    jsonResult.setCode(201);
                    jsonResult.setResult("选课失败");
                }
            }else {
                jsonResult.setCode(202);
                jsonResult.setResult("课程容量已满，选课失败");
            }
        }else{
            jsonResult.setCode(201);
            jsonResult.setResult("已选择，选课失败");
        }



        return jsonResult;
    }
}

