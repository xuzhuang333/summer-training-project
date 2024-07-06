package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "给前端返回寝室审批数据时用的类，包含状态码code和数据result，提示信息msg，数据等")
public class JsonResult1 {
    @ApiModelProperty(value =  "状态码")
    private String code;
    @ApiModelProperty(value =  "结果")
    private Object result;
    @ApiModelProperty(value =  "提示信息")
    private String msg;
    @ApiModelProperty(value =  "总审批数")
    private Integer totalnum;
    @ApiModelProperty(value =  "未审批数")
    private Integer livenum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getLivenum() {
        return livenum;
    }

    public void setLivenum(Integer livenum) {
        this.livenum = livenum;
    }

    @Override
    public String toString() {
        return "JsonResult1{" +
                "code='" + code + '\'' +
                ", result=" + result +
                ", msg='" + msg + '\'' +
                ", totalnum=" + totalnum +
                ", livenum=" + livenum +
                '}';
    }
}