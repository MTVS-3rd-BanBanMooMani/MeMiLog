package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.report.RPTCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RPTCategoryMapper {
    List<RPTCategoryDTO> findAllRPTCategory();
}
