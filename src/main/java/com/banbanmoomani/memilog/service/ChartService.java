package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.ChartMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChartService {

    private final ChartMapper chartMapper;

    public ChartService(ChartMapper chartMapper) {
        this.chartMapper = chartMapper;
    }

    public Map<Integer, Integer> getEmotionCounts(Integer user_id, List<Integer> emotion_ids) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("emotionIds", emotion_ids);

        List<Map<String, Object>> results = chartMapper.getEmotionCounts(params);
        Map<Integer, Integer> emotionCounts = new HashMap<>();

        for (Map<String, Object> result : results) {
            Integer emotion_id = (Integer) result.get("emotion_id");
            Integer count = ((Long) result.get("count")).intValue();
            emotionCounts.put(emotion_id, count);
        }

        return emotionCounts;
    }
}
