package com.banbanmoomani.memilog.DTO.admin.report;

public class RPTCategoryDTO {
    private String category_name;

    public RPTCategoryDTO() {
    }

    public RPTCategoryDTO(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
