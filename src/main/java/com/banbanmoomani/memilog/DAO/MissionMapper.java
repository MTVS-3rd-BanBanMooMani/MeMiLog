package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MissionMapper {
    List<MissionDTO> findAllMission();

    List<MissionDTO> findTemaMission();

    List<MissionDTO> missionDetailByDate();
}
