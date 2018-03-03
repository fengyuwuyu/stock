package com.stock.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.stock.model.StockMain;
import com.stock.model.StockMaxIncrease;
import com.stock.model.StockQuery;

public interface StockMaxIncreaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockMaxIncrease record);

    int insertSelective(StockMaxIncrease record);

    StockMaxIncrease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockMaxIncrease record);

    int updateByPrimaryKey(StockMaxIncrease record);

	void insertList(Map<String, List<StockMain>> stockList);

	Date findLastDay();

	long countByQuery(StockQuery query);

	List<StockMaxIncrease> findByQuery(StockQuery query);
}