package com.example.back.beans;

public class EmailForPost {
    private String email;
    private Integer student_id;

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailForPost{" +
                "email='" + email + '\'' +
                ", student_id=" + student_id +
                '}';
    }

    public EmailForPost() {
    }
}
