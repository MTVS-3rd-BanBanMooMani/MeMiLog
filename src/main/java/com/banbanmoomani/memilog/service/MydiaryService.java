package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.MydiaryMapper;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MydiaryService {
    private final MydiaryMapper mydiaryMapper;

    public MydiaryService(MydiaryMapper mydiaryMapper) {
        this.mydiaryMapper = mydiaryMapper;
    }

    public List<PostDTO> findAllPosts() {
        return mydiaryMapper.findAllPosts();
    }

    public UserDTO findUserById() {
        return mydiaryMapper.findUserById();
    }
}