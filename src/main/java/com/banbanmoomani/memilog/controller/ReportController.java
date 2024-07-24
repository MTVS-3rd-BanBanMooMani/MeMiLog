package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.ReportDTO;
import com.banbanmoomani.memilog.DTO.ReportRequestDTO;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
import com.banbanmoomani.memilog.service.PostService;
import com.banbanmoomani.memilog.service.RPTCategoryService;
import com.banbanmoomani.memilog.service.ReportService;
import com.banbanmoomani.memilog.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Controller
public class ReportController {

    private final ReportService reportService;
    private final UserService userService;
    private final RPTCategoryService rptCategoryService;

    public ReportController(ReportService reportService,UserService userService, RPTCategoryService rptCategoryService) {
        this.reportService = reportService;
        this.userService = userService;
        this.rptCategoryService = rptCategoryService;
    }

    @GetMapping("/report")
    public String showReport() {
        return "main/report";
    }

    @PostMapping("/post/report")
    public String createReport(ReportRequestDTO reportRequestDTO,
                               @SessionAttribute(name = "user_id") int rpterUserId) {

        System.out.println("createReport 동작함");
        System.out.println("rptCategoryId = " + reportRequestDTO.getRptCategoryId());
        System.out.println("rptContent = " + reportRequestDTO.getRptContent());
        System.out.println("postId = " + reportRequestDTO.getPostId());
        System.out.println("rpterUserId = " + rpterUserId);
        System.out.println("rptedUserId = " + reportRequestDTO.getRptedUserId());

        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        ReportDTO reportDTO = new ReportDTO(reportRequestDTO.getRptCategoryId(), reportRequestDTO.getRptContent(), reportRequestDTO.getPostId(), rpterUserId, reportRequestDTO.getRptedUserId(), new Date(currentTimestamp.getTime()));
        reportService.createReport(reportDTO);

        UserDTO userDTO = userService.findUserById(reportRequestDTO.getRptedUserId()); // user 경고 점수 조회
        if(userDTO.getCaution_weights() > 30) {
            // user 경고 점수가 일정 이상이면 temporary_YN = Y
            userService.updateTemporary(reportRequestDTO.getRptedUserId());
        } else {
            RPTCategoryDTO rptCategoryDTO = rptCategoryService.findRPTWeightById(reportRequestDTO.getRptCategoryId());
            // user 경고 점수가 일정 이하면 caution_weights 값에 추가로 더해주기
            userService.addCaution_Weights(rptCategoryDTO.getRpt_weight());
        }

        return "redirect:/post/bymission";
    }
}
