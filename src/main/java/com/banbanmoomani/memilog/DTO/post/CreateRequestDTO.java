package com.banbanmoomani.memilog.DTO.post;

public class CreateRequestDTO {
    private String mission_content;
    private String content;
    private int emotion_id;
    private int companion_id;
    private int user_id;

    public CreateRequestDTO() {}

    public CreateRequestDTO(String mission_content, String content, int emotion_id, int companion_id, int user_id) {
        this.mission_content = mission_content;
        this.content = content;
        this.emotion_id = emotion_id;
        this.companion_id = companion_id;
        this.user_id = user_id;
    }

    public String getMission_content() {
        return mission_content;
    }

    public void setMission_content(String mission_content) {
        this.mission_content = mission_content;
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

    @Override
    public String toString() {
        return "CreateRequestDTO{" +
                "mission_content='" + mission_content + '\'' +
                ", content='" + content + '\'' +
                ", emotion_id=" + emotion_id +
                ", companion_id=" + companion_id +
                ", user_id=" + user_id +
                '}';
    }
}