package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> findAllNotice();
}
