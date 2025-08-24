package com.example.springbackend.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpaForwardController {

    // Handle all GET requests that don't match static resources or API endpoints
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
    @ResponseBody
    public Resource forwardToSpa() {
        return new ClassPathResource("/static/browser/index.html");
    }
    
    // Catch-all for all other paths except those with file extensions
    @GetMapping("/**/{path:[^.]*}")
    @ResponseBody
    public Resource forwardToSpaCatchAll() {
        return new ClassPathResource("/static/browser/index.html");
    }
}
