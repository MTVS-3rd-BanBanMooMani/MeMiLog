package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DTO.IntegratedDTO;
import com.banbanmoomani.memilog.DAO.IntegratedMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MydiaryService {
    private final IntegratedMapper integratedMapper;

    public MydiaryService(IntegratedMapper integratedMapper) {
        this.integratedMapper = integratedMapper;
    }

    public List<IntegratedDTO> findAllContents() {
        return integratedMapper.findAllContents();
    }
}