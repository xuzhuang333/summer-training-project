package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "大屏报道数据对象类")
public class Baodaodaping {
    @ApiModelProperty(value = "已报道或未报到的人数")
    public int value;//value是已报道或未报到的人数
    @ApiModelProperty(value = "”已报道”或”未报到”")
    public String name;//name=”已报道”或”未报到”

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
