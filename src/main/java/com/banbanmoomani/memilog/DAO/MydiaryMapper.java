package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.IntegratedDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MydiaryMapper {
    List<IntegratedDTO> findAllContents();
}