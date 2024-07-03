package com.example.back.Controller;

import com.example.back.Entity.Vacation;
import com.example.back.beans.JsonResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = "假期相关接口")
public class VacationRelated {
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/requestVacation")
    @ApiOperation(value = "请假")
    public JsonResult<String> RequestVacation(@RequestBody Vacation vacation){
        JsonResult<String> result = new JsonResult<>();
        Vacation vacation1;//存时段
        Integer jud;//判断是否可以请假,0为可以请假，1为不能请假，请假时间有冲突
        try {
            jud = jdbc.queryForObject("SELECT COUNT(*) FROM vacation WHERE state = 0 AND student_id = ? AND ( (begin_data <= ? AND end_data >= ?) OR (begin_data <= ?AND end_data >= ?) OR (? <= begin_data AND ? >= end_data) )",Integer.class,vacation.getStudent_id(),vacation.getBegin_data(),vacation.getBegin_data(),vacation.getEnd_data(),vacation.getEnd_data(),vacation.getBegin_data(),vacation.getEnd_data());
        } catch (DataAccessException e) {
            result.setCode(201);
            result.setResult("未知问题，请联系管理员");
            e.printStackTrace();
            log.error("找不到判断值");
            return result;
        }
        if (jud != null) {
            if (jud == 0 ){
                try {
                    jdbc.update("INSERT INTO vacation (reason,  state, student_id, type, parent, parent_phone, begin_data, end_data, college,student_name) VALUES (?,  ?, ?, ?, ?, ?, ?, ?, ?,?)",vacation.getReason(),vacation.getState(),vacation.getStudent_id(),vacation.getType(),vacation.getParent(),vacation.getParent_phone(),vacation.getBegin_data(),vacation.getEnd_data(),vacation.getCollege(),vacation.getStudent_name());
                    result.setResult("请假成功，请等到辅导员审批");
                    result.setCode(200);
                    log.info("请假成功：{}",vacation);
                    return result;
                } catch (DataAccessException e) {
                    result.setResult("请假失败，请联系管理员");
                    result.setCode(202);
                    e.printStackTrace();
                    log.error("插入请假信息错误");
                    return result;
                }
            }
            else{
                result.setResult("请假失败，请假时间冲突，请重新选择请假时间");
                result.setCode(203);
                log.info("插入信息冲突，无法请假");
                return result;
            }
        } else {
            result.setResult("请假失败，请联系管理员");
            result.setCode(202);
            log.error("jud判断变量为空指针");
            return result;
        }


    }

}
