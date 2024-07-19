package com.banbanmoomani.memilog.DTO.post;
import java.sql.Timestamp;

public class PostDTO {
    private int postId;
    private String title;
    private String content;
    private int like_count;
    private int mission_id;
    private Timestamp writtenDatetime;
    private int emotion_id;
    private int user_id;
    private int companion_id;

    public PostDTO() {}

    public PostDTO(int postId, String title, String content, int like_count, int mission_id, Timestamp writtenDatetime, int emotion_id, int user_id, int companion_id) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.like_count = like_count;
        this.mission_id = mission_id;
        this.writtenDatetime = writtenDatetime;
        this.emotion_id = emotion_id;
        this.user_id = user_id;
        this.companion_id = companion_id;
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

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public Timestamp getWrittenDatetime() {
        return writtenDatetime;
    }

    public void setWrittenDatetime(Timestamp writtenDatetime) {
        this.writtenDatetime = writtenDatetime;
    }

    public int getEmotion_id() {
        return emotion_id;
    }

    public void setEmotion_id(int emotion_id) {
        this.emotion_id = emotion_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCompanion_id() {
        return companion_id;
    }

    public void setCompanion_id(int companion_id) {
        this.companion_id = companion_id;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", like_count=" + like_count +
                ", mission_id=" + mission_id +
                ", writtenDatetime=" + writtenDatetime +
                ", emotion_id=" + emotion_id +
                ", user_id=" + user_id +
                ", companion_id=" + companion_id +
                '}';
    }
}