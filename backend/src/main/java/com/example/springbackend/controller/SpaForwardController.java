package com.example.springbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaForwardController {
    
    @GetMapping("/**/{path:^(?!.*\\.).*$}")
    public String forwardToSpa() {
        return "forward:/browser/index.html";
    }
    
    @GetMapping("/")
    public String forwardToIndex() {
        return "forward:/browser/index.html";
    }
}
