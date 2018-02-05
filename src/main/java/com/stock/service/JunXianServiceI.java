package com.stock.service;

import java.sql.Date;
import java.util.Map;

public interface JunXianServiceI {

	Map<String, Object> createLine(Date lastDay, Date begin, String stockId, int... days);

	Date findLastDay();
}
