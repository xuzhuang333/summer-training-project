package com.example.back.Controller;

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
@Api(tags = "只由老师控制的功能接⼝")
public class TeacherControl {
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/postNotice")
    @ApiOperation(value = "信息发布")
    public JsonResult<String> postnotice(@RequestBody NoticeForPost noticeForPost){
        JsonResult<String> result = new JsonResult<String>();
        try {
            jdbc.update("INSERT INTO notice(notice_title,content)VALUES(?,?)",noticeForPost.getTitle(),noticeForPost.getContent());
            result.setResult("消息发布成功");
            result.setCode(200);
            log.info("消息已发布");

        } catch (DataAccessException e) {
            e.printStackTrace();
            result.setResult("消息未发布成功");
            result.setCode(201);
            log.error("消息未发布");

        }
        return result;

    }

}
