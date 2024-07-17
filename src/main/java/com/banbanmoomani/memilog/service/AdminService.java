package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.BlackListMapper;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final BlackListMapper blackListMapper;

    public AdminService(BlackListMapper blackListMapper) {
        this.blackListMapper = blackListMapper;
    }

    public List<BanListDTO> getBanListDTO() {
        return null;
    }

    public List<BlackListDTO> getBlackListDTO() {
        return null;
    }
}
