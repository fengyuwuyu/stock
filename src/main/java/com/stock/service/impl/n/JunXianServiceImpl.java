package com.stock.service.impl.n;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.JunXianDayMapper;
import com.stock.dao.StockMainMapper;
import com.stock.dao.StockTableInfoMapper;
import com.stock.model.StockMain;
import com.stock.model.StockQuery;
import com.stock.service.JunXianServiceI;
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
	public Map<String, Object> createLine(String symbol, int... days) {
		if(StringUtil.isNullEmpty(symbol) || days == null || days.length == 0) {
			return MapUtils.createSuccessMap("This action seems to unnecessary");
		}
		StockQuery query = new StockQuery();
		query.setSymbol(symbol);
		List<StockMain> stocks = this.stockMainMapper.selectBySymbol(query);
		
		float total = 0.0f;
		float avg = 0.0f;
		
		for (int day : days) {
			total = 0f;
			if(stocks == null || stocks.size() < day) {
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
			Map<String, Object> first = MapUtils.createMap("price", avg, "day", stocks.get(day - 1).getDay());
			prices.add(first);
			
			for (int i = day; i < stocks.size(); i++) {
				index = index % day;
				priceBak = priceBaks[index];
				priceBaks[index++] = stocks.get(i).getClose();
				
				total += stocks.get(i).getClose();
				total -= priceBak;
				avg = total / day;
				
				Map<String, Object> map = MapUtils.createMap("price", avg, "day", stocks.get(i).getDay());
				prices.add(map);		
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

}
