package com.banbanmoomani.memilog.DTO;

public class PostDTO {
    private int postId;
    private String title;
    private String content;
    private int likeCount;
    private String writtenDatetime;
    private String companionType;
    private String emotionName;
    private String nickname;
    private String themeName;
    private String srcUrl;

    public PostDTO() {}

    public PostDTO(int postId, String title, String content, int likeCount, String writtenDatetime, String companionType, String emotionName, String nickname, String themeName, String srcUrl) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.writtenDatetime = writtenDatetime;
        this.companionType = companionType;
        this.emotionName = emotionName;
        this.nickname = nickname;
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

    public String getCompanionType() {
        return companionType;
    }

    public void setCompanionType(String companionType) {
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

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
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
                ", srcUrl='" + srcUrl + '\'' +
                '}';
    }
}
