package com.example.back.beans;

public class Collegecount {
    private String name;
    private Integer num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Collegecount{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
