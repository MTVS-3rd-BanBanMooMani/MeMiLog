package com.banbanmoomani.memilog.DTO.mydiary;

import java.util.List;

public class PostRequestDTO {

    private int post_id;
    private String mission_content;
    private String content;
    private int like_count;
    private int mission_id;
    private String written_datetime;
    private int user_id;
    private String nickname;
    private String mission_date;
    private String pri_theme_name;
    private String sub_theme_name;
    private String emotion_name;
    private String companion_type;
    private String src_url;

    private String profile_img;
    private List<String> postUrl;

    private boolean likeInfo;

    public boolean isLikeInfo() {
        return likeInfo;
    }

    public void setLikeInfo(boolean likeInfo) {
        this.likeInfo = likeInfo;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public List<String> getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(List<String> postUrl) {
        this.postUrl = postUrl;
    }

    PostRequestDTO(){}

    public PostRequestDTO(int post_id, String mission_content, String content, int like_count, int mission_id, String written_datetime, int user_id, String nickname, String mission_date, String pri_theme_name, String sub_theme_name, String emotion_name, String companion_type, String src_url) {
        this.post_id = post_id;
        this.mission_content = mission_content;
        this.content = content;
        this.like_count = like_count;
        this.mission_id = mission_id;
        this.written_datetime = written_datetime;
        this.user_id = user_id;
        this.nickname = nickname;
        this.mission_date = mission_date;
        this.pri_theme_name = pri_theme_name;
        this.sub_theme_name = sub_theme_name;
        this.emotion_name = emotion_name;
        this.companion_type = companion_type;
        this.src_url = src_url;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
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

    public String getWritten_datetime() {
        return written_datetime;
    }

    public void setWritten_datetime(String written_datetime) {
        this.written_datetime = written_datetime;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMission_date() {
        return mission_date;
    }

    public void setMission_date(String mission_date) {
        this.mission_date = mission_date;
    }

    public String getPri_theme_name() {
        return pri_theme_name;
    }

    public void setPri_theme_name(String pri_theme_name) {
        this.pri_theme_name = pri_theme_name;
    }

    public String getSub_theme_name() {
        return sub_theme_name;
    }

    public void setSub_theme_name(String sub_theme_name) {
        this.sub_theme_name = sub_theme_name;
    }

    public String getEmotion_name() {
        return emotion_name;
    }

    public void setEmotion_name(String emotion_name) {
        this.emotion_name = emotion_name;
    }

    public String getCompanion_type() {
        return companion_type;
    }

    public void setCompanion_type(String companion_type) {
        this.companion_type = companion_type;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    @Override
    public String toString() {
        return "PostRequestDTO{" +
                "post_id=" + post_id +
                ", mission_content='" + mission_content + '\'' +
                ", content='" + content + '\'' +
                ", like_count=" + like_count +
                ", mission_id=" + mission_id +
                ", written_datetime='" + written_datetime + '\'' +
                ", user_id=" + user_id +
                ", nickname='" + nickname + '\'' +
                ", mission_date='" + mission_date + '\'' +
                ", pri_theme_name='" + pri_theme_name + '\'' +
                ", sub_theme_name='" + sub_theme_name + '\'' +
                ", emotion_name='" + emotion_name + '\'' +
                ", companion_type='" + companion_type + '\'' +
                ", src_url='" + src_url + '\'' +
                '}';
    }
}
