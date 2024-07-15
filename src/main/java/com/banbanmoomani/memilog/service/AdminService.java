package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.AdminDAO;
import com.banbanmoomani.memilog.DTO.AdminDTO;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

import static com.banbanmoomani.memilog.config.Template.getSqlSession;

public class AdminService {

    private final AdminDAO adminDAO;

    public AdminService() {
        adminDAO = new AdminDAO();
    }

    public AdminDTO.getBlackListDTO getBlackListDTO() {

        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AdminDTO.getBlackListDTO getBlackListDTO = adminDAO.getBlackList(sqlSession);

        sqlSession.close();

        return null;
    }
}
