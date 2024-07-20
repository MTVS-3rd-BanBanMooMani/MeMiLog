package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MissionMapper;
import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.admin.daily.DailyMissionRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private final MissionMapper missionMapper;

    public MissionService(MissionMapper missionMapper) {
        this.missionMapper = missionMapper;
    }

    public List<MissionDTO> findAllMission() {
        return missionMapper.findAllMission();
    }

    public List<MissionDTO> findTemaMission() {
        return missionMapper.findTemaMission();
    }

//    public List<MissionDTO> missionDetailByDate() { return missionMapper.missionDetailByDate(); }

    public void createMission(DailyMissionRequestDTO dailyMissionRequestDTO) {
        String priThemeId = dailyMissionRequestDTO.getPriThemeId();
        String subThemeId = dailyMissionRequestDTO.getSubThemeId();
        MissionDTO missionDTO = new MissionDTO();
        missionDTO.setMissionId(dailyMissionRequestDTO.getMissionId());
        missionDTO.setMissionContent(dailyMissionRequestDTO.getMissionContent());
        missionDTO.setMissionDate(dailyMissionRequestDTO.getMissionDate());
        // 테마 아이디 적용 필요
        missionDTO.setPriThemeId(0);
        missionDTO.setSubThemeId(1);
        missionMapper.createMission(missionDTO);
    }
}
