package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.ReportDTO;
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

    @PostMapping("/report")
    public String createReport(@RequestParam("rpt_category_id") int rptCategoryId,
                               @RequestParam("rpt_content") String rptContent,
                               @RequestParam("post_id") int postId,
                               @RequestParam("rpted_user_id") int rptedUserId,
                               @SessionAttribute(name = "user_id") int rpterUserId) {
        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        ReportDTO reportDTO = new ReportDTO(rptCategoryId, rptContent, postId, rpterUserId, rptedUserId, new Date(currentTimestamp.getTime()));
        reportService.createReport(reportDTO);

        UserDTO userDTO = userService.findUserById(rptedUserId); // user 경고 점수 조회
        if(userDTO.getCaution_weights() > 30) {
            // user 경고 점수가 일정 이상이면 temporary_YN = Y
            userService.updateTemporary(rptedUserId);
        } else {
            RPTCategoryDTO rptCategoryDTO = rptCategoryService.findRPTWeightById(rptCategoryId);
            // user 경고 점수가 일정 이하면 caution_weights 값에 추가로 더해주기
            userService.addCaution_Weights(rptCategoryDTO.getRpt_weight());
        }

        System.out.println("동작함");
        System.out.println("rptCategoryId = " + rptCategoryId);
        System.out.println("rptContent = " + rptContent);
        System.out.println("postId = " + postId);
        System.out.println("rpterUserId = " + rpterUserId);
        System.out.println("rptedUserId = " + rptedUserId);


        return "redirect:/report";
    }
}
