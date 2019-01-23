package com.ll.stock.model.type;

import java.util.HashMap;
import java.util.Map;

public enum SearchTypeEnum {

	MAKE_MONEY(-1, "必赚"), MAKE_MONEY1(-2, "必赚1"), MAX_INCREASE(1, "最大涨幅"), SERIAL_LOW_VOLUME(2, "连续低成交量"), DECREASE_AND_SERIAL_LOW_VOLUME(4, "下跌且低成交量"), SERIAL_INCREASE(8, "连续上涨"), 
	SERIAL_INCREASE_AND_LOW_VOLUME(16, "上涨且低成交量"), NEARLY_TEN_DAY(32, "近十天");

	private SearchTypeEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	private final static Map<Integer, SearchTypeEnum> CACHE = new HashMap<>();
	
	static {
		for (SearchTypeEnum type : SearchTypeEnum.values()) {
			CACHE.put(type.getType(), type);
		}
	}

	private int type;
	private String desc;
	
	public static SearchTypeEnum valueOf(Integer type) {
		return CACHE.get(type);
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

}
