package com.banbanmoomani.memilog.DTO;

public class MainTitleDTO {

    int mission_id;
    String mission_content;
    String src_url;

    public MainTitleDTO() {
    }

    public MainTitleDTO(int mission_id, String mission_content, String src_url) {
        this.mission_id = mission_id;
        this.mission_content = mission_content;
        this.src_url = src_url;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public String getMission_content() {
        return mission_content;
    }

    public void setMission_content(String mission_content) {
        this.mission_content = mission_content;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    @Override
    public String toString() {
        return "MainTitleDTO{" +
                "mission_id=" + mission_id +
                ", mission_content='" + mission_content + '\'' +
                ", src_url='" + src_url + '\'' +
                '}';
    }
}
