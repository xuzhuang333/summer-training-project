package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "给前端返回数据时用的类，包含状态码code和数据result")
public class JsonResult<T> {
    @ApiModelProperty(value = "code状态码，登录时：200为成功，201为未激活，202为密码错误。" +
            "   查宿舍时：200为成功，201为寝室只有一个人，202为ID不存在或ID不是学生ID," +
            "   查志愿时长时：200为成功，202为用户不存在，消息发布时：200为成功，201为发布失败。" +
            "   查我的班级时：200为成功，201为查询班级人数错误，202为用户不存在，203为询辅导员姓名错误" +
            "   发送反馈时：200为成功，201错误。")
    private int code;
    @ApiModelProperty(value = "result为结果信息")
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public JsonResult() {
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }

}
