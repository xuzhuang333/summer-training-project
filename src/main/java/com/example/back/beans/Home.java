package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "大屏地区发布展示需要的类")
public class Home {
    @ApiModelProperty(value = "家乡名")
    private String name;
    @ApiModelProperty(value = "家乡人数")
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Home{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
