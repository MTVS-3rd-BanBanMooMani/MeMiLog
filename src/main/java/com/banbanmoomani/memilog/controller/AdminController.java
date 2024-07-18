package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.AdminDTO;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import com.banbanmoomani.memilog.service.AdminService;
import com.banbanmoomani.memilog.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final NoticeService noticeService;

    public AdminController(NoticeService noticeService) {
        this.noticeService = noticeService;
        adminService = new AdminService();
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
    public void noticeBoard(Model model) {
        List<NoticeDTO> noticeList = noticeService.findAllNotice();
        model.addAttribute("noticeList", noticeList);
    }

    @GetMapping("/dailyTopicBoard")
    public void dailyTopicBoard() {}
}
