package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDTO> findAllPost();

    void createPost(PostDTO post);

    List<PostDTO> findAllPostOnMissionByDate();

    List<PostDTO> findPostsByCompanion(@Param("companionIds") List<Integer> companionIds);
}
