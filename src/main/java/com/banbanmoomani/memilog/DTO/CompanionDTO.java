package com.banbanmoomani.memilog.DTO;

public class CompanionDTO {
    private int companionId;
    private String companionType;

    public CompanionDTO() {}

    public CompanionDTO(int companionId, String companionType) {
        this.companionId = companionId;
        this.companionType = companionType;
    }

    public int getCompanionId() {
        return companionId;
    }

    public void setCompanionId(int companionId) {
        this.companionId = companionId;
    }

    public String getCompanionType() {
        return companionType;
    }

    public void setCompanionType(String companionType) {
        this.companionType = companionType;
    }

    @Override
    public String toString() {
        return "CompanionDTO{" +
                "companionId=" + companionId +
                ", companionType='" + companionType + '\'' +
                '}';
    }
}
