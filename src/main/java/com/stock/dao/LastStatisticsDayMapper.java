package com.stock.dao;

public interface LastStatisticsDayMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(LastStatisticsDay record);
//
//    int insertSelective(LastStatisticsDay record);
//
//    int updateByPrimaryKeySelective(LastStatisticsDay record);

    int updateByPrimaryKey(String day);

    String selectByPrimaryKey();
}