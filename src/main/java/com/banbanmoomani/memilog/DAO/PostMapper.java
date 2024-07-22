package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> findAllPost();

    void createPost(CreateRequestDTO post);

    void updatePost(PostDTO post);

    PostDTO findPostById(int postId);

    List<PostDTO> findAllPostOnMissionByDate();

    void deletePostById(int postId);
}
