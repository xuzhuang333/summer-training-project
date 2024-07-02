package com.example.back.beans;

public class Agreedata {
    private Integer submit;
    private Integer id;

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "agreedata{" +
                "submit=" + submit +
                ", id=" + id +
                '}';
    }
}
