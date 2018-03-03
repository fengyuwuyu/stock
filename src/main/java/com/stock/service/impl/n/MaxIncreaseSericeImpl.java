package com.stock.service.impl.n;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.StockMaxIncreaseMapper;
import com.stock.model.StockMaxIncrease;
import com.stock.model.StockQuery;
import com.stock.service.MaxIncreaseSericeI;
import com.stock.util.MapUtils;

@Service
public class MaxIncreaseSericeImpl implements MaxIncreaseSericeI {

	@Autowired
	private StockMaxIncreaseMapper maxIncreaseMapper;
	
	@Override
	public Map<String, Object> datalist(StockQuery query) {
		if(query == null || query.getBegin() == null) {
			return MapUtils.createSuccessMap("total", 0L, "rows", Collections.emptyList());
		}
		long total = maxIncreaseMapper.countByQuery(query);
		if(total == 0) {
			return MapUtils.createSuccessMap("total", 0L, "rows", Collections.emptyList());
		}
		List<StockMaxIncrease> list = maxIncreaseMapper.findByQuery(query);
		return MapUtils.createSuccessMap("total", total, "rows", list);
	}

}
