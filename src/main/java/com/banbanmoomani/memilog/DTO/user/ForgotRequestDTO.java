package com.banbanmoomani.memilog.DTO.user;

public class ForgotRequestDTO {
    private String id_email; // 아이디 찾기 email input
    private String email; // 비밀번호 찾기 email input
    private String verify_code; // 비밀번호 찾기 verify code

    public ForgotRequestDTO() {}

    public ForgotRequestDTO(String id_email, String email, String verify_code) {
        this.id_email = id_email;
        this.email = email;
        this.verify_code = verify_code;
    }

    public String getId_email() {
        return id_email;
    }

    public void setId_email(String id_email) {
        this.id_email = id_email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    @Override
    public String toString() {
        return "ForgotRequestDTO{" +
                "id_email='" + id_email + '\'' +
                ", email='" + email + '\'' +
                ", verify_code='" + verify_code + '\'' +
                '}';
    }
}
