package com.banbanmoomani.memilog.DTO;

public class archivePostDTO {
    private int archiveId;
    private String picture;
    private String nickname;
    public archivePostDTO() {}

    public archivePostDTO(int archiveId, String picture, String nickname) {
        this.archiveId = archiveId;
        this.picture = picture;
        this.nickname = nickname;
    }

    public int getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(int archiveId) {
        this.archiveId = archiveId;
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
        return "archivePostDTO{" +
                "archiveId=" + archiveId +
                ", picture='" + picture + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
