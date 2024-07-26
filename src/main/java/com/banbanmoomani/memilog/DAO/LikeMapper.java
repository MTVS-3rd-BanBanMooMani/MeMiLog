package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.LikeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    void insertLikeInfo(LikeDTO likeDTO);

    void deleteLikeInfo(LikeDTO likeDTO);

    int getLikeInfo(LikeDTO likeDTO);

}
