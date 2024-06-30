package com.example.back.Controller;

import com.example.back.Entity.Yonghu;
import com.example.back.beans.CourseTable;
import com.example.back.beans.IdForPost;
import com.example.back.beans.JsonResult;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.awt.*;
import java.util.ArrayList;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = "查询一切信息的接⼝")
public class Query {
    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping("/dormitory/{id}")
    @ApiOperation(value = "查询寝室接口，老师不可用")
    public JsonResult<ArrayList> Querydormitory(@PathVariable Integer id){
        ArrayList<String> list = new ArrayList<String>();//返回的list数组，里面存有室友的名字
        JsonResult<ArrayList> arrayListJsonResult = new JsonResult<> ();
        ArrayList<Yonghu> arrayList = new ArrayList<Yonghu>();
        Yonghu yonghu ;
        try {
            yonghu = jdbc.queryForObject("SELECT * FROM yonghu where id=? and leibie != 1",new BeanPropertyRowMapper<>(Yonghu.class),id);
            list.add(yonghu.getDormitory_number());//加入寝室号
            log.info("学生存在:{}",yonghu);
            try {
                arrayList = (ArrayList<Yonghu>) jdbc.query("SELECT * FROM yonghu where dormitory_number =? and name!= ? ",new BeanPropertyRowMapper<>(Yonghu.class),yonghu.getDormitory_number(),yonghu.getName());
                log.info("找到室友");
                int size = arrayList.size();
                int number = 0;
                for (number = 0; number < size; number++) {
                    Yonghu yonghu1 = arrayList.get(number);
                    list.add(yonghu1.getName());//加入室友名字
                }
                arrayListJsonResult.setResult(list);
                arrayListJsonResult.setCode(200);
            } catch (DataAccessException e) {
                e.printStackTrace();
                log.error("宿舍只有一个人");
                arrayListJsonResult.setCode(201);
            }

        } catch (DataAccessException e) {
            log.error("ID不存在或ID不是学生ID！");
            arrayListJsonResult.setCode(202);
            e.printStackTrace();
        }
        return arrayListJsonResult;
    }

    @GetMapping("/volunteer/{id}")
    @ApiOperation(value = "查询志愿时长接口，师生都可用")
    public int Queryvolunteer(@PathVariable Integer id){
        Yonghu yonghu = new Yonghu();
        JsonResult<Yonghu> yonghuJsonResult = new JsonResult<>();
        try {
            yonghu = jdbc.queryForObject("SELECT * FROM yonghu where id=?",new BeanPropertyRowMapper<>(Yonghu.class),id);
            yonghuJsonResult.setResult(yonghu);
            yonghuJsonResult.setCode(200);
            log.info("该师生存在:{}",yonghu);

        } catch (DataAccessException e) {
            yonghuJsonResult.setCode(202);
            e.printStackTrace();
            log.error("ID不存在！");
        }

        return yonghuJsonResult.getResult().getVolunteer();
    }

    @GetMapping("/myclass/{id}")
    @ApiOperation(value = "查询班级信息接口，学生都可用")
    public JsonResult<ArrayList> Querymyclass(@PathVariable Integer id){
        Yonghu yonghu = new Yonghu();//存找到的用户
        String name = null;//存辅导员
        String college;//存学院
        String majorclass;//学院班级
        ArrayList<Object> arrayList = new ArrayList<>();
        Integer count = 0;//班级人数

        JsonResult<ArrayList> yonghuJsonResult = new JsonResult<>();
        try {
            yonghu = jdbc.queryForObject("SELECT * FROM yonghu where id=?",new BeanPropertyRowMapper<>(Yonghu.class),id);
            college = yonghu.getCollege();
            majorclass = yonghu.getMajor_class();
            log.info("该学生存在:{}",yonghu);
            try {
                count = jdbc.queryForObject("SELECT COUNT(*) AS count FROM yonghu WHERE major_class = ?",Integer.class,majorclass);
            } catch (DataAccessException e) {
                e.printStackTrace();
                yonghuJsonResult.setCode(201);

                log.error("查询班级人数错误");
            }
            try {
                name  = jdbc.queryForObject("SELECT name FROM yonghu where college=? and leibie = ?",String.class,college,1);
            } catch (DataAccessException e) {
                e.printStackTrace();
                yonghuJsonResult.setCode(203);
                log.error("查询辅导员姓名错误");
            }
            arrayList.add(name);
            arrayList.add(college);
            arrayList.add(majorclass);
            arrayList.add(count);
            yonghuJsonResult.setResult(arrayList);
            yonghuJsonResult.setCode(200);
        } catch (DataAccessException e) {
            yonghuJsonResult.setCode(202);
            e.printStackTrace();
            log.error("ID不存在！");
        }

        return yonghuJsonResult;
    }

    @PostMapping("/queryCourse")
    @ApiOperation(value = "查询学生课程消息")
    public JsonResult<ArrayList<CourseTable>> querycourse(@RequestBody IdForPost id){
        JsonResult<ArrayList<CourseTable>> jsonResult = new JsonResult<>();
        ArrayList<CourseTable> arrayList= new ArrayList<>();
        try {
            arrayList = (ArrayList<CourseTable>) jdbc.query("SELECT course_name,course_room,startDate,week,time,duration FROM courserelation natural join course natural join weekforcourse where student_id = ?",new BeanPropertyRowMapper<>(CourseTable.class),(Integer)id.getId());
            System.out.println(arrayList);
            jsonResult.setResult(arrayList);
            jsonResult.setCode(200);

            log.info("查询成功");

        } catch (DataAccessException e) {
            e.printStackTrace();
            jsonResult.setCode(201);
            log.error("查询失败");
        }


        return jsonResult ;


    }




    }



