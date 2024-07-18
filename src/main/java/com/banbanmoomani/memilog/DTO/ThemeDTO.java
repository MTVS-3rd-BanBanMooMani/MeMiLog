package com.banbanmoomani.memilog.DTO;

public class ThemeDTO {
    private int themeId;
    private String themeName;

    private ThemeDTO(){}

    public ThemeDTO(int themeId, String themeName) {
        this.themeId = themeId;
        this.themeName = themeName;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return "ThemeDTO{" +
                "themeId=" + themeId +
                ", themeName='" + themeName + '\'' +
                '}';
    }
}
