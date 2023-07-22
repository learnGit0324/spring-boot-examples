package com.neo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
    @RequestMapping("/hello")
    public String index() {
        return "Hello World!!!";
    }

    @RequestMapping("/checkUser")
    public String checkUser(String username){
        return String.format("user %s is valid", username);
    }
}