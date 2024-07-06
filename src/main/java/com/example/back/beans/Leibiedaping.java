package com.example.back.beans;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "大屏需要的政治面貌的类")
public class Leibiedaping {
    @ApiModelProperty(value = "团员或群众的人数")
    public int value;//value是团员或群众的人数
    @ApiModelProperty(value = "团员或群众")
    public String name;//name=”团员”或”群众”

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
