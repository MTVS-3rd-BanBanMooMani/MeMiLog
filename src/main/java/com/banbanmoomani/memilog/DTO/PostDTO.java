package com.banbanmoomani.memilog.DTO;

import java.util.Date;

public class PostDTO {
    private int postId;
    private String title;
    private String content;
    private int likeCount;
    private int missionId;
    private Date writtenDatetime;
    private String together;
    private int emotionId;
    private int userId;
    private int companionId;
    private String missionContent;
    private String missionDate;
    private String mainThemaName;
    private String subThemaName;
    private String emotionName;
    private String companionType;

    public PostDTO() {}

    public PostDTO(int postId, String content, int likeCount, int missionId, Date writtenDatetime, String together, int emotionId, int userId, int companionId, String missionContent, String missionDate, String mainThemaName, String subThemaName, String emotionName, String companionType) {
        this.postId = postId;
        this.title = missionContent;
        this.content = content;
        this.likeCount = likeCount;
        this.missionId = missionId;
        this.writtenDatetime = writtenDatetime;
        this.together = together;
        this.emotionId = emotionId;
        this.userId = userId;
        this.companionId = companionId;
        this.missionContent = missionContent;
        this.missionDate = missionDate;
        this.mainThemaName = mainThemaName;
        this.subThemaName = subThemaName;
        this.emotionName = emotionName;
        this.companionType = companionType;
    }

    // Getter and Setter methods

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

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public Date getWrittenDatetime() {
        return writtenDatetime;
    }

    public void setWrittenDatetime(Date writtenDatetime) {
        this.writtenDatetime = writtenDatetime;
    }

    public String getTogether() {
        return together;
    }

    public void setTogether(String together) {
        this.together = together;
    }

    public int getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(int emotionId) {
        this.emotionId = emotionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanionId() {
        return companionId;
    }

    public void setCompanionId(int companionId) {
        this.companionId = companionId;
    }

    public String getMissionContent() {
        return missionContent;
    }

    public void setMissionContent(String missionContent) {
        this.missionContent = missionContent;
        this.title = missionContent; // title을 missionContent로 설정
    }

    public String getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(String missionDate) {
        this.missionDate = missionDate;
    }

    public String getMainThemaName() {
        return mainThemaName;
    }

    public void setMainThemaName(String mainThemaName) {
        this.mainThemaName = mainThemaName;
    }

    public String getSubThemaName() {
        return subThemaName;
    }

    public void setSubThemaName(String subThemaName) {
        this.subThemaName = subThemaName;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    public String getCompanionType() {
        return companionType;
    }

    public void setCompanionType(String companionType) {
        this.companionType = companionType;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", missionId=" + missionId +
                ", writtenDatetime=" + writtenDatetime +
                ", together='" + together + '\'' +
                ", emotionId=" + emotionId +
                ", userId=" + userId +
                ", companionId=" + companionId +
                ", missionContent='" + missionContent + '\'' +
                ", missionDate='" + missionDate + '\'' +
                ", mainThemaName='" + mainThemaName + '\'' +
                ", subThemaName='" + subThemaName + '\'' +
                ", emotionName='" + emotionName + '\'' +
                ", companionType='" + companionType + '\'' +
                '}';
    }
}
