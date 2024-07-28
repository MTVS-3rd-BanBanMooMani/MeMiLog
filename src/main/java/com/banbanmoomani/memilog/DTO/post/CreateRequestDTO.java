package com.banbanmoomani.memilog.DTO.post;

public class CreateRequestDTO {
    private int postId;
    private int mission_id;
    private String content;
    private int emotion_id;
    private int companion_id;
    private int user_id;
    private String fileUrl; // 추가된 필드

    public CreateRequestDTO() {}

    public CreateRequestDTO(int postId, int mission_id, String content, int emotion_id, int companion_id, int user_id, String fileUrl) {
        this.postId = postId;
        this.mission_id = mission_id;
        this.content = content;
        this.emotion_id = emotion_id;
        this.companion_id = companion_id;
        this.user_id = user_id;
        this.fileUrl = fileUrl;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEmotion_id() {
        return emotion_id;
    }

    public void setEmotion_id(int emotion_id) {
        this.emotion_id = emotion_id;
    }

    public int getCompanion_id() {
        return companion_id;
    }

    public void setCompanion_id(int companion_id) {
        this.companion_id = companion_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "CreateRequestDTO{" +
                "postId=" + postId +
                ", mission_id=" + mission_id +
                ", content='" + content + '\'' +
                ", emotion_id=" + emotion_id +
                ", companion_id=" + companion_id +
                ", user_id=" + user_id +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
