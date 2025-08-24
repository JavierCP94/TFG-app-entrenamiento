package com.example.springbackend.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        "/**/{path:^(?!api|static|assets|.*\\.(js|css|json|png|jpg|jpeg|gif|ico|svg)$).*$}"
    })
    @ResponseBody
    public Resource forwardToSpa() {
        return new ClassPathResource("/static/browser/index.html");
    }
}
