package com.stock.model;

import java.util.List;

/**
 * 
 * @author ll
 * 
 */
public class StockBuySellGroup {

	private String code;
	private String symbol;
	List<StockBuySellItem> item;

	public StockBuySellGroup() {
	}

	public StockBuySellGroup(String code, String symbol, List<StockBuySellItem> item) {
		super();
		this.code = code;
		this.symbol = symbol;
		this.item = item;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<StockBuySellItem> getItem() {
		return item;
	}

	public void setItem(List<StockBuySellItem> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "StockBuySellGroup [code=" + code + ", symbol=" + symbol + ", item=" + item + "]";
	}

}