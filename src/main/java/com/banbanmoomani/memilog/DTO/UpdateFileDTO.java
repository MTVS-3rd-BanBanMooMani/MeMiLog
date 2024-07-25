package com.banbanmoomani.memilog.DTO;

public class UpdateFileDTO {
    private int picture_id;
    private String src_url;
    private int post_id;
    private int picture_order;

    public UpdateFileDTO() {}

    public UpdateFileDTO(int picture_id, String src_url, int post_id, int picture_order) {
        this.picture_id = picture_id;
        this.src_url = src_url;
        this.post_id = post_id;
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

    @Override
    public String toString() {
        return "UpdateFileDTO{" +
                "picture_id=" + picture_id +
                ", src_url='" + src_url + '\'' +
                ", post_id=" + post_id +
                ", picture_order=" + picture_order +
                '}';
    }
}
