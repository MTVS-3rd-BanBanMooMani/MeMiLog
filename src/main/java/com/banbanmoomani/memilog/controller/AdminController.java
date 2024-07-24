package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import com.banbanmoomani.memilog.DTO.PageResult;
import com.banbanmoomani.memilog.DTO.ThemeDTO;
import com.banbanmoomani.memilog.DTO.admin.AdminDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import com.banbanmoomani.memilog.DTO.admin.dashboard.*;
import com.banbanmoomani.memilog.DTO.admin.notice.NoticeUpdateRequestDTO;
import com.banbanmoomani.memilog.DTO.admin.report.*;
import com.banbanmoomani.memilog.DTO.admin.notice.NoticeRequestDTO;
import com.banbanmoomani.memilog.DTO.user.LoginRequestDTO;
import com.banbanmoomani.memilog.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final NoticeService noticeService;
    private final MissionService missionService;
    private final RPTCategoryService rptCategoryService;
    private final ThemeService themeService;

    public AdminController(AdminService adminService, NoticeService noticeService, MissionService missionService, RPTCategoryService rptCategoryService, ThemeService themeService) {
        this.adminService = adminService;
        this.noticeService = noticeService;
        this.missionService = missionService;
        this.rptCategoryService = rptCategoryService;
        this.themeService = themeService;
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if(session.getAttribute("failMessage") != null) {
            model.addAttribute("failMessage", session.getAttribute("failMessage"));
            session.removeAttribute("failMessage");
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String adminLogin(LoginRequestDTO loginRequestDTO, RedirectAttributes rttr,
                             HttpSession httpSession) {

        AdminDTO adminInfo = adminService.findAdminByEmail(loginRequestDTO.getEmail());
        if(adminInfo != null && adminInfo.getPassword().equals(loginRequestDTO.getPassword())) {
            // session에 user_id 추가
            httpSession.setAttribute("admin_id", adminInfo.getAdmin_id());
            rttr.addFlashAttribute("successMessage", adminInfo.getAdmin_name() + "님, 환영합니다.");
            return "redirect:/admin/dashBoard";
        } else {
            rttr.addFlashAttribute("failMessage", "로그인에 실패하셨습니다.");
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/dashBoard")
    public String dashBoard(Model model, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

        AdminDTO adminInfo = adminService.findAdminById((int) admin_id);
        model.addAttribute("adminInfo", adminInfo);

        // 최근 10일 간의 전체 회원 수 추이
        List<MeMiLogInfoDTO> meMiLogInfoDTOList = adminService.getMeMiLogInfo();
        int meMiLogCount = meMiLogInfoDTOList.size();
        MeMiLogInfoDiff meMiLogInfoDiff = adminService.calculateMeMiLogInfoDiff(meMiLogInfoDTOList.get(meMiLogCount - 1), meMiLogInfoDTOList.get(meMiLogCount - 2));
        model.addAttribute("meMiLogInfoDiff", meMiLogInfoDiff);
        model.addAttribute("meMiLogInfoDTOList", meMiLogInfoDTOList);

        if (!meMiLogInfoDTOList.isEmpty()) {
            MeMiLogInfoDTO latestMeMiLogInfo = meMiLogInfoDTOList.get(meMiLogInfoDTOList.size() - 1);
            model.addAttribute("latestMeMiLogInfo", latestMeMiLogInfo);
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
        model.addAttribute("todayReportCount", todayReportCount);
        model.addAttribute("reportedPosts", reportedPosts);

        System.out.println(todayReportCount);
        for (ReportedPostDTO reportedPostDTO : reportedPosts) {
            System.out.println(reportedPostDTO);
        }

        return "admin/dashboard";
    }


    @GetMapping("/userBlackList")
    public String userBlackList(Model model, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

        AdminDTO adminInfo = adminService.findAdminById((int) admin_id);
        model.addAttribute("adminInfo", adminInfo);

        List<BanListDTO> banListDTO = adminService.getBanListDTO();
        List<BlackListDTO> blackListDTO = adminService.getBlackListDTO();

        model.addAttribute("banListDTO", banListDTO);
        model.addAttribute("blackListDTO", blackListDTO);

        return "admin/userBlackList";
    }

    @PostMapping("/userBlackList/black")
    public String blackUser(@RequestParam("userIdList") List<String> userIdList, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

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
    public String releaseUser(@RequestParam("userIdList") List<String> userIdList, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

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
    public String reportTotal(Model model, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

        AdminDTO adminInfo = adminService.findAdminById((int) admin_id);
        model.addAttribute("adminInfo", adminInfo);

        List<unProcessedPostListDTO> unProcessedPostList = adminService.getUnProcessedPostList();
        List<processedPostListDTO> processedPostList = adminService.getProcessedPostList();

        model.addAttribute("unProcessedPostListDTO", unProcessedPostList);
        model.addAttribute("processedPostListDTO", processedPostList);

        List<RPTCategoryListDTO> rptCategoryDTOList = adminService.getRPTCategoryDTOList();

        model.addAttribute("rptCategoryDTOList", rptCategoryDTOList);

        return "admin/reportTotal";
    }

    @PostMapping("/reportTotal/process")
    public String processReport(@RequestParam("postIdList") List<String> postIdList, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

        if (postIdList == null || postIdList.isEmpty()) {
            System.out.println("postIdList is null or empty");
            return "redirect:/admin/reportTotal";
        }

        for (String postId : postIdList) {
            System.out.println(postId);
        }

        adminService.processReport(postIdList, (int) admin_id);

        return "redirect:/admin/reportTotal";
    }

    @GetMapping("/point")
    public void point(Model model, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

        AdminDTO adminInfo = adminService.findAdminById((int) admin_id);
        model.addAttribute("adminInfo", adminInfo);
        List<RPTCategoryDTO> rpt_categorise = rptCategoryService.findAllCategorise();
        model.addAttribute("rpt_categorise", rpt_categorise);
    }

    @PostMapping("/point")
    public String createRPTCategory(@RequestBody RPTCategoryRequestDTO rptCategoryRequestDTO) {
        rptCategoryService.createRPTCategory(rptCategoryRequestDTO);
        return "redirect:/admin/point";
    }

    @PostMapping("/updateRPTCategory")
    public String updateRPTCategory(@RequestBody RPTCategoryDTO rptCategoryDTO) {
        rptCategoryService.updateRPTCategory(rptCategoryDTO);
        return "redirect:/admin/point";
    }
    @PostMapping("/deleteRPTCategory")
    public String deleteRPTCategory(@RequestBody RPTCategoryDeleteRequestDTO request) {

        rptCategoryService.deleteRPTCategory(request.getRptList());
        return "redirect:/admin/point";
    }

    @GetMapping("/noticeBoard")
    public void noticeBoard(@RequestParam(defaultValue = "1", value = "pageNum") int pageNum,
                            @RequestParam(defaultValue = "10", value = "pageSize") int pageSize,
                            @RequestParam(defaultValue = "", value = "content") String content,
                            Model model, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

        AdminDTO adminInfo = adminService.findAdminById((int) admin_id);
        model.addAttribute("adminInfo", adminInfo);

        PageResult<NoticeDTO> pagedResult = noticeService.findAllNotice(pageNum, pageSize, content);
        model.addAttribute("noticeList", pagedResult.getData());
        model.addAttribute("totalPages", (int) Math.ceil((double) pagedResult.getTotal() / pageSize));
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("content", content);
    }

    @PostMapping("/noticeBoard")
    public String createNotice(@RequestBody NoticeRequestDTO noticeRequestDTO,
                               HttpSession httpSession) {
        int adminId = (int) httpSession.getAttribute("admin_id");
        noticeService.createNotice(noticeRequestDTO, adminId);
        return "redirect:/admin/noticeBoard";
    }

    @PostMapping("/notice-update")
    public String updateNotice(@RequestBody NoticeUpdateRequestDTO noticeUpdateRequestDTO) {
        noticeService.updateNotice(noticeUpdateRequestDTO);
        return "redirect:/admin/noticeBoard";
    }

    @GetMapping("/dailyTopicBoard")
    public void dailyTopicBoard(@RequestParam(defaultValue = "1", value = "pageNum") int pageNum,
                                @RequestParam(defaultValue = "10", value = "pageSize") int pageSize,
                                @RequestParam(defaultValue = "", value = "content") String content,
                                Model model, HttpSession httpSession) {

        Object admin_id = httpSession.getAttribute("admin_id");

        AdminDTO adminInfo = adminService.findAdminById((int) admin_id);
        model.addAttribute("adminInfo", adminInfo);

        PageResult<MissionDTO> pagedResult = missionService.findAllMissionPaging(pageNum, pageSize, content);
        model.addAttribute("missionList", pagedResult.getData());
        model.addAttribute("totalPages", (int) Math.ceil((double) pagedResult.getTotal() / pageSize));
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("content", content);

        List<ThemeDTO> themeList = themeService.getTheme();
        model.addAttribute("themeList", themeList);

    }

    @PostMapping("/dailyTopicBoard")
    public ResponseEntity createMission(@RequestBody MissionDTO missionDTO) {
        try {
            missionService.createMission(missionDTO);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/updateMission")
    public ResponseEntity updateMission(@RequestBody MissionDTO missionDTO) {
        try {
            missionService.updateMission(missionDTO);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {

        httpSession.invalidate();
        return "redirect:/admin/login";
    }
}
