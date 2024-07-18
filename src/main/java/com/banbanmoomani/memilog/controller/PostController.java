package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.PostDTO;
import com.banbanmoomani.memilog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        List<PostDTO> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "main/postcreate";
    }

    @PostMapping("/create")
    public String createPost(PostDTO post) {
        postService.createPost(post);
        return "redirect:/post/all";
    }

    @GetMapping("/all")
    public String viewAllPost(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "main/allview";
    }
}