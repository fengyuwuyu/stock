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
import com.stock.model.CacheItem;
import com.stock.model.StockBuySell;
import com.stock.model.statistics.StockStatisticsDetail;
import com.stock.service.StatisticsStockI;
import com.stock.util.DateUtil;
import com.stock.util.MapUtils;
import com.stock.util.StockCache;

public class StatisticsStockImpl implements StatisticsStockI {
	public Map<String, StockStatisticsDetail> cache = new HashMap<String, StockStatisticsDetail>();
	public static boolean init = false;

	@Autowired
	private LastStatisticsDayMapper lastStatisticsDayMapper;
	@Autowired
	private StockMainMapper mainMapper;
	@Autowired
	private StockBuySellMapper stockBuySellMapper;

	public Map<String, Object> statistics() throws ParseException {
		List<String> codes = this.mainMapper.selectAllCodes();
		
		
		List<String> symbols = mainMapper.selectAll();
		String day = lastStatisticsDayMapper.selectByPrimaryKey();
		for (String symbol : symbols) {
			List<StockBuySell> list = getRecord(symbol, day);
			if(list == null || list.size() == 0){
				continue;
			}
			StockStatisticsDetail detail = getStockStatisticsDetail(symbol);
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
	
	
	private StockStatisticsDetail getStockStatisticsDetail(String symbol){
		StockStatisticsDetail result = cache.get(symbol);
		if(result == null){
			result = new StockStatisticsDetail();
		}
		return result;
	}
	
	private void setCache(StockBuySell stockBuySell) throws ParseException {
		StockStatisticsDetail detail = getStockStatisticsDetail(stockBuySell.getSymbol());
		//max;
		detail.setMax(stockBuySell.getPrice());
		//min;
		
		//open;
		
		//increase;
		
		//volume;
		
		//perMinuteVolume;
		
		//totalVolume = 0L;
		
		//count = 0;
		
		//maxMinuteVolume;
		
		//maxMinuteVolumeDate;
		
		//maxMinutePrice;
		
		//maxMinutePriceDate;
		
		//volumes = new StringBuilder();
		
		
		if(detail.getMaxMinuteVolume() == null || detail.getMaxMinuteVolume() < stockBuySell.getVolume() - detail.getVolume()){
			detail.setMaxMinuteVolume(stockBuySell.getVolume() - detail.getVolume());
			detail.setMaxMinuteVolumeDate(DateUtil.parseToStandardDate(stockBuySell.getTime()));
		}
		
		detail.setTotalVolume(stockBuySell.getVolume());
	}
	
	private Double computeSell(StockBuySell stockBuySell){
		return compute(stockBuySell.getAsk1(), stockBuySell.getAsk2(), stockBuySell.getAsk3(), stockBuySell.getAsk4(), stockBuySell.getAsk5(),
				stockBuySell.getAskvol1(), stockBuySell.getAskvol2(), stockBuySell.getAskvol3(), stockBuySell.getAskvol4(), stockBuySell.getAskvol5());
	}
	
	private Double compute(Double ask1, Double ask2, Double ask3, Double ask4, Double ask5, Integer askvol1,
			Integer askvol2, Integer askvol3, Integer askvol4, Integer askvol5) {
		return (ask1 * askvol1 + ask2 * askvol2 + ask3 * askvol3 + ask4 * askvol4 + ask5 * askvol5) / 5;
	}

	private Double computeBuy(StockBuySell stockBuySell){
		return compute(stockBuySell.getBid1(), stockBuySell.getBid2(), stockBuySell.getBid3(), stockBuySell.getBid4(), stockBuySell.getBid5(),
				stockBuySell.getBidvol1(), stockBuySell.getBidvol2(), stockBuySell.getBidvol3(), stockBuySell.getBidvol4(), stockBuySell.getBidvol5());
	}
	
	public void initCache(){
		for(CacheItem item : StockCache.prePrices.values()){
			StockStatisticsDetail detail = getStockStatisticsDetail(item.getSymbol());
			detail.setSymbol(item.getSymbol());
		}
	}
	
}
