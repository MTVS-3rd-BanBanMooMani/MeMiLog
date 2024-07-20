package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.dashboard.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashBoardMapper {

    List<MemberTrendDTO> findLast10DaysMemberTrends();

    List<VisitorTrendDTO> findLast10DaysVisitorTrends();

    List<PostTrendDTO> findLast10DaysPostTrends();

    List<AgeGroupMemberDTO> findAgeGroupMembers();

    List<ReportedPostDTO> findTodayReportedPosts();

    int getTodayReportCount();
}
