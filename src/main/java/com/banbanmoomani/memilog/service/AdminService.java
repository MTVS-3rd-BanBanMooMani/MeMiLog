package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.BlackListMapper;
import com.banbanmoomani.memilog.DAO.ReportBoardMapper;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryDTO;
import com.banbanmoomani.memilog.DTO.admin.report.processedPostListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.unProcessedPostListDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final BlackListMapper blackListMapper;
    private final ReportBoardMapper reportBoardMapper;

    public AdminService(BlackListMapper blackListMapper, ReportBoardMapper reportBoardMapper) {
        this.blackListMapper = blackListMapper;
        this.reportBoardMapper = reportBoardMapper;
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
        return reportBoardMapper.getUnProcessedPostList();
    }

    public List<processedPostListDTO> getProcessedPostList() {
        return reportBoardMapper.getProcessedPostList();
    }

    public List<RPTCategoryDTO> getRPTCategoryDTOList() {
        return reportBoardMapper.getRPTCategoryDTOList();
    }

    @Transactional
    public void processReport(List<String> postIdList) {
        reportBoardMapper.processReport(postIdList);
    }
}
