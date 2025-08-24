package com.example.springbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SpaForwardController {

    @GetMapping({
        "/",
        "/home",
        "/login",
        "/register",
        "/dashboard",
        "/exercises",
        "/workouts",
        "/profile"
    })
    public String forwardToSpa() {
        return "forward:/index.html";
    }
    
    @GetMapping("/{path:[^\.]*}")
    public String redirect(@PathVariable String path) {
        return "forward:/index.html";
    }
}
