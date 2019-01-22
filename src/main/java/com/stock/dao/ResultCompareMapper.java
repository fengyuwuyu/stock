package com.stock.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stock.model.ResultCompare;

public interface ResultCompareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResultCompare record);

    int insertSelective(ResultCompare record);

    ResultCompare selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResultCompare record);

    int updateByPrimaryKey(ResultCompare record);

	int statisticsCount(Map<String, Object> createMap);

	int insertList(@Param("list") List<ResultCompare> list);
}