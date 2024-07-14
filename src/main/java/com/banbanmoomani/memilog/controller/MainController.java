package com.banbanmoomani.memilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // index page
    @GetMapping("/")
    public String home() {
        return "main/home";
    }
}
