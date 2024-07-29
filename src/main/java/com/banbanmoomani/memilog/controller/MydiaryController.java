package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.FileDTO;
import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
import com.banbanmoomani.memilog.service.FileService;
import com.banbanmoomani.memilog.service.MydiaryService;
import com.banbanmoomani.memilog.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MydiaryController {

    private final MydiaryService mydiaryService;
    private final UserService userService;
    private final FileService fileService;

    public MydiaryController(MydiaryService mydiaryService, UserService userService, FileService fileService) {
        this.mydiaryService = mydiaryService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/mydiary")
    public String mydiary(HttpSession session, Model model) throws JsonProcessingException {

        int user_id = (Integer) session.getAttribute("user_id");
        System.out.println("user_id: " + user_id);

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);

        List<PostRequestDTO> postList = mydiaryService.findPosts(params);

        UserProfileDTO user = mydiaryService.findUserInfoById(params);

        ObjectMapper objectMapper = new ObjectMapper();
        String postsJson = objectMapper.writeValueAsString(postList);

        model.addAttribute("postsJson", postsJson);
        model.addAttribute("user", user);
        return "main/mydiary";
    }

    @PostMapping("/selectedDate")
    public ResponseEntity<List<PostRequestDTO>> selectedDate(@RequestBody Map<String, String> payload, HttpSession session) {
        int user_id = (Integer) session.getAttribute("user_id");
        String selectedDate = (String) payload.get("selectedDate");

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("selectedDate", selectedDate);

        System.out.println("Selected Date: " + selectedDate);
        List<PostRequestDTO> posts = mydiaryService.findPosts(params);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/editUserInfo")
    public String editUserInfo(@ModelAttribute UserDTO user) {
        try {
            userService.updateUser(user);
            UserDTO userDTO = userService.findByEmail(user.getEmail());
            System.out.println("userDTO = " + userDTO);
            return "redirect:/mydiary";
        } catch (Exception e) {
            return "fail";
        }
    }

    @PostMapping("/upload")
    public RedirectView uploadProfilePic(@RequestParam("profilePic") MultipartFile file, @RequestParam("type") String type, HttpSession session) {
        if (file.isEmpty()) {
            System.out.println("파일이 존재하지 않습니다.");
            return new RedirectView("/errorPage"); // 파일이 없을 때 처리
        }
        try {
            Integer user_id = (Integer) session.getAttribute("user_id");
            if (user_id == null) {
                System.out.println("로그인이 필요합니다.");
                return new RedirectView("/loginPage"); // 로그인 필요 처리
            }

            // 업로드 시도 전 디버깅 로그
            System.out.println("파일 업로드 시도: " + file.getOriginalFilename());

            FileDTO fileDTO = fileService.updateFile(file, type, user_id);

            // 업로드 성공 후 디버깅 로그
            System.out.println("파일 업로드 성공: ");
            return new RedirectView("/mydiary");

        } catch (IOException e) {
            System.out.println("파일 저장에 실패했습니다. 예외: " + e.getMessage());
            return new RedirectView("/errorPage"); // 파일 저장 실패 처리
        } catch (Exception e) {
            System.out.println("서버 에러가 발생했습니다. 예외: " + e.getMessage());
            return new RedirectView("/errorPage"); // 기타 서버 에러 처리
        }
    }

}
