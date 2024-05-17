package com.KKApplication.KK.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user")
    public String test(){
        return "test dc rui ne";
    }
}
