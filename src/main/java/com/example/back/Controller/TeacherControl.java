package com.example.back.Controller;

import com.example.back.beans.Classes;
import com.example.back.beans.JsonResult;
import com.example.back.beans.NoticeForPost;
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
@Api(tags = "只由老师控制的功能接⼝")
public class TeacherControl {
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/postNotice")
    @ApiOperation(value = "信息发布")
    public JsonResult<String> postnotice(@RequestBody NoticeForPost noticeForPost){
        JsonResult<String> result = new JsonResult<String>();
        try {
            jdbc.update("INSERT INTO notice(notice_title,content)VALUES(?,?)",noticeForPost.getTitle(),noticeForPost.getContent());
            result.setResult("消息发布成功");
            result.setCode(200);
            log.info("消息已发布");

        } catch (DataAccessException e) {
            e.printStackTrace();
            result.setResult("消息未发布成功");
            result.setCode(201);
            log.error("消息未发布");

        }
        return result;

    }

    @GetMapping("/activatequery/{College}")
    @ApiOperation(value = "老师查看激活")
    public JsonResult activatequery(@PathVariable String College ){
        System.out.println("激活查看");
        System.out.println(College);

        JsonResult res = new JsonResult();
        try {
            ArrayList<Integer> dys = new ArrayList<>();
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

    @GetMapping("/dataAnalysis1/{College}")//学生数据分析
    @ApiOperation(value = "学生数据分析1")
    public JsonResult dataAnalysis1(@PathVariable String College ) {
        System.out.println("数据分析1");
        System.out.println(College);
        JsonResult res = new JsonResult();

        ArrayList<Integer> data=new ArrayList<>();
        int total;
        total = jdbc.queryForObject("select count(*) from yonghu where college=? and leibie = ?",
                Integer.class, College, 0);

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where college=? and leibie =? and gender = ?",
                Integer.class, College, 0, "男");

        int num2;
        num2 = total - num1;

        int num3;
        num3 = jdbc.queryForObject("select count(*) from yonghu where college=? and leibie=? and politics=?",
                Integer.class, College, 0, 1);

        int num4;
        num4 = total - num3;

        data.add(total);
        data.add(num1);
        data.add(num2);
        data.add(num3);
        data.add(num4);

        res.setResult(data);
        res.setCode(200);
        return res;
    }


    @GetMapping("/dataAnalysis2/{College}")//学生数据分析
    @ApiOperation(value = "学生数据分析2")
    public JsonResult dataAnalysis2(@PathVariable String College ){
        System.out.println("数据分析2");
        System.out.println(College);

        JsonResult res = new JsonResult();
        try {
            List<Classes> classesList=null;
            classesList=jdbc.query("select major_class,count(*) as number from yonghu where college=? and leibie=? group by major_class",
                    new BeanPropertyRowMapper<>(Classes.class),College,0);

            res.setResult(classesList);
            res.setCode(200);
            return res;
        } catch (DataAccessException e) {
            res.setResult("查询失败");
            res.setCode(201);//
            return res;
        }
    }


}
