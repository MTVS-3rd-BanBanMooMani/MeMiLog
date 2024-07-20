package com.banbanmoomani.memilog.DTO.mydiary;

public class UserProfileDTO {
    private int user_id;
    private String email;
    private String password;
    private String birthday;
    private String nickname;
    private String gender;
    private String signup_date;
    private String src_url;

    public UserProfileDTO() {}

    public UserProfileDTO(int user_id, String email, String password, String birthday, String nickname, String gender, String signup_date, String src_url) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.nickname = nickname;
        this.gender = gender;
        this.signup_date = signup_date;
        this.src_url = src_url;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(String signup_date) {
        this.signup_date = signup_date;
    }

    public String getSrc_url() {
        return src_url;
    }

    public void setSrc_url(String src_url) {
        this.src_url = src_url;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", signup_date='" + signup_date + '\'' +
                ", src_url='" + src_url + '\'' +
                '}';
    }
}