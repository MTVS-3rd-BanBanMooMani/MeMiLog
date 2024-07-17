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

        // STOP DTO

        // 각 STOP DTO 로 USER DTO 찾기

        // USER DTO BanListDTO 전환

        return null;
    }

    public List<BlackListDTO> getBlackListDTO() {
        return null;
    }
}
