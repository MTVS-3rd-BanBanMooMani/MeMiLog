package com.banbanmoomani.memilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/main")
public class MainController {
    // index page
    @GetMapping("/")
    public String home() {
        return "main/home";
    }

    @GetMapping("/mydiary")
    public void mydiary() {}
}
