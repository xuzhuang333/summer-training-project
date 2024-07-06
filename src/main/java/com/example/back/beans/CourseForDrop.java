package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "前端传来的退课数据")
public class CourseForDrop {
    @ApiModelProperty(value = "学生ID")
    private Integer student_id;
    @ApiModelProperty(value = "课程名")
    private String  course_name;
    @ApiModelProperty(value = "课程号")
    private Integer course_id;

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public CourseForDrop() {
    }

    @Override
    public String toString() {
        return "CourseForDrop{" +
                "student_id=" + student_id +
                ", course_name='" + course_name + '\'' +
                ", course_id=" + course_id +
                '}';
    }
}
