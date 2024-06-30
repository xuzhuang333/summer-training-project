package com.example.back.beans;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "接受前端数据的类（发送反馈用）")
public class Feedback {
  @ApiModelProperty(value = "反馈内容")
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Feedback() {
  }
  @Override
  public String toString() {
    return "Feedback{" +
            "content='" + content + '\'' +
            '}';
  }
}
