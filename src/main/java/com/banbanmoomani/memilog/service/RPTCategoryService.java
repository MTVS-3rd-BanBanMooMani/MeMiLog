package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.RPTCategoryMapper;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryDTO;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RPTCategoryService {
    private final RPTCategoryMapper rptCategoryMapper;

    public RPTCategoryService(RPTCategoryMapper rptCategoryMapper) {
        this.rptCategoryMapper = rptCategoryMapper;
    }

    public List<RPTCategoryDTO> findAllCategorise() {
        return rptCategoryMapper.findAllRPTCategory();
    }

    public void createRPTCategory(RPTCategoryRequestDTO rptCategoryRequestDTO) {
        rptCategoryMapper.createRPTCategory(rptCategoryRequestDTO);
    }


}
