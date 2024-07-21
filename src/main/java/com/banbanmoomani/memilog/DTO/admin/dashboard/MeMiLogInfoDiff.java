package com.banbanmoomani.memilog.DTO.admin.dashboard;

public class MeMiLogInfoDiff {

    private int user_total_diff;
    private int user_diff;
    private int post_diff;

    public MeMiLogInfoDiff(int post_diff, int user_diff, int user_total_diff) {
        this.post_diff = post_diff;
        this.user_diff = user_diff;
        this.user_total_diff = user_total_diff;
    }

    public int getUser_total_diff() {
        return user_total_diff;
    }

    public void setUser_total_diff(int user_total_diff) {
        this.user_total_diff = user_total_diff;
    }

    public int getUser_diff() {
        return user_diff;
    }

    public void setUser_diff(int user_diff) {
        this.user_diff = user_diff;
    }

    public int getPost_diff() {
        return post_diff;
    }

    public void setPost_diff(int post_diff) {
        this.post_diff = post_diff;
    }
}
