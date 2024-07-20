package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import com.banbanmoomani.memilog.DTO.user.ModifyRequestDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
import com.banbanmoomani.memilog.service.MydiaryService;
import com.banbanmoomani.memilog.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String mydiary(@RequestParam(value = "selectedDate", required = false) String selectedDate, Model model) {

        List<PostRequestDTO> postList = mydiaryService.findPosts(selectedDate);
        postList.forEach(System.out::println);

        UserProfileDTO user = mydiaryService.findUserById();
        System.out.println(user);

        model.addAttribute("posts", postList);
        model.addAttribute("user", user);

        return "main/mydiary";
    }

    @PostMapping("/selectedDate")
    public ResponseEntity<List<PostRequestDTO>> selectedDate(@RequestBody Map<String, String> payload) {
        String selectedDate = (String) payload.get("selectedDate");
        System.out.println("Selected Date: " + selectedDate);
        List<PostRequestDTO> posts = mydiaryService.findPosts(selectedDate);
        System.out.println(posts);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/editUserInfo")
    public ResponseEntity<UserDTO> editUserInfo(@ModelAttribute UserDTO user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
