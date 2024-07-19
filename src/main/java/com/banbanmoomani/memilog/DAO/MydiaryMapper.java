package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MydiaryMapper {
    List<PostDTO> findAllPosts();
    UserDTO findUserById();
}