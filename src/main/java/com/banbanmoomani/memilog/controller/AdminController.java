package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import com.banbanmoomani.memilog.DTO.admin.daily.DailyMissionRequestDTO;
import com.banbanmoomani.memilog.DTO.admin.dashboard.*;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.processedPostListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.unProcessedPostListDTO;
import com.banbanmoomani.memilog.DTO.admin.notice.NoticeRequestDTO;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryDTO;
import com.banbanmoomani.memilog.service.AdminService;
import com.banbanmoomani.memilog.service.MissionService;
import com.banbanmoomani.memilog.service.NoticeService;
import com.banbanmoomani.memilog.service.RPTCategoryService;
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
    private final RPTCategoryService rptCategoryService;

    public AdminController(AdminService adminService, NoticeService noticeService, MissionService missionService, RPTCategoryService rptCategoryService) {
        this.adminService = adminService;
        this.noticeService = noticeService;
        this.missionService = missionService;
        this.rptCategoryService = rptCategoryService;
    }

    @GetMapping("/dashBoard")
    public String dashBoard(Model model) {

        // 최근 10일 간의 전체 회원 수 추이
        List<MeMiLogInfoDTO> meMiLogInfoDTOList = adminService.getMeMiLogInfo();
        model.addAttribute("adminDashBoardDTOList", meMiLogInfoDTOList);

        for (MeMiLogInfoDTO meMiLogInfoDTO : meMiLogInfoDTOList) {
            System.out.println(meMiLogInfoDTO);
        }

        // 연령대 별 총 회원 수 추이
        List<AgeGroupMemberDTO> ageGroupMembers = adminService.getAgeGroupMembers();
        model.addAttribute("ageGroupMembers", ageGroupMembers);

        for(AgeGroupMemberDTO ageGroupMemberDTO : ageGroupMembers) {
            System.out.println(ageGroupMemberDTO);
        }

        // 당일 신고된 포스트 수
        int todayReportCount = adminService.getTodayReportCount();
        List<ReportedPostDTO> reportedPosts = adminService.getTodayReportedPosts();
        model.addAttribute("reportedPosts", reportedPosts);

        for (ReportedPostDTO reportedPostDTO : reportedPosts) {
            System.out.println(reportedPostDTO);
        }

        return "admin/dashboard";
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

    @PostMapping("/userBlackList/release")
    public String releaseUser(@RequestParam("userIdList") List<String> userIdList) {

        if (userIdList == null || userIdList.isEmpty()) {
            System.out.println("userIdList is null or empty");
            return "redirect:/admin/userBlackList";
        }

        for (String userId : userIdList) {
            System.out.println(userId);
        }

        adminService.releaseUser(userIdList);

        return "redirect:/admin/userBlackList";
    }

    @GetMapping("/reportTotal")
    public String reportTotal(Model model) {

        List<unProcessedPostListDTO> unProcessedPostList = adminService.getUnProcessedPostList();
        List<processedPostListDTO> processedPostList = adminService.getProcessedPostList();

        model.addAttribute("unProcessedPostListDTO", unProcessedPostList);
        model.addAttribute("processedPostListDTO", processedPostList);

        List<RPTCategoryListDTO> rptCategoryDTOList = adminService.getRPTCategoryDTOList();

        model.addAttribute("rptCategoryDTOList", rptCategoryDTOList);

        return "admin/reportTotal";
    }

    @PostMapping("/reportTotal/process")
    public String processReport(@RequestParam("postIdList") List<String> postIdList) {
        if (postIdList == null || postIdList.isEmpty()) {
            System.out.println("postIdList is null or empty");
            return "redirect:/admin/userBlackList";
        }

        for (String postId : postIdList) {
            System.out.println(postId);
        }

        adminService.processReport(postIdList);

        return "redirect:/admin/userBlackList";
    }

    @PostMapping("/reportTotal/process")
    public String processReport(@RequestParam("postIdList") List<String> postIdList) {
        if (postIdList == null || postIdList.isEmpty()) {
            System.out.println("postIdList is null or empty");
            return "redirect:/admin/userBlackList";
        }

        for (String postId : postIdList) {
            System.out.println(postId);
        }

        adminService.processReport(postIdList);

        return "redirect:/admin/userBlackList";
    }

    @GetMapping("/point")
    public void point(Model model) {
        List<RPTCategoryDTO> rpt_categorise = rptCategoryService.findAllCategorise();
        model.addAttribute("rpt_categorise", rpt_categorise);
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
    public String createMission(@RequestBody DailyMissionRequestDTO dailyMissionRequestDTO) {
        missionService.createMission(dailyMissionRequestDTO);
        return "redirect:/admin/dailyTopicBoard";
    }

}
