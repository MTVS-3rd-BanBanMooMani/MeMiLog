package com.banbanmoomani.memilog.DTO;

import java.sql.Date;

public class IntegratedDTO {
    // User related fields
    private int userId;
    private String email;
    private String password;
    private Date birthday;
    private String nickname;
    private Date signupDate;

    // Theme related fields
    private int themeId;
    private String themeName;

    // Mission related fields
    private int missionId;
    private String missionContent;
    private String missionDate;
    private int priThemeId;
    private int subThemeId;

    // Picture related fields
    private int pictureId;
    private String srcUrl;
    private String type;
    private int pictureOrder;

    // Post related fields
    private int postId;
    private String title;
    private String content;
    private int likeCount;
    private String writtenDatetime;
    private String together;

    // Companion related fields
    private int companionId;
    private String companionType;

    // Emotion related fields
    private int emotionId;
    private String emotionName;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
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

    public int getPriThemeId() {
        return priThemeId;
    }

    public void setPriThemeId(int priThemeId) {
        this.priThemeId = priThemeId;
    }

    public int getSubThemeId() {
        return subThemeId;
    }

    public void setSubThemeId(int subThemeId) {
        this.subThemeId = subThemeId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPictureOrder() {
        return pictureOrder;
    }

    public void setPictureOrder(int pictureOrder) {
        this.pictureOrder = pictureOrder;
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

    public String getTogether() {
        return together;
    }

    public void setTogether(String together) {
        this.together = together;
    }

    public int getCompanionId() {
        return companionId;
    }

    public void setCompanionId(int companionId) {
        this.companionId = companionId;
    }

    public String getCompanionType() {
        return companionType;
    }

    public void setCompanionType(String companionType) {
        this.companionType = companionType;
    }

    public int getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(int emotionId) {
        this.emotionId = emotionId;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    @Override
    public String toString() {
        return "IntegratedDTO{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", nickname='" + nickname + '\'' +
                ", signupDate=" + signupDate +
                ", themeId=" + themeId +
                ", themeName='" + themeName + '\'' +
                ", missionId=" + missionId +
                ", missionContent='" + missionContent + '\'' +
                ", missionDate='" + missionDate + '\'' +
                ", priThemeId=" + priThemeId +
                ", subThemeId=" + subThemeId +
                ", pictureId=" + pictureId +
                ", srcUrl='" + srcUrl + '\'' +
                ", type='" + type + '\'' +
                ", pictureOrder=" + pictureOrder +
                ", postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", writtenDatetime='" + writtenDatetime + '\'' +
                ", together='" + together + '\'' +
                ", companionId=" + companionId +
                ", companionType='" + companionType + '\'' +
                ", emotionId=" + emotionId +
                ", emotionName='" + emotionName + '\'' +
                '}';
    }
}
