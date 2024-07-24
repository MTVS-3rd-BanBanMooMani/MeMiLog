package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.processedPostListDTO;
import com.banbanmoomani.memilog.DTO.admin.report.unProcessedPostListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportBoardMapper {
    List<unProcessedPostListDTO> getUnProcessedPostList();

    List<processedPostListDTO> getProcessedPostList();

    List<RPTCategoryListDTO> getRPTCategoryDTOList();

    void hidePosts(@Param("postIdList") List<String> postIdList);

    void updateUserBans(@Param("postIdList") List<String> postIdList);

    void insertStopRecord(@Param("postIdList") List<String> postIdList, @Param("adminId") int adminId);
}
