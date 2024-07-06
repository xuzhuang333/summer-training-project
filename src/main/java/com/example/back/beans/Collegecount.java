package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "大屏学院名称和人数的对象类")
public class Collegecount {
    @ApiModelProperty(value = "学院名称")
    private String name;
    @ApiModelProperty(value = "学院人数")
    private Integer num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Collegecount{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
