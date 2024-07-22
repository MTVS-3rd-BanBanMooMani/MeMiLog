package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MissionMapper {
    List<MissionDTO> findAllMission();

    List<MissionDTO> findMissionsByTheme(@Param("themeIds") List<Integer> themeIds);

//    List<MissionDTO> missionDetailByDate();
}
