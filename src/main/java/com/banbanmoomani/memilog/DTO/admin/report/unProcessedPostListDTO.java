package com.banbanmoomani.memilog.DTO.admin.report;

import java.util.Date;

public class unProcessedPostListDTO {

    private int post_id;
    private String reported_user_nickName;
    private String post_content;
    private String report_content;
    private int report_count;
    private String rpt_category;
    private Date report_datetime;

    public unProcessedPostListDTO() {
    }

    public unProcessedPostListDTO(int post_id, String reported_user_nickName, String post_content, String report_content, int report_count, String rpt_category, Date report_datetime) {
        this.post_id = post_id;
        this.reported_user_nickName = reported_user_nickName;
        this.post_content = post_content;
        this.report_content = report_content;
        this.report_count = report_count;
        this.rpt_category = rpt_category;
        this.report_datetime = report_datetime;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getReported_user_nickName() {
        return reported_user_nickName;
    }

    public String getPost_content() {
        return post_content;
    }

    public String getReport_content() {
        return report_content;
    }

    public int getReport_count() {
        return report_count;
    }

    public String getRpt_category() {
        return rpt_category;
    }

    public Date getReport_datetime() {
        return report_datetime;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setReported_user_nickName(String reported_user_nickName) {
        this.reported_user_nickName = reported_user_nickName;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    public void setReport_count(int report_count) {
        this.report_count = report_count;
    }

    public void setRpt_category(String rpt_category) {
        this.rpt_category = rpt_category;
    }

    public void setReport_datetime(Date report_datetime) {
        this.report_datetime = report_datetime;
    }
}
