package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.SignUpRequestDTO;
import com.banbanmoomani.memilog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    public String signup(SignUpRequestDTO signUpRequestDTO, RedirectAttributes rttr, Model model) {
        // 중복 확인
        boolean duplicateCheck = userService.duplicateCheck(signUpRequestDTO);
        if(duplicateCheck) {
            // 중복 시 alert창 띄우면서 signup페이지로 되돌아가기
            rttr.addFlashAttribute("failMessage", "닉네임이 중복되었습니다. 다시 회원가입해주세요");
            return "redirect:/signup";
        }


        userService.insertUser(signUpRequestDTO);
        rttr.addFlashAttribute("successMessage", "회원가입 되셨습니다. "+signUpRequestDTO.getNickname()+"환영합니다");
        return "redirect:/";
    }

    @GetMapping("/forgot")
    public String forgotIdOrPwd(Model model) {
        return "user/forgot";
    }
}
