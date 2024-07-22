package com.banbanmoomani.memilog.DTO.user;

import java.sql.Date;

public class UserDTO {
    private int user_id;
    private String email;
    private String password;
    private java.sql.Date birthday;
    private String nickname;
    private String gender;
    private int caution_weights;
    private String temporary_YN;
    private String today_access_YN;
    private java.sql.Date signup_date;
    private java.sql.Date susp_period;

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", caution_weights=" + caution_weights +
                ", temporary_YN='" + temporary_YN + '\'' +
                ", today_access_YN='" + today_access_YN + '\'' +
                ", signup_date=" + signup_date +
                ", susp_period=" + susp_period +
                '}';
    }

    public UserDTO() {}

    public UserDTO(int user_id, String email, String password, Date birthday, String nickname, String gender, int caution_weights, String temporary_YN, String today_access_YN, Date signup_date, Date susp_period) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.nickname = nickname;
        this.gender = gender;
        this.caution_weights = caution_weights;
        this.temporary_YN = temporary_YN;
        this.today_access_YN = today_access_YN;
        this.signup_date = signup_date;
        this.susp_period = susp_period;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCaution_weights() {
        return caution_weights;
    }

    public void setCaution_weights(int caution_weights) {
        this.caution_weights = caution_weights;
    }

    public String getTemporary_YN() {
        return temporary_YN;
    }

    public void setTemporary_YN(String temporary_YN) {
        this.temporary_YN = temporary_YN;
    }

    public String getToday_access_YN() {
        return today_access_YN;
    }

    public void setToday_access_YN(String today_access_YN) {
        this.today_access_YN = today_access_YN;
    }

    public Date getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(Date signup_date) {
        this.signup_date = signup_date;
    }

    public Date getSusp_period() {
        return susp_period;
    }

    public void setSusp_period(Date susp_period) {
        this.susp_period = susp_period;
    }

}
