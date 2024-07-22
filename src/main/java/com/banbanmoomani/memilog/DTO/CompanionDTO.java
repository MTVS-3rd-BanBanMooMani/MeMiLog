package com.banbanmoomani.memilog.DTO;

public class CompanionDTO {
    private int companion_id;
    private String companion_type;

    public CompanionDTO() {}

    public CompanionDTO(int companion_id, String companion_type) {
        this.companion_id = companion_id;
        this.companion_type = companion_type;
    }

    public int getCompanion_id() {
        return companion_id;
    }

    public void setCompanion_id(int companion_id) {
        this.companion_id = companion_id;
    }

    public String getCompanion_type() {
        return companion_type;
    }

    public void setCompanion_type(String companion_type) {
        this.companion_type = companion_type;
    }

    @Override
    public String toString() {
        return "CompanionDTO{" +
                "companion_id=" + companion_id +
                ", companion_type='" + companion_type + '\'' +
                '}';
    }
}
