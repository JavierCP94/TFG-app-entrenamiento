package com.example.springbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepAliveController {

    @GetMapping("/keepalive")
    public String keepAlive() {
        return "P-I-N-G";
    }
}
