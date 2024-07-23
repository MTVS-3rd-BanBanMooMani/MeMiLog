package com.banbanmoomani.memilog.DTO;

public class LikeDTO {
    private int user_id;
    private int post_id;

    public LikeDTO() {}

    public LikeDTO(int user_id, int post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "LikeDTO{" +
                "user_id=" + user_id +
                ", post_id=" + post_id +
                '}';
    }
}
