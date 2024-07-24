package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MydiaryMapper {
    UserProfileDTO findUserInfoById(HashMap<String, Object> params);
    List<PostRequestDTO> findPosts(HashMap<String, Object> params);
}