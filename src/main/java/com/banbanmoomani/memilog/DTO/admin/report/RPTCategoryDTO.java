package com.banbanmoomani.memilog.DTO.admin.report;

public class RPTCategoryDTO {
    private int rpt_category_id;
    private String category_name;
    private int rpt_weight;
    private int stop_day;

    public RPTCategoryDTO() {
    }

    public RPTCategoryDTO(int rpt_category_id, String category_name, int rpt_weight, int stop_day) {
        this.rpt_category_id = rpt_category_id;
        this.category_name = category_name;
        this.rpt_weight = rpt_weight;
        this.stop_day = stop_day;
    }

    public int getRpt_category_id() {
        return rpt_category_id;
    }

    public void setRpt_category_id(int rpt_category_id) {
        this.rpt_category_id = rpt_category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getRpt_weight() {
        return rpt_weight;
    }

    public void setRpt_weight(int rpt_weight) {
        this.rpt_weight = rpt_weight;
    }

    public int getStop_day() {
        return stop_day;
    }

    public void setStop_day(int stop_day) {
        this.stop_day = stop_day;
    }
}
