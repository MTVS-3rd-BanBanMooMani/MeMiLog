package com.banbanmoomani.memilog.DTO.admin.dashboard;

public class MissionViewDataDTO {
    private int missionId;
    private String missionContent;
    private String missionDate;
    private String priThemeName;
    private String subThemeName;

    public MissionViewDataDTO() {
    }

    public MissionViewDataDTO(int missionId, String missionContent, String missionDate, String priThemeName, String subThemeName) {
        this.missionId = missionId;
        this.missionContent = missionContent;
        this.missionDate = missionDate;
        this.priThemeName = priThemeName;
        this.subThemeName = subThemeName;
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

    public String getPriThemeName() {
        return priThemeName;
    }

    public void setPriThemeName(String priThemeName) {
        this.priThemeName = priThemeName;
    }

    public String getSubThemeName() {
        return subThemeName;
    }

    public void setSubThemeName(String subThemeName) {
        this.subThemeName = subThemeName;
    }
}
