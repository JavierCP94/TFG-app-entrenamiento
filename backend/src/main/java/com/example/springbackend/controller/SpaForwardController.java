package com.example.springbackend.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpaForwardController {

    // Rutas principales que apunta Angular
    @GetMapping({
            "/",
            "/login"
    })
    @ResponseBody
    public Resource forwardToSpa() {
        return new ClassPathResource("/static/browser/index.html");
    }

    // Catch-all para cualquier otra ruta
    @GetMapping("/**")
    @ResponseBody
    public Resource forwardToSpaCatchAll() {
        return new ClassPathResource("/static/browser/index.html");
    }
}
