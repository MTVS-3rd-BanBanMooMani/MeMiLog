package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DAO.PostMapper;
import com.banbanmoomani.memilog.DTO.*;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryDTO;
import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.DTO.post.PostSearchCriteria;
import com.banbanmoomani.memilog.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final MissionService missionService;
    private final EmotionService emotionService;
    private final ComPanionService comPanionService;
    private final FileService fileService;
    private final PostMapper postMapper;
    private final ReportService reportService;
    private final RPTCategoryService rptCategoryService;
    private final FileStorageService fileStorageService;

    public PostController(PostService postService, MissionService missionService, EmotionService emotionService, ComPanionService comPanionService, FileService fileService, PostMapper postMapper, ReportService reportService, RPTCategoryService rptCategoryService, FileStorageService fileStorageService) {
        this.postService = postService;
        this.missionService = missionService;
        this.emotionService = emotionService;
        this.comPanionService = comPanionService;
        this.fileService = fileService;
        this.postMapper = postMapper;
        this.reportService = reportService;
        this.rptCategoryService = rptCategoryService;
        this.fileStorageService = fileStorageService;
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
                             Model model,
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

        int postId = createRequestDTO.getPostId();

        model.addAttribute("postId", postId);

        MultipartFile[] files = {file1, file2, file3, file4, file5};
        int pictureOrder = 1;
        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                String fileUrl = fileService.uploadFile(file, (int) user_id);
                fileService.saveFileUrl(fileUrl, "post", postId, (int) user_id, pictureOrder++);
            }
        }
        return "redirect:/post/bymission";
    }

    @GetMapping("/update")
    public String updatePost(@RequestParam(value = "postId", required = false) Integer postId,
                             Model model,
                             HttpSession session,
                             RedirectAttributes rttr) {
        Object user_id = session.getAttribute("user_id");
        PostDTO post = postService.findPostById(postId);
        System.out.println("post 1= " + post);

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
        for(UpdateFileDTO updateFileDTO : updateFileDTOList) {
            System.out.println("updateFileDTO.getP = " + updateFileDTO.getPicture_id() + " " + updateFileDTO.getPicture_order());
        }
        String mainFile = postService.findMainFile(postId);

        model.addAttribute("post", post);
        model.addAttribute("mission", mission);
        model.addAttribute("priThemeName", priThemeName);
        model.addAttribute("subThemeName", subThemeName);
        model.addAttribute("emotions", emotions);
        model.addAttribute("companions", companions);
        model.addAttribute("imageUrls", updateFileDTOList);
        System.out.println("updateFileDTOList = " + updateFileDTOList);
        model.addAttribute("mainImageUrl", mainFile);

        System.out.println("mainFile = " + mainFile);

        return "main/postupdate";
    }

    @GetMapping("/oldFile")
    public ResponseEntity oldFile(@RequestParam("postId") int postId) {
        List<FileDTO> fileListDTO = fileService.findAllByPostId(postId);
        System.out.println("fileListDTO = " + fileListDTO);
        return ResponseEntity.ok(fileListDTO);
    }


    @PostMapping("/update")
    public String updatePostSubmit(@ModelAttribute PostDTO post,
                                   @RequestParam(name = "oldFile1", required = false) Integer oldFile1,
                                   @RequestParam(name = "oldFile2", required = false) Integer oldFile2,
                                   @RequestParam(name = "oldFile3", required = false) Integer oldFile3,
                                   @RequestParam(name = "oldFile4", required = false) Integer oldFile4,
                                   @RequestParam(name = "oldFile5", required = false) Integer oldFile5,
                                   @RequestParam(name = "newFile1", required = false) MultipartFile newFile1,
                                   @RequestParam(name = "newFile2", required = false) MultipartFile newFile2,
                                   @RequestParam(name = "newFile3", required = false) MultipartFile newFile3,
                                   @RequestParam(name = "newFile4", required = false) MultipartFile newFile4,
                                   @RequestParam(name = "newFile5", required = false) MultipartFile newFile5,
                                   @RequestParam(name = "post_id") Integer post_id,
                                   @RequestParam(name = "imageOrder") String imageOrder, // 새로운 순서값 JSON
                                   HttpSession session,
                                   RedirectAttributes rttr) throws IOException {
        System.out.println("Received postId: " + post_id);
        System.out.println("Received oldFile1: " + oldFile1);
        System.out.println("Received newFile1: " + newFile1);
        System.out.println("imageOrder = " + imageOrder);

        Object user_id = session.getAttribute("user_id");
        if (user_id == null) {
            rttr.addFlashAttribute("failMessage", "사용자 정보가 없습니다.");
            return "redirect:/post/bymission";
        }

        Integer userId = (Integer) user_id;
        post.setUser_id(userId);

        Integer[] oldFiles = {oldFile1, oldFile2, oldFile3, oldFile4, oldFile5};
        MultipartFile[] newFiles = {newFile1, newFile2, newFile3, newFile4, newFile5};
        // 기존 파일 삭제 로직
        List<Integer> currentFileIds = postService.getFileIdsByPostId(post_id, userId);
        for (Integer fileId : currentFileIds) {
            if (!Arrays.asList(oldFiles).contains(fileId)) {
                postService.deleteFileById(fileId, userId);
            }
        }

        // 새로운 파일 추가 로직
        int existingFileCount = currentFileIds.size();
        String cleanedImageOrder = imageOrder.replaceAll("[\\[\\]\"]", "");
        String[] fileList = cleanedImageOrder.split(",");
        for (int i = 0; i < fileList.length; i++) {
            System.out.println("fileList = " + fileList[i]);
            if (fileList[i].startsWith("oldFile")) {
                String number = fileList[i].replaceAll("[^0-9]", "");
                int index = Integer.parseInt(number) - 1;

                postService.updateOldFile(oldFiles[index], i + 1);
            } else {
                String number = fileList[i].replaceAll("[^0-9]", "");
                int index = Integer.parseInt(number) - 1;

                String newFileUrl = fileStorageService.saveFile(newFiles[index], post_id);
                UpdateFileDTO newFileDTO = new UpdateFileDTO();
                newFileDTO.setSrc_url(newFileUrl);
                newFileDTO.setPost_id(post_id);
                newFileDTO.setPicture_order(i+1); // 기존 파일 수에 따른 순서 지정
                newFileDTO.setUser_id(userId); // 사용자 ID 설정
                System.out.println(userId);
                newFileDTO.setType("post"); // type 필드 설정
                System.out.println(post);
                postService.saveFile(newFileDTO);
            }
        }

        return "redirect:/post/bymission";
    }

    @GetMapping("/like")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> likePost(@RequestParam(name = "post_id") Long post_id, @SessionAttribute(name = "user_id") int user_id) {
        System.out.println("like post_id = " + post_id);
        postService.increaseLikeCount(post_id, user_id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/dislike")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> dislikePost(@RequestParam(name = "post_id") Long post_id, @SessionAttribute(name = "user_id") int user_id) {
        System.out.println("dislike post_id = " + post_id);
        postService.decreaseLikeCount(post_id, user_id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
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

    @PostMapping(value = "/bymission", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public PostRequestDTO showPostDetail(@RequestBody Map<String, Long> req,
                                         @SessionAttribute(name = "user_id") int user_id) {
        Long post_id = req.get("postId");
        System.out.println("post_id = " + post_id);

        PostRequestDTO postDetail = postService.showPostDetail(post_id);
        System.out.println("=============포스트 디테일=============");
        System.out.println(postDetail);

        String profile_img = fileService.getProfileUrl(post_id);
        System.out.println("========프로필 img===============");
        System.out.println("profile_img = " + profile_img);

        List<String> postUrl = fileService.getPostUrl(post_id);
        System.out.println("=======포스트 img=======");
        System.out.println("postUrl = " + postUrl);

        postDetail.setProfile_img(profile_img);
        postDetail.setPostUrl(postUrl);

        // 현재 user가 해당 post에 대해 LIKE 테이블에 정보가 들어있는지 확인 후
        // boolean 값을 postDetail에 넣어서 반환하기
        boolean likeInfo = postService.getLikeInfo(post_id, user_id);
        System.out.println("likeInfo = " + likeInfo);
        postDetail.setLikeInfo(likeInfo);

        return postDetail;

    }

    @GetMapping("/bymission")
    public String findAllPostOnMissionByDate(Model model,
                                             @RequestParam(name = "date",required = false)String date,
                                             @RequestParam(name = "type", required = false) String companionTypes) {

        System.out.println("date = " + date);
        System.out.println("companionTypes = " + companionTypes);

        List<PostRequestDTO> posts = new ArrayList<>();

        if (date != null) {

            MainTitleDTO mainTitleDTO = postService.showBanner(date);
            System.out.println("===============banner 정보");
            System.out.println("mainTitleDTO = " + mainTitleDTO);
            model.addAttribute("bannerInfo", mainTitleDTO);

            posts = postService.findAllPostOnMissionByDate(date);
            model.addAttribute("posts", posts);
            model.addAttribute("date", date);
            System.out.println("=======날짜별 post 조회");
            posts.forEach(System.out::println);

            // 날짜 형식 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("M월 d일의 미션");
            try {
                Date parsedDate = inputFormat.parse(date);
                String formattedDate = outputFormat.format(parsedDate);
                model.addAttribute("formattedDate", formattedDate);
            } catch (Exception e) {
                model.addAttribute("formattedDate", "Invalid date format");
            }

            if (companionTypes != null && !companionTypes.isEmpty()) {

                List<Integer> companionIds = Arrays.stream(companionTypes.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                PostSearchCriteria postSearchCriteria = new PostSearchCriteria(date, companionIds);

                posts = postService.findPostsByCompanion(postSearchCriteria);
                model.addAttribute("posts", posts);

                System.out.println("========누구와 필터 적용 결과");
                posts.forEach(System.out::println);
            }
        }

        List<RPTCategoryDTO> reportCategory = rptCategoryService.findAllCategorise();
        model.addAttribute("reportCategory", reportCategory);
        System.out.println("==========report 종류");
        reportCategory.forEach(System.out::println);

        return "main/postview";
    }


}