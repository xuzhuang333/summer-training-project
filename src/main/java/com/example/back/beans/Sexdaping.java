package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "大屏所需性别数据对象类")
public class Sexdaping {
    @ApiModelProperty(value = "男生或女生的人数")
    public int value;//value是男生或女生的人数
    @ApiModelProperty(value = "男生或女生")
    public String name;//name=”男生”或”女生”

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
