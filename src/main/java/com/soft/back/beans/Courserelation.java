package com.soft.back.beans;


public class Courserelation {

  private long courseId;
  private String courseName;
  private String teacherName;
  private long studentId;


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


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  @Override
  public String toString() {
    return "Courserelation{" +
            "courseId=" + courseId +
            ", courseName='" + courseName + '\'' +
            ", teacherName=" + teacherName +
            ", studentId=" + studentId +
            '}';
  }
}
