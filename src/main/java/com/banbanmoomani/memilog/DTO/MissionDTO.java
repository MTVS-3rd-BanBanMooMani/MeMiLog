package com.banbanmoomani.memilog.DTO;

import java.util.Date;

public class MissionDTO {
    private int id; // 미션 번호
    private String title; // 미션 내용(제목)
    private Date date; // 미션 등록 일자
    private int mainThema; // 주 테마
    private int subThema; // 부가 테마

    public MissionDTO() {}

    public MissionDTO(int id, String title, Date date, int mainThema, int subThema) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.mainThema = mainThema;
        this.subThema = subThema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMainThema() {
        return mainThema;
    }

    public void setMainThema(int mainThema) {
        this.mainThema = mainThema;
    }

    public int getSubThema() {
        return subThema;
    }

    public void setSubThema(int subThema) {
        this.subThema = subThema;
    }

    @Override
    public String toString() {
        return "MissionDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", mainThema=" + mainThema +
                ", subThema=" + subThema +
                '}';
    }

}
