package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DAO.MissionMapper;
import com.banbanmoomani.memilog.DTO.CompanionDTO;
import com.banbanmoomani.memilog.DTO.EmotionDTO;
import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.service.ComPanionService;
import com.banbanmoomani.memilog.service.EmotionService;
import com.banbanmoomani.memilog.service.MissionService;
import com.banbanmoomani.memilog.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final MissionService missionService;
    private final EmotionService emotionService;
    private final ComPanionService comPanionService;

    public PostController(PostService postService, MissionService missionService, EmotionService emotionService, ComPanionService comPanionService) {
        this.postService = postService;
        this.missionService = missionService;
        this.emotionService = emotionService;
        this.comPanionService = comPanionService;
    }

    @GetMapping("/create")
    public String createPost(Model model, HttpSession session, RedirectAttributes rttr) {
        Object user_id = session.getAttribute("user_id");
        if(user_id == null) {
            rttr.addFlashAttribute("failMessage", "로그인을 먼저 해주세요!");
            return "redirect:/user/login";
        }

        MissionDTO mission = missionService.findTodayMission();

        String priThemeName = missionService.findPriThemeName(mission.getPriThemeId());
        String subThemeName = missionService.findSubThemeName(mission.getSubThemeId());
        System.out.println(mission);
        List<EmotionDTO> emotions = emotionService.findAllEmotions();

        model.addAttribute("today", mission.getMissionDate());
        model.addAttribute("mission", mission);
        model.addAttribute("priThemeName", priThemeName);
        model.addAttribute("subThemeName", subThemeName);
        model.addAttribute("emotion", emotions);
        return "main/postcreate";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute CreateRequestDTO createRequestDTO,
                             HttpSession session,
                             RedirectAttributes rttr) {
        System.out.println(createRequestDTO);

        Object user_id = session.getAttribute("user_id");
        if(user_id == null) {
            rttr.addFlashAttribute("failMessage", "로그인을 먼저 해주세요!");
            return "redirect:/user/login";
        }
        createRequestDTO.setUser_id((int) user_id);
        postService.createPost(createRequestDTO);

        return "redirect:/post/all";
    }

    @GetMapping("/all")
    public String viewAllPost(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "main/allview";
    }

    // 오늘 mission에 해당하는 post 보기
//    @GetMapping("/by-mission")
//    public String findAllPostOnMissionByDate(Model model) {
//
//        List<PostDTO> posts = postService.findAllPostOnMissionByDate();
//        posts.forEach(System.out::println);
//
//        return "main/test";
//    }
}
