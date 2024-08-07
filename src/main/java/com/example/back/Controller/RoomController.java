package com.example.back.Controller;


import com.example.back.Entity.Yonghu;
import com.example.back.beans.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j


@Api(tags = "住宿申请与审批的接口")
public class RoomController {

    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/roomshow")
    @ApiOperation(value = "可选寝室展示")
    public JsonResultZDK roomshow(@RequestBody Yonghu yonghu){
        JsonResultZDK res = new JsonResultZDK();
        //List<String> roommsg = null;
        try {
            List<String> roommsg= jdbc.query("select dormitory_number from yonghu where gender = ? and college = ? and dormitory_number is not null group by dormitory_number having count(*) < 4"
                    ,(rs, rowNum) -> rs.getString("dormitory_number"),yonghu.getGender(),yonghu.getCollege());
            res.setCode("200");
            res.setMsg("连接成功");
            res.setResult(roommsg);
            System.out.println(roommsg);
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("连接失败");
            return res;
        }
    }

    @PostMapping("/roomapply")
    @ApiOperation(value = "申请住宿")
    public JsonResultZDK roomapply(@RequestBody Roomagree roomagree) {
        JsonResultZDK res = new JsonResultZDK();
        try {
            if ("1".equals(roomagree.getState())) {
                jdbc.update("update yonghu set dormitory_number=?,dormitory_state = 1 where id = ?", roomagree.getDormitory_number(),roomagree.getStudentid());
                res.setCode("200");
                res.setMsg("申请成功");
                return res;
            } else {
                jdbc.update("insert into roomagree values (?,?,?,?,?)"
                        , roomagree.getPhone(), roomagree.getReason(), roomagree.getStudentname(), roomagree.getCollege(), roomagree.getStudentid());
                res.setCode("200");
                res.setMsg("申请成功");
                return res;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("申请失败");
            return res;
        }
    }

    @PostMapping("/roomagreeshow")
    @ApiOperation(value = "走读申请展示")
    public JsonResult1 roomagreeshow(@RequestBody Roomagree roomagree){
        JsonResult1 res = new JsonResult1();
        List<Roomagree> rmg = null;
        try {
            rmg = jdbc.query("select * from roomagree where college = ?",new BeanPropertyRowMapper<>(Roomagree.class),roomagree.getCollege());
            res.setCode("200");
            res.setMsg("连接成功");
            res.setResult(rmg);
            Integer num0 = jdbc.queryForObject("select count(*) as totalnum from yonghu where college = ?",Integer.class,roomagree.getCollege());
            res.setTotalnum(num0);
            Integer num1 = jdbc.queryForObject("select count(*) as totalnum from yonghu where college = ? and dormitory_state = 1",Integer.class,roomagree.getCollege());
            res.setLivenum(num1);
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("连接失败");
            return res;
        }
    }

    @PostMapping("/roomagreee")
    @ApiOperation(value = "审批走读申请")
    public JsonResultZDK roomagreee(@RequestBody Agreedata agreedata){
        JsonResultZDK res = new JsonResultZDK();
        try {
            if(agreedata.getSubmit() == 1){
                jdbc.update("update yonghu set dormitory_state = 0 where id = ?",agreedata.getId());
                jdbc.update("update yonghu set dormitory_number = NULL where id = ?",agreedata.getId());
                jdbc.update("delete from roomagree where studentid = ?",agreedata.getId());
                res.setCode("200");
                res.setMsg("审批成功");
                return res;
            }else {
                jdbc.update("update yonghu set dormitory_state = 1 where id = ?",agreedata.getId());
                jdbc.update("delete from roomagree where studentid = ?",agreedata.getId());
                res.setCode("200");
                res.setMsg("拒批成功");
                return res;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("连接失败");
            return res;
        }
    }

}
