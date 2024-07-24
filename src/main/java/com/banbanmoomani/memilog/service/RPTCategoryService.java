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
    public void deleteRPTCategory(List<Integer> rptList) {
        rptList.forEach(rptCategoryMapper::deleteRPTCategory);
    }

    public void updateRPTCategory(RPTCategoryDTO rptCategoryDTO) {
        rptCategoryMapper.updateRPTCategory(rptCategoryDTO);
    }

    public RPTCategoryDTO findRPTWeightById(int rptCategoryId) {
        return rptCategoryMapper.findRPTWeightById(rptCategoryId);
    }
}
