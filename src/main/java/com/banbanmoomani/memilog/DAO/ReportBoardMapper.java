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

    void processReport(@Param("postIdList") List<String> postIdList);
}