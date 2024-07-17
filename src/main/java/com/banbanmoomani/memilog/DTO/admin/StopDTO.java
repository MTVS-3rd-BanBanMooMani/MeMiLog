package com.banbanmoomani.memilog.DTO.admin;

import java.util.Date;

public class StopDTO {

    private int stop_id;

    private int admin_id;
    private int user_id;
    private int stop_type_id;

    private String perm_YN;

    private Date stop_start_date;
    private Date stop_end_date;

    public StopDTO() {
    }

    public StopDTO(int stop_id, int admin_id, int user_id, int stop_type_id, String perm_YN, Date stop_start_date, Date stop_end_date) {
        this.stop_id = stop_id;
        this.admin_id = admin_id;
        this.user_id = user_id;
        this.stop_type_id = stop_type_id;
        this.perm_YN = perm_YN;
        this.stop_start_date = stop_start_date;
        this.stop_end_date = stop_end_date;
    }

    public int getStop_id() {
        return stop_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getStop_type_id() {
        return stop_type_id;
    }

    public String getPerm_YN() {
        return perm_YN;
    }

    public Date getStop_start_date() {
        return stop_start_date;
    }

    public Date getStop_end_date() {
        return stop_end_date;
    }

    public void setStop_id(int stop_id) {
        this.stop_id = stop_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setStop_type_id(int stop_type_id) {
        this.stop_type_id = stop_type_id;
    }

    public void setPerm_YN(String perm_YN) {
        this.perm_YN = perm_YN;
    }

    public void setStop_start_date(Date stop_start_date) {
        this.stop_start_date = stop_start_date;
    }

    public void setStop_end_date(Date stop_end_date) {
        this.stop_end_date = stop_end_date;
    }
}
