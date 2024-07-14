package com.banbanmoomani.memilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String login(Model model) {
        return "user/login";
    }
    @GetMapping("/forgot_password")
    public String forgotPassword(Model model) {
        return "user/forgot_password";
    }
    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }
}
