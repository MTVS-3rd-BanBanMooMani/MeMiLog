package com.banbanmoomani.memilog.DTO.admin;

public class AdminDTO {

    private int admin_id;
    private String email;
    private String password;
    private String admin_name;

    public AdminDTO() {
    }

    public AdminDTO(int admin_id, String email, String password, String admin_name) {
        this.admin_id = admin_id;
        this.email = email;
        this.password = password;
        this.admin_name = admin_name;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "admin_id=" + admin_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", admin_name='" + admin_name + '\'' +
                '}';
    }
}
