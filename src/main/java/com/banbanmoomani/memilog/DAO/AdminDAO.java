package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.AdminDTO;
import org.apache.ibatis.session.SqlSession;

public class AdminDAO {

    public AdminDTO.getBlackListDTO getBlackList(SqlSession sqlSession) {
        return null;//sqlSession.selectList("BlackListMapper.getBlackList");
    }
}
