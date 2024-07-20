package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.CompanionMapper;
import com.banbanmoomani.memilog.DTO.CompanionDTO;
import org.springframework.stereotype.Service;

@Service
public class ComPanionService {
    private final CompanionMapper companionMapper;

    public ComPanionService(CompanionMapper companionMapper) {
        this.companionMapper = companionMapper;
    }
    public CompanionDTO findCompanionById(int companionId) {
        return companionMapper.findCompanionById(companionId);
    }
}
