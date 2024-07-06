package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel (value = "大屏需要的学院数据")
public class CollegeData {
    @ApiModelProperty(value = "学院名称数组")
    private String[] names;
    @ApiModelProperty(value = "学院人数数组")
    private Integer[] nums;

    // Getter 和 Setter 方法
    public String[] getNames() { return names; }
    public void setNames(String[] names) { this.names = names; }
    public Integer[] getNums() { return nums; }
    public void setNums(Integer[] nums) { this.nums = nums; }
}
