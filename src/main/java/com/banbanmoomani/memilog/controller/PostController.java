package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DAO.PostMapper;
import com.banbanmoomani.memilog.DTO.CompanionDTO;
import com.banbanmoomani.memilog.DTO.EmotionDTO;
import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.UpdateFileDTO;
import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final MissionService missionService;
    private final EmotionService emotionService;
    private final ComPanionService comPanionService;
    private final FileService fileService;
    private final PostMapper postMapper;

    public PostController(PostService postService, MissionService missionService, EmotionService emotionService, ComPanionService comPanionService, FileService fileService, PostMapper postMapper) {
        this.postService = postService;
        this.missionService = missionService;
        this.emotionService = emotionService;
        this.comPanionService = comPanionService;
        this.fileService = fileService;
        this.postMapper = postMapper;
    }

    @GetMapping("/create")
    public String createPost(Model model, HttpSession session, @SessionAttribute(name = "user_id", required = false) String userId, RedirectAttributes rttr) {
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
                             @RequestParam(name = "file1", required = true) MultipartFile file1,
                             @RequestParam(name = "file2", required = false) MultipartFile file2,
                             @RequestParam(name = "file3", required = false) MultipartFile file3,
                             @RequestParam(name = "file4", required = false) MultipartFile file4,
                             @RequestParam(name = "file5", required = false) MultipartFile file5,
                             HttpSession session) throws IOException {
        System.out.println(createRequestDTO);

        Object user_id = session.getAttribute("user_id");

        createRequestDTO.setUser_id((int) user_id);
        EmotionDTO emotion = emotionService.findEmotionById(createRequestDTO.getEmotion_id());
        System.out.println(emotion);
        CompanionDTO companion = comPanionService.findCompanionById(createRequestDTO.getCompanion_id());
        System.out.println(companion);
        createRequestDTO.setEmotion_id(emotion.getEmotionId());
        createRequestDTO.setCompanion_id(companion.getCompanion_id());

        postService.createPost(createRequestDTO);

        MultipartFile[] files = {file1, file2, file3, file4, file5};
        int pictureOrder = 1;
        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                String fileUrl = fileService.uploadFile(file, (int) user_id);
                fileService.saveFileUrl(fileUrl, "post", createRequestDTO.getPostId(), (int) user_id, pictureOrder++);
            }
        }

        return "redirect:/post/bymission";
    }

    @GetMapping("/update")
    public String updatePost(@RequestParam("postId") int postId,
                             Model model,
                             HttpSession session,
                             RedirectAttributes rttr) {
        Object user_id = session.getAttribute("user_id");
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
        List<UpdateFileDTO> updateFileDTOList = postService.updatefiles(postId);
        String mainFile = postService.findMainFile(postId);

        model.addAttribute("post", post);
        model.addAttribute("mission", mission);
        model.addAttribute("priThemeName", priThemeName);
        model.addAttribute("subThemeName", subThemeName);
        model.addAttribute("emotions", emotions);
        model.addAttribute("companions", companions);
        model.addAttribute("imageUrls", updateFileDTOList);
        model.addAttribute("mainImageUrl", mainFile);

        return "main/postupdate";
    }

    @PostMapping("/update")
    public String updatePostSubmit(@ModelAttribute PostDTO post,
                                   HttpSession session,
                                   RedirectAttributes rttr) {
        Object user_id = session.getAttribute("user_id");
        post.setUser_id((int) user_id);
        try {
            postService.updatePost(post);
            rttr.addFlashAttribute("successMessage", "게시물이 성공적으로 업데이트되었습니다.");
        } catch (IllegalArgumentException e) {
            rttr.addFlashAttribute("failMessage", e.getMessage());
        }
        return "redirect:/post/bymission";
    }

    @GetMapping("like")
    @ResponseBody
    public ResponseEntity likePost(@RequestParam(name = "post_id") int post_id, @SessionAttribute(name = "user_id") int user_id) {
        postService.increaseLikeCount(post_id, user_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("dislike")
    @ResponseBody
    public ResponseEntity dislikePost(@RequestParam(name = "post_id") int post_id, @SessionAttribute(name = "user_id") int user_id) {
        postService.decreaseLikeCount(post_id, user_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam("postId") int postId,
                             HttpSession session,
                             RedirectAttributes rttr) {
        Object user_id = session.getAttribute("user_id");
        System.out.println("postId = " + postId);

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
        List<PostDTO> post = postService.findAllPosts();
        System.out.println("============all post");
        post.forEach(System.out::println);
        model.addAttribute("posts", postService.findAllPosts());
        return "main/allview";
    }

    // 오늘 mission에 해당하는 post 보기
    @GetMapping("/bymission")
    public String findAllPostOnMissionByDate(Model model) {

        List<PostRequestDTO> posts = postService.findAllPostOnMissionByDate();

        model.addAttribute("posts", posts);

        System.out.println("findAllPostOnMissionByDate");
        posts.forEach(System.out::println);

//        @RequestParam(name = "post_id", required = false) int post_id
//        List<PostDTO> postDetail = postService.showPostDetail(post_id);
//        System.out.println("=======포스트 디테일=============");
//        System.out.println(postDetail);
//        System.out.println("post_id = " + post_id);

        return "main/postview";
    }

    @PostMapping(value = "/bymission", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public PostRequestDTO showPostDetail(@RequestBody Map<String, Long> req) {
        Long post_id = req.get("postId");
        System.out.println("post_id = " + post_id);

        PostRequestDTO postDetail = postService.showPostDetail(post_id);
        System.out.println("=======포스트 디테일=============");
        System.out.println(postDetail);


        return postDetail;

    }


//    ============================ 연습

    // 오늘 mission에 해당하는 post 보기
//    @GetMapping("/bymission")
//    public String findAllPostOnMissionByDate(Model model) {
//
//        List<PostDTO> posts = postService.findAllPostOnMissionByDate();
//        model.addAttribute("post", posts);
//
//        System.out.println("====================post");
//        posts.forEach(System.out::println);
//
//        return "main/cateTest";
//    }

    // 누구와 카테고리 필터 적용
    @GetMapping("/companion")
    public String findPostsByCompanion(@RequestParam("type") String companionTypes, Model model) {

        System.out.println("companionTypes = " + companionTypes);

        List<PostRequestDTO> posts;

        if (companionTypes != null && !companionTypes.isEmpty()) {
            List<Integer> companionIds = Arrays.stream(companionTypes.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            posts = postService.findPostsByCompanion(companionIds);
        } else {
            // 선택한 타입이 없는 경우에는 어떤 걸로 할지
            posts = postService.findAllPostOnMissionByDate();
//            return "redirect:/post/bymission";
        }

        model.addAttribute("posts", posts);

        posts.forEach(System.out::println);

        return "main/postview";

    }

}
