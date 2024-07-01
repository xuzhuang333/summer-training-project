package com.example.back.beans;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "第一个选课页面需要的数据")
public class FirstSelectingCourse {
    private String course_name;
    private String course_num;
    private Integer credit;
    private Integer state;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_num() {
        return course_num;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FirstSelectingCourse{" +
                "course_name='" + course_name + '\'' +
                ", course_num='" + course_num + '\'' +
                ", credit=" + credit +
                ", state=" + state +
                '}';
    }
}
