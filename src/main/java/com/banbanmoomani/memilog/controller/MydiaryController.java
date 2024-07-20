package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import com.banbanmoomani.memilog.service.MydiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MydiaryController {

    private final MydiaryService mydiaryService;

    public MydiaryController(MydiaryService mydiaryService) {
        this.mydiaryService = mydiaryService;
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

}
