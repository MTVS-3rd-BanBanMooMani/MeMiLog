package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.AdminDTO;
import com.banbanmoomani.memilog.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashBoard")
    public void dashBoard() {}

    @GetMapping("/userBlackList")
    public void userBlackList(Model model) {

        AdminDTO.getBlackListDTO getBlackListDTO = adminService.getBlackListDTO();
        model.addAttribute("blackListDTO", getBlackListDTO);
    }

    @GetMapping("/reportTotal")
    public void reportTotal() {}

    @GetMapping("/point")
    public void point() {}

    @GetMapping("/noticeBoard")
    public void noticeBoard() {}

    @GetMapping("/dailyTopicBoard")
    public void dailyTopicBoard() {}
}
