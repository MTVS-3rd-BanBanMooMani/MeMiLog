package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.PostDTO;
import com.banbanmoomani.memilog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        postService.createPost(postDTO);

        return "redirect:/post/all";
    }

    @GetMapping("/all")
    public String viewAllPost(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "main/allview";
    }

    // 오늘 mission에 해당하는 post 보기
//    @GetMapping("/bymission")
//    public String findAllPostOnMissionByDate(Model model) {
//
//        List<PostDTO> posts = postService.findAllPostOnMissionByDate();
//        model.addAttribute("posts", posts);
//        posts.forEach(System.out::println);
//        return "main/postview";
//    }


//    ============================ 연습

    // 오늘 mission에 해당하는 post 보기
    @GetMapping("/bymission")
    public String findAllPostOnMissionByDate(Model model) {

        List<PostDTO> posts = postService.findAllPostOnMissionByDate();
        model.addAttribute("post", posts);

        posts.forEach(System.out::println);
        return "main/cateTest";
    }

    // 누구와 카테고리 필터 적용
    @GetMapping("/companion")
    public String findPostsByCompanion(@RequestParam("type") String companionTypes, Model model) {

        System.out.println("companionTypes = " + companionTypes);

        List<PostDTO> posts;

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

        model.addAttribute("post", posts);

        posts.forEach(System.out::println);

        return "main/cateTest";

    }

}
