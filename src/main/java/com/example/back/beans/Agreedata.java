package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "前端传来的审批走读的数据")
public class Agreedata {
    @ApiModelProperty(value = "是否同意，0：同意，1：不同意")
    private Integer submit;
    @ApiModelProperty(value = "学生ID")
    private Integer id;

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "agreedata{" +
                "submit=" + submit +
                ", id=" + id +
                '}';
    }
}
