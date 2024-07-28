package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.MissionSearhCriteria;
import com.banbanmoomani.memilog.service.MissionService;
import com.banbanmoomani.memilog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
        System.out.println("=======모든미션");
        return "main/allview";
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
//    @GetMapping("/allmission")
//    public String showAllMission(Model model, @RequestParam(name = "post_id",required = false)String post_id) {
//        if(post_id != null) {
//            System.out.println(post_id);
//            // mission 내용 조회
//            // post
//        }
//        List<MissionDTO> missions = missionService.findAllMission();
//        model.addAttribute("missions", missions);
//
//        System.out.println("==============모든 미션");
//        missions.forEach(System.out::println);
//        return "main/themeTest";
//    }

    @GetMapping( "/theme")
    @ResponseBody
    public ResponseEntity<?> findMissionsByTheme(@RequestParam(name = "word", required = false) String word,
                                                 @RequestParam(name = "type", required = false) String type,
                                                 Model model) {

        System.out.println("keyword = " + word);
        System.out.println("themeTypes = " + type);

        List<Integer> themeIds = new ArrayList<>();

        if (type != null && !type.isEmpty()) {
            themeIds = Arrays.stream(type.split(","))
                    .map(Integer::parseInt)
                    .toList();
        }

        MissionSearhCriteria missionSearhCriteria = new MissionSearhCriteria(word, themeIds);

        List<MissionDTO> missions;

        missions = missionService.findMissionsBySearchCriteria(missionSearhCriteria);
//        model.addAttribute("missions", missions);

        System.out.println("=======미션 조회=======");
        missions.forEach(System.out::println);

        return  ResponseEntity.ok(missions);
    }

    @GetMapping("/allview")
    public String allView(@RequestParam(required = false, name = "word") String word, Model model) {
        model.addAttribute("word", word);
        return "main/allview"; // "allview"는 "allview.html" 템플릿 파일을 의미
    }
}
