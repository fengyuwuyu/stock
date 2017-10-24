package com.stock.service;

import java.util.Map;

public interface StockFinderI {

	/**
	 * 查找处在低位的股票
	 * @return
	 */
	Map<String, Object> findLows();
	
	/**
	 * 查找处在中位且上升的股票
	 * @return
	 */
	Map<String, Object> findMids();
	
	/**
	 * 查找处在高位的股票
	 * @return
	 */
	Map<String, Object> findHighs();
	
}
