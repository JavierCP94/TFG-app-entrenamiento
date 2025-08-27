package com.example.springbackend.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpaForwardController {

    // Catch-all para rutas que NO sean archivos estáticos NI vacías (SPA fallback)
    @GetMapping(value = "/{path:^(?!.*\\.(js|css|png|jpg|jpeg|gif|svg|ico|json)$)(?!$).*$}")
    @ResponseBody
    public Resource forwardToSpa() {
        return new ClassPathResource("/static/browser/index.html");
    }
}
