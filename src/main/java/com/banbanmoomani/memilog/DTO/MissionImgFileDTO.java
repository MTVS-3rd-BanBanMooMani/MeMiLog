package com.banbanmoomani.memilog.DTO;

public class MissionImgFileDTO {
    private String src_url;
    private int mission_id;

    public MissionImgFileDTO(String src_url, int mission_id) {
        this.src_url = src_url;
        this.mission_id = mission_id;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }
}
