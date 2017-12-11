package com.stock.dao;

import java.util.List;
import java.util.Map;

import com.stock.model.StockBuySell;
import com.stock.model.StockBuySellGroup;

public interface StockBuySellMapper {
	
	String selectFirstStockDay();
	
	List<StockBuySell> datalist(String day);
	
	int deleteByIdLower(Integer id);
	
    int deleteByPrimaryKey(Integer id);

    int insert(StockBuySell record);

    int insertSelective(StockBuySell record);

    StockBuySell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockBuySell record);

    int updateByPrimaryKey(StockBuySell record);
    
    List<String> selectDays();

	String selectMaxHoliday();

	List<StockBuySell> selectByDay(Map<String, Object> map);
	
	List<StockBuySellGroup> selectRecord();
	
	List<StockBuySell> selectByPage(Map<String, Object> map);

	void insertList(Map<String, Object> param);

	void deleteById(Map<String, Object> map);
}