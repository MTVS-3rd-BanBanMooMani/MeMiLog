package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> findAllNotice(@Param("content") String content, RowBounds rowBounds);
    void createNotice(NoticeDTO noticeDTO);

    int countNotices(@Param("content") String content);
}
