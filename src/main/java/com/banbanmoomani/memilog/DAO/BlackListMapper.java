package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlackListMapper {

    AdminDTO.getBlackListDTO getBlackListDTO();
}
