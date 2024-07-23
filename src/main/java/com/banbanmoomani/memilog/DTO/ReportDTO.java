package com.banbanmoomani.memilog.DTO;

import java.util.Date;
import java.sql.Timestamp;

public class ReportDTO {
    private int rpt_id;
    private int rpt_category_id;
    private String rpt_content;
    private int post_id;
    private int rpter_user_id;
    private int rpted_user_id;
    private Date rpt_datetime;

    public ReportDTO() {}

    public ReportDTO(int rpt_category_id, String rpt_content, int post_id, int rpter_user_id, int rpted_user_id, Date rpt_datetime) {
        this.rpt_category_id = rpt_category_id;
        this.rpt_content = rpt_content;
        this.post_id = post_id;
        this.rpter_user_id = rpter_user_id;
        this.rpted_user_id = rpted_user_id;
        this.rpt_datetime = rpt_datetime;
    }

    public int getRpt_id() {
        return rpt_id;
    }

    public void setRpt_id(int rpt_id) {
        this.rpt_id = rpt_id;
    }

    public int getRpt_category_id() {
        return rpt_category_id;
    }

    public void setRpt_category_id(int rpt_category_id) {
        this.rpt_category_id = rpt_category_id;
    }

    public String getRpt_content() {
        return rpt_content;
    }

    public void setRpt_content(String rpt_content) {
        this.rpt_content = rpt_content;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getRpter_user_id() {
        return rpter_user_id;
    }

    public void setRpter_user_id(int rpter_user_id) {
        this.rpter_user_id = rpter_user_id;
    }

    public int getRpted_user_id() {
        return rpted_user_id;
    }

    public void setRpted_user_id(int rpted_user_id) {
        this.rpted_user_id = rpted_user_id;
    }

    public Date getRpt_datetime() {
        return rpt_datetime;
    }

    public void setRpt_datetime(Date rpt_datetime) {
        this.rpt_datetime = rpt_datetime;
    }
}
