package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.FileDTO;
import com.banbanmoomani.memilog.service.FileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/resetPic")
    public ResponseEntity<?> deleteProfilePic(@RequestParam("type") String type, HttpSession session) {
        Integer user_id = (Integer) session.getAttribute("user_id");

        if (user_id == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("먼저 로그인을 해주세요!");
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setType(type);
        fileDTO.setUser_id(user_id);

        fileService.deleteFileUrl(fileDTO);
        return ResponseEntity.ok("사진이 삭제되었습니다.");
    }
}

