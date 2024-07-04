package com.example.back.Controller;

import com.example.back.Entity.Yonghu;
import com.example.back.beans.EmailForPost;
import com.example.back.beans.JsonResult;
import com.example.back.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class EmailController {  // 更正了类名
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MailService mailService;  // 注入 MailService

    @PostMapping("/sendemailcode")
    public JsonResult<String> emailVerification(@RequestBody EmailForPost emailForPost) {
        JsonResult<String> result = new JsonResult<>();
        Yonghu yonghu;
        try {
            yonghu = jdbc.queryForObject("SELECT * FROM yonghu WHERE id=?;", new BeanPropertyRowMapper<>(Yonghu.class), emailForPost.getStudent_id());
        } catch (DataAccessException e) {
            result.setResult("用户不存在");
            result.setCode(201);
            e.printStackTrace();
            return result;  // 添加了返回语句
        }

        System.out.println(emailForPost);

        String code = mailService.generateVerificationCode();  // 通过实例调用方法
        System.out.println(code);
        try {
            jdbc.update("UPDATE yonghu set code  = ? where id =?", code, emailForPost.getStudent_id());
            mailService.sendVerificationCode(emailForPost.getEmail(), code);  // 通过实例调用方法
            result.setCode(200);
            result.setResult("发送验证码成功");
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            result.setResult("发送验证码失败");
            result.setCode(202);
            e.printStackTrace();
            return result;
        }
    }
}
