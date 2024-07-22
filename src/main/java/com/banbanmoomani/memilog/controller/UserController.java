package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.user.*;
import com.banbanmoomani.memilog.service.user.RegisterMailService;
import com.banbanmoomani.memilog.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RegisterMailService registerMailService;

    public UserController(UserService userService, RegisterMailService registerMailService) {
        this.userService = userService;
        this.registerMailService = registerMailService;
    }

    @GetMapping("/deleteSession")
    public String deleteSession(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if(session.getAttribute("failMessage") != null) {
            model.addAttribute("failMessage", session.getAttribute("failMessage"));
            session.removeAttribute("failMessage");
        }
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(LoginRequestDTO loginRequestDTO, RedirectAttributes rttr,
                            HttpSession httpSession) {
        UserDTO userInfo = userService.findByEmail(loginRequestDTO.getEmail());
        if(userInfo != null && userInfo.getPassword().equals(loginRequestDTO.getPassword())) {
            // session에 user_id 추가
            httpSession.setAttribute("user_id", userInfo.getUser_id());
            rttr.addFlashAttribute("successMessage", userInfo.getNickname() + "님, 환영합니다.");
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

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody String deletePassword, HttpSession session) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        if (user_id == null) {
            return new ResponseEntity<>("세션이 만료되었거나 잘못된 요청입니다.", HttpStatus.UNAUTHORIZED);
        }

        UserDTO user = userService.findUserById(user_id);
        if (user == null) {
            return new ResponseEntity<>("사용자를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        if (!user.getPassword().equals(deletePassword)) {
            System.out.println(user.getPassword());
            System.out.println(deletePassword);
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        try {
            System.out.println("*********컨트롤러 진입*********");
            userService.deleteUser(user);
            session.invalidate();
            return new ResponseEntity<>("회원 탈퇴가 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("회원 탈퇴 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
