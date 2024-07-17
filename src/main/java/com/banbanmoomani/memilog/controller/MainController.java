package com.banbanmoomani.memilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
    // index page
    @GetMapping("/")
    public String home() {
        return "main/home";
    }

    @GetMapping("/mydiary")
    public void mydiary() {}

}
