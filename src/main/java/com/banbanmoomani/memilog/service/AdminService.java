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
    public void processReport(List<String> postIdList, int adminId) {

        reportBoardMapper.hidePosts(postIdList);
        reportBoardMapper.updateUserBans(postIdList);
        int suspDaysNum = 7;
        reportBoardMapper.insertStopRecord(postIdList, adminId, suspDaysNum);
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

        // 오늘과 어제의 사용자 총 수 차이 비율 계산
        int userTotalDiffPercent = calculatePercentageDiff(todayMeMiLogInfoDTO.getUserTotalCount(), yesterdayMeMiLogInfoDTO.getUserTotalCount());
        System.out.println(userTotalDiffPercent);

        // 오늘과 어제의 사용자 수 차이 비율 계산
        int userDiffPercent = calculatePercentageDiff(todayMeMiLogInfoDTO.getUserCount(), yesterdayMeMiLogInfoDTO.getUserCount());
        System.out.println(userDiffPercent);

        // 오늘과 어제의 게시물 수 차이 비율 계산
        int postDiffPercent = calculatePercentageDiff(todayMeMiLogInfoDTO.getPostCount(), yesterdayMeMiLogInfoDTO.getPostCount());
        System.out.println(postDiffPercent);

        return new MeMiLogInfoDiff(userTotalDiffPercent, userDiffPercent, postDiffPercent);
    }

    private int calculatePercentageDiff(int todayCount, int yesterdayCount) {
        if (yesterdayCount == 0) {
            return 0; // 어제의 수치가 0일 경우, 변화율을 0으로 설정
        }
        return (int) Math.round(((double)(todayCount - yesterdayCount) / yesterdayCount) * 100);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void saveTodayMeMiLogInfo() {

        int todayUserTotalCount = meMiLogInfoMapper.getTodayUserTotalCount();
        int todayUserCount = meMiLogInfoMapper.getTodayUserCount();
        int todayPostCount = meMiLogInfoMapper.getTodayPostCount();

        MeMiLogInfoDTO meMiLogInfoDTO = new MeMiLogInfoDTO(LocalDate.now(), todayUserTotalCount, todayUserCount, todayPostCount);
        meMiLogInfoMapper.saveTodayMeMiLogInfoDTO(meMiLogInfoDTO);

        blackListMapper.releaseToday();
    }

    public MeMiLogInfoDTO getTodayMeMiLogInfo() {
        int todayUserTotalCount = meMiLogInfoMapper.getTodayUserTotalCount();
        int todayUserCount = meMiLogInfoMapper.getTodayUserCount();
        int todayPostCount = meMiLogInfoMapper.getTodayPostCount();

        return new MeMiLogInfoDTO(LocalDate.now(), todayUserTotalCount, todayUserCount, todayPostCount);
    }
}
