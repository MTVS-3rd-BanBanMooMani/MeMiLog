package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.dashboard.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashBoardMapper {

    List<MeMiLogInfoDTO> getMeMiLogInfo();

    List<AgeGroupMemberDTO> findAgeGroupMembers();

    List<ReportedPostDTO> findTodayReportedPosts();

    int getTodayReportCount();
}
