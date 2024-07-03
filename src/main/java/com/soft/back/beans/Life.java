package com.soft.back.beans;


public class Life {

  private long id;
  private float money;
  private float electric;
  private float water;
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
