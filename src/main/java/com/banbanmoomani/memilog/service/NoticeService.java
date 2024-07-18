package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.NoticeMapper;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import com.banbanmoomani.memilog.DTO.admin.notice.NoticeRequestDTO;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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

    public void createNotice(NoticeRequestDTO noticeRequestDTO) {

        Date date = java.sql.Date.valueOf(LocalDate.now());

        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNotice_title(noticeRequestDTO.getTitle());
        noticeDTO.setNotice_content(noticeRequestDTO.getContent());
        noticeDTO.setAdmin_id(1);
        noticeDTO.setWritten_date(date);
        noticeDTO.setNotice_file_url("temp");
        noticeDTO.setVisible_YN("Y");
//        return noticeDTO;
        noticeMapper.createNotice(noticeDTO);
    }

}
