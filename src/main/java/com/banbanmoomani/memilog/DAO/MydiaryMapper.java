package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MydiaryMapper {
    UserProfileDTO findUserById();
    List<PostRequestDTO> findPosts(@Param("selectedDate") String selectedDate);
}