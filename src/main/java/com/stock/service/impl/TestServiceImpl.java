package com.stock.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.StockBuySellMapper;
import com.stock.model.StockBuySell;
import com.stock.service.TestServiceI;
import com.stock.util.MapUtils;

@Service
public class TestServiceImpl implements TestServiceI {
	
	@Autowired
	StockBuySellMapper buySellMapper;

	@Override
	public boolean initStockBuySell(Integer page, Integer size) {
		try {
			Map<String, Object> map = MapUtils.createMap("page", page, "size", size);
			List<StockBuySell> list = buySellMapper.selectByPage(map);
			if(list != null && list.size() > 0){
				Map<String, Object> param = MapUtils.createMap("list", list);
				buySellMapper.insertList(param);
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
