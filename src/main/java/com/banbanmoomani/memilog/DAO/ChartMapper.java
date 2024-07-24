package com.banbanmoomani.memilog.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChartMapper {

    List<Map<String, Object>> getEmotionCounts(Map<String, Object> params);

    List<Map<String, Object>> getMonthlyEmotionCounts(Map<String, Object> params);

}
