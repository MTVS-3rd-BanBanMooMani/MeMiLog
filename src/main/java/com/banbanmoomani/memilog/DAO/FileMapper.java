package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {
    String getFileUrl(@Param("user_id") Integer user_id, @Param("type") String type);
    void updateFile(FileDTO fileDTO);
}

