package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.archivePostDTO;
import com.banbanmoomani.memilog.DTO.todayPostDTO;
import com.banbanmoomani.memilog.service.MissionService;
import com.banbanmoomani.memilog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        String missionTitle = missionService.getMissionTitle();
        model.addAttribute("missionTitle", missionTitle);

        // today post
        List<todayPostDTO> todayPostDTOList = postService.getTodayPostDTOList();
        model.addAttribute("todayPostDTOList", todayPostDTOList);

        // post archive
        List<archivePostDTO> archivePostDTOList = postService.getArchivePostDTOList();
        model.addAttribute("archivePostDTOList", archivePostDTOList);

        // today post count
        int todayPostCount = postService.getTodayPostCount();
        model.addAttribute("todayPostCount", todayPostCount);

        return "main/home";
    }
}
