package com.banbanmoomani.memilog.DTO.admin.blacklist;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class BlackListDTO {

    private int user_id;
    private String nickName;
    private String age;
    private Date permanent_date;

    public BlackListDTO() {
    }

    public BlackListDTO(int user_id, String nickName, String age, Date permanent_date) {
        this.user_id = user_id;
        this.nickName = nickName;
        this.age = age;
        this.permanent_date = permanent_date;
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

    public Date getPermanent_date() {
        return permanent_date;
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

    public void setPermanent_date(Date permanent_date) {
        this.permanent_date = permanent_date;
    }

    private String calculateAge(Date birthday) {
        LocalDate birthDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period agePeriod = Period.between(birthDate, LocalDate.now());
        return agePeriod.getYears() + "대";
    }
}
