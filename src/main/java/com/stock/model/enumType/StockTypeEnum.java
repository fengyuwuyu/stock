package com.stock.model.enumType;

import java.util.HashMap;
import java.util.Map;

public enum StockTypeEnum {

	LOW_SHOCK(1, "低位震荡"), HIGH_SHOCK(2, "高位震荡"), LOW_INCREASE(3, "低位上涨"), HIGH_DECREASE(4, "高位下跌") ;
	
	private int id;
	private String desc;
	private StockTypeEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	public String getDesc() {
		return desc;
	}
	
	private static Map<Integer, StockTypeEnum> cache = new HashMap<>(StockTypeEnum.values().length);
	
	static {
		for (StockTypeEnum type : StockTypeEnum.values()) {
			cache.put(type.getId(), type);
		}
	}
	
	public static StockTypeEnum valueOf(int id){
		return cache.get(id);
	}
	
}
