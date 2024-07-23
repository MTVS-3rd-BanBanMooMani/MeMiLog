package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
    void createReport(ReportDTO reportDTO);
}
