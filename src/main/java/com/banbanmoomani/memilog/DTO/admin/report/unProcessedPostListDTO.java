package com.banbanmoomani.memilog.DTO.admin.report;

import java.util.Date;

public class unProcessedPostListDTO {

    private int report_id;
    private String reported_user_nickName;
    private String content;
    private int reporter_user_id;
    private String rpt_category;
    private Date report_datetime;

    public unProcessedPostListDTO() {
    }

    public unProcessedPostListDTO(int report_id, String reported_user_nickName, String content, int reporter_user_id, String rpt_category, Date report_datetime) {
        this.report_id = report_id;
        this.reported_user_nickName = reported_user_nickName;
        this.content = content;
        this.reporter_user_id = reporter_user_id;
        this.rpt_category = rpt_category;
        this.report_datetime = report_datetime;
    }

    public int getReport_id() {
        return report_id;
    }

    public String getReported_user_nickName() {
        return reported_user_nickName;
    }

    public String getContent() {
        return content;
    }

    public int getReporter_user_id() {
        return reporter_user_id;
    }

    public String getRpt_category() {
        return rpt_category;
    }

    public Date getReport_datetime() {
        return report_datetime;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public void setReported_user_nickName(String reported_user_nickName) {
        this.reported_user_nickName = reported_user_nickName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReporter_user_id(int reporter_user_id) {
        this.reporter_user_id = reporter_user_id;
    }

    public void setRpt_category(String rpt_category) {
        this.rpt_category = rpt_category;
    }

    public void setReport_datetime(Date report_datetime) {
        this.report_datetime = report_datetime;
    }
}
