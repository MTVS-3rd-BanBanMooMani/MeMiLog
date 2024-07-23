package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.MissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MissionMapper {
    MissionDTO findMissionByContent(String content);

    List<MissionDTO> findAllMission();

    List<MissionDTO> findMissionsByTheme(@Param("themeIds") List<Integer> themeIds);

//    List<MissionDTO> missionDetailByDate();

    void createMission(MissionDTO missionDTO);
    List<MissionDTO> missionDetailByDate();

    MissionDTO findTodayMission(String today);

    MissionDTO findMissionById(int missionId);

    String findPriThemeName(int priThemeId);

    String findSubThemeName(int subThemeId);
}
