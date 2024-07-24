package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> findAllPost();

    void createPost(CreateRequestDTO post);

    void updatePost(PostDTO post);

    PostDTO findPostById(int postId);

    List<PostRequestDTO> findAllPostOnMissionByDate();

    List<PostRequestDTO> findPostsByCompanion(@Param("companionIds") List<Integer> companionIds);

    void deletePostById(int postId);

    PostRequestDTO showPostDetail(Long postId);
}
