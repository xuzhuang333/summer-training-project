package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用来构建第二页选择的课程的类")
public class StateForCourse {
    @ApiModelProperty(value = "课程ID")
    private Integer course_id;
    @ApiModelProperty(value = "最大容量")
    private Integer max_capacity;
    @ApiModelProperty(value = "最新容量")
    private Integer min_capacity;
    @ApiModelProperty(value = "课程状态。0:可以选，1：选满，2：选过了，3：已选了")
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
