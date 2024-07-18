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
    private Date birthday;
    private Date stop_end_date;

    public BanListDTO() {
    }

    public BanListDTO(int user_id, String nickName, Date birthday, int caution_weights, Date stop_end_date) {
        this.user_id = user_id;
        this.nickName = nickName;
        this.birthday = birthday;
        this.caution_weights = caution_weights;
        this.stop_end_date = stop_end_date;
        this.age = calculateAge(birthday);
        this.remain_susp_period = calculateRemainSuspPeriod(stop_end_date);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getCaution_weights() {
        return caution_weights;
    }

    public void setCaution_weights(int caution_weights) {
        this.caution_weights = caution_weights;
    }

    public String getRemain_susp_period() {
        return remain_susp_period;
    }

    public void setRemain_susp_period(String remain_susp_period) {
        this.remain_susp_period = remain_susp_period;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.age = calculateAge(birthday);
    }

    public Date getStop_end_date() {
        return stop_end_date;
    }

    public void setStop_end_date(Date stop_end_date) {
        this.stop_end_date = stop_end_date;
        this.remain_susp_period = calculateRemainSuspPeriod(stop_end_date);
    }

    @Override
    public String toString() {
        return "BanListDTO{" +
                "user_id=" + user_id +
                ", nickName='" + nickName + '\'' +
                ", age='" + age + '\'' +
                ", caution_weights=" + caution_weights +
                ", remain_susp_period='" + remain_susp_period + '\'' +
                '}';
    }

    private String calculateAge(Date birthday) {
        LocalDate birthDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period agePeriod = Period.between(birthDate, LocalDate.now());
        return ((agePeriod.getYears() / 10) * 10) + "대";
    }

    private String calculateRemainSuspPeriod(Date stopEndDate) {
        LocalDate stopEndLocalDate = stopEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period remainingPeriod = Period.between(LocalDate.now(), stopEndLocalDate);
        return remainingPeriod.getDays() + "일";
    }
}
