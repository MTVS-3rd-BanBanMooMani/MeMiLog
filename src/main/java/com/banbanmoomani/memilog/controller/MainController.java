package com.banbanmoomani.memilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // index page
    @GetMapping("/")
    public String home() {
        return "main/home";
    }

    @GetMapping("/post/create")
    public String postcreate(Model model) {
        return "main/postcreate";
    }

    @GetMapping("/post/update")
    public String postupdate(Model model) {
        return "main/postupdate";
    }
}
