package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.EmotionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmotionMapper {
    EmotionDTO findEmotionById(int id);

    List<EmotionDTO> findAllEmotions();
}
