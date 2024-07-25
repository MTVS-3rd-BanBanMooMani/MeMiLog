package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.MainTitleDTO;
import com.banbanmoomani.memilog.DTO.archivePostDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.DTO.todayPostDTO;
import com.banbanmoomani.memilog.service.MissionService;
import com.banbanmoomani.memilog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class MainController {

    private final MissionService missionService;
    private final PostService postService;

    public MainController(MissionService missionService, PostService postService) {
        this.missionService = missionService;
        this.postService = postService;
    }

    // index page
    @GetMapping("/")
    public String home(Model model) {

        // mission title
        MainTitleDTO mainTitleDTO = missionService.getMainTitle();
        model.addAttribute("mainTitleDTO", mainTitleDTO);

        // LocalDate
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일");
        String missionDate = today.format(formatter) + "의 미션";
        model.addAttribute("missionDate", missionDate);

        // today post
        List<todayPostDTO> todayPostDTOList = postService.getTodayPostDTOList();
        model.addAttribute("todayPostDTOList", todayPostDTOList);
        System.out.println(todayPostDTOList);

        // post archive
        List<archivePostDTO> archivePostDTOList = postService.getArchivePostDTOList();
        model.addAttribute("archivePostDTOList", archivePostDTOList);

        // today post count
        int todayPostCount = postService.getTodayPostCount();
        model.addAttribute("todayPostCount", todayPostCount);

        return "main/home";
    }
}
