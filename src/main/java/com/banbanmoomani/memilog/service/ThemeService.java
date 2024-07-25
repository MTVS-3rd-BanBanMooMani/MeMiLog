package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.ThemeMapper;
import com.banbanmoomani.memilog.DTO.ThemeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
    private final ThemeMapper themeMapper;

    public ThemeService(ThemeMapper themeMapper) {
        this.themeMapper = themeMapper;
    }

    public List<ThemeDTO> getTheme() {
        return themeMapper.findAllTheme();
    }

    public String findById(int id) {
        return themeMapper.findThemeById(id);
    }
}
