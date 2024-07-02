package com.example.back.Controller;


import com.example.back.Entity.Yonghu;
import com.example.back.beans.JsonResultZDK;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "修改用户")
    public JsonResultZDK update(@RequestBody Yonghu yonghu){
        JsonResultZDK res = new JsonResultZDK();
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
