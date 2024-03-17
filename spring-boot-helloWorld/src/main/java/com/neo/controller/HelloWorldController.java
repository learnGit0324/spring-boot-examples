package com.neo.controller;

import com.neo.config.HazelcastUtil;
import com.neo.model.EmpRepository;
import com.neo.model.EmpRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class HelloWorldController {

    @Autowired
    EmpRepository empRepository;

    @Autowired
    EmpRepository2 empRepository2;

    @Autowired
    private HazelcastUtil hazelcastUtil;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World!!!";
    }

    @RequestMapping("/now")
    String hehe() {
        return "现在时间：" + LocalDateTime.now();
    }

    @RequestMapping("/checkUser")
    public String checkUser(@RequestParam("name") String username){
        return String.format("user %s is valid", username);
    }

    @RequestMapping("/findAll")
    String findAll() {
        return empRepository.findAll().toString();
    }

    @RequestMapping("/save")
    String saveCache(@RequestParam("id") String id) {
        System.out.println("start to find and save in cache");
        String emp = empRepository.findById(id).toString();
        hazelcastUtil.set(id, emp);
        System.out.println("end of find and save in cache");
        return emp;
    }

    @RequestMapping("/save2")
    String saveCache2(@RequestParam("id") String id) throws SQLException {
        System.out.println("start to find and save in cache");
        String emp = empRepository2.findById(id).toString();
        hazelcastUtil.set(id, emp);
        System.out.println("end of find and save in cache");
        return emp;
    }

    @RequestMapping("/find")
    String getCache(@RequestParam("id") String id) {
        System.out.println("start to get from cache");
        String emp = (String) hazelcastUtil.get(id);
        System.out.println("end of get from cache");
        return emp;
    }
}