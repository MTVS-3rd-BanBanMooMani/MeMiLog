package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import com.banbanmoomani.memilog.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String userBlackList(Model model) {

        List<BanListDTO> banListDTO = adminService.getBanListDTO();
        List<BlackListDTO> blackListDTO = adminService.getBlackListDTO();

        model.addAttribute("banListDTO", banListDTO);
        model.addAttribute("blackListDTO", blackListDTO);

        return "admin/userBlackList";
    }

    @PostMapping("/userBlackList/black")
    public String blackUser(@RequestParam("userIdList") List<String> userIdList) {

        if (userIdList == null || userIdList.isEmpty()) {
            System.out.println("userIdList is null or empty");
        } else {
            for (String userId : userIdList) {
                System.out.println(userId);
            }

            adminService.blackUser(userIdList);
        }

        return "redirect:/admin/userBlackList";
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
