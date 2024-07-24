package com.banbanmoomani.memilog.DTO;

public class todayPostDTO {
    private int postId;
    private String picture;
    private String nickname;

    public todayPostDTO() {}

    public todayPostDTO(int postId, String picture, String nickname) {
        this.postId = postId;
        this.picture = picture;
        this.nickname = nickname;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "todayPostDTO{" +
                "postId=" + postId +
                ", picture='" + picture + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
