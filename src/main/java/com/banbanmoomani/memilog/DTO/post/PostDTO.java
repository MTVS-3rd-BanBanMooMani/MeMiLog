package com.banbanmoomani.memilog.DTO.post;
import java.sql.Timestamp;

public class PostDTO {
    private int post_id;
    private String content;
    private int like_count;
    private int mission_id;
    private Timestamp written_datetime;
    private int emotion_id;
    private int user_id;
    private int companion_id;
    private int report_count;
    private boolean hidden_YN;

    public PostDTO() {}

    public PostDTO(int post_id, String content, int like_count, int mission_id, Timestamp written_datetime, int emotion_id, int user_id, int companion_id, int report_count, boolean hidden_YN) {
        this.post_id = post_id;
        this.content = content;
        this.like_count = like_count;
        this.mission_id = mission_id;
        this.written_datetime = written_datetime;
        this.emotion_id = emotion_id;
        this.user_id = user_id;
        this.companion_id = companion_id;
        this.report_count = report_count;
        this.hidden_YN = hidden_YN;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
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

    public Timestamp getWritten_datetime() {
        return written_datetime;
    }

    public void setWritten_datetime(Timestamp written_datetime) {
        this.written_datetime = written_datetime;
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

    public int getReport_count() {
        return report_count;
    }

    public void setReport_count(int report_count) {
        this.report_count = report_count;
    }

    public boolean isHidden_YN() {
        return hidden_YN;
    }

    public void setHidden_YN(boolean hidden_YN) {
        this.hidden_YN = hidden_YN;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "post_id=" + post_id +
                ", content='" + content + '\'' +
                ", like_count=" + like_count +
                ", mission_id=" + mission_id +
                ", written_datetime=" + written_datetime +
                ", emotion_id=" + emotion_id +
                ", user_id=" + user_id +
                ", companion_id=" + companion_id +
                ", report_count=" + report_count +
                ", hidden_YN=" + hidden_YN +
                '}';
    }
}
