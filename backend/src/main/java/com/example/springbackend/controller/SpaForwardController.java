package com.example.springbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaForwardController {

    // Handle all routes and forward to index.html
    @GetMapping({
        "/",
        "/{path:^(?!.*\\.).*$",
        "/**/{path:^(?!.*\\.).*$}"
    })
    public String forwardToSpa() {
        return "forward:/index.html";
    }
}
