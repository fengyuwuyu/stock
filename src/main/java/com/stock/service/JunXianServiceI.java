package com.stock.service;

import java.util.Map;

public interface JunXianServiceI {

	Map<String, Object> createLine(String stockId, int... days);
}
