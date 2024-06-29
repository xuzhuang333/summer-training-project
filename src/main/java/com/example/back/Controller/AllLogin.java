package com.example.back.Controller;

import com.example.back.Entity.Yonghu;
import com.example.back.beans.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = "控制⽤户登录的接⼝")
public class AllLogin {
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/doLogin")
    @ResponseBody
    @ApiOperation(value = "登陆接口")
    public Yonghu login(@RequestBody Yonghu yonghu){
        Yonghu yonghu2 = new Yonghu();
        try {
            yonghu2= jdbc.queryForObject("select * from yonghu where id=? and password=?",new BeanPropertyRowMapper<>(Yonghu.class),yonghu.getId(),yonghu.getPassword());
            log.info("登录成功:{}",yonghu2);
        } catch (Exception e) {
            e.printStackTrace();
            JsonResult<String> result = new JsonResult<>();
            result.setResult("用户名或密码错误");
            result.setCode(201);
            log.error("没有收到的数据");
        }
        return yonghu2;
    }



}
