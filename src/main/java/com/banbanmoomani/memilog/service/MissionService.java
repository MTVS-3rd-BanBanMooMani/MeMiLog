package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MissionMapper;
import com.banbanmoomani.memilog.DAO.ThemeMapper;
import com.banbanmoomani.memilog.DTO.*;
import com.banbanmoomani.memilog.DTO.admin.dashboard.MissionViewDataDTO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MissionService {

    private final MissionMapper missionMapper;
    private final ThemeService themeService;

    public MissionService(MissionMapper missionMapper, ThemeMapper themeMapper, ThemeService themeService) {
        this.missionMapper = missionMapper;
        this.themeService = themeService;
    }

    public MissionDTO findMissionByContent(String content) {
        return missionMapper.findMissionByContent(content);
    }

    public List<MissionDTO> findAllMission() {
        return missionMapper.findAllMission();
    }

    public List<MissionDTO> findMissionsByTheme(List<Integer> themeIds) {
        return missionMapper.findMissionsByTheme(themeIds);
    }

//    public List<MissionDTO> missionDetailByDate() { return missionMapper.missionDetailByDate(); }

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

    public List<MissionDTO> findMissionsByWord(String wordTypes) {
        return missionMapper.findMissionsByWord(wordTypes);
    }

    public List<MissionDTO> findMissionsBySearchCriteria(MissionSearhCriteria missionSearhCriteria) {
        return missionMapper.findMissionByCriteria(missionSearhCriteria);
    }
    // mission 전체조회 paging

    public PageResult<MissionViewDataDTO> findAllMissionPaging(int pageNum, int pageSize, String content) {
        RowBounds rowBounds = new RowBounds((pageNum - 1) * pageSize, pageSize);
        List<MissionDTO> missionList = missionMapper.findAllMissionPaging(content, rowBounds);
        List<MissionViewDataDTO> missionViewDataDTOS = new ArrayList<>();

        for (MissionDTO missionDTO : missionList) {
            MissionViewDataDTO missionViewDataDTO = new MissionViewDataDTO(
                    missionDTO.getMissionId(),
                    missionDTO.getMissionContent(),
                    missionDTO.getMissionDate(),
                    themeService.findById(missionDTO.getPriThemeId()),
                    themeService.findById(missionDTO.getSubThemeId()));

            missionViewDataDTOS.add(missionViewDataDTO);
        }
        int total = missionMapper.countMissions(content);
        return new PageResult<>(missionViewDataDTOS, total);
    }
    public void createMission(MissionDTO missionDTO) {
        String missionDate = missionDTO.getMissionDate();
        MissionDTO findMission = missionMapper.findMissionByDate(missionDate);
        if (findMission == null) {
            missionMapper.createMission(missionDTO);
        } else {
            throw new IllegalArgumentException("mission already exists");
        }
    }

    public void updateMission(MissionViewDataDTO missionViewDataDTO) {
        int priThemeId = themeService.findByName(missionViewDataDTO.getPriThemeName());
        int subThemeId = themeService.findByName(missionViewDataDTO.getSubThemeName());
        MissionDTO missionDTO = new MissionDTO();
        missionDTO.setMissionId(missionViewDataDTO.getMissionId());
        missionDTO.setMissionContent(missionViewDataDTO.getMissionContent());
        missionDTO.setMissionDate(missionViewDataDTO.getMissionDate());
        missionDTO.setPriThemeId(priThemeId);
        missionDTO.setSubThemeId(subThemeId);
        missionMapper.updateMission(missionDTO);
    }

    public MainTitleDTO getMainTitle() {
        return missionMapper.getMainTitle();
    }
}
