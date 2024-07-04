package com.example.back.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Time;
import java.time.LocalTime;

//定义用来查询个人信息使用的用户数据，查询需要所有的信息！
@ApiModel(value = "学生用户信息的实体类")
public class Yonghu {
    @ApiModelProperty(value = "学工号")
    private int id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private int age;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "所属学院")
    private String college;
    @ApiModelProperty(value = "专业班级")
    private String major_class;
    @ApiModelProperty(value = "宿舍号")
    private String dormitory_number;
    @ApiModelProperty(value = "志愿时长")
    private int volunteer;
    @ApiModelProperty(value = "修改后的密码")
    private String password;
    @ApiModelProperty(value = "类别：1为老师，0为学生")
    private int leibie;
    @ApiModelProperty(value = "类别：1为激活，0为未激活")
    private int activate;
    @ApiModelProperty(value = "民族")
    private String nation;
    @ApiModelProperty(value = "政治面貌:0群众，1团员，2党员")
    private String politics;
    @ApiModelProperty(value = "家乡")
    private String home;
    @ApiModelProperty(value = "原密码")
    private Integer orgin_password;
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "注册时间")
    private Time register_time;

    public Time getRegister_time() {
        return register_time;
    }
    public void setRegisterTimeToLocalNow() {
        this.register_time = Time.valueOf(LocalTime.now());
    }
    public void setRegister_time(Time register_time) {
        this.register_time = register_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrgin_password() {
        return orgin_password;
    }

    public void setOrgin_password(Integer orgin_password) {
        this.orgin_password = orgin_password;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public int getLeibie() {
        return leibie;
    }

    public void setLeibie(int leibie) {
        this.leibie = leibie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(int volunteer) {
        this.volunteer = volunteer;
    }

    public Yonghu() {
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
                ", volunteer=" + volunteer +
                ", password='" + password + '\'' +
                ", leibie=" + leibie +
                ", activate=" + activate +
                ", nation='" + nation + '\'' +
                ", politics='" + politics + '\'' +
                ", home='" + home + '\'' +
                ", orgin_password=" + orgin_password +
                ", code=" + code +
                ", register_time=" + register_time +
                '}';
    }
}
