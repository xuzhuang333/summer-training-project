package com.project.controller;


import com.project.beans.*;
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
public class RoomController {

    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/roomshow")
    public JsonResult roomshow(@RequestBody Yonghu yonghu){
        JsonResult res = new JsonResult();
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
    public JsonResult roomapply(@RequestBody Roomagree roomagree) {
        JsonResult res = new JsonResult();
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
    public JsonResult roomagreee(@RequestBody Agreedata agreedata){
        JsonResult res = new JsonResult();
        try {
            if(agreedata.getSubmit() == 1){
                jdbc.update("update yonghu set dormitory_state = 0 where id = ?",agreedata.getId());
                jdbc.update("delete from yonghu where id = ?",agreedata.getId());
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
