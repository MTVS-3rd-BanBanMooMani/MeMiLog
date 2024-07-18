package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.user.ForgotRequestDTO;
import com.banbanmoomani.memilog.DTO.user.LoginRequestDTO;
import com.banbanmoomani.memilog.DTO.user.SignUpRequestDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
import com.banbanmoomani.memilog.service.user.RegisterMailService;
import com.banbanmoomani.memilog.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RegisterMailService registerMailService;

    public UserController(UserService userService, RegisterMailService registerMailService) {
        this.userService = userService;
        this.registerMailService = registerMailService;
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(LoginRequestDTO loginRequestDTO, RedirectAttributes rttr,
                            HttpSession httpSession) {
        UserDTO userInfo = userService.findByEmail(loginRequestDTO.getEmail());
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
    public String signup(SignUpRequestDTO signUpRequestDTO, RedirectAttributes rttr) {
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
    public String forgotIdOrPwd() {
        return "user/forgot";
    }

    @PostMapping("/forgot")
    public String forgotIdOrPwd(ForgotRequestDTO forgotRequestDTO, RedirectAttributes rttr,
                                HttpSession httpSession) {
        if(forgotRequestDTO.getId_email().equals(null)) {
            // pwd 찾기를 원하는 경우
            UserDTO userInfo = userService.findByEmail(forgotRequestDTO.getEmail());
            String verify_code = (String) httpSession.getAttribute(forgotRequestDTO.getEmail());
            if(userInfo == null || !verify_code.equals(forgotRequestDTO.getVerify_code())) {
                // 잘못된 정보를 입력했을 떄
                // 해당 이메일로 된 계정이 존재하는지, 인증코드가 맞는지
                rttr.addFlashAttribute("해당 이메일 계정의 비밀번호는 " + userInfo.getPassword() + "입니다.");
                return "redirect:/user/forgot";
            }
            rttr.addFlashAttribute("잘못된 정보를 입력하셨습니다 다시 입력해주세요!");
            return "redirect:/";
        } else {
            // id찾기를 원하는 경우
            UserDTO userInfo = userService.findByEmail(forgotRequestDTO.getId_email());
            if(userInfo == null) {
                // 잘못된 정보를 입력했을 떄
                rttr.addFlashAttribute("해당 이메일로 된 계정이 존재하지 않습니다.");
                return "redirect:/user/forgot";
            }
            rttr.addFlashAttribute("해당 이메일로 된 계정이 존재합니다.");
            return "redirect:/user/forgot";
        }
    }

    @GetMapping("/verify")
    @ResponseBody
    public String verifyCode(@RequestParam(name = "email") String email, HttpSession httpSession) throws Exception{
        String code = registerMailService.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증 코드 ==> " + code);
        httpSession.setAttribute(email, code);
        return code;
    }
}
