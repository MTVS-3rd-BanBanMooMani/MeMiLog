package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.report.unProcessedPostListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportBoardMapper {
    List<unProcessedPostListDTO> getUnProcessedPostList();
}
