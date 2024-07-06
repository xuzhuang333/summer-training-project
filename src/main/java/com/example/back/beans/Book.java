package com.example.back.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "后端传送的图书信息对象类")
public class Book {
  @ApiModelProperty(value = "图书ID")
  private long id;
  @ApiModelProperty(value = "图书名字")
  private String name;
  @ApiModelProperty(value = "作者")
  private String author;
  @ApiModelProperty(value = "校区")
  private String area;
  @ApiModelProperty(value = "图书位置")
  private String location;
  @ApiModelProperty(value = "借出状态。0：未借出；1：已借出")
  private long borrowed;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


  public long getBorrowed() {
    return borrowed;
  }

  public void setBorrowed(long borrowed) {
    this.borrowed = borrowed;
  }

  @Override
  public String toString() {
    return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", author='" + author + '\'' +
            ", area='" + area + '\'' +
            ", location='" + location + '\'' +
            ", borrowed=" + borrowed +
            '}';
  }
}
