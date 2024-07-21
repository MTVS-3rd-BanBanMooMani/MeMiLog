package com.banbanmoomani.memilog.DTO.admin.dashboard;

import java.time.LocalDate;

public class MeMiLogInfoDTO {
    private LocalDate date;
    private int userTotalCount;
    private int userCount;
    private int postCount;

    public MeMiLogInfoDTO() {
    }

    public MeMiLogInfoDTO(LocalDate date, int userTotalCount, int userCount, int postCount) {
        this.date = date;
        this.userTotalCount = userTotalCount;
        this.userCount = userCount;
        this.postCount = postCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUserTotalCount() {
        return userTotalCount;
    }

    public void setUserTotalCount(int userTotalCount) {
        this.userTotalCount = userTotalCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @Override
    public String toString() {
        return "MeMiLogInfoDTO{" +
                "date=" + date +
                ", userTotalCount=" + userTotalCount +
                ", userCount=" + userCount +
                ", postCount=" + postCount +
                '}';
    }
}
