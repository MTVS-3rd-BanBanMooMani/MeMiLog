package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.ReportDTO;
import com.banbanmoomani.memilog.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) { this.reportService = reportService; }

    @GetMapping("/report")
    public String showReport() {
        return "main/report";
    }

    @PostMapping("/report")
    public String createReport(@RequestParam("rpt_category_id") int rptCategoryId,
                               @RequestParam("rpt_content") String rptContent,
                               @RequestParam("post_id") int postId,
                               @RequestParam("rpter_user_id") int rpterUserId,
                               @RequestParam("rpted_user_id") int rptedUserId) {
        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        ReportDTO reportDTO = new ReportDTO(rptCategoryId, rptContent, postId, rpterUserId, rptedUserId, new Date(currentTimestamp.getTime()));
        reportService.createReport(reportDTO);

        System.out.println("동작함");
        System.out.println("rptCategoryId = " + rptCategoryId);
        System.out.println("rptContent = " + rptContent);
        System.out.println("postId = " + postId);
        System.out.println("rpterUserId = " + rpterUserId);
        System.out.println("rptedUserId = " + rptedUserId);


        return "redirect:/report";
    }
}
