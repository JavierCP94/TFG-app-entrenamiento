package com.example.springbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaForwardController {

    @RequestMapping(value = {
        "/",
        "/{x:^(?!api|browser|assets|static|v3|swagger-ui|webjars|v2|swagger-resources|configuration|favicon.ico|error).*$}/**"
    })
    public String forwardToSpa() {
        return "forward:/browser/index.html";
    }
}
