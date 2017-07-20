package com.stock.dao;

import java.util.List;

import com.stock.model.StockBuySell;

public interface StockBuySellMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockBuySell record);

    int insertSelective(StockBuySell record);

    StockBuySell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockBuySell record);

    int updateByPrimaryKey(StockBuySell record);
    
    List<String> selectDays();

	String selectMaxHoliday();
}