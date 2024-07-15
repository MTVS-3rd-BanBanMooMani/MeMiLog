package com.banbanmoomani.memilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashBoard")
    public void dashBoard() {

        // 활동 정지 회원 리스트 조회

        // 블랙 리스트 회원 리스트 조회

        // DTO 반환
    }

    @GetMapping("/userBlackList")
    public void userBlackList() {}

    @GetMapping("/reportTotal")
    public void reportTotal() {}

    @GetMapping("/point")
    public void point() {}

    @GetMapping("/noticeBoard")
    public void noticeBoard() {}

    @GetMapping("/dailyTopicBoard")
    public void dailyTopicBoard() {}
}
