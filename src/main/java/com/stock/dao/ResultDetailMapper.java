package com.stock.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stock.model.ResultDetail;

public interface ResultDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResultDetail record);

    int insertSelective(ResultDetail record);

    ResultDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResultDetail record);

    int updateByPrimaryKey(ResultDetail record);

	int insertList(@Param("list") List<ResultDetail> list);
	
	int insertList1(@Param("list") List<ResultDetail> list);

	List<ResultDetail> findIncrease(Map<String, Object> param);
	
	List<ResultDetail> findDecrease(Map<String, Object> param);

	int countIncrease(Map<String, Object> param);
	int countDecrease(Map<String, Object> param);
}