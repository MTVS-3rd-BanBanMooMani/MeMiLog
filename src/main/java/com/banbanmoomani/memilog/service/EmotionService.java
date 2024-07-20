package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.EmotionMapper;
import com.banbanmoomani.memilog.DTO.EmotionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmotionService {
    private final EmotionMapper emotionMapper;

    public EmotionService(EmotionMapper emotionMapper) {
        this.emotionMapper = emotionMapper;
    }
    public EmotionDTO findEmotionByName(int id) {
        return emotionMapper.findEmotionById(id);
    }
    public List<EmotionDTO> findAllEmotions() {
        return emotionMapper.findAllEmotions();
    }
}
