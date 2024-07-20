package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryDTO;

import java.util.List;

public interface RPTCategoryMapper {
    List<RPTCategoryDTO> findAllRPTCategory();
}
