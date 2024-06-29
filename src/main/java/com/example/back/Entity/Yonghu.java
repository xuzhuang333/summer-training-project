package com.example.back.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

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
    @ApiModelProperty(value = "密码")
    private String password;

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

    public Yonghu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                '}';
    }
}
