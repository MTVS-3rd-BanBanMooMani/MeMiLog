package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MissionMapper;
import com.banbanmoomani.memilog.DTO.MissionDTO;
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

    public List<MissionDTO> missionDetailByDate() { return missionMapper.missionDetailByDate(); }

    public void createMission(MissionDTO missionDTO) {
        missionMapper.createMission(missionDTO);
    }
}
