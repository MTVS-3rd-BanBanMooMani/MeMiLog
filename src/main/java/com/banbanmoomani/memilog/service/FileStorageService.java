package com.banbanmoomani.memilog.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    public String saveFile(MultipartFile file, Integer postId) throws IOException {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String fileName = postId + "-" + System.currentTimeMillis() + "." + fileExtension;
        BlobId blobId = BlobId.of("memilog", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getInputStream());
        return "https://storage.googleapis.com/memilog/" + fileName;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
