package com.banbanmoomani.memilog.DTO;

public class UpdateFileDTO {
    private int picture_id;
    private String src_url;
    private int post_id;
    private int picture_order;
    private int user_id;  // 추가
    private String type;  // 추가

    public UpdateFileDTO() {}

    public UpdateFileDTO(int picture_id, String src_url, int post_id, int picture_order, int user_id, String type) {
        this.picture_id = picture_id;
        this.src_url = src_url;
        this.post_id = post_id;
        this.picture_order = picture_order;
        this.user_id = user_id;  // 추가
        this.type = type;  // 추가
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

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getPicture_order() {
        return picture_order;
    }

    public void setPicture_order(int picture_order) {
        this.picture_order = picture_order;
    }

    public int getUser_id() {
        return user_id;  // 추가
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;  // 추가
    }

    public String getType() {
        return type;  // 추가
    }

    public void setType(String type) {
        this.type = type;  // 추가
    }

    @Override
    public String toString() {
        return "UpdateFileDTO{" +
                "picture_id=" + picture_id +
                ", src_url='" + src_url + '\'' +
                ", post_id=" + post_id +
                ", picture_order=" + picture_order +
                ", user_id=" + user_id +  // 추가
                ", type='" + type + '\'' +  // 추가
                '}';
    }
}
