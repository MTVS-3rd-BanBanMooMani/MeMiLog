package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MissionMapper;
import com.banbanmoomani.memilog.DAO.ThemeMapper;
import com.banbanmoomani.memilog.DTO.MissionDTO;
import com.banbanmoomani.memilog.DTO.MissionSearhCriteria;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import com.banbanmoomani.memilog.DTO.PageResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MissionService {

    private final MissionMapper missionMapper;

    public MissionService(MissionMapper missionMapper, ThemeMapper themeMapper) {
        this.missionMapper = missionMapper;
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
    public String getMissionTitle() {
        return missionMapper.getMissionTitle();
    }
    // mission 전체조회 paging

    public PageResult<MissionDTO> findAllMissionPaging(int pageNum, int pageSize, String content) {
        RowBounds rowBounds = new RowBounds((pageNum - 1) * pageSize, pageSize);
        List<MissionDTO> missionList = missionMapper.findAllMissionPaging(content, rowBounds);
        int total = missionMapper.countMissions(content);
        return new PageResult<>(missionList, total);
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

    public void updateMission(MissionDTO missionDTO) {
        missionMapper.updateMission(missionDTO);
    }
}
