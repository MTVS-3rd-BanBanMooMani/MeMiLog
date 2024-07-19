package com.banbanmoomani.memilog.DTO.user;

public class VerifyRequestDTO {
    private String email;
    private String verify_code;

    public VerifyRequestDTO() {}

    public VerifyRequestDTO(String email, String verify_code) {
        this.email = email;
        this.verify_code = verify_code;
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
        return "VerifyRequestDTO{" +
                "email='" + email + '\'' +
                ", verify_code='" + verify_code + '\'' +
                '}';
    }
}
