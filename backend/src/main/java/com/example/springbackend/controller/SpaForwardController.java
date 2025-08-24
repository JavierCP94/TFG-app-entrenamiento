package com.example.springbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
        "/profile",
        "/**/{path:^[^.]*$"
    })
    public String forwardToSpa() {
        return "forward:/index.html";
    }
}
