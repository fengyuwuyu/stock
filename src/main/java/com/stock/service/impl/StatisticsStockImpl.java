package com.stock.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.stock.dao.LastStatisticsDayMapper;
import com.stock.dao.StockBuySellMapper;
import com.stock.model.StockBuySell;
import com.stock.service.StatisticsStockI;

public class StatisticsStockImpl implements StatisticsStockI {

	@Autowired
	private LastStatisticsDayMapper lastStatisticsDayMapper;
	@Autowired
	private StockBuySellMapper stockBuySellMapper;

	public Map<String, Object> statistics() {
		String day = lastStatisticsDayMapper.selectByPrimaryKey();
		List<StockBuySell> list = stockBuySellMapper.selectByDay(day);
		if(list != null && list.size() > 0){
			for (StockBuySell stockBuySell : list) {
				
			}
		}
		return null;
	}

}
