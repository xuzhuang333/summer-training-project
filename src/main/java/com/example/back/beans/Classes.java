package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel (value = "数据分析用的对象类")
public class Classes {
    @ApiModelProperty(value = "专业班级")
    private String majorClass;
    @ApiModelProperty(value = "人数")
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
