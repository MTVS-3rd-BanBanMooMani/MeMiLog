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

    @PostMapping("/upload")
    public ResponseEntity<?> uploadProfilePic(@RequestParam("profilePic") MultipartFile file, @RequestParam("type") String type, HttpSession session) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일이 존재하지 않습니다.");
        }
        try {
            Integer user_id = (Integer) session.getAttribute("user_id");
            if (user_id == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
            }

            FileDTO fileDTO = fileService.updateFile(file, type, user_id);

            return ResponseEntity.ok(fileDTO);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장에 실패했습니다." + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러가 발생했습니다." + e.getMessage());
        }
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
        return ResponseEntity.ok("프로필 사진이 삭제되었습니다.");
    }
}

