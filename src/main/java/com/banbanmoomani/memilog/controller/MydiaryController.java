package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
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

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MydiaryController {

    private final MydiaryService mydiaryService;
    private final UserService userService;

    public MydiaryController(MydiaryService mydiaryService, UserService userService) {
        this.mydiaryService = mydiaryService;
        this.userService = userService;
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
    public String editUserInfo(@ModelAttribute UserDTO user, Model model) {
        try {
            userService.updateUser(user);
            model.addAttribute("user", user);
            return "main/mydiary :: #userInfo"; // 업데이트된 사용자 정보 부분만 반환
        } catch (Exception e) {
            model.addAttribute("error", "회원 정보 수정에 실패하였습니다.");
            return "main/mydiary :: #errorMessage"; // 에러 메시지 부분만 반환
        }
    }
}
