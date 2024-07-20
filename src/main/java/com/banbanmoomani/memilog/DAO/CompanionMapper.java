package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.CompanionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanionMapper {
    CompanionDTO findCompanionById(int companionId);
}
