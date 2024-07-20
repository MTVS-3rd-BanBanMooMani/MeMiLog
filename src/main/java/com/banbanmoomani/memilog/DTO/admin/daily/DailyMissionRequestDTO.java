package com.banbanmoomani.memilog.DTO.admin.daily;

public class DailyMissionRequestDTO {

    private int missionId;
    private String missionContent;
    private String missionDate;
    private String priThemeId;
    private String subThemeId;

    public DailyMissionRequestDTO() {
    }

    public DailyMissionRequestDTO(int missionId, String missionContent, String missionDate, String priThemeId, String subThemeId) {
        this.missionId = missionId;
        this.missionContent = missionContent;
        this.missionDate = missionDate;
        this.priThemeId = priThemeId;
        this.subThemeId = subThemeId;
    }

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public String getMissionContent() {
        return missionContent;
    }

    public void setMissionContent(String missionContent) {
        this.missionContent = missionContent;
    }

    public String getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(String missionDate) {
        this.missionDate = missionDate;
    }

    public String getPriThemeId() {
        return priThemeId;
    }

    public void setPriThemeId(String priThemeId) {
        this.priThemeId = priThemeId;
    }

    public String getSubThemeId() {
        return subThemeId;
    }

    public void setSubThemeId(String subThemeId) {
        this.subThemeId = subThemeId;
    }
}
