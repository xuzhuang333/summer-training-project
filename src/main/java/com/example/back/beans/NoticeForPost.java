package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "发布消息：接受前端信息的类")
public class NoticeForPost {
    @ApiModelProperty(value = "传入消息标题")
    private String title;
    @ApiModelProperty(value = "传入消息内容")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoticeForPost() {
    }

    @Override
    public String toString() {
        return "NoticeForPost{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
