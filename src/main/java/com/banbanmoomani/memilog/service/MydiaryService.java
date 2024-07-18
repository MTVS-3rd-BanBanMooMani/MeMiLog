package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DTO.IntegratedDTO;
import com.banbanmoomani.memilog.DAO.MydiaryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MydiaryService {
    private final MydiaryMapper MydiaryMapper;

    public MydiaryService(com.banbanmoomani.memilog.DAO.MydiaryMapper mydiaryMapper) {
        this.MydiaryMapper = mydiaryMapper;
    }

    public List<IntegratedDTO> findAllContents() {
        return MydiaryMapper.findAllContents();
    }
}