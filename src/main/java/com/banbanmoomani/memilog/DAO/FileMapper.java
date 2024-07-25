package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.FileDTO;
import com.banbanmoomani.memilog.DTO.MissionImgFileDTO;
import com.banbanmoomani.memilog.DTO.UpdateFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    String getFileUrl(@Param("user_id") Integer user_id, @Param("type") String type);
    void updateFile(FileDTO fileDTO);
    void deleteFileUrl(FileDTO fileDTO);
    void insertFile(FileDTO fileDTO);
    void insertMissionImage(MissionImgFileDTO missionImgFileDTO);
    void updateMissionImage(MissionImgFileDTO missionImgFileDTO);
    List<FileDTO> findAllByPostId(int postId);
    List<Integer> selectFileIdsByPostId(@Param("postId") int postId);

    void deleteFileById(@Param("fileId") int fileId);

    void getFile(UpdateFileDTO updateFileDTO);
}