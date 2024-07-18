package com.banbanmoomani.memilog.DTO;

import java.sql.Date;

public class NoticeDTO {
    private int notice_id;
    private String notice_title;
    private String notice_content;
    private String notice_file_url;
    private String visible_YN;
    private Date written_date;
    private int admin_id;

    public NoticeDTO() {
    }

    public NoticeDTO(int notice_id, String notice_title, String notice_content, String notice_file_url, String visible_YN/*, Date written_date*//*, int admin_id*/) {
        this.notice_id = notice_id;
        this.notice_title = notice_title;
        this.notice_content = notice_content;
        this.notice_file_url = notice_file_url;
        this.visible_YN = visible_YN;
        this.written_date = written_date;
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "notice_id=" + notice_id +
                ", notice_title='" + notice_title + '\'' +
                ", notice_content='" + notice_content + '\'' +
                ", notice_file_url='" + notice_file_url + '\'' +
                ", visible_YN='" + visible_YN + '\'' +
                ", written_date=" + written_date +
                ", admin_id=" + admin_id +
                '}';
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

    public String getNotice_file_url() {
        return notice_file_url;
    }

    public void setNotice_file_url(String notice_file_url) {
        this.notice_file_url = notice_file_url;
    }

    public String getVisible_YN() {
        return visible_YN;
    }

    public void setVisible_YN(String visible_YN) {
        this.visible_YN = visible_YN;
    }

    public Date getWritten_date() {
        return written_date;
    }

    public void setWritten_date(Date written_date) {
        this.written_date = written_date;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}

