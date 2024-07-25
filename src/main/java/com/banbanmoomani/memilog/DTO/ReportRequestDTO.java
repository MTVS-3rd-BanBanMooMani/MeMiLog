package com.banbanmoomani.memilog.DTO;

public class ReportRequestDTO {

    private int rptCategoryId;
    private String rptContent;
    private int postId;
    private int rptedUserId;

    public ReportRequestDTO() {}

    public ReportRequestDTO(int rptCategoryId, String rptContent, int postId, int rptedUserId) {
        this.rptCategoryId = rptCategoryId;
        this.rptContent = rptContent;
        this.postId = postId;
        this.rptedUserId = rptedUserId;
    }

    public int getRptCategoryId() {
        return rptCategoryId;
    }

    public void setRptCategoryId(int rptCategoryId) {
        this.rptCategoryId = rptCategoryId;
    }

    public String getRptContent() {
        return rptContent;
    }

    public void setRptContent(String rptContent) {
        this.rptContent = rptContent;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getRptedUserId() {
        return rptedUserId;
    }

    public void setRptedUserId(int rptedUserId) {
        this.rptedUserId = rptedUserId;
    }

    @Override
    public String toString() {
        return "ReportRequestDTO{" +
                "rptCategoryId=" + rptCategoryId +
                ", rptContent='" + rptContent + '\'' +
                ", postId=" + postId +
                ", rptedUserId=" + rptedUserId +
                '}';
    }
}
