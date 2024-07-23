package com.banbanmoomani.memilog.controller;


import com.banbanmoomani.memilog.service.ChartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ChartController {

    private final ChartService chartService;

    public ChartController(ChartService chartService){
        this.chartService = chartService;
    }

    @GetMapping("/emotions")
    public ResponseEntity<Map<Integer, Integer>> getEmotionCounts(
            HttpSession session,
            @RequestParam("emotion_ids") String emotion_ids) {

        Integer user_id = (Integer) session.getAttribute("user_id");
        if (user_id == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Integer> emotionIdList = Arrays.stream(emotion_ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Map<Integer, Integer> emotionCounts = chartService.getEmotionCounts(user_id, emotionIdList);
        return ResponseEntity.ok(emotionCounts);
    }


}
