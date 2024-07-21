package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.AttachmentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachmentMapper {
    void createAttachment(AttachmentDTO attachmentDTO);
    void updateProfilePic(AttachmentDTO attachment);
}
