package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.LoginRequestDTO;
import com.banbanmoomani.memilog.DTO.SignUpRequestDTO;
import com.banbanmoomani.memilog.DTO.UserDTO;
import com.banbanmoomani.memilog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final HttpServletRequest httpServletRequest;

    public UserController(UserService userService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(LoginRequestDTO loginRequestDTO, RedirectAttributes rttr,
                            HttpSession httpSession) {
        UserDTO userInfo = userService.findByEmail(loginRequestDTO);
        if(userInfo != null && userInfo.getPassword().equals(loginRequestDTO.getPassword())) {
            // session에 user_id 추가
            httpSession.setAttribute("user_id", userInfo.getUser_id());
            rttr.addFlashAttribute("successMessage", userInfo.getNickName() + "님, 환영합니다.");
            return "redirect:/";
        } else {
            rttr.addFlashAttribute("failMessage", "로그인에 실패하셨습니다.");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/signup")
    public String signup() { return "user/signup"; }

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
