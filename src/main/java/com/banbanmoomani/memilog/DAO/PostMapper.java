package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.DTO.archivePostDTO;
import com.banbanmoomani.memilog.DTO.todayPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> findAllPost();

    void createPost(CreateRequestDTO post);

    void updatePost(PostDTO post);

    PostDTO findPostById(int postId);

    List<PostDTO> findAllPostOnMissionByDate();

    List<PostDTO> findPostsByCompanion(@Param("companionIds") List<Integer> companionIds);

    void deletePostById(int postId);

    List<todayPostDTO> findTodayPost();

    List<archivePostDTO> findArchivePost();

    Integer findTodayPostCount();
}
