package com.neo.demo;


import org.springframework.stereotype.Component;

@Component
public class UserService {

    public String test(){
       return "this is a user service";
    }
}
