package com.stock.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.StockBuySellMapper;
import com.stock.service.UtilsServiceI;

@Service
public class UtilsServiceImpl implements UtilsServiceI {

	private StockBuySellMapper stockBuySellMapper;
	
	@Autowired
	public void setStockBuySellMapper(StockBuySellMapper stockBuySellMapper) {
		this.stockBuySellMapper = stockBuySellMapper;
	}



	
	public Map<String, Object> getDays() {
		List<String> days = stockBuySellMapper.selectDays();
		String maxHoliday = stockBuySellMapper.selectMaxHoliday();
		if(days!=null&&days.size()>0){
			for (String day : days) {
				
			}
		}
		return null;
	}

}
