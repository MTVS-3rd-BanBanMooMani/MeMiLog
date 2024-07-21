package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.CompanionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanionMapper {
    CompanionDTO findCompanionById(int companionId);

    List<CompanionDTO> findAllCompanions();
}
