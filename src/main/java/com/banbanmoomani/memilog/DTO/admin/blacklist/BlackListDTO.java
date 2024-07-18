package com.banbanmoomani.memilog.DTO.admin.blacklist;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class BlackListDTO {

    private int user_id;
    private String nickName;
    private String age;
    private Date stop_start_date;
    private Date birthday;

    public BlackListDTO() {
    }

    public BlackListDTO(int user_id, String nickName, Date birthday, Date stop_start_date) {
        this.user_id = user_id;
        this.nickName = nickName;
        this.age = calculateAge(birthday);
        this.stop_start_date = stop_start_date;
        this.birthday = birthday;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAge() {
        return age;
    }

    public Date getStop_start_date() {
        return stop_start_date;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setStop_start_date(Date stop_start_date) {
        this.stop_start_date = stop_start_date;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.age = calculateAge(birthday);
    }

    private String calculateAge(Date birthday) {
        LocalDate birthDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period agePeriod = Period.between(birthDate, LocalDate.now());
        return ((agePeriod.getYears() / 10) * 10) + "대";
    }
}
