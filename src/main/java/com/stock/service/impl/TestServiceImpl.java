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
			Integer id = 0;
			Map<String, Object> map = MapUtils.createMap("id", id, "size", size);
			long now = System.currentTimeMillis();
			List<StockBuySell> list = buySellMapper.selectByPage(map);
			long end = System.currentTimeMillis();
			System.out.println(end-now);
			if(list != null && list.size() > 0){
				id = list.get(list.size() - 1).getId();
				System.out.println("插入数据的最小id ： " + list.get(0).getId() + "， 插入数据的最大id ： " + id);
				Map<String, Object> param = MapUtils.createMap("list", list);
				buySellMapper.insertList(param);
				now = System.currentTimeMillis();
				System.out.println(now-end);
				buySellMapper.deleteById(MapUtils.createMap("id", id));
				System.out.println(System.currentTimeMillis()-now);
			}else{
				System.out.println("查询数据为空！");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
