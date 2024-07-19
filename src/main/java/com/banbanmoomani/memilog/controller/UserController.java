package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.user.*;
import com.banbanmoomani.memilog.service.user.RegisterMailService;
import com.banbanmoomani.memilog.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String signup(SignUpRequestDTO signUpRequestDTO, RedirectAttributes rttr, HttpSession httpSession) {
        // 중복 확인
        boolean duplicateCheck = userService.duplicateCheck(signUpRequestDTO);
        if(duplicateCheck) {
            // 중복 시 alert창 띄우면서 signup페이지로 되돌아가기
            rttr.addFlashAttribute("failMessage", "닉네임이 중복되었습니다. 다시 회원가입해주세요");
            return "redirect:/signup";
        }

        httpSession.removeAttribute(signUpRequestDTO.getEmail());
        userService.insertUser(signUpRequestDTO);
        rttr.addFlashAttribute("successMessage", "회원가입 되셨습니다. "+signUpRequestDTO.getNickname()+"환영합니다");
        return "redirect:/";
    }

    @GetMapping("/forgot")
    public String forgotIdOrPwd() {
        return "user/forgot";
    }

    @PostMapping("/forgotID")
    public ResponseEntity forgotID(@RequestBody String email) {
        UserDTO userInfo = userService.findByEmail(email.replaceAll("\"", ""));
        if(userInfo != null) {
            System.out.println("성공");
            return new ResponseEntity(userInfo, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/forgotPWD")
    public String forgotPwd(@ModelAttribute ForgotPWDRequestDTO forgotPWDRequestDTO,
                            RedirectAttributes rttr,
                            HttpSession httpSession) {
        System.out.println(forgotPWDRequestDTO);

        if(httpSession.getAttribute(forgotPWDRequestDTO.getEmail()).equals(forgotPWDRequestDTO.getVerifyCode())) {
            UserDTO userDTO = userService.findByEmail(forgotPWDRequestDTO.getEmail());
            rttr.addFlashAttribute("successMessage", "회원님의 비밀번호는 "+userDTO.getPassword()+"입니다. 빠른 시일 내에 비밀번호를 변경해주세요.");
            return "redirect:/";
        }
        rttr.addFlashAttribute("failMessage", "잘못된 정보를 입력하셨습니다.");
        httpSession.removeAttribute(forgotPWDRequestDTO.getEmail());
        return "redirect:/user/forgot";
    }

    @GetMapping(value = "/verify")
    @ResponseBody
    public String verifyCode(@RequestParam(name = "email") String email, HttpSession httpSession) throws Exception{
        String code = registerMailService.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증 코드 ==> " + code);
        httpSession.setAttribute(email, code);

        return code;
    }

    @PostMapping(value = "/verify", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity verifyCode(@RequestBody VerifyRequestDTO verifyRequestDTO,
                             HttpSession httpSession) throws Exception {
        String server_verify_code = (String) httpSession.getAttribute(verifyRequestDTO.getEmail());
        if(server_verify_code != null && server_verify_code.equals(verifyRequestDTO.getVerify_code())) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
