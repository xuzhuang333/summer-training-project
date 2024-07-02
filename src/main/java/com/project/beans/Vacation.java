package com.project.beans;


import java.sql.Timestamp;

public class Vacation {

  private String reason;
  private long vacation_id;
  private long state;
  private java.sql.Timestamp begin_data;
  private java.sql.Timestamp end_data;
  private long student_id;
  private String type;
  private String parent;
  private String parent_phone;
  private String college;
  private Integer notification;
  private String student_name;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public long getVacation_id() {
    return vacation_id;
  }

  public void setVacation_id(long vacation_id) {
    this.vacation_id = vacation_id;
  }

  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

  public Timestamp getBegin_data() {
    return begin_data;
  }

  public void setBegin_data(Timestamp begin_data) {
    this.begin_data = begin_data;
  }

  public Timestamp getEnd_data() {
    return end_data;
  }

  public void setEnd_data(Timestamp end_data) {
    this.end_data = end_data;
  }

  public long getStudent_id() {
    return student_id;
  }

  public void setStudent_id(long student_id) {
    this.student_id = student_id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getParent() {
    return parent;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }

  public String getParent_phone() {
    return parent_phone;
  }

  public void setParent_phone(String parent_phone) {
    this.parent_phone = parent_phone;
  }

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public Integer getNotification() {
    return notification;
  }

  public void setNotification(Integer notification) {
    this.notification = notification;
  }

  public String getStudent_name() {
    return student_name;
  }

  public void setStudent_name(String student_name) {
    this.student_name = student_name;
  }

  @Override
  public String toString() {
    return "Vacation{" +
            "reason='" + reason + '\'' +
            ", vacation_id=" + vacation_id +
            ", state=" + state +
            ", begin_data=" + begin_data +
            ", end_data=" + end_data +
            ", student_id=" + student_id +
            ", type='" + type + '\'' +
            ", parent='" + parent + '\'' +
            ", parent_phone='" + parent_phone + '\'' +
            ", college='" + college + '\'' +
            ", notification=" + notification +
            ", student_name='" + student_name + '\'' +
            '}';
  }
}
