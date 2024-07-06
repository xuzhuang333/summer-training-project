package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "课表需要数据的对象类")
public class CourseTable {
    @ApiModelProperty(value = "课程名")
    private String course_name;
    @ApiModelProperty(value = "上课教室")
    private String course_room;
    @ApiModelProperty(value = "课程开始日期")
    private String startDate;
    @ApiModelProperty(value = "星期号")
    private String week;
    @ApiModelProperty(value = "一天第几节")
    private int time;
    @ApiModelProperty(value = "持续周次")
    private int duration;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_room() {
        return course_room;
    }

    public void setCourse_room(String course_room) {
        this.course_room = course_room;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CourseTable{" +
                "course_name='" + course_name + '\'' +
                ", course_room='" + course_room + '\'' +
                ", time=" + time +
                ", startDate='" + startDate + '\'' +
                ", week='" + week + '\'' +
                ", duration=" + duration +
                '}';
    }
}
