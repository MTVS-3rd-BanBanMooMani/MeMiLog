package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MissionMapper;
import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import com.banbanmoomani.memilog.DTO.PageResult;
import com.banbanmoomani.memilog.DTO.admin.daily.DailyMissionRequestDTO;
import org.apache.ibatis.session.RowBounds;
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

    // mission 전체조회 paging
    public PageResult<MissionDTO> findAllMissionPaging(int pageNum, int pageSize) {
        RowBounds rowBounds = new RowBounds((pageNum - 1) * pageSize, pageSize);
        List<MissionDTO> missionList = missionMapper.findAllMissionPaging(rowBounds);
        int total = missionMapper.countMissions();
        return new PageResult<>(missionList, total);
    }
}
