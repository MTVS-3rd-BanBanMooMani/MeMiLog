package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.PostDTO;
import com.banbanmoomani.memilog.DTO.UserDTO;
import com.banbanmoomani.memilog.service.MydiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MydiaryController {

    private final MydiaryService mydiaryService;

    public MydiaryController(MydiaryService mydiaryService) {
        this.mydiaryService = mydiaryService;
    }

    @GetMapping("/mydiary")
    public String mydiary(Model model) {
        List<PostDTO> postList =  mydiaryService.findAllPosts();
        System.out.println(postList);
        UserDTO user = mydiaryService.findUserById();
        System.out.println(user);
        model.addAttribute("postList", postList);
        model.addAttribute("user", user);
        return "main/mydiary";
    }

}
