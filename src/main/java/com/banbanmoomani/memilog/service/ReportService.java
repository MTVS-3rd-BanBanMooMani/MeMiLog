package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.ReportMapper;
import com.banbanmoomani.memilog.DTO.ReportDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {
    private final ReportMapper reportMapper;

    public ReportService(ReportMapper reportMapper) { this.reportMapper = reportMapper; }

    @Transactional
    public void createReport(ReportDTO reportDTO) {
        reportMapper.createReport(reportDTO);
    }
}
