package com.example.back.beans;

public class Baodaodaping {

    public int value;//value是已报道或未报到的人数
    public String name;//name=”已报道”或”未报到”

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
