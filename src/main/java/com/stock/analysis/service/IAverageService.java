package com.stock.analysis.service;

import java.util.Map;

public interface IAverageService {

	Map<String, Object> createAverageLine(String symbol, int... days);
}
