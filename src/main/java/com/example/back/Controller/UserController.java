package com.example.back.Controller;


import com.example.back.Entity.Yonghu;
import com.example.back.beans.HeadForPost;
import com.example.back.beans.IdForPost;
import com.example.back.beans.JsonResult;
import com.example.back.beans.JsonResultZDK;
import io.swagger.annotations.ApiOperation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
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
                jdbc.update("update yonghu set password = ? where id=?"
                        ,yonghu.getPassword(),yonghu.getId());
                res.setCode(200);
                res.setResult("修改密码成功");
                return res;
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

    @PostMapping("/resetpassword")
    @ApiOperation(value = "忘记密码：重置用户密码")
    public JsonResult<String> ResetPassword(@RequestBody Yonghu yonghu){
        JsonResult<String> res = new JsonResult<String>();
        String code = null;
        String email = null;
        try {
            code = jdbc.queryForObject("SELECT code FROM yonghu WHERE ID = ?",String.class,yonghu.getId());
            email = jdbc.queryForObject("SELECT email FROM yonghu WHERE ID = ?",String.class,yonghu.getId());
        } catch (DataAccessException e) {
            res.setCode(201);
            res.setResult("未知错误");
            e.printStackTrace();
            return res;
        }
        log.info("验证码为：{}",code);
        log.info("传入数据为：{}",yonghu.getCode());
        if ( email!=null &&code!=null){
            if(code.equals(yonghu.getCode()) && yonghu.getPassword() != null && yonghu.getEmail().equals(email)){
                jdbc.update("update yonghu set password=?,code = ? where id=?",yonghu.getPassword(),null,yonghu.getId());
                res.setCode(200);
                res.setResult("重置密码成功");
            }
            else{
                res.setCode(201);
                res.setResult("重置密码失败");
            }

            return res;
        }
        else {
            res.setCode(201);
            res.setResult("重置密码失败");
            return res;
        }
    }

    @PostMapping("/head")
    @ApiOperation(value = "头像上传")
    public JsonResult<Yonghu> head(@RequestBody HeadForPost headForPost){
        JsonResult<Yonghu> result = new JsonResult<>();
        Yonghu yonghu = null;
        try {
            jdbc.update("update yonghu set url = ? where id = ?",headForPost.getUrl(),headForPost.getId());
            yonghu = jdbc.queryForObject("select * from yonghu where id=?",new BeanPropertyRowMapper<>(Yonghu.class),headForPost.getId());
            result.setCode(200);
            result.setResult(yonghu);
        } catch (DataAccessException e) {
            result.setCode(201);
            result.setResult(yonghu);
            e.printStackTrace();
        }
        return result;
    }

}
