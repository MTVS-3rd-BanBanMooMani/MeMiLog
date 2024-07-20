package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MydiaryMapper;
import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MydiaryService {
    private final MydiaryMapper mydiaryMapper;

    public MydiaryService(MydiaryMapper mydiaryMapper) {
        this.mydiaryMapper = mydiaryMapper;
    }

    public UserProfileDTO findUserById() {
        return mydiaryMapper.findUserById();
    }

    public List<PostRequestDTO> findPosts(String selectedDate) {
        return mydiaryMapper.findPosts(selectedDate);
    }
}