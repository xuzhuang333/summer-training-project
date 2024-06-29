package com.soft.back.beans;

//统一的数据结构
public class JsonResult<T> {
    private int code;
    private T result;

    public JsonResult() {
    }

    public JsonResult(T result) {
        this.code=200;
        this.result = result;
    }

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
}
