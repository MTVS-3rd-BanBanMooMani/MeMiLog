package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.FileMapper;
import com.banbanmoomani.memilog.DTO.FileDTO;
import com.banbanmoomani.memilog.DTO.UpdateFileDTO;
import com.banbanmoomani.memilog.DTO.MissionImgFileDTO;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@Service
public class FileService {

    @Value("${spring.cloud.gcp.storage.bucket}")

    private String bucketName;
    private final Storage storage;

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) throws IOException {
        this.fileMapper = fileMapper;
        this.storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("src/main/resources/memilog-4ed085e451cd.json")))
                .build()
                .getService();
    }

    public FileDTO updateFile(MultipartFile file, String type, Integer user_id) throws IOException {

        String oldFileUrl = fileMapper.getFileUrl(user_id, type);

        String newImageUrl = uploadFile(file, user_id);

        if (oldFileUrl != null && !oldFileUrl.isEmpty()) {
            String oldFileName = oldFileUrl.substring(oldFileUrl.lastIndexOf('/') + 1);
            deleteFile(oldFileName);
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setUser_id(user_id);
        fileDTO.setType(type);
        fileDTO.setSrc_url(newImageUrl);

        fileMapper.updateFile(fileDTO);

        return fileDTO;
    }

    public String uploadFile(MultipartFile file, Integer user_id) throws IOException {

        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null) {
            throw new IOException("파일이 존재하지 않습니다.");
        }

        String extension = file.getContentType();
        String fileName = user_id + "-" +System.currentTimeMillis() + "." + extension;

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                        .setContentType(extension)
                        .build();

        storage.create(blobInfo, file.getInputStream());

        return "https://storage.googleapis.com/" + bucketName + "/" + fileName;
    }

    public void saveFileUrl(String srcUrl, String type, int postId, int userId, int pictureOrder) {
        FileDTO fileDTO = new FileDTO(srcUrl, type, postId, userId, pictureOrder);
        fileMapper.insertFile(fileDTO);
    }

    public void deleteFile(String fileName) {
        Blob blob = storage.get(bucketName, fileName);
        if (blob != null) {
            blob.delete();
        }
    }

    public void deleteFileUrl(FileDTO fileDTO) {
        int user_id = fileDTO.getUser_id();
        String type = fileDTO.getType();

        String fileUrl = fileMapper.getFileUrl(user_id, type);

        if (fileUrl != null && !fileUrl.isEmpty()) {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
            deleteFile(fileName);
            fileMapper.deleteFileUrl(fileDTO);
        }
    }

    public void insertMissionImage(MissionImgFileDTO missionImgFileDTO) {
        fileMapper.insertMissionImage(missionImgFileDTO);
    }

    public void updateMissionImage(MissionImgFileDTO missionImgFileDTO) {
        fileMapper.updateMissionImage(missionImgFileDTO);
    }

    public List<FileDTO> findAllByPostId(int postId) {
        return fileMapper.findAllByPostId(postId);
    }
//    public void updateOrSaveFiles(List<UpdateFileDTO> files, Integer postId) {
//        for (UpdateFileDTO file : files) {
//            if (file.getPictureId() != null) {
//                fileMapper.updateFileUrl(file);
//            }else {
//                f
//            }
//        }
//    }
}

