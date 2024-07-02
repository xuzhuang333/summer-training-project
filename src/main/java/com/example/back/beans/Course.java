package com.example.back.beans;


public class Course {

  private long course_id;
  private String course_name;
  private String course_room;
  private String course_teacher;
  private String week;
  private long time;
  private long max_capacity;
  private long now_people;
  private String course_num;

  public long getCourse_id() {
    return course_id;
  }

  public void setCourse_id(long course_id) {
    this.course_id = course_id;
  }

  public String getCourse_name() {
    return course_name;
  }

  public void setCourse_name(String course_name) {
    this.course_name = course_name;
  }

  public String getCourse_room() {
    return course_room;
  }

  public void setCourse_room(String course_room) {
    this.course_room = course_room;
  }

  public String getCourse_teacher() {
    return course_teacher;
  }

  public void setCourse_teacher(String course_teacher) {
    this.course_teacher = course_teacher;
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

  public long getMax_capacity() {
    return max_capacity;
  }

  public void setMax_capacity(long max_capacity) {
    this.max_capacity = max_capacity;
  }

  public long getNow_people() {
    return now_people;
  }

  public void setNow_people(long now_people) {
    this.now_people = now_people;
  }

  public String getCourse_num() {
    return course_num;
  }

  public void setCourse_num(String course_num) {
    this.course_num = course_num;
  }

  @Override
  public String toString() {
    return "Course{" +
            "course_id=" + course_id +
            ", course_name='" + course_name + '\'' +
            ", course_room='" + course_room + '\'' +
            ", course_teacher='" + course_teacher + '\'' +
            ", week='" + week + '\'' +
            ", time=" + time +
            ", max_capacity=" + max_capacity +
            ", now_people=" + now_people +
            ", course_num='" + course_num + '\'' +
            '}';
  }
}
