package com.stock.service;

import java.sql.Date;
import java.util.Map;

public interface JunXianServiceI {

	Map<String, Object> createLine(Date begin, String stockId, int... days);
}
