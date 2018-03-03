package com.stock.analysis.service;

import java.sql.Date;
import java.util.Map;

public interface ISearchService {

	Map<String, Object> findTopN(Date day, float lowLimit);
}
