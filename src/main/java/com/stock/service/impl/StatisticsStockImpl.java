package com.stock.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.stock.dao.LastStatisticsDayMapper;
import com.stock.dao.StockBuySellMapper;
import com.stock.dao.StockMainMapper;
import com.stock.model.StockBuySell;
import com.stock.service.StatisticsStockI;
import com.stock.util.DateUtil;
import com.stock.util.MapUtils;

public class StatisticsStockImpl implements StatisticsStockI {
	Map<String, Map<String, Object>> cache = new HashMap<String, Map<String, Object>>();

	@Autowired
	private LastStatisticsDayMapper lastStatisticsDayMapper;
	@Autowired
	private StockMainMapper mainMapper;
	@Autowired
	private StockBuySellMapper stockBuySellMapper;

	public Map<String, Object> statistics() throws ParseException {
		List<String> symbols = mainMapper.selectAll();
		String day = lastStatisticsDayMapper.selectByPrimaryKey();
		for (String symbol : symbols) {
			List<StockBuySell> list = getRecord(symbol, day);
			if(list == null || list.size() == 0){
				return MapUtils.createSuccessMap();
			}
			for (StockBuySell stockBuySell : list) {
				
			}
		}
		return null;
	}

	private List<StockBuySell> getRecord(String symbol, String day) throws ParseException {
		List<StockBuySell> list = stockBuySellMapper.selectByDay(MapUtils.createMap("symbol", symbol, "day", day));
		while (list == null || list.size() == 0) {
			Date date = DateUtil.strToDate(day);
			if(date.getTime() > new java.sql.Date(System.currentTimeMillis()).getTime()){
				break;
			}
			day = DateUtil.dateToStardardYYYYMMDD(DateUtil.getDateAfter(date, 1));
			list = stockBuySellMapper.selectByDay(MapUtils.createMap("symbol", symbol, "day", day));
		}
		return list;
	}
}
