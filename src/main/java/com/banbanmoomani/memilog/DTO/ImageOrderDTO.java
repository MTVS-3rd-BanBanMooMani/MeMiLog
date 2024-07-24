package com.banbanmoomani.memilog.DTO;

public class ImageOrderDTO {
    private int post_id;
    private String src_url;
    private int picture_order;

    // getters and setters

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    public int getPicture_order() {
        return picture_order;
    }

    public void setPicture_order(int picture_order) {
        this.picture_order = picture_order;
    }
}