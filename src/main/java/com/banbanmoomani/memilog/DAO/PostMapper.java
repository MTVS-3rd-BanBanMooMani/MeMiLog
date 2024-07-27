package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.UpdateFileDTO;
import com.banbanmoomani.memilog.DTO.*;
import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.post.CreateRequestDTO;
import com.banbanmoomani.memilog.DTO.post.PostDTO;
import com.banbanmoomani.memilog.DTO.post.PostSearchCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> findAllPost();

    void createPost(CreateRequestDTO post);

    void updatePost(PostDTO post);

    PostDTO findPostById(int postId);

    List<PostRequestDTO> findAllPostOnMissionByDate(String date);

    List<PostRequestDTO> findPostsByCompanion(PostSearchCriteria postSearchCriteria);

    PostRequestDTO showPostDetail(Long postId);

    void deletePostById(int postId);

    void increaseLikeCount(@Param("post_id") Long post_id);

    void decreaseLikeCount(@Param("post_id") Long post_id);

    List<todayPostDTO> findTodayPost();

    List<archivePostDTO> findArchivePost();

    Integer findTodayPostCount();

    void updateHidden();

    List<UpdateFileDTO> updateFile(@Param("postId") int postId);

    String findMainFile(@Param("postId") int postId);


    MainTitleDTO showBanner(@Param("date") String date);

    int getPostUser(@Param("post_id") Long postId, @Param("user_id") int user_id);
}