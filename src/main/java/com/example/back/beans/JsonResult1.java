package com.example.back.beans;

public class JsonResult1 {
    private String code;
    private Object result;
    private String msg;
    private Integer totalnum;
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