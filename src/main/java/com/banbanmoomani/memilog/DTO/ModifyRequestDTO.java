package com.banbanmoomani.memilog.DTO;

public class ModifyRequestDTO {
    private String email;
    private String modify_nickname;
    private String modify_password;

    public ModifyRequestDTO() {}

    public ModifyRequestDTO(String email, String modify_nickname, String modify_password) {
        this.email = email;
        this.modify_nickname = modify_nickname;
        this.modify_password = modify_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModify_nickname() {
        return modify_nickname;
    }

    public void setModify_nickname(String modify_nickname) {
        this.modify_nickname = modify_nickname;
    }

    public String getModify_password() {
        return modify_password;
    }

    public void setModify_password(String modify_password) {
        this.modify_password = modify_password;
    }

    @Override
    public String toString() {
        return "ModifyUserDTO{" +
                "email='" + email + '\'' +
                ", modify_nickname='" + modify_nickname + '\'' +
                ", modify_password='" + modify_password + '\'' +
                '}';
    }
}
