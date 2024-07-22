package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.CompanionMapper;
import com.banbanmoomani.memilog.DTO.CompanionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComPanionService {
    private final CompanionMapper companionMapper;

    public ComPanionService(CompanionMapper companionMapper) {
        this.companionMapper = companionMapper;
    }
    public CompanionDTO findCompanionById(int companionId) {
        return companionMapper.findCompanionById(companionId);
    }
    public List<CompanionDTO> findAllCompanions() {
        return companionMapper.findAllCompanions();
    }
}
