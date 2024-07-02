package com.project.controller;


import com.project.beans.JsonResult;
import com.project.beans.Yonghu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/update")
    public JsonResult update(@RequestBody Yonghu yonghu){
        JsonResult res = new JsonResult();
        try {
            jdbc.update("update yonghu set phone=?,email=?,password=? where id=?"
                    ,yonghu.getPhone(),yonghu.getEmail(),yonghu.getPassword(),yonghu.getId());
            res.setCode("200");
            res.setMsg("修改成功");
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("修改失败");
            return res;
        }

    }
}
