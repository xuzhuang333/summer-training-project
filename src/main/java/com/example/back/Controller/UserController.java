package com.example.back.Controller;


import com.example.back.Entity.Yonghu;
import com.example.back.beans.JsonResult;
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
            jdbc.update("update yonghu set phone=?,email=?,politics=?,nation = ? where id=?"
                    ,yonghu.getPhone(),yonghu.getEmail(),yonghu.getPolitics(),yonghu.getNation(),yonghu.getId());
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

    @PostMapping("/changepassword")
    @ApiOperation(value = "修改用户密码")
    public JsonResult<String> ChangePassword(@RequestBody Yonghu yonghu){
        JsonResult<String> res = new JsonResult<String>();
        Integer ps = 0;

        try {
            ps =   jdbc.queryForObject("SELECT password FROM yonghu WHERE id=?",Integer.class,yonghu.getId());
        } catch (DataAccessException e) {
            res.setCode(202);
            res.setResult("未知错误，修改密码失败");
            e.printStackTrace();
            return res;
        }
        if (yonghu.getOrgin_password().equals(ps)){
            try {
                int a = jdbc.update("update yonghu set password = ? where id=? AND code = ?"
                        ,yonghu.getPassword(),yonghu.getId(),yonghu.getCode());
                if(a == 0){
                    res.setCode(203);
                    res.setResult("验证码错误，修改密码失败");
                    return res;
                }
                else{jdbc.update("update yonghu set code=? where id=?", null,yonghu.getId());
                    res.setCode(200);
                    res.setResult("修改密码成功");
                    return res;}

            } catch (DataAccessException e) {
                res.setCode(202);
                res.setResult("未知错误，修改密码失败");
                e.printStackTrace();
                return res;
            }
        }
        else{
            res.setCode(201);
            res.setResult("修改密码失败，原密码不正确");
            return res;
        }

    }


}
