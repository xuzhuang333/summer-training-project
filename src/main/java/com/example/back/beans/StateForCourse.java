package com.example.back.beans;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "用来构建第二页选择的课程的类")
public class StateForCourse {
    private Integer course_id;
    private Integer max_capacity;
    private Integer min_capacity;
    private Integer state;

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(Integer max_capacity) {
        this.max_capacity = max_capacity;
    }

    public Integer getMin_capacity() {
        return min_capacity;
    }

    public void setMin_capacity(Integer min_capacity) {
        this.min_capacity = min_capacity;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public StateForCourse() {
    }

    @Override
    public String toString() {
        return "StateForCourse{" +
                "course_id=" + course_id +
                ", max_capacity=" + max_capacity +
                ", min_capacity=" + min_capacity +
                ", state=" + state +
                '}';
    }
}
