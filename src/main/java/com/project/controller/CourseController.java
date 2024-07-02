package com.project.controller;


import com.project.beans.Course;
import com.project.beans.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CourseController {

    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/coursemsg")
    public JsonResult coursemsg(@RequestBody Course course){
        JsonResult res = new JsonResult();
        List<Course> message = null;
        try {
            message = jdbc.query("select * from course where course_name=?"
                    ,new BeanPropertyRowMapper<>(Course.class),course.getCourse_name());
            res.setCode("200");
            res.setMsg("成功");
            res.setResult(message);
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("失败");
            return res;
        }
    }

}
