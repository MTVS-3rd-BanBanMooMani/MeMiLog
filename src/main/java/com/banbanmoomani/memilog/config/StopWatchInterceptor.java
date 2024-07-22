package com.banbanmoomani.memilog.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class StopWatchInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                            HttpServletResponse response,
                            Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user_id") == null) {
            session.setAttribute("failMessage", "로그인을 먼저 해주세요!");
            response.sendRedirect("/user/login"); // 세션에 user_id가 없으면 루트로 리다이렉트
            return false; // 요청 처리 중단
        }
        else if (session.getAttribute("admin_id") == null) {
            session.setAttribute("failMessage", "로그인을 먼저 해주세요!");
            response.sendRedirect("/admin/login"); // 세션에 user_id가 없으면 루트로 리다이렉트
            return false; // 요청 처리 중단
        }

        return true;
    }
}

