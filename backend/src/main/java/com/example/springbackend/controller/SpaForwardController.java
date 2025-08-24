package com.example.springbackend.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpaForwardController {

    // Rutas principales conocidas
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

    // Catch-all: cualquier ruta sin extensión (para SPA)
    @GetMapping("/{path:^(?!api$)[^\\.]*}")
    @ResponseBody
    public Resource forwardToSpaCatchAll() {
        return new ClassPathResource("/static/browser/index.html");
    }
}
