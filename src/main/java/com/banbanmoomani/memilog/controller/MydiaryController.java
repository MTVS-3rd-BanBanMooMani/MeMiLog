package com.banbanmoomani.memilog.controller;

import com.banbanmoomani.memilog.DTO.IntegratedDTO;
import com.banbanmoomani.memilog.service.MydiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MydiaryController {

    private final MydiaryService mydiaryService;

    public MydiaryController(MydiaryService mydiaryService) {
        this.mydiaryService = mydiaryService;
    }

    @GetMapping("/mydiary")
    public String mydiary(Model model) {
//        model.addAttribute();
        List<IntegratedDTO> contentsList =  mydiaryService.findAllContents();
        System.out.println(contentsList);
        return "main/mydiary";
    }

}