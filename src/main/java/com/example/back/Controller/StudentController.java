package com.example.back.Controller;

import com.example.back.Entity.Student;
import com.example.back.beans.UserForLogin;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@Slf4j
public class StudentController {
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/doLogin")
    @ResponseBody
    public Student login(@RequestBody UserForLogin userForLogin){
        Student student = new Student();
        try {
            student = jdbc.queryForObject("select * from student where student_id=? and password=?",new BeanPropertyRowMapper<>(Student.class),userForLogin.getStudent_id(),userForLogin.getPassword());
            log.info("登录成功:{}",student);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("没有收到的数据");
        }
        return student;
    }

}
