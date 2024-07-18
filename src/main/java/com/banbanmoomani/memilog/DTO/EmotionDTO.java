package com.banbanmoomani.memilog.DTO;

public class EmotionDTO {
    private int emotionId;
    private String emotionName;

    public EmotionDTO(){}

    public EmotionDTO(int emotionId, String emotionName) {
        this.emotionId = emotionId;
        this.emotionName = emotionName;
    }

    public int getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(int emotionId) {
        this.emotionId = emotionId;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    @Override
    public String toString() {
        return "EmotionDTO{" +
                "emotionId=" + emotionId +
                ", emotionName='" + emotionName + '\'' +
                '}';
    }
}
