package com.example.back.Entity;


public class Notice {

  private long noticeId;
  private String noticeTitle;
  private String time;
  private String type;
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
