package com.example.back.Controller;

import com.example.back.Entity.Yonghu;
import com.example.back.beans.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @PostMapping("/doActivate")//激活
    @ApiOperation(value = "激活")
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

}
