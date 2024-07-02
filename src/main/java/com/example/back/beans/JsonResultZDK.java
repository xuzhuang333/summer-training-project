package com.example.back.beans;

public class JsonResultZDK {
    private String code;
    private Object result;
    private String msg;

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

    @Override
    public String toString() {
        return "JsonResult{" +
                "code='" + code + '\'' +
                ", result=" + result +
                ", msg='" + msg + '\'' +
                '}';
    }
}
