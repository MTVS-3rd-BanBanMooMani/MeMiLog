package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> findAllPost();

    void createPost(PostDTO post);
}
