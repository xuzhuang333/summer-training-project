package com.example.back.beans;

public class CourseForPost {
    private String course_num;
    private String course_name;
    private int course_id;
    private int student_id;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getCourse_num() {
        return course_num;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "CourseForPost{" +
                "course_num='" + course_num + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_id=" + course_id +
                ", student_id=" + student_id +
                '}';
    }
}
