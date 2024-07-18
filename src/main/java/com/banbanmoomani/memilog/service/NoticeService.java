package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.NoticeMapper;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public List<NoticeDTO> findAllNotice() {
        return noticeMapper.findAllNotice();
    }

}
