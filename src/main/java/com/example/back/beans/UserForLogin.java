package com.example.back.beans;

//定义用来常规登录使用的用户数据，常规登录仅仅需要学工号（student_id）和密码（password）
public class UserForLogin {
    private int student_id;
    private String password;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserForLogin{" +
                "student_id=" + student_id +
                ", password='" + password + '\'' +
                '}';
    }
}
