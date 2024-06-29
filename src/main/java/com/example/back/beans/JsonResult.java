package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "判断是否成功的类")
public class JsonResult<T> {
    @ApiModelProperty(value = "code状态码，200为成功，100为失败")
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
