package com.banbanmoomani.memilog.DTO.admin.blacklist;

import com.banbanmoomani.memilog.DTO.UserDTO;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class BanListDTO {

    private int user_id;
    private String nickName;
    private String age;
    private int caution_weights;
    private String remain_susp_period;

    public BanListDTO() {
    }

    public BanListDTO(UserDTO userDTO) {
        this.user_id = userDTO.getUser_id();
        this.nickName = userDTO.getNickName();
        this.age = getAge(userDTO.getBirthday());
        this.caution_weights = userDTO.getCaution_weights();
        this.remain_susp_period = getRemainSuspPeriod(userDTO.getSusp_period());
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

    public int getCaution_weights() {
        return caution_weights;
    }

    public String getRemain_susp_period() {
        return remain_susp_period;
    }

    protected String getAge(Date birthday) {

        StringBuilder sb = new StringBuilder();

        LocalDate birthdate = LocalDate.parse(birthday.toString());
        LocalDate now = LocalDate.now();

        int age = Period.between(birthdate, now).getYears();
        age = (age / 10) * 10;

        sb.append(age).append("대");

        return sb.toString();
    }

    protected String getRemainSuspPeriod(Date suspPeriod) {

        StringBuilder sb = new StringBuilder();

        LocalDate suspDate = LocalDate.parse(suspPeriod.toString());
        LocalDate now = LocalDate.now();

        int remain = Period.between(suspDate, now).getDays();

        sb.append(remain).append("일");

        return sb.toString();
    }
}
