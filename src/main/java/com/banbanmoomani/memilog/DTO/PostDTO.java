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
    private String priThemeName;
    private String subThemeName;
    private String emotionName;
    private String companionType;

    public PostDTO() {}

    public PostDTO(int postId, String title, String content, int likeCount, int missionId, Date writtenDatetime, String nickname, String missionContent, String missionDate, String priThemeName, String subThemeName, String emotionName, String companionType) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.missionId = missionId;
        this.writtenDatetime = writtenDatetime;
        this.nickname = nickname;
        this.missionContent = missionContent;
        this.missionDate = missionDate;
        this.priThemeName = priThemeName;
        this.subThemeName = subThemeName;
        this.emotionName = emotionName;
        this.companionType = companionType;
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
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getMissionContent() {
        return missionContent;
    }
    public void setMissionContent(String missionContent) {
        this.missionContent = missionContent;
    }
    public String getMissionDate() {
        return missionDate;
    }
    public void setMissionDate(String missionDate) {
        this.missionDate = missionDate;
    }

    public String getPriThemeName() {
        return priThemeName;
    }

    public void setPriThemeName(String mainThemeName) {
        this.priThemeName = mainThemeName;
    }

    public String getSubThemeName() {
        return subThemeName;
    }
    public void setSubThemeName(String subThemeName) {
        this.subThemeName = subThemeName;
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
                ", nickname='" + nickname + '\'' +
                ", missionContent='" + missionContent + '\'' +
                ", missionDate='" + missionDate + '\'' +
                ", priThemeName='" + priThemeName + '\'' +
                ", subThemeName='" + subThemeName + '\'' +
                ", emotionName='" + emotionName + '\'' +
                ", companionType='" + companionType + '\'' +
                '}';
    }
}