package com.project.controller;


import com.project.beans.Agreedata;
import com.project.beans.JsonResult;
import com.project.beans.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class VacationController {

    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/agreehome")
    public JsonResult agreehome(@RequestBody Vacation vacations){
        JsonResult res = new JsonResult();
        List<Vacation> vacation =null;
        try {
            vacation=jdbc.query("select * from vacation where state = 0 and college =?",new BeanPropertyRowMapper<>(Vacation.class),vacations.getCollege());
            res.setCode("200");
            res.setMsg("连接成功");
            res.setResult(vacation);
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("连接失败");
            return res;
        }
    }

    @PostMapping("/agree")
    public JsonResult agree(@RequestBody Agreedata agreedata){
        JsonResult res = new JsonResult();
        try {
            if(agreedata.getSubmit()==1){
                jdbc.update("update vacation set state = 1 where vacation_id=?",agreedata.getId());
                res.setCode("200");
                res.setMsg("批假成功");
                return res;
            }else {
                jdbc.update("update vacation set state = 3 where vacation_id=?",agreedata.getId());
                res.setCode("200");
                res.setMsg("拒绝批假成功");
                return res;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("执行批假失败");
            return res;
        }
    }
}
