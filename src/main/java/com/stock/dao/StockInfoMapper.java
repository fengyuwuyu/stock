package com.stock.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.stock.model.StockInfo;

public interface StockInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockInfo record);

    int insertSelective(StockInfo record);

    StockInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockInfo record);

    int updateByPrimaryKey(StockInfo record);

	void insertSelectiveMap(LinkedHashMap<String, Object> map);

	List<String> selectAll();
	
	void updateStatus(String symbol);
}