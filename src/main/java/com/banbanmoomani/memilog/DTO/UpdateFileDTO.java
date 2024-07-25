package com.banbanmoomani.memilog.DTO;

public class UpdateFileDTO {
    private int pictureId;
    private String src_url;

    public UpdateFileDTO() {}

    public UpdateFileDTO(int pictureId, String src_url) {
        this.pictureId = pictureId;
        this.src_url = src_url;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    @Override
    public String toString() {
        return "UpdateFileDTO{" +
                "pictureId=" + pictureId +
                ", src_url='" + src_url + '\'' +
                '}';
    }
}
