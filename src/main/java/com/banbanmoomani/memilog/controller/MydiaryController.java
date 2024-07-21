package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.AttachmentDTO;
import com.banbanmoomani.memilog.DTO.mydiary.PostRequestDTO;
import com.banbanmoomani.memilog.DTO.mydiary.UserProfileDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
import com.banbanmoomani.memilog.service.MydiaryService;
import com.banbanmoomani.memilog.service.user.AttachmentService;
import com.banbanmoomani.memilog.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
public class MydiaryController {

    private final MydiaryService mydiaryService;
    private final UserService userService;
    private final AttachmentService attachmentService;

    private final String UPLOADED_FOLDER = "src/main/resources/static/document/img/";

    public MydiaryController(MydiaryService mydiaryService, UserService userService, AttachmentService attachmentService) {
        this.mydiaryService = mydiaryService;
        this.userService = userService;
        this.attachmentService = attachmentService;
    }

    @GetMapping("/mydiary")
    public String mydiary(@RequestParam(value = "selectedDate", required = false) String selectedDate, Model model) {

        List<PostRequestDTO> postList = mydiaryService.findPosts(selectedDate);
        postList.forEach(System.out::println);

        UserProfileDTO user = mydiaryService.findUserById();
        System.out.println(user);

        model.addAttribute("posts", postList);
        model.addAttribute("user", user);

        return "main/mydiary";
    }

    @PostMapping("/selectedDate")
    public ResponseEntity<List<PostRequestDTO>> selectedDate(@RequestBody Map<String, String> payload) {
        String selectedDate = (String) payload.get("selectedDate");
        System.out.println("Selected Date: " + selectedDate);
        List<PostRequestDTO> posts = mydiaryService.findPosts(selectedDate);
        System.out.println(posts);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/editUserInfo")
    public ResponseEntity<UserDTO> editUserInfo(@ModelAttribute UserDTO user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/uploadProfilePic")
    public ResponseEntity<?> uploadProfilePic(@RequestParam("profilePic") MultipartFile file, @RequestParam("type") String type) throws IOException {
        if(file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일이 존재하지 않습니다.");
        }
        try{
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            file.transferTo(path.toFile());
            System.out.println("Saved File");

            String newImageUrl = "/document/img/" + file.getOriginalFilename();
            AttachmentDTO attachment = new AttachmentDTO();
            attachment.setSrc_url(newImageUrl);
            attachment.setType(type);

            attachmentService.updateProfilePic(attachment);
            System.out.println("Database Updated");

            return ResponseEntity.ok(attachment);

        } catch (IOException e) {
            System.err.println("Error Occurred while saving profile pic");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장에 실패했습니다." + e.getMessage());
        } catch (Exception e) {
            System.err.println("Server Error Occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러가 발생했습니다." + e.getMessage());
        }
    }
}
