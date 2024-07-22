package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DAO.MissionMapper;
import com.banbanmoomani.memilog.DAO.PostMapper;
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
import org.springframework.web.bind.annotation.*;
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
    private final PostMapper postMapper;

    public PostController(PostService postService, MissionService missionService, EmotionService emotionService, ComPanionService comPanionService, PostMapper postMapper) {
        this.postService = postService;
        this.missionService = missionService;
        this.emotionService = emotionService;
        this.comPanionService = comPanionService;
        this.postMapper = postMapper;
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
        List<EmotionDTO> emotions = emotionService.findAllEmotions();
        List<CompanionDTO> companions = comPanionService.findAllCompanions();
        System.out.println(mission);


        model.addAttribute("today", mission.getMissionDate());
        model.addAttribute("mission", mission);
        model.addAttribute("priThemeName", priThemeName);
        model.addAttribute("subThemeName", subThemeName);
        model.addAttribute("emotions", emotions);
        model.addAttribute("companions", companions);

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
        EmotionDTO emotion = emotionService.findEmotionById(createRequestDTO.getEmotion_id());
        System.out.println(emotion);
        CompanionDTO companion = comPanionService.findCompanionById(createRequestDTO.getCompanion_id());
        System.out.println(companion);
        createRequestDTO.setEmotion_id(emotion.getEmotionId());
        createRequestDTO.setCompanion_id(companion.getCompanion_id());
        postService.createPost(createRequestDTO);

        return "redirect:/post/all";
    }
    @GetMapping("/update")
    public String updatePost(
                             Model model,
                             HttpSession session,
                             RedirectAttributes rttr) {
        int postId = 35;  // postId를 하드코딩합니다.
        int user_id = 1;
        PostDTO post = postService.findPostById(postId);

        if(post == null || post.getUser_id() != (int)user_id) {
            rttr.addFlashAttribute("failMessage", "수정 권한이 없습니다.");
            return "redirect:/post/all";
        }
        MissionDTO mission = missionService.findTodayMission();
        String priThemeName = missionService.findPriThemeName(mission.getPriThemeId());
        String subThemeName = missionService.findSubThemeName(mission.getSubThemeId());
        List<EmotionDTO> emotions = emotionService.findAllEmotions();
        List<CompanionDTO> companions = comPanionService.findAllCompanions();

        model.addAttribute("post", post);
        model.addAttribute("mission", mission);
        model.addAttribute("priThemeName", priThemeName);
        model.addAttribute("subThemeName", subThemeName);
        model.addAttribute("emotions", emotions);
        model.addAttribute("companions", companions);

        return "main/postupdate";
    }

//    @PostMapping("/update")
//    public String updatePost(@ModelAttribute PostDTO post,
//                             HttpSession session,
//                             RedirectAttributes rttr) {
//        Object user_id = session.getAttribute("user_id");
//        if(user_id == null) {
//            rttr.addFlashAttribute("failMessage", "로그인을 먼저 해주세요.");
//            return "redirect:/user/login";
//        }
//        post.setUser_id((int) user_id);
//        try {
//            postService.updatePost(post);
//            rttr.addFlashAttribute("successMessage", "포스트가 성공적으로 업데이트되었습니다");
//        } catch (IllegalArgumentException e) {
//            rttr.addFlashAttribute("failMessage", "업데이트 권한이 없습니다.");
//        } catch (Exception e) {
//            rttr.addFlashAttribute("failMessage", "포스트 업데이트 중 오류가 발생했습니다.");
//        }
//        return "redirect:/post/all";
//    }
@PostMapping("/update")
public String updatePostSubmit(@ModelAttribute PostDTO post,
                               RedirectAttributes rttr) {
    // 하드코딩된 값 설정
    post.setUser_id(1);  // 사용자 ID를 하드코딩합니다.
    post.setPost_id(35); // post_id를 하드코딩합니다.

    try {
        postService.updatePost(post);
        rttr.addFlashAttribute("successMessage", "게시물이 성공적으로 업데이트되었습니다.");
    } catch (IllegalArgumentException e) {
        rttr.addFlashAttribute("failMessage", e.getMessage());
    }
    return "redirect:/post/all";
}

    @PostMapping("/delete")
    public String deletePost(@RequestParam("postId") int postId,
                             HttpSession session,
                             RedirectAttributes rttr) {
        Object user_id = session.getAttribute("user_id");
        if(user_id == null) {
            rttr.addFlashAttribute("failMessage", "로그인을 먼저 해주세요.");
            return "redirect:/user/login";
        }
        try {
            postService.deletePost(postId, (int) user_id);
            rttr.addFlashAttribute("successMessage", "포스트가 성공적으로 삭제되었습니다");
        } catch (IllegalArgumentException e) {
            rttr.addFlashAttribute("failMessage", "삭제 권한이 없습니다.");
        } catch (Exception e) {
            rttr.addFlashAttribute("failMessage", "포스트 삭제 중 오류가 발생했습니다.");
        }
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
