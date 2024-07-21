package com.banbanmoomani.memilog.service.user;

import com.banbanmoomani.memilog.DAO.AttachmentMapper;
import com.banbanmoomani.memilog.DTO.AttachmentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttachmentService {

    private final AttachmentMapper attachmentMapper;

    public AttachmentService(AttachmentMapper attachmentMapper) {
        this.attachmentMapper = attachmentMapper;
    }

    @Transactional
    public void updateProfilePic(AttachmentDTO attachment) {
        attachmentMapper.updateProfilePic(attachment);
    }
}
