package com.project.beans;


public class Roomagree {

  private String phone;
  private String reason;
  private String studentname;
  private String college;
  private String studentid;
  private String state;
  private String dormitory_number;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getStudentname() {
    return studentname;
  }

  public void setStudentname(String studentname) {
    this.studentname = studentname;
  }

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public String getStudentid() {
    return studentid;
  }

  public void setStudentid(String studentid) {
    this.studentid = studentid;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getDormitory_number() {
    return dormitory_number;
  }

  public void setDormitory_number(String dormitory_number) {
    this.dormitory_number = dormitory_number;
  }

  @Override
  public String toString() {
    return "Roomagree{" +
            "phone='" + phone + '\'' +
            ", reason='" + reason + '\'' +
            ", studentname='" + studentname + '\'' +
            ", college='" + college + '\'' +
            ", studentid='" + studentid + '\'' +
            ", state='" + state + '\'' +
            ", dormitory_number='" + dormitory_number + '\'' +
            '}';
  }
}
