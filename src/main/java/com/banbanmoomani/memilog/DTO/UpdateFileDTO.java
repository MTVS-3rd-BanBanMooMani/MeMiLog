package com.banbanmoomani.memilog.DTO;

public class UpdateFileDTO {
    private String src_url;

    public UpdateFileDTO() {}

    public UpdateFileDTO(String src_url) {
        this.src_url = src_url;
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
                "src_url='" + src_url + '\'' +
                '}';
    }
}
