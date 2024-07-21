package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.admin.dashboard.MeMiLogInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeMiLogInfoMapper {
    int getTodayUserTotalCount();

    int getTodayUserCount();

    int getTodayPostCount();

    void saveTodayMeMiLogInfoDTO(MeMiLogInfoDTO meMiLogInfoDTO);
}
