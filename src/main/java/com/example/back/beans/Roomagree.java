package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "接受前端传过来的寝室申请信息")
public class Roomagree {
    @ApiModelProperty(value = "学生电话")
    private String phone;
    @ApiModelProperty(value = "申请原因")
    private String reason;
    @ApiModelProperty(value = "学生名字")
    private String studentname;
    @ApiModelProperty(value = "学院")
    private String college;
    @ApiModelProperty(value = "学生ID")
    private String studentid;
    @ApiModelProperty(value = "住宿状态")
    private String state;
    @ApiModelProperty(value = "寝室号")
    private String dormitory_number;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDormitory_number() {
        return dormitory_number;
    }

    public void setDormitory_number(String dormitory_number) {
        this.dormitory_number = dormitory_number;
    }

    @Override
    public String toString() {
        return "Roomagree{" +
                "phone='" + phone + '\'' +
                ", reason='" + reason + '\'' +
                ", studentname='" + studentname + '\'' +
                ", college='" + college + '\'' +
                ", studentid='" + studentid + '\'' +
                ", state='" + state + '\'' +
                ", dormitory_number='" + dormitory_number + '\'' +
                '}';
    }
}
