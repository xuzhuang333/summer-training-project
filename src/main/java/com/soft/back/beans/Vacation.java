package com.soft.back.beans;


public class Vacation {

  private String reason;
  private long vacation_id;
  private long state;
  private long student_id;
  private String type;
  private String parent;
  private String parent_phone;
  private java.sql.Date begin_data;
  private java.sql.Date end_data;
  private String college;


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }


  public long getVacationId() {
    return vacation_id;
  }

  public void setVacationId(long vacation_id) {
    this.vacation_id = vacation_id;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public long getStudentId() {
    return student_id;
  }

  public void setStudentId(long student_id) {
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


  public String getParentPhone() {
    return parent_phone;
  }

  public void setParentPhone(String parent_phone) {
    this.parent_phone = parent_phone;
  }


  public java.sql.Date getBeginData() {
    return begin_data;
  }

  public void setBeginData(java.sql.Date begin_data) {
    this.begin_data = begin_data;
  }


  public java.sql.Date getEndData() {
    return end_data;
  }

  public void setEndData(java.sql.Date end_data) {
    this.end_data = end_data;
  }


  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  @Override
  public String toString() {
    return "Vacation{" +
            "reason='" + reason + '\'' +
            ", vacation_id=" + vacation_id +
            ", state=" + state +
            ", student_id=" + student_id +
            ", type='" + type + '\'' +
            ", parent='" + parent + '\'' +
            ", parent_phone='" + parent_phone + '\'' +
            ", begin_data=" + begin_data +
            ", end_data=" + end_data +
            ", college='" + college + '\'' +
            '}';
  }
}
