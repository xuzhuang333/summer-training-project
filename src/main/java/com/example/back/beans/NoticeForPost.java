package com.example.back.beans;

public class NoticeForPost {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoticeForPost() {
    }

    @Override
    public String toString() {
        return "NoticeForPost{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
