package com.project.controller;


import com.project.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class BigScreenController {

    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping("/home")
    public JsonResult home(){
        JsonResult res = new JsonResult();
        List<Home> homes = null;
        try {
            homes = jdbc.query("select home as name ,count(*) as value from yonghu where leibie = 0 group by home",new BeanPropertyRowMapper<>(Home.class));
            res.setCode("200");
            res.setMsg("连接成功");
            res.setResult(homes);
            return res;
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("连接失败");
            return res;
        }
    }

    @GetMapping("/time")
    public JsonResult time() {
        JsonResult res = new JsonResult();
        try {
            // 定义查询时间段的 SQL 语句
            String[] queries = {
                    "SELECT COUNT(*) FROM yonghu WHERE leibie = 0 and activate = 1 AND register_time BETWEEN '07:00:00' AND '09:00:00'",
                    "SELECT COUNT(*) FROM yonghu WHERE leibie = 0 and activate = 1 AND register_time BETWEEN '09:00:00' AND '11:00:00'",
                    "SELECT COUNT(*) FROM yonghu WHERE leibie = 0 and activate = 1 AND register_time BETWEEN '11:00:00' AND '13:00:00'",
                    "SELECT COUNT(*) FROM yonghu WHERE leibie = 0 and activate = 1 AND register_time BETWEEN '13:00:00' AND '15:00:00'",
                    "SELECT COUNT(*) FROM yonghu WHERE leibie = 0 and activate = 1 AND register_time BETWEEN '15:00:00' AND '17:00:00'",
                    "SELECT COUNT(*) FROM yonghu WHERE leibie = 0 and activate = 1 AND register_time BETWEEN '17:00:00' AND '19:00:00'",
                    "SELECT COUNT(*) FROM yonghu WHERE leibie = 0 and activate = 1 AND register_time BETWEEN '19:00:00' AND '21:00:00'"
            };

            // 创建一个数组来存储查询结果
            Integer[] timeCounts = new Integer[queries.length];

            // 执行每个查询并存储结果
            for (int i = 0; i < queries.length; i++) {
                timeCounts[i] = jdbc.queryForObject(queries[i], Integer.class);
            }

            // 设置响应结果
            res.setCode("200");
            res.setMsg("连接成功");
            res.setResult(timeCounts);
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("连接失败");
        }
        return res;
    }

    @GetMapping("/college")
    public JsonResult college() {
        JsonResult res = new JsonResult();
        try {
            // 查询数据库，获取学院名称和人数
            List<Collegecount> colleges = jdbc.query(
                    "SELECT college AS name, COUNT(*) AS num FROM yonghu WHERE leibie = 0 GROUP BY college",
                    new BeanPropertyRowMapper<>(Collegecount.class)
            );

            // 创建两个数组来存储学院名称和人数
            String[] names = new String[colleges.size()];
            Integer[] nums = new Integer[colleges.size()];

            // 遍历查询结果，填充数组
            for (int i = 0; i < colleges.size(); i++) {
                names[i] = colleges.get(i).getName();
                nums[i] = colleges.get(i).getNum();
            }

            // 创建 CollegeData 对象来封装两个数组
            CollegeData collegeData = new CollegeData();
            collegeData.setNames(names);
            collegeData.setNums(nums);

            // 设置响应结果
            res.setCode("200");
            res.setMsg("连接成功");
            res.setResult(collegeData);
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("连接失败");
        }
        return res;
    }

    @GetMapping("/province")
    public JsonResult province() {
        JsonResult res = new JsonResult();
        try {
            // 查询数据库，获取前十个省份名字和人数
            List<Home> provinces = jdbc.query(
                    "SELECT home AS name, COUNT(*) AS value FROM yonghu WHERE leibie = 0 GROUP BY home ORDER BY value DESC LIMIT 10",
                    new BeanPropertyRowMapper<>(Home.class)
            );

            // 创建两个数组来存储省份名字和人数
            String[] names = new String[provinces.size()];
            Integer[] values = new Integer[provinces.size()];

            // 遍历查询结果，填充数组
            for (int i = 0; i < provinces.size(); i++) {
                names[i] = provinces.get(i).getName();
                values[i] = provinces.get(i).getValue();
            }

            // 创建 ProvinceData 对象来封装两个数组
            HomeData homeData = new HomeData();
            homeData.setNames(names);
            homeData.setValues(values);

            // 设置响应结果
            res.setCode("200");
            res.setMsg("连接成功");
            res.setResult(homeData);
        } catch (DataAccessException e) {
            e.printStackTrace();
            res.setCode("201");
            res.setMsg("连接失败");
        }
        return res;
    }
}
