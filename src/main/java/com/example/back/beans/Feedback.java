package com.example.back.beans;


public class Feedback {

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
