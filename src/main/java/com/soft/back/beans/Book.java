package com.soft.back.beans;


public class Book {

  private long id;
  private String name;
  private String author;
  private String area;
  private String location;
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
