package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    String getFileUrl(@Param("user_id") Integer user_id, @Param("type") String type);
    void updateFile(FileDTO fileDTO);
    void deleteFileUrl(FileDTO fileDTO);
    void insertFile(FileDTO fileDTO);
    List<FileDTO> findAllByPostId(int postId);
}