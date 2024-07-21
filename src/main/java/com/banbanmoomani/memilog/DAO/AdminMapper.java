package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.AdminDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    AdminDTO findAdminByEmail(@Param("email") String email);
}
