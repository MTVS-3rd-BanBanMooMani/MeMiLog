package com.banbanmoomani.memilog.DTO.user;

public class ForgotPWDRequestDTO {
    private String email; // 비밀번호 찾기 email input
    private String verifyCode; // 비밀번호 찾기 verify code

    public ForgotPWDRequestDTO() {}

    public ForgotPWDRequestDTO(String email, String verifyCode) {
        this.email = email;
        this.verifyCode = verifyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "ForgotPWDRequestDTO{" +
                "email='" + email + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
