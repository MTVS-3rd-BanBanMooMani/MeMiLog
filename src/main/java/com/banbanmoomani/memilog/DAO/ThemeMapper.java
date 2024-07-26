package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.ThemeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ThemeMapper {

    List<ThemeDTO> findAllTheme();

    String findThemeById(int id);

    ThemeDTO findThemeByName(@Param("name") String themeName);
}
