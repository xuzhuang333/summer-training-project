package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "给前端传的生活数据的类")
public class Life {
    @ApiModelProperty(value = "学生ID")
    private long id;
    @ApiModelProperty(value = "水电费总余额")
    private float money;
    @ApiModelProperty(value = "电费补助")
    private float electric;
    @ApiModelProperty(value = "水费补助")
    private float water;
    @ApiModelProperty(value = "一卡通余额")
    private float livingCost;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }


    public float getElectric() {
        return electric;
    }

    public void setElectric(float electric) {
        this.electric = electric;
    }


    public float getWater() {
        return water;
    }

    public void setWater(float water) {
        this.water = water;
    }


    public float getLivingCost() {
        return livingCost;
    }

    public void setLivingCost(float livingCost) {
        this.livingCost = livingCost;
    }

    @Override
    public String toString() {
        return "Life{" +
                "id=" + id +
                ", money=" + money +
                ", electric=" + electric +
                ", water=" + water +
                ", livingCost=" + livingCost +
                '}';
    }
}