package com.banbanmoomani.memilog.DTO;

import java.util.Date;

public class PostDTO {
    private int postId;
    private String title;
    private String content;
    private int likeCount;
    private int missionId;
    private Date writtenDatetime;
    private String nickname;
    private String missionContent;
    private String missionDate;
    private String mainThemeName;
    private String subThemeName;
    private String emotionName;
    private String nickname;
    private String themeName;
    private String srcUrl;
    private String companionType;

    public PostDTO() {}

    public PostDTO(int postId, String title, String content, int likeCount, int missionId, Date writtenDatetime, String nickname, String missionContent, String missionDate, String mainThemeName, String subThemeName, String emotionName, String companionType) {
    public PostDTO(int postId, String title, String content, int likeCount, String writtenDatetime, String companionType, String emotionName, String nickname, String themeName, String srcUrl) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.missionId = missionId;
        this.writtenDatetime = writtenDatetime;
        this.nickname = nickname;
        this.missionContent = missionContent;
        this.missionDate = missionDate;
        this.mainThemeName = mainThemeName;
        this.subThemeName = subThemeName;
        this.emotionName = emotionName;
        this.companionType = companionType;
        this.themeName = themeName;
        this.srcUrl = srcUrl;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getWrittenDatetime() {
        return writtenDatetime;
    }

    public void setWrittenDatetime(String writtenDatetime) {
        this.writtenDatetime = writtenDatetime;
    }

    public String setcompanionType() {
        return companionType;
    }

    public void setCompanionName(String companionType) {
        this.companionType = companionType;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", writtenDatetime='" + writtenDatetime + '\'' +
                ", companionType='" + companionType + '\'' +
                ", emotionName='" + emotionName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", themeName='" + themeName + '\'' +
                '}';
    }
}
