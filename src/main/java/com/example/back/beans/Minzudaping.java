package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "大屏需要的民族数据对象类")
public class Minzudaping {
    @ApiModelProperty(value = "该民族人数")
    public int value;// 人数
    @ApiModelProperty(value = "民族")
    public String name;//民族

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
