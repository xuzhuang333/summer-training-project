package com.example.back.Controller;

import com.example.back.beans.Feedback;
import com.example.back.beans.JsonResult;
import com.example.back.beans.NoticeForPost;
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
@Api(tags = "开发者接口（获取反馈）")
public class DeveloperAcquisition {
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/postfeedback")
    @ApiOperation(value = "用户发送反馈")
    public JsonResult<String> postnotice(@RequestBody Feedback feedback){
        JsonResult<String> result = new JsonResult<String>();
        try {
            jdbc.update("INSERT INTO feedback(content)VALUES(?)",feedback.getContent());
            result.setResult("反馈提交成功");
            result.setCode(200);
            log.info("反馈已提交");

        } catch (DataAccessException e) {
            e.printStackTrace();
            result.setResult("反馈提交未成功");
            result.setCode(201);
            log.error("反馈未提交");

        }
        return result;

    }

}
