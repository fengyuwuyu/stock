package com.stock.dao;

import com.stock.model.StockMaxIncrease;

public interface StockMaxIncreaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockMaxIncrease record);

    int insertSelective(StockMaxIncrease record);

    StockMaxIncrease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockMaxIncrease record);

    int updateByPrimaryKey(StockMaxIncrease record);
}