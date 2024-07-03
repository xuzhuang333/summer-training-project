package com.example.back.Controller;


import com.example.back.beans.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "大屏相关")
public class DaPingController {
    @Autowired
    private JdbcTemplate jdbc;


    @GetMapping("/sex")
    public JsonResult<List<Sexdaping>> sex(){
        JsonResult res = new JsonResult();
        //返回一个包含两个元素的数组（第一个元素代表男生，第二个元素代表女生），数组中的元素是Sexdaping类
        ArrayList<Sexdaping>sex = new ArrayList<>();

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where gender=? ",
                Integer.class,"男");
        Sexdaping sexdaping1 = new Sexdaping();
        sexdaping1.setValue(num1);
        sexdaping1.setName("男");

        int num2;
        num2 = jdbc.queryForObject("select count(*) from yonghu where gender=? ",
                Integer.class,"女");
        Sexdaping sexdaping2 = new Sexdaping();
        sexdaping2.setValue(num2);
        sexdaping2.setName("女");

        sex.add(sexdaping1);
        sex.add(sexdaping2);

        res.setResult(sex);
        return res;
    }

    @GetMapping("/baodao")
    public JsonResult<List<Baodaodaping>> baodao(){
        JsonResult res = new JsonResult();
        //需要返回一个包含两个元素的数组（第一个元素代表已报道，第二个元素代表未报到），数组中的元素是Baodaodaping类
        ArrayList<Baodaodaping>baodao = new ArrayList<>();

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where activate=? and leibie=?",
                Integer.class,1,0);
        Baodaodaping baodaodaping1 = new Baodaodaping();
        baodaodaping1.setValue(num1);
        baodaodaping1.setName("已报到");

        int num2;
        num2 = jdbc.queryForObject("select count(*) from yonghu where activate=? and leibie=?",
                Integer.class,0,0);
        Baodaodaping baodaodaping2 = new Baodaodaping();
        baodaodaping2.setValue(num2);
        baodaodaping2.setName("未报到");

        baodao.add(baodaodaping1);
        baodao.add(baodaodaping2);

        res.setResult(baodao);
        return res;

    }

    @GetMapping("/leibie")
    public JsonResult<List<Leibiedaping>> leibie(){
        JsonResult res = new JsonResult();
        //需要返回一个包含两个元素的数组（第一个元素代表团员，第二个元素代表群众），数组中的元素是Leibiedaping类
        ArrayList<Leibiedaping> leibie = new ArrayList<>();

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where politics=? ",
                Integer.class,1);
        Leibiedaping leibiedaping1=new Leibiedaping();
        leibiedaping1.setValue(num1);
        leibiedaping1.setName("团员");

        int num2;
        num2 = jdbc.queryForObject("select count(*) from yonghu where politics=? ",
                Integer.class,0);
        Leibiedaping leibiedaping2=new Leibiedaping();
        leibiedaping2.setValue(num2);
        leibiedaping2.setName("群众");

        leibie.add(leibiedaping1);
        leibie.add(leibiedaping2);

        res.setResult(leibie);
        return res;

    }

    @GetMapping("/minzu")
    public JsonResult<List<Minzudaping>> minzu(){
        JsonResult res = new JsonResult();
        //需要返回一个包含八个元素的数组（代表不同民族），数组中的元素是Minzudaping类
        ArrayList<Minzudaping>minzu = new ArrayList<>();

        int num1;
        num1 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"汉族",0);
        Minzudaping minzudaping1=new Minzudaping();
        minzudaping1.setValue(num1);
        minzudaping1.setName("汉族");

        int num2;
        num2 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"壮族",0);
        Minzudaping minzudaping2=new Minzudaping();
        minzudaping2.setValue(num2);
        minzudaping2.setName("壮族");

        int num3;
        num3 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"维吾尔族",0);
        Minzudaping minzudaping3=new Minzudaping();
        minzudaping3.setValue(num3);
        minzudaping3.setName("维吾尔族");

        int num4;
        num4 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"回族",0);
        Minzudaping minzudaping4=new Minzudaping();
        minzudaping4.setValue(num4);
        minzudaping4.setName("回族");

        int num5;
        num5 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"苗族",0);
        Minzudaping minzudaping5=new Minzudaping();
        minzudaping5.setValue(num5);
        minzudaping5.setName("苗族");

        int num6;
        num6 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"满族",0);
        Minzudaping minzudaping6=new Minzudaping();
        minzudaping6.setValue(num6);
        minzudaping6.setName("满族");

        int num7;
        num7 = jdbc.queryForObject("select count(*) from yonghu where nation=? and leibie=?",
                Integer.class,"土家族",0);
        Minzudaping minzudaping7=new Minzudaping();
        minzudaping7.setValue(num7);
        minzudaping7.setName("土家族");

        int num;
        int num8;
        num = jdbc.queryForObject("select count(*) from yonghu where leibie=?",
                Integer.class,0);
        num8=num-num1-num2-num3-num4-num5-num6-num7;
        Minzudaping minzudaping8=new Minzudaping();
        minzudaping8.setValue(num8);
        minzudaping8.setName("其他");

        minzu.add(minzudaping1);
        minzu.add(minzudaping2);
        minzu.add(minzudaping3);
        minzu.add(minzudaping4);
        minzu.add(minzudaping5);
        minzu.add(minzudaping6);
        minzu.add(minzudaping7);
        minzu.add(minzudaping8);

        res.setResult(minzu);
        return res;

    }

}
