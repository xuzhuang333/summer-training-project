package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "查课表用的类")
public class IdForPost {
    @ApiModelProperty(value = "接收前端的id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IdForPost() {
    }

    @Override
    public String toString() {
        return "IdForPost{" +
                "id=" + id +
                '}';
    }
}
