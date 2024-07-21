package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.AdminMapper;
import com.banbanmoomani.memilog.DAO.BlackListMapper;
import com.banbanmoomani.memilog.DAO.DashBoardMapper;
import com.banbanmoomani.memilog.DAO.ReportBoardMapper;
import com.banbanmoomani.memilog.DTO.admin.AdminDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import com.banbanmoomani.memilog.DTO.admin.dashboard.*;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.processedPostListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.unProcessedPostListDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final AdminMapper adminMapper;
    private final BlackListMapper blackListMapper;
    private final ReportBoardMapper reportBoardMapper;
    private final DashBoardMapper dashBoardMapper;

    public AdminService(AdminMapper adminMapper, BlackListMapper blackListMapper, ReportBoardMapper reportBoardMapper, DashBoardMapper dashBoardMapper) {
        this.adminMapper = adminMapper;
        this.blackListMapper = blackListMapper;
        this.reportBoardMapper = reportBoardMapper;
        this.dashBoardMapper = dashBoardMapper;
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

    public List<RPTCategoryListDTO> getRPTCategoryDTOList() {
        return reportBoardMapper.getRPTCategoryDTOList();
    }

    @Transactional
    public void processReport(List<String> postIdList) {
        reportBoardMapper.processReport(postIdList);
    }

    public List<MeMiLogInfoDTO> getMeMiLogInfo() {
        return dashBoardMapper.getMeMiLogInfo();
    }

    public List<AgeGroupMemberDTO> getAgeGroupMembers() {
        return dashBoardMapper.findAgeGroupMembers();
    }

    public List<ReportedPostDTO> getTodayReportedPosts() {
        return dashBoardMapper.findTodayReportedPosts();
    }

    public int getTodayReportCount() {
        return dashBoardMapper.getTodayReportCount();
    }

    public AdminDTO findAdminByEmail(String email) {
        return adminMapper.findAdminByEmail(email);
    }
}
