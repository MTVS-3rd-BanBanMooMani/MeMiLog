package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.service.MissionService;
import com.banbanmoomani.memilog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MissionController {

    private final MissionService missionService;
    private final PostService postService;

    public MissionController(MissionService missionService, PostService postService) {
        this.missionService = missionService;
        this.postService = postService;
    }

    // 모든 mission 보기 & 검색 결과 mission 보기
//    @GetMapping("/mission")
//    public String allMission(Model model,
//                             @RequestParam(required = false, name = "word") String word,
//                             @RequestParam(required = false, name = "today") boolean today) {
//        if(word != null && !word.isEmpty()) { // 검색한 주제에 대해서 mission들을 보여주는 검색
//            // List<String> missions = new ArrayList<>();
//            model.addAttribute("word", word); // 검색 결과 input에 넣기 위함
//        }
//        if(today) { // 오늘 올라온 post들에 대한 간편 겁색
//            // List<String> missions = new ArrayList<>();
//        }
//        return "main/allview";
//    }

    // 모든 미션 보기
    @GetMapping("/mission")
    public String allMission() {
        return "/main/allview";
    }

    @GetMapping(value = "/main/allMission", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MissionDTO> findAllMission() {

        List<MissionDTO> missions = missionService.findAllMission();
        missions.forEach(System.out::println);

        return missions;
    }


    // ==================================연습용
    // 모든 미션 보기
    @GetMapping("/allmission")
    public String showAllMission(Model model, @RequestParam(name = "post_id",required = false)String post_id) {
        if(post_id != null) {
            System.out.println(post_id);
            // mission 내용 조회
            // post
        }
        List<MissionDTO> missions = missionService.findAllMission();
        model.addAttribute("missions", missions);

        missions.forEach(System.out::println);
        return "main/themeTest";
    }

    @GetMapping("/theme")
    public String findMissionsByTheme(@RequestParam("type") String themeTypes, Model model) {

        System.out.println("themeTypes = " + themeTypes);

        List<MissionDTO> missions;

        if (themeTypes != null && !themeTypes.isEmpty()) {
            List<Integer> themeIds = Arrays.stream(themeTypes.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            missions = missionService.findMissionsByTheme(themeIds);
        } else {
            missions = missionService.findAllMission();
        }

        model.addAttribute("missions", missions);

        missions.forEach(System.out::println);
        return "main/themeTest";
    }

}
