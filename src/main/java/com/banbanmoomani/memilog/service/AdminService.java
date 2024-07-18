package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.BlackListMapper;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.processedPostListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.unProcessedPostListDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final BlackListMapper blackListMapper;

    public AdminService(BlackListMapper blackListMapper) {
        this.blackListMapper = blackListMapper;
    }

    public List<BanListDTO> getBanListDTO() {
        return blackListMapper.getBanList();
    }

    public List<BlackListDTO> getBlackListDTO() {
        return blackListMapper.getBlackList();
    }

    @Transactional
    public void blackUser(List<String> userIdList) {
        blackListMapper.blackUser(userIdList);
    }

    @Transactional
    public void releaseUser(List<String> userIdList) {
        blackListMapper.releaseUser(userIdList);
    }

    public List<unProcessedPostListDTO> getUnProcessedPostList() {
        return null;
    }

    public List<processedPostListDTO> getProcessedPostList() {
        return null;
    }
}
