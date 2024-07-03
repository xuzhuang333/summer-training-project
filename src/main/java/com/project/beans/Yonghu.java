package com.project.beans;


public class Yonghu {

  private long id;
  private String name;
  private long age;
  private String gender;
  private String phone;
  private String email;
  private String college;
  private String major_class;
  private String dormitory_number;
  private String password;
  private long leibie;
  private Integer activate;
  private Integer volunteer;
  private Integer max_credit;
  private Integer dormitory_state;
  private String home;

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

  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public String getMajor_class() {
    return major_class;
  }

  public void setMajor_class(String major_class) {
    this.major_class = major_class;
  }

  public String getDormitory_number() {
    return dormitory_number;
  }

  public void setDormitory_number(String dormitory_number) {
    this.dormitory_number = dormitory_number;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public long getLeibie() {
    return leibie;
  }

  public void setLeibie(long leibie) {
    this.leibie = leibie;
  }

  public Integer getActivate() {
    return activate;
  }

  public void setActivate(Integer activate) {
    this.activate = activate;
  }

  public Integer getVolunteer() {
    return volunteer;
  }

  public void setVolunteer(Integer volunteer) {
    this.volunteer = volunteer;
  }

  public Integer getMax_credit() {
    return max_credit;
  }

  public void setMax_credit(Integer max_credit) {
    this.max_credit = max_credit;
  }

  public Integer getDormitory_state() {
    return dormitory_state;
  }

  public void setDormitory_state(Integer dormitory_state) {
    this.dormitory_state = dormitory_state;
  }

  public String getHome() {
    return home;
  }

  public void setHome(String home) {
    this.home = home;
  }

  @Override
  public String toString() {
    return "Yonghu{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", gender='" + gender + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", college='" + college + '\'' +
            ", major_class='" + major_class + '\'' +
            ", dormitory_number='" + dormitory_number + '\'' +
            ", password='" + password + '\'' +
            ", leibie=" + leibie +
            ", activate=" + activate +
            ", volunteer=" + volunteer +
            ", max_credit=" + max_credit +
            ", dormitory_state=" + dormitory_state +
            ", home='" + home + '\'' +
            '}';
  }
}
