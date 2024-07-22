package com.banbanmoomani.memilog.DTO;

public class FileDTO {
    private int picture_id;
    private String src_url;
    private String type;
    private int post_id;
    private int user_id;
    private int picture_order;
    private int notice_id;

    public FileDTO() {}

    public FileDTO(String src_url, String type, int post_id, int user_id, int picture_order) {
        this.src_url = src_url;
        this.type = type;
        this.post_id = post_id;
        this.user_id = user_id;
        this.picture_order = picture_order;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPicture_order() {
        return picture_order;
    }

    public void setPicture_order(int picture_order) {
        this.picture_order = picture_order;
    }

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "picture_id=" + picture_id +
                ", src_url='" + src_url + '\'' +
                ", type='" + type + '\'' +
                ", post_id=" + post_id +
                ", user_id=" + user_id +
                ", picture_order=" + picture_order +
                ", notice_id=" + notice_id +
                '}';
    }
}
