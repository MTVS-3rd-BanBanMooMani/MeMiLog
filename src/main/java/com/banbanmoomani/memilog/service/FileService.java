package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.FileMapper;
import com.banbanmoomani.memilog.DTO.FileDTO;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class FileService {

    @Value("${spring.cloud.gcp.storage.bucket}")

    private String bucketName;
    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public FileDTO handleFileUpload(MultipartFile file, String type, Integer user_id) throws IOException {

        System.out.println("******프사 바꾸기 서비스 요청 들옴******");

        String oldFileUrl = fileMapper.getFileUrl(user_id, type);

        String newImageUrl = uploadFile(file);

        if (oldFileUrl != null && !oldFileUrl.isEmpty()) {
            String oldFileName = oldFileUrl.substring(oldFileUrl.lastIndexOf('/') + 1);
            deleteFile(oldFileName);
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setUser_id(user_id);
        fileDTO.setType(type);
        fileDTO.setSrc_url(newImageUrl);

        fileMapper.updateFile(fileDTO);
        System.out.println("서비스-매퍼 파일 수정 완.");

        return fileDTO;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        String uuid = UUID.randomUUID().toString();
        String extension = file.getContentType();

        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(extension)
                        .build(),
                        file.getInputStream()
        );

        URL newImageUrl = storage.signUrl(blobInfo, 365, TimeUnit.DAYS, Storage.SignUrlOption.httpMethod(HttpMethod.POST));

        System.out.println("************newImageUrl************: " + newImageUrl);
        return newImageUrl.toString();
    }

    public void deleteFile(String fileName) {
        Blob blob = storage.get(bucketName, fileName);
        if (blob != null) {
            blob.delete();
        }
    }
}

