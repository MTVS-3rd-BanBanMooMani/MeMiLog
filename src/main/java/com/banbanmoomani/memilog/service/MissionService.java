package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MissionMapper;
import com.banbanmoomani.memilog.DTO.MissionDTO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MissionService {

    private final MissionMapper missionMapper;

    public MissionService(MissionMapper missionMapper) {
        this.missionMapper = missionMapper;
    }

    public MissionDTO findMissionByContent(String content) {
        return missionMapper.findMissionByContent(content);
    }

    public List<MissionDTO> findAllMission() {
        return missionMapper.findAllMission();
    }

    public List<MissionDTO> findTemaMission() {
        return missionMapper.findTemaMission();
    }

    public List<MissionDTO> missionDetailByDate() { return missionMapper.missionDetailByDate(); }

    public MissionDTO findTodayMission() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());
        System.out.println(today);
        MissionDTO missionDTO = missionMapper.findTodayMission(today);

        System.out.println("service : " + missionDTO);
        return missionMapper.findTodayMission(today);
    }

    public String findPriThemeName(int priThemeId) {
        return missionMapper.findPriThemeName(priThemeId);
    }
    public String findSubThemeName(int subThemeId) {
        return missionMapper.findSubThemeName(subThemeId);
    }
    public MissionDTO findMissionById(int missionId) {
        return missionMapper.findMissionById(missionId);
    }

}
