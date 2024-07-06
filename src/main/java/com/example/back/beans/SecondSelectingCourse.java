package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "第二个选课页面需要的数据")
public class SecondSelectingCourse {
    @ApiModelProperty(value = "课程名")
    private String course_name;
    @ApiModelProperty(value = "课程ID")
    private String course_id;
    @ApiModelProperty(value = "科任教师")
    private String course_teacher;
    @ApiModelProperty(value = "上课教室")
    private String course_room;
    @ApiModelProperty(value = "最大容量")
    private int max_capacity;
    @ApiModelProperty(value = "最新容量")
    private int now_capacity;
    @ApiModelProperty(value = "课程状态。0:可以选，1：选满，2：选过了，3：已选了")
    private int state;//0:可以选，1：选满，2：选过了，3：已选了

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_teacher() {
        return course_teacher;
    }

    public void setCourse_teacher(String course_teacher) {
        this.course_teacher = course_teacher;
    }

    public String getCourse_room() {
        return course_room;
    }

    public void setCourse_room(String course_room) {
        this.course_room = course_room;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public int getNow_capacity() {
        return now_capacity;
    }

    public void setNow_capacity(int now_capacity) {
        this.now_capacity = now_capacity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public SecondSelectingCourse() {
    }

    @Override
    public String toString() {
        return "SecondSelectingCourse{" +
                "course_name='" + course_name + '\'' +
                ", course_id='" + course_id + '\'' +
                ", course_teacher='" + course_teacher + '\'' +
                ", course_room='" + course_room + '\'' +
                ", max_capacity=" + max_capacity +
                ", now_capacity=" + now_capacity +
                ", state=" + state +
                '}';
    }
}
