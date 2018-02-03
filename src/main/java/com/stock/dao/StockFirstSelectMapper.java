package com.stock.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.stock.model.StockFirstSelect;
import com.stock.model.StockMain;

public interface StockFirstSelectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockFirstSelect record);

    int insertSelective(StockFirstSelect record);

    StockFirstSelect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockFirstSelect record);

    int updateByPrimaryKey(StockFirstSelect record);

	void insertList(Map<String, Object> createMap);

	List<StockFirstSelect> selectByDay(Date begin);

	List<Date> selectWorkerDay();
}