package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.NoticeMapper;
import com.banbanmoomani.memilog.DTO.NoticeDTO;
import com.banbanmoomani.memilog.DTO.PageResult;
import com.banbanmoomani.memilog.DTO.admin.notice.NoticeRequestDTO;
import com.banbanmoomani.memilog.DTO.admin.notice.NoticeUpdateRequestDTO;
import org.apache.ibatis.session.RowBounds;
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

    public PageResult<NoticeDTO> findAllNotice(int pageNum, int pageSize, String content) {
        RowBounds rowBounds = new RowBounds((pageNum - 1) * pageSize, pageSize);
        List<NoticeDTO> noticeList = noticeMapper.findAllNotice(content, rowBounds);
        int total = noticeMapper.countNotices(content);
        return new PageResult<>(noticeList, total);
    }

    public void createNotice(NoticeRequestDTO noticeRequestDTO, int adminId) {

        Date date = java.sql.Date.valueOf(LocalDate.now());

        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNotice_title(noticeRequestDTO.getTitle());
        noticeDTO.setNotice_content(noticeRequestDTO.getContent());
        noticeDTO.setAdmin_id(adminId);
        noticeDTO.setWritten_date(date);
        noticeDTO.setNotice_file_url("temp");
        noticeDTO.setVisible_YN("Y");

        noticeMapper.createNotice(noticeDTO);
    }

    public void updateNotice(NoticeUpdateRequestDTO noticeUpdateRequestDTO) {
        noticeMapper.updateNotice(noticeUpdateRequestDTO);
    }
}
