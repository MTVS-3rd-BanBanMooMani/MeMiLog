package com.banbanmoomani.memilog.DTO;

public class AttachmentDTO {
    private int pictureId;
    private String srcUrl;
    private String type;
    private int postId;
    private int userId;
    private int pictureOrder;
    private int noticeId;

    public AttachmentDTO() {}

    public AttachmentDTO(int pictureId, String srcUrl, String type, int postId, int userId, int pictureOrder, int noticeId) {
        this.pictureId = pictureId;
        this.srcUrl = srcUrl;
        this.type = type;
        this.postId = postId;
        this.userId = userId;
        this.pictureOrder = pictureOrder;
        this.noticeId = noticeId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPictureOrder() {
        return pictureOrder;
    }

    public void setPictureOrder(int pictureOrder) {
        this.pictureOrder = pictureOrder;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    @Override
    public String toString() {
        return "AttachmentDTO{" +
                "pictureId=" + pictureId +
                ", srcUrl='" + srcUrl + '\'' +
                ", type='" + type + '\'' +
                ", postId=" + postId +
                ", userId=" + userId +
                ", pictureOrder=" + pictureOrder +
                ", noticeId=" + noticeId +
                '}';
    }
}
