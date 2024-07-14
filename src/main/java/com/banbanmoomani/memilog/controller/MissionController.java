package com.banbanmoomani.memilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MissionController {
    // 모든 mission 보기 & 검색 결과 mission 보기
    @GetMapping("/mission")
    public String allMission(Model model,
                             @RequestParam(required = false, name = "word") String word,
                             @RequestParam(required = false, name = "today") boolean today) {
        if(word != null && !word.isEmpty()) { // 검색한 주제에 대해서 mission들을 보여주는 검색
            // List<String> missions = new ArrayList<>();
            model.addAttribute("word", word); // 검색 결과 input에 넣기 위함
        }
        if(today) { // 오늘 올라온 post들에 대한 간편 겁색
            // List<String> missions = new ArrayList<>();
        }
        return "main/allview";
    }

    // 오늘 mission에 해당하는 post 보기
}
