package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.PostDTO;
import com.banbanmoomani.memilog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String createPost(@ModelAttribute PostDTO postDTO) {

        if (postDTO.getTitle() == null || postDTO.getTitle().isEmpty()) {
            postDTO.setTitle(postDTO.getMissionContent());
        }

        postDTO.setTogether(postDTO.getCompanionType());

        postService.createPost(postDTO);

        return "redirect:/post/all";
    }

    @GetMapping("/all")
    public String viewAllPost(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "main/allview";
    }
}
