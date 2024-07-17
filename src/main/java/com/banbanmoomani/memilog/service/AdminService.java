package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.BlackListMapper;
import com.banbanmoomani.memilog.DTO.AdminDTO;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final BlackListMapper blackListMapper;

    public AdminService(BlackListMapper blackListMapper) {
        this.blackListMapper = blackListMapper;
    }

    public AdminDTO.getBlackListDTO getBlackListDTO() {
        return blackListMapper.getBlackListDTO();
    }
}
