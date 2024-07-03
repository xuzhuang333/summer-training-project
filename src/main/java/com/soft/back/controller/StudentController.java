package com.soft.back.controller;

import com.soft.back.beans.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    /*@GetMapping("/courses")
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
    }*/

/*    @PostMapping("/ChoiceCourse")//选课
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
    }*/

    @PostMapping("/library") //图书馆
    public JsonResult library(String bookname){
        System.out.println("图书查询");
        System.out.println(bookname);
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

    @GetMapping("/destroyinfo/{Student_id}")//可进行销假的假期展示
    public JsonResult destroyinfo(@PathVariable int Student_id){
        System.out.println("销假信息");

        JsonResult res = new JsonResult();
        try {
            List<Vacation> dys=null;

            dys =  jdbc.query("select * from vacation where student_id=? and state !=?",
                    new BeanPropertyRowMapper<>(Vacation.class),Student_id,2);

            res.setResult(dys);
            res.setCode(200);
            return res;
        } catch (DataAccessException e) {
            res.setResult("当前无请假信息，无需销假");
            res.setCode(201);//
            return res;
        }
    }

    @GetMapping("/destroy/{Vacation_id}")//销假
    public JsonResult destroy(@PathVariable int Vacation_id){

        JsonResult res = new JsonResult();
        try {
            jdbc.update("update vacation set state=? where vacation_id=?",2,Vacation_id);
            res.setCode(200);
            res.setResult("销假成功");
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.info("销假失败");
            res.setCode(201);
            res.setResult("销假失败");
            return res;
        }
    }

    @GetMapping("/life/{ID}")//生活
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

    @GetMapping("/activatequery/{College}")
    public JsonResult activatequery(@PathVariable String College ){
        System.out.println("激活查看");
        System.out.println(College);

        JsonResult res = new JsonResult();
        try {
            ArrayList<Integer>dys = new ArrayList<>();
            Integer dys1;
            Integer dys2;
            Integer dys3;

            dys1 = jdbc.queryForObject("select count(*) from yonghu where college=? ",
                    Integer.class,College);
            dys2 = jdbc.queryForObject("select count(*) from yonghu where college=? and activate=?",
                    Integer.class,College,1);
            dys3 = dys1-dys2;

            dys.add(dys1);
            dys.add(dys2);
            dys.add(dys3);
            res.setResult(dys);
            res.setCode(200);
            return res;
        } catch (DataAccessException e) {
            res.setResult("查询失败");
            res.setCode(201);//
            return res;
        }
    }

    @GetMapping("/dataAnalysis/{College}")//学生数据分析
    public JsonResult dataAnalysis(@PathVariable String College ){
        System.out.println("数据分析");

        JsonResult res = new JsonResult();
        try {
            ArrayList<Integer>dys = new ArrayList<>();
            Integer dys1;
            Integer dys2;
            Integer dys3;

            dys1 = jdbc.queryForObject("select count(*) from yonghu where college=? ",
                    Integer.class,College);
            dys2 = jdbc.queryForObject("select count(*) from yonghu where college=? and activate=?",
                    Integer.class,College,1);
            dys3 = dys1-dys2;

            dys.add(dys1);
            dys.add(dys2);
            dys.add(dys3);
            res.setResult(dys);
            res.setCode(200);
            return res;
        } catch (DataAccessException e) {
            res.setResult("查询失败");
            res.setCode(201);//
            return res;
        }
    }

    @GetMapping("/sex")
    public JsonResult<List<Sexdaping>> sex(){
        JsonResult res = new JsonResult();
        //返回一个包含两个元素的数组（第一个元素代表男生，第二个元素代表女生），数组中的元素是Sexdaping类
        ArrayList<Sexdaping>sex = new ArrayList<>();

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where gender=? ",
                Integer.class,"男");
        Sexdaping sexdaping1 = new Sexdaping();
        sexdaping1.setValue(num1);
        sexdaping1.setName("男");

        int num2;
        num2 = jdbc.queryForObject("select count(*) from yonghu where gender=? ",
                Integer.class,"女");
        Sexdaping sexdaping2 = new Sexdaping();
        sexdaping2.setValue(num2);
        sexdaping2.setName("女");

        sex.add(sexdaping1);
        sex.add(sexdaping2);

        res.setResult(sex);
        return res;
    }

    @GetMapping("/baodao")
    public JsonResult<List<Baodaodaping>> baodao(){
        JsonResult res = new JsonResult();
        //需要返回一个包含两个元素的数组（第一个元素代表已报道，第二个元素代表未报到），数组中的元素是Baodaodaping类
        ArrayList<Baodaodaping>baodao = new ArrayList<>();

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where activate=? and leibie=?",
                Integer.class,1,0);
        Baodaodaping baodaodaping1 = new Baodaodaping();
        baodaodaping1.setValue(num1);
        baodaodaping1.setName("已报到");

        int num2;
        num2 = jdbc.queryForObject("select count(*) from yonghu where activate=? and leibie=?",
                Integer.class,0,0);
        Baodaodaping baodaodaping2 = new Baodaodaping();
        baodaodaping2.setValue(num2);
        baodaodaping2.setName("未报到");

        baodao.add(baodaodaping1);
        baodao.add(baodaodaping2);

        res.setResult(baodao);
        return res;

    }

    @GetMapping("/leibie")
    public JsonResult<List<Leibiedaping>> leibie(){
        JsonResult res = new JsonResult();
        //需要返回一个包含两个元素的数组（第一个元素代表团员，第二个元素代表群众），数组中的元素是Leibiedaping类
        ArrayList<Leibiedaping>leibie = new ArrayList<>();

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where politics=? ",
                Integer.class,1);
        Leibiedaping leibiedaping1=new Leibiedaping();
        leibiedaping1.setValue(num1);
        leibiedaping1.setName("团员");

        int num2;
        num2 = jdbc.queryForObject("select count(*) from yonghu where politics=? ",
                Integer.class,0);
        Leibiedaping leibiedaping2=new Leibiedaping();
        leibiedaping2.setValue(num2);
        leibiedaping2.setName("群众");

        leibie.add(leibiedaping1);
        leibie.add(leibiedaping2);

        res.setResult(leibie);
        return res;

    }

    @GetMapping("/minzu")
    public JsonResult<List<Minzudaping>> minzu(){
        JsonResult res = new JsonResult();
        //需要返回一个包含八个元素的数组（代表不同民族），数组中的元素是Minzudaping类
        ArrayList<Minzudaping>minzu = new ArrayList<>();

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"汉族",0);
        Minzudaping minzudaping1=new Minzudaping();
        minzudaping1.setValue(num1);
        minzudaping1.setName("汉族");

        int num2;
        num2 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"壮族",0);
        Minzudaping minzudaping2=new Minzudaping();
        minzudaping2.setValue(num2);
        minzudaping2.setName("壮族");

        int num3;
        num3 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"维吾尔族",0);
        Minzudaping minzudaping3=new Minzudaping();
        minzudaping3.setValue(num3);
        minzudaping3.setName("维吾尔族");

        int num4;
        num4 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"回族",0);
        Minzudaping minzudaping4=new Minzudaping();
        minzudaping4.setValue(num4);
        minzudaping4.setName("回族");

        int num5;
        num5 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"苗族",0);
        Minzudaping minzudaping5=new Minzudaping();
        minzudaping5.setValue(num5);
        minzudaping5.setName("苗族");

        int num6;
        num6 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"满族",0);
        Minzudaping minzudaping6=new Minzudaping();
        minzudaping6.setValue(num6);
        minzudaping6.setName("满族");

        int num7;
        num7 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"土家族",0);
        Minzudaping minzudaping7=new Minzudaping();
        minzudaping7.setValue(num7);
        minzudaping7.setName("土家族");

        int num;
        int num8;
        num = jdbc.queryForObject("select count(*) from yonghu where leibie=?",
                Integer.class,0);
        num8=num-num1-num2-num3-num4-num5-num6-num7;
        Minzudaping minzudaping8=new Minzudaping();
        minzudaping8.setValue(num8);
        minzudaping8.setName("其他");

        minzu.add(minzudaping1);
        minzu.add(minzudaping2);
        minzu.add(minzudaping3);
        minzu.add(minzudaping4);
        minzu.add(minzudaping5);
        minzu.add(minzudaping6);
        minzu.add(minzudaping7);
        minzu.add(minzudaping8);

        res.setResult(minzu);
        return res;

    }
}

