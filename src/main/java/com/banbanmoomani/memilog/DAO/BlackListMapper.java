package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackListMapper {

    // TODO: perm_YN = 'N' 인 STOP 과 USER 를 JOIN 해서 데이터 수정 및 가져옴
    List<BanListDTO> getBanList();
}
