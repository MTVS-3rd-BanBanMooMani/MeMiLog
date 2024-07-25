package com.banbanmoomani.memilog.DTO.admin.notice;

import java.sql.Date;

public class NoticeUpdateRequestDTO {
    private int notice_id;
    private String notice_title;
    private String notice_content;
    private String visible_YN;

    public NoticeUpdateRequestDTO() {
    }

    public NoticeUpdateRequestDTO(int notice_id, String notice_title, String notice_content, String visible_YN) {
        this.notice_id = notice_id;
        this.notice_title = notice_title;
        this.notice_content = notice_content;
        this.visible_YN = visible_YN;
    }

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getVisible_YN() {
        return visible_YN;
    }

    public void setVisible_YN(String visible_YN) {
        this.visible_YN = visible_YN;
    }
}
