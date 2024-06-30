package com.soft.back.beans;


public class Course {

  private long courseId;
  private String courseName;
  private String courseRoom;
  private String courseTeacher;
  private String week;
  private long time;
  private String courseNum;
  private long maxCapacity;
  private long nowCapacity;


  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public String getCourseRoom() {
    return courseRoom;
  }

  public void setCourseRoom(String courseRoom) {
    this.courseRoom = courseRoom;
  }


  public String getCourseTeacher() {
    return courseTeacher;
  }

  public void setCourseTeacher(String courseTeacher) {
    this.courseTeacher = courseTeacher;
  }


  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }


  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }


  public String getCourseNum() {
    return courseNum;
  }

  public void setCourseNum(String courseNum) {
    this.courseNum = courseNum;
  }


  public long getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(long maxCapacity) {
    this.maxCapacity = maxCapacity;
  }


  public long getNowCapacity() {
    return nowCapacity;
  }

  public void setNowCapacity(long nowCapacity) {
    this.nowCapacity = nowCapacity;
  }

  @Override
  public String toString() {
    return "Course{" +
            "courseId=" + courseId +
            ", courseName='" + courseName + '\'' +
            ", courseRoom='" + courseRoom + '\'' +
            ", courseTeacher='" + courseTeacher + '\'' +
            ", week='" + week + '\'' +
            ", time=" + time +
            ", courseNum='" + courseNum + '\'' +
            ", maxCapacity=" + maxCapacity +
            ", nowCapacity=" + nowCapacity +
            '}';
  }
}
