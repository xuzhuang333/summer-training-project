package com.example.back.Controller;

import com.example.back.Entity.Notice;
import com.example.back.beans.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin

@Api(tags = "首页公告展示的接口")
public class NoticeController {
    @Autowired
    private JdbcTemplate jdbc;


    @GetMapping("/notices")
    @ApiOperation(value = "首页公告展示")
    public JsonResult notices(){
        System.out.println("公告");
        JsonResult res = new JsonResult();
        try {
            List<Notice> dys=null;
            dys =  jdbc.query("select notice_title,DATE(time) as time,content from notice order by notice_id DESC",new BeanPropertyRowMapper<>(Notice.class));
            System.out.println(dys);
            res.setResult(dys);//数据
            res.setCode(200);//代码  成功0
            return res;
        } catch (DataAccessException e) {
            res.setResult("出错了"+e.getMessage());//数据
            res.setCode(201);//代码  出错
            return res;
        }
    }



}

