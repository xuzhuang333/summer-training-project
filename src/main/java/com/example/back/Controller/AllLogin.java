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
    public JsonResult<Yonghu> login(@RequestBody Yonghu yonghu){
        JsonResult<Yonghu> yonghu2 = new JsonResult<Yonghu>();
        try {
            Yonghu yonghu3= jdbc.queryForObject("select * from yonghu where id=? and password=?",new BeanPropertyRowMapper<>(Yonghu.class),yonghu.getId(),yonghu.getPassword());
            if(yonghu3.getActivate() == 1){
                log.info("登录成功:{}",yonghu2);
                yonghu2.setResult(yonghu3);
                yonghu2.setCode(200);
                return yonghu2;
            }
            else{
                log.info("账户未激活:{}",yonghu2);
                yonghu2.setCode(201);
                return yonghu2;
            }
        } catch (Exception e) {

            e.printStackTrace();
            yonghu2.setCode(202);
            log.error("没有收到的数据");
            return yonghu2;
        }
    }



}
