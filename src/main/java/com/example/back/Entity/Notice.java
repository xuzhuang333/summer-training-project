package com.example.back.Entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "前端需要展示公告内容的对象类")
public class Notice {
  @ApiModelProperty(value = "公告ID")
  private long noticeId;
  @ApiModelProperty(value = "公告标题")
  private String noticeTitle;
  @ApiModelProperty(value = "公告时间")
  private String time;
  @ApiModelProperty(value = "公告类型")
  private String type;
  @ApiModelProperty(value = "公告内容")
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getNoticeId() {
    return noticeId;
  }

  public void setNoticeId(long noticeId) {
    this.noticeId = noticeId;
  }


  public String getNoticeTitle() {
    return noticeTitle;
  }

  public void setNoticeTitle(String noticeTitle) {
    this.noticeTitle = noticeTitle;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Notice{" +
            "noticeId=" + noticeId +
            ", noticeTitle='" + noticeTitle + '\'' +
            ", time='" + time + '\'' +
            ", type='" + type + '\'' +
            ", content='" + content + '\'' +
            '}';
  }
}
