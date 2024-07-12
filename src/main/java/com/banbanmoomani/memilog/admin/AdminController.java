package com.banbanmoomani.memilog.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashBoard")
    public void dashBoard() {}

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
