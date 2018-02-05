package com.stock.service.impl.n;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.JunXianDayMapper;
import com.stock.dao.StockMainMapper;
import com.stock.model.StockMain;
import com.stock.model.StockQuery;
import com.stock.service.JunXianServiceI;
import com.stock.util.DateUtil;
import com.stock.util.MapUtils;
import com.stock.util.StringUtil;

@Service
public class JunXianServiceImpl implements JunXianServiceI {

	@Autowired
	StockMainMapper stockMainMapper;

	@Autowired
	JunXianDayMapper junXianDayMapper;
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Map<String, Object> createLine(Date lastDay, Date begin, String symbol, int... days) {
		if (StringUtil.isNullEmpty(symbol) || days == null || days.length == 0) {
			return MapUtils.createSuccessMap("This action seems to unnecessary");
		}
		StockQuery query = new StockQuery();
		query.setSymbol(symbol);
		query.setBegin(begin);
		List<StockMain> stocks = this.stockMainMapper.selectBySymbol(query);

		float total = 0.0f;
		float avg = 0.0f;

		for (int day : days) {
			total = 0f;
			if (stocks == null || stocks.size() < day) {
				log.info("the data is too small, day = %d", day);
				continue;
			}

			Map<String, Object> param = new HashMap<>();
			List<Map<String, Object>> prices = new ArrayList<Map<String, Object>>(stocks.size() - day + 1);
			float[] priceBaks = new float[day];
			float priceBak = 0.0f;
			int index = 0;
			priceBak = stocks.get(0).getClose();

			for (int i = 0; i < day; i++) {
				priceBaks[i] = stocks.get(i).getClose();
				total += stocks.get(i).getClose();
			}

			avg = total / day;
			Date computeDay = stocks.get(day - 1).getDay();
			if (DateUtil.isAfterDate(computeDay, lastDay)) {
				Map<String, Object> first = MapUtils.createMap("price", avg, "day", computeDay);
				prices.add(first);
			}

			for (int i = day; i < stocks.size(); i++) {
				index = index % day;
				priceBak = priceBaks[index];
				priceBaks[index++] = stocks.get(i).getClose();

				total += stocks.get(i).getClose();
				total -= priceBak;
				avg = total / day;
				computeDay = stocks.get(i).getDay();
				if (DateUtil.isAfterDate(computeDay, lastDay)) {
					Map<String, Object> map = MapUtils.createMap("price", avg, "day", stocks.get(i).getDay());
					prices.add(map);
				}
			}

			param.put("symbol", symbol);
			param.put("type", day);
			param.put("prices", prices);

			try {
				junXianDayMapper.insertByMap(param);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return null;
	}

	@Override
	public Date findLastDay() {
		return junXianDayMapper.findLastDay();
	}

	@Override
	public void createMaxIncrease(Date lastDay) {
		StockQuery query = new StockQuery();
		query.setBegin(lastDay);
		query.setRemainDays(-1);
		List<StockMain> stocks = stockMainMapper.findBySymbolAndDay(query);
		Map<String, List<StockMain>> stockMap = getStockMap(stocks);
		
		Map<Date, List<StockMain>> matchConditionMap = new HashMap<>();
		
		for (Entry<String, List<StockMain>> entry : stockMap.entrySet()) {
			List<StockMain> stockList = entry.getValue();
			for (int i = 1; i < stockList.size(); i++) {
				if(matchCondition(stockList, i)) {
					putMaxIncrease(matchConditionMap, stockList.get(i - 1));
				}
			}
			
		}
		
		for (Entry<Date, List<StockMain>> entry : matchConditionMap.entrySet()) {
			Date day = entry.getKey();
			List<StockMain> stockList = entry.getValue();
			
			
		}
		
	}
	
	private boolean matchCondition(List<StockMain> stockList, int index) {
		float maxIncrease = stockList.get(index).getClose();
		for (int i = index; i < index + 10; i++) {
			if(stockList.get(i).getClose() > maxIncrease) {
				maxIncrease = stockList.get(i).getClose();
			}
		}
		
		float increase = (maxIncrease - stockList.get(index - 1).getClose()) / stockList.get(index - 1).getClose();
		if(increase >= 0.15) {
			stockList.get(index - 1).setMaxIncrease(increase);
			return true;
		}
		return false;
	}

	private void putMaxIncrease(Map<Date, List<StockMain>> matchConditionMap, StockMain stock) {
		List<StockMain> stockMainList = matchConditionMap.get(stock.getDay());
		if(stockMainList == null) {
			stockMainList = new ArrayList<>();
			matchConditionMap.put(stock.getDay(), stockMainList);
		}
		
		stockMainList.add(stock);
	}

	private Map<String, List<StockMain>> getStockMap(List<StockMain> stocks) {
		Map<String, List<StockMain>> map = new HashMap<>();
		for (StockMain stockMain : stocks) {
			List<StockMain> list = map.get(stockMain.getSymbol());
			if(list == null) {
				list = new ArrayList<>();
				map.put(stockMain.getSymbol(), list);
			}
			list.add(stockMain);
		}
		return map;
	}

}
