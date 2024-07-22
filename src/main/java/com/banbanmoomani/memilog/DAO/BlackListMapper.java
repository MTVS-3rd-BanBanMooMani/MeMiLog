package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.blacklist.BanListDTO;
import com.banbanmoomani.memilog.DTO.admin.blacklist.BlackListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlackListMapper {

    List<BanListDTO> getBanList();

    List<BlackListDTO> getBlackList();

    void blackUser(@Param("userIdList") List<String> userIdList);

    void releaseUser(@Param("userIdList") List<String> userIdList);
}
