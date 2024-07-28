package com.banbanmoomani.memilog.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String handleError(@RequestParam("code") int code, HttpSession session, Model model) {
        String dest = (String) session.getAttribute("dest");
        model.addAttribute("dest", dest);

        String message = switch (code) {
            case 400 -> "잘못된 요청입니다. 웹 서버에서 구문 분석할 수 없는 스크립트 형식입니다.";
            case 401 -> "인증이 필요합니다. 로그인을 먼저 해주세요.";
            case 403 -> "접근이 금지되었습니다.";
            case 404 -> "찾을 수 없는 페이지입니다.";
            default -> "내부 서버 오류가 발생했습니다.";
        };

        model.addAttribute("error", code);
        model.addAttribute("message", message);
        return "error/error";
    }
}