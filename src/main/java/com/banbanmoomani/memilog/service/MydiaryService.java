package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MydiaryMapper;
import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MydiaryService {
    private final MydiaryMapper mydiaryMapper;

    public MydiaryService(MydiaryMapper mydiaryMapper) {
        this.mydiaryMapper = mydiaryMapper;
    }

    public UserProfileDTO findUserInfoById(HashMap<String, Object> params) {
        return mydiaryMapper.findUserInfoById(params);
    }

    public List<PostRequestDTO> findPosts(HashMap<String, Object> params) {
        return mydiaryMapper.findPosts(params);
    }
}