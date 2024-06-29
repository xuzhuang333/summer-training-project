package com.soft.back.controller;

import com.soft.back.beans.JsonResult;
import com.soft.back.beans.Notice;
import com.soft.back.beans.Yonghu;
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

    @PostMapping("/doActivate")
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
            dys =  jdbc.query("select * from notice",new BeanPropertyRowMapper<>(Notice.class));
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

