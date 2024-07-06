package com.example.back.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Api(value = "邮箱服务接口")
public class MailService {
    @Autowired
    private JavaMailSender mailSender;  // 去掉 static 关键字

    public void sendVerificationCode(String sendTo, String code) {  // 去掉 static 关键字
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3302821496@qq.com");
        message.setTo(sendTo);
        message.setSubject("你的验证码");
        message.setText("你的验证码是：" + code);
        mailSender.send(message);
    }

    public String generateVerificationCode() {  // 去掉 static 关键字
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }
}







































































