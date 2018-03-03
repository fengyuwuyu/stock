package com.stock.service;

import java.util.Map;

import com.stock.model.StockQuery;

public interface MaxIncreaseSericeI {

	Map<String, Object> datalist(StockQuery query);
}
