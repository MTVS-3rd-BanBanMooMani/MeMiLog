package com.banbanmoomani.memilog.DTO;

import java.util.List;

public class MissionSearhCriteria {

    private String word;
    private List<Integer> themeIds;

    public MissionSearhCriteria() {
    }

    public MissionSearhCriteria(String word, List<Integer> themeIds) {
        this.word = word;
        this.themeIds = themeIds;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Integer> getThemeIds() {
        return themeIds;
    }

    public void setThemeIds(List<Integer> themeIds) {
        this.themeIds = themeIds;
    }
}
