package com.neo.controller;

import com.neo.dao.City;
import com.neo.demo.UserService;
import com.neo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping("/")
    public String index() {
        String test = userService.test();
        return "Hello Spring Boot 2.0!" + "\r\n" + test;
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public List<City> findById(int id) {
        return cityRepository.findById(id);
    }

    @RequestMapping("/queryByName")
    @ResponseBody
    public List<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @RequestMapping("/queryByFuzzyName")
    @ResponseBody
    public List<City> findByFuzzyName(String fuzzyName) {
        return cityRepository.findByFuzzyName(fuzzyName);
    }

    @RequestMapping("/updateById")
    @ResponseBody
    public String updateById(int id, String name) {
        return cityRepository.updateById(id, name);
    }
}
