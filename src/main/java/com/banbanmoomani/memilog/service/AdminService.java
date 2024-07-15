package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.AdminDAO;
import com.banbanmoomani.memilog.DTO.AdminDTO;

public class AdminService {

    private final AdminDAO adminDAO;

    public AdminService() {
        adminDAO = new AdminDAO();
    }

    public AdminDTO.getBlackListDTO getBlackListDTO() {
        return null;
    }
}
