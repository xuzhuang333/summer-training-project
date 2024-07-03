package com.example.back.beans;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "第二个选课页面需要的数据")
public class SecondSelectingCourse {
    private String course_name;
    private String course_id;
    private String course_teacher;
    private String course_room;
    private int max_capacity;
    private int now_capacity;
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
