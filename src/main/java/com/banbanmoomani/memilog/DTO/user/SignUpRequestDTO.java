package com.banbanmoomani.memilog.DTO.user;

public class SignUpRequestDTO {
    private String email;
    private String password;
    private String year;
    private String month;
    private String day;
    private String nickname;
    private String gender;

    public SignUpRequestDTO() {}

    @Override
    public String toString() {
        return "SignUpRequestDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public SignUpRequestDTO(String email, String password, String year, String month, String day, String nickname, String gender) {
        this.email = email;
        this.password = password;
        this.year = year;
        this.month = month;
        this.day = day;
        this.nickname = nickname;
        this.gender = gender;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
