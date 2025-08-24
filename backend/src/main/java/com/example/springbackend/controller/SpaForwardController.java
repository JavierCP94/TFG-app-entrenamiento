package com.example.springbackend.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    @ResponseBody
    public Resource forwardToSpa() {
        return new ClassPathResource("/static/browser/index.html");
    }
    
    @GetMapping("/**/{path:[^.]*}")
    @ResponseBody
    public Resource spaRoutes(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        // Check if the request is for an API endpoint
        if (path != null && path.startsWith("/api/")) {
            return null; // Let other controllers handle API requests
        }
        // For all other paths, serve index.html
        return new ClassPathResource("/static/browser/index.html");
    }
}
