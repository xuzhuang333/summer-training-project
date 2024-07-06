package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "前端发送的邮箱信息")
public class EmailForPost {
    @ApiModelProperty(value = "邮箱号")
    private String email;
    @ApiModelProperty(value = "学生ID")
    private Integer student_id;

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailForPost{" +
                "email='" + email + '\'' +
                ", student_id=" + student_id +
                '}';
    }

    public EmailForPost() {
    }
}
