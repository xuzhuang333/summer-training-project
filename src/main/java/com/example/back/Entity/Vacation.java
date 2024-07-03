package com.example.back.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "假条的实体类")
public class Vacation {
    private Integer vacation_id;
    private Integer student_id;
    private String reason;
    private String type;
    private String parent;
    private String parent_phone;
    private String begin_data;
    private String end_data;
    private String college;
    private String student_name;
    private Integer notification;
    @ApiModelProperty(value = "state 0：请假未审批，1：审批通过未销假，2：已销假")
    private Integer state;

    public Integer getNotification() {
        return notification;
    }

    public void setNotification(Integer notification) {
        this.notification = notification;
    }

    public Integer getVacation_id() {
        return vacation_id;
    }

    public void setVacation_id(Integer vacation_id) {
        this.vacation_id = vacation_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParent_phone() {
        return parent_phone;
    }

    public void setParent_phone(String parent_phone) {
        this.parent_phone = parent_phone;
    }

    public String getBegin_data() {
        return begin_data;
    }

    public void setBegin_data(String begin_data) {
        this.begin_data = begin_data;
    }

    public String getEnd_data() {
        return end_data;
    }

    public void setEnd_data(String end_data) {
        this.end_data = end_data;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Vacation() {
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "vacation_id=" + vacation_id +
                ", student_id=" + student_id +
                ", reason='" + reason + '\'' +
                ", type='" + type + '\'' +
                ", parent='" + parent + '\'' +
                ", parent_phone='" + parent_phone + '\'' +
                ", begin_data='" + begin_data + '\'' +
                ", end_data='" + end_data + '\'' +
                ", college='" + college + '\'' +
                ", student_name='" + student_name + '\'' +
                ", notification=" + notification +
                ", state=" + state +
                '}';
    }
}
