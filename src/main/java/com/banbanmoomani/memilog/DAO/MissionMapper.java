package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.MainTitleDTO;
import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.MissionSearhCriteria;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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

    List<MissionDTO> findAllMissionPaging(String content, RowBounds rowBounds);

    int countMissions(String content);
    void updateMission(MissionDTO missionDTO);

    MissionDTO findMissionByDate(String missionDate);

    String getMissionTitle();

    List<MissionDTO> findMissionsByWord(String wordTypes);

    List<MissionDTO> findMissionByCriteria(MissionSearhCriteria missionSearhCriteria);

    MainTitleDTO getMainTitle();
}