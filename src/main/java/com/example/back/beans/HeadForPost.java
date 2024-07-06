package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "前端上传头像发送的对象类")
public class HeadForPost {
    @ApiModelProperty(value = "学生ID")
    private Integer id;
    @ApiModelProperty(value = "学生url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HeadForPost() {
    }

    @Override
    public String toString() {
        return "HeadForPost{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
