package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import com.banbanmoomani.memilog.DTO.admin.notice.NoticeRequestDTO;
import com.banbanmoomani.memilog.service.AdminService;
import com.banbanmoomani.memilog.service.MissionService;
import com.banbanmoomani.memilog.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final NoticeService noticeService;
    private final MissionService missionService;

    public AdminController(AdminService adminService, NoticeService noticeService, MissionService missionService) {
        this.adminService = adminService;
        this.noticeService = noticeService;
        this.missionService = missionService;
    }

    @GetMapping("/dashBoard")
    public void dashBoard() {
    }

    @GetMapping("/userBlackList")
    public String userBlackList(Model model) {

        List<BanListDTO> banListDTO = adminService.getBanListDTO();
        List<BlackListDTO> blackListDTO = adminService.getBlackListDTO();

        model.addAttribute("banListDTO", banListDTO);
        model.addAttribute("blackListDTO", blackListDTO);

        return "admin/userBlackList";
    }

    @PostMapping("/userBlackList/black")
    public String blackUser(BanListDTO banListDTO) {

        return "redirect:/admin/userBlackList";
    }

    @GetMapping("/reportTotal")
    public void reportTotal() {
    }

    @GetMapping("/point")
    public void point() {
    }

    @GetMapping("/noticeBoard")
    public void noticeBoard(Model model) {
        List<NoticeDTO> noticeList = noticeService.findAllNotice();
        model.addAttribute("noticeList", noticeList);
    }

    @PostMapping("/noticeBoard")
    public String createNotice(@RequestBody NoticeRequestDTO noticeRequestDTO) {
        noticeService.createNotice(noticeRequestDTO);
        return "redirect:/admin/noticeBoard";
    }

    @GetMapping("/dailyTopicBoard")
    public void dailyTopicBoard(Model model) {
        List<MissionDTO> missionList = missionService.findAllMission();
        model.addAttribute("missionList", missionList);
    }

    @PostMapping("/dailyTopicBoard")
    public String createMission(@RequestBody MissionDTO missionDTO) {
        missionService.createMission(missionDTO);
        return "redirect:/admin/dailyTopicBoard";
    }

}
