package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.AttachmentMapper;
import com.banbanmoomani.memilog.DTO.AttachmentDTO;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService {
    private final AttachmentMapper attachmentMapper;

    public AttachmentService(AttachmentMapper attachmentMapper) {
        this.attachmentMapper = attachmentMapper;
    }

    public void createAttachment(AttachmentDTO attachmentDTO) {
        attachmentMapper.createAttachment(attachmentDTO);
    }
}
