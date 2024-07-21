package com.banbanmoomani.memilog.DTO.admin.dashboard;

public class AgeGroupMemberDTO {
    private String ageGroup;
    private int memberCount;

    public AgeGroupMemberDTO() {
    }

    public AgeGroupMemberDTO(String ageGroup, int memberCount) {
        this.ageGroup = ageGroup;
        this.memberCount = memberCount;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    @Override
    public String toString() {
        return "AgeGroupMemberDTO{" +
                "ageGroup='" + ageGroup + '\'' +
                ", memberCount=" + memberCount +
                '}';
    }
}
