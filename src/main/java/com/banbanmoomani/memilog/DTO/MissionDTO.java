package com.banbanmoomani.memilog.DTO;

public class MissionDTO {
    private int missionId;
    private String missionContent;
    private String missionDate;
    private int priThemeId;
    private int subThemeId;

    public MissionDTO(){}

    public MissionDTO(int missionId, String missionContent, String missionDate, int priThemeId, int subThemeId) {
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

    public int getPriThemeId() {
        return priThemeId;
    }

    public void setPriThemeId(int priThemeId) {
        this.priThemeId = priThemeId;
    }

    public int getSubThemeId() {
        return subThemeId;
    }

    public void setSubThemeId(int subThemeId) {
        this.subThemeId = subThemeId;
    }

    @Override
    public String toString() {
        return "MissionDTO{" +
                "missionId=" + missionId +
                ", missionContent='" + missionContent + '\'' +
                ", missionDate='" + missionDate + '\'' +
                ", priThemeId=" + priThemeId +
                ", subThemeId=" + subThemeId +
                '}';
    }
}
