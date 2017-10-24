package com.stock.dao;

import com.stock.model.StockTableInfo;

public interface StockTableInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockTableInfo record);

    int insertSelective(StockTableInfo record);

    StockTableInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockTableInfo record);

    int updateByPrimaryKey(StockTableInfo record);
}