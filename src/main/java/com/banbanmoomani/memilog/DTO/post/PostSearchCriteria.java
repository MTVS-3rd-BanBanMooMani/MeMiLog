package com.banbanmoomani.memilog.DTO.post;

import java.util.List;

public class PostSearchCriteria {
    private String date;
    private List<Integer> companionTypes;

    public PostSearchCriteria() {}

    public PostSearchCriteria(String date, List<Integer> companionTypes) {
        this.date = date;
        this.companionTypes = companionTypes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Integer> getCompanionTypes() {
        return companionTypes;
    }

    public void setCompanionTypes(List<Integer> companionTypes) {
        this.companionTypes = companionTypes;
    }

    @Override
    public String toString() {
        return "PostSearchCriteria{" +
                "date='" + date + '\'' +
                ", companionTypes=" + companionTypes +
                '}';
    }
}
