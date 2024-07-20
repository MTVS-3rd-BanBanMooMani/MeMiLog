package com.banbanmoomani.memilog.DTO.admin.dashboard;

public class ReportedPostDTO {

    private int postId;
    private String reporterUserNickName;
    private String reportedUserNickName;
    private int reportCount;

    public ReportedPostDTO() {
    }

    public ReportedPostDTO(int postId, String reporterUserNickName, String reportedUserNickName, int postReportCount, int reportCount) {
        this.postId = postId;
        this.reporterUserNickName = reporterUserNickName;
        this.reportedUserNickName = reportedUserNickName;
        this.reportCount = reportCount;
    }

    // Getters and Setters
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getReporterUserNickName() {
        return reporterUserNickName;
    }

    public void setReporterUserNickName(String reporterUserNickName) {
        this.reporterUserNickName = reporterUserNickName;
    }

    public String getReportedUserNickName() {
        return reportedUserNickName;
    }

    public void setReportedUserNickName(String reportedUserNickName) {
        this.reportedUserNickName = reportedUserNickName;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }
}
