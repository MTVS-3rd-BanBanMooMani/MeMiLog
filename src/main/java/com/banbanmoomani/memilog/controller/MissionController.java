package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.service.MissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
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


    // ==================================연습용
    @GetMapping("/mission")
    public String findAllMission(Model model) {

        List<MissionDTO> missionList = missionService.findAllMission();
        model.addAttribute("missionList", missionList);

        return "main/allview";
    }

    @GetMapping("/test")
    public String allMission() {
        return "/main/test";
    }

    @GetMapping(value = "/main/allMission", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MissionDTO> findAllMission() {
        return missionService.findAllMission();
    }

    @GetMapping("/findmission")
    public String findTemaAllMission(Model model) {

        List<MissionDTO> temaMissionList = missionService.findTemaMission();
        model.addAttribute("temaMissionList", temaMissionList);

        return "main/test2";
    }

    // 오늘 mission에 해당하는 post 보기
}
