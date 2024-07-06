package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "大屏家乡人数需要的对象类")
public class HomeData {
    @ApiModelProperty(value = "城市名数组")
    private String[] names;
    @ApiModelProperty(value = "城市人数数组")
    private Integer[] values;

    // Getter 和 Setter 方法
    public String[] getNames() { return names; }
    public void setNames(String[] names) { this.names = names; }
    public Integer[] getValues() { return values; }
    public void setValues(Integer[] values) { this.values = values; }
}