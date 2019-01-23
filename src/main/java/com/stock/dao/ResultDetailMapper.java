package com.stock.dao;

import com.stock.model.ResultDetail;

public interface ResultDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResultDetail record);

    int insertSelective(ResultDetail record);

    ResultDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResultDetail record);

    int updateByPrimaryKey(ResultDetail record);
}