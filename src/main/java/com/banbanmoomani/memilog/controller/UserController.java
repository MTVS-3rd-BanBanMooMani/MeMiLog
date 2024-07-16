package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }

    // login Post 요청, SpringSecurity로 해결

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(UserDTO userDTO) {
        // service : 해당 이메일 중복인지 확인 후 DAO의 insertUser 호출

        // DAO : MyBatis의 insertUser 호출

        // service에서 false 반환시 alert 띄우기

        // true 반환시 index page로 이동
        return "main/home";
    }

    @GetMapping("/forgot")
    public String forgotIdOrPwd(Model model) {
        return "user/forgot";
    }
}
