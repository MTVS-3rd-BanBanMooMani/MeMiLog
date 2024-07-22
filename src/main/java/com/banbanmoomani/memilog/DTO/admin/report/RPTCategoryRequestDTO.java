package com.banbanmoomani.memilog.DTO.admin.report;

public class RPTCategoryRequestDTO {
    private String categoryName;
    private int rptWeight;
    private int stopDay;

    public RPTCategoryRequestDTO(String categoryName, int rptWeight, int stopDay) {
        this.categoryName = categoryName;
        this.rptWeight = rptWeight;
        this.stopDay = stopDay;
    }

    public RPTCategoryRequestDTO() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getRptWeight() {
        return rptWeight;
    }

    public void setRptWeight(int rptWeight) {
        this.rptWeight = rptWeight;
    }

    public int getStopDay() {
        return stopDay;
    }

    public void setStopDay(int stopDay) {
        this.stopDay = stopDay;
    }
}
