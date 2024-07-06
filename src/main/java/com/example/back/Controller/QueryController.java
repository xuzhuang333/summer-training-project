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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = "查询一切信息的接⼝")
public class QueryController {
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
            yonghu = jdbc.queryForObject("SELECT * FROM yonghu where id=? and leibie != 1 and dormitory_state = 1",new BeanPropertyRowMapper<>(Yonghu.class),id);
            list.add(yonghu.getDormitory_number());//加入寝室号
            log.info("学生存在:{}",yonghu);
            try {
                arrayList = (ArrayList<Yonghu>) jdbc.query("SELECT * FROM yonghu where dormitory_number =? and name!= ? and dormitory_state = ?",new BeanPropertyRowMapper<>(Yonghu.class),yonghu.getDormitory_number(),yonghu.getName(),1);
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
            log.error("该学生为走读生！");
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

    @PostMapping("/coursemsg")
    @ApiOperation(value = "查询所有课程消息")
    public JsonResultZDK coursemsg(@RequestBody Course course){
        JsonResultZDK res = new JsonResultZDK();
        List<Course> message = null;
        try {
            message = jdbc.query("select * from course where course_name=?"
                    ,new BeanPropertyRowMapper<>(Course.class),course.getCourse_name());
            res.setCode("200");
            res.setMsg("成功");
            res.setResult(message);
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("失败");
            return res;
        }
    }

    @PostMapping("/library") //图书馆
    @ApiOperation(value = "查询图书馆暑假")
    public JsonResult library(@RequestBody String bookname){
        System.out.println("图书查询");
        JsonResult res = new JsonResult();

        try {
            Book bk = jdbc.queryForObject("select * from book where name=?",
                    new BeanPropertyRowMapper<>(Book.class), bookname);
            res.setCode(200);
            res.setResult(bk);//用户数据放入结果中
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode(201);
            res.setResult("您搜索的图书不存在");
            return res;
        }
    }

    @GetMapping("/life/{ID}")//生活
    @ApiOperation(value = "查询生活费用")
    public JsonResult life(@PathVariable int ID){
        System.out.println("生活");

        JsonResult res = new JsonResult();
        try {
            Life dys=new Life();

            dys =  jdbc.queryForObject("select * from life where id=?",
                    new BeanPropertyRowMapper<>(Life.class),ID);

            res.setResult(dys);
            res.setCode(200);
            return res;
        } catch (DataAccessException e) {
            res.setResult("当前无信息");
            res.setCode(201);//
            return res;
        }
    }


    }



