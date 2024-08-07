package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryDTO;
import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RPTCategoryMapper {
    List<RPTCategoryDTO> findAllRPTCategory();
    void createRPTCategory(RPTCategoryRequestDTO rptCategoryRequestDTO);
    void deleteRPTCategory(int rpt_category_id);
    void updateRPTCategory(RPTCategoryDTO rptCategoryDTO);

    RPTCategoryDTO findRPTWeightById(int rptCategoryId);
}
