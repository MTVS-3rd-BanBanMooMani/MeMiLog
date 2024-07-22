package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.*;
import com.banbanmoomani.memilog.DTO.admin.AdminDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import com.banbanmoomani.memilog.DTO.admin.dashboard.*;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.processedPostListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.unProcessedPostListDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {

    private final AdminMapper adminMapper;
    private final BlackListMapper blackListMapper;
    private final ReportBoardMapper reportBoardMapper;
    private final DashBoardMapper dashBoardMapper;
    private final MeMiLogInfoMapper meMiLogInfoMapper;

    public AdminService(AdminMapper adminMapper, BlackListMapper blackListMapper, ReportBoardMapper reportBoardMapper, DashBoardMapper dashBoardMapper, MeMiLogInfoMapper meMiLogInfoMapper) {
        this.adminMapper = adminMapper;
        this.blackListMapper = blackListMapper;
        this.reportBoardMapper = reportBoardMapper;
        this.dashBoardMapper = dashBoardMapper;
        this.meMiLogInfoMapper = meMiLogInfoMapper;
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

    public AdminDTO findAdminById(int adminId) {
        return adminMapper.findAdminById(adminId);
    }

    public MeMiLogInfoDiff calculateMeMiLogInfoDiff(MeMiLogInfoDTO todayMeMiLogInfoDTO, MeMiLogInfoDTO yesterdayMeMiLogInfoDTO) {

        // Ensure there is no division by zero
        int userTotalDiffPercent = yesterdayMeMiLogInfoDTO.getUserTotalCount() != 0
                ? (int)(((double)(todayMeMiLogInfoDTO.getUserTotalCount() - yesterdayMeMiLogInfoDTO.getUserTotalCount()) / yesterdayMeMiLogInfoDTO.getUserTotalCount()) * 100)
                : 0;

        int userDiffPercent = yesterdayMeMiLogInfoDTO.getUserCount() != 0
                ? (int)(((double)(todayMeMiLogInfoDTO.getUserCount() - yesterdayMeMiLogInfoDTO.getUserCount()) / yesterdayMeMiLogInfoDTO.getUserCount()) * 100)
                : 0;

        int postDiffPercent = yesterdayMeMiLogInfoDTO.getPostCount() != 0
                ? (int)(((double)(todayMeMiLogInfoDTO.getPostCount() - yesterdayMeMiLogInfoDTO.getPostCount()) / yesterdayMeMiLogInfoDTO.getPostCount()) * 100)
                : 0;

        return new MeMiLogInfoDiff(userTotalDiffPercent, userDiffPercent, postDiffPercent);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void saveTodayMeMiLogInfo() {

        int todayUserTotalCount = meMiLogInfoMapper.getTodayUserTotalCount();
        int todayUserCount = meMiLogInfoMapper.getTodayUserCount();
        int todayPostCount = meMiLogInfoMapper.getTodayPostCount();

        MeMiLogInfoDTO meMiLogInfoDTO = new MeMiLogInfoDTO(LocalDate.now(), todayUserTotalCount, todayUserCount, todayPostCount);
        meMiLogInfoMapper.saveTodayMeMiLogInfoDTO(meMiLogInfoDTO);
    }
}
