package com.example.back.beans;

public class HeadForPost {
    private Integer id;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HeadForPost() {
    }

    @Override
    public String toString() {
        return "HeadForPost{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
