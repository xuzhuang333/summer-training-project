package com.soft.back.beans;

public class Classes {
    private String majorClass;
    private int number;

    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "majorClass='" + majorClass + '\'' +
                ", number=" + number +
                '}';
    }
}
