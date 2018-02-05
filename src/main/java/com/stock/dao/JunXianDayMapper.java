package com.stock.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.stock.model.JunXianDay;

public interface JunXianDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JunXianDay record);

    int insertSelective(JunXianDay record);

    JunXianDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JunXianDay record);

    int updateByPrimaryKey(JunXianDay record);

	void insertByMap(Map<String, Object> param);

	JunXianDay findBySumbolAndTypeAndDay(Map<String, Object> createMap);

	Date findLastDay();
}