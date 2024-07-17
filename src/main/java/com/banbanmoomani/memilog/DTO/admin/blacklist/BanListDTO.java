package com.banbanmoomani.memilog.DTO.admin.blacklist;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class BanListDTO {

    private int user_id;
    private String nickName;
    private String age;
    private int caution_weights;
    private String remain_susp_period;

    public BanListDTO() {
    }

    public BanListDTO(int user_id, String nickName, Date age, int caution_weights, Date remain_susp_period) {
        this.user_id = user_id;
        this.nickName = nickName;
        this.age = getAge(age);
        this.caution_weights = caution_weights;
        this.remain_susp_period = getRemainSuspPeriod(remain_susp_period);
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
        if (birthday == null) {
            return "N/A";
        }
        LocalDate birthdate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();

        int age = Period.between(birthdate, now).getYears();
        age = (age / 10) * 10;

        return age + "대";
    }

    protected String getRemainSuspPeriod(Date suspPeriod) {
        if (suspPeriod == null) {
            return "N/A";
        }
        LocalDate suspDate = suspPeriod.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();

        int remain = Period.between(now, suspDate).getDays();

        return remain + "일";
    }
}