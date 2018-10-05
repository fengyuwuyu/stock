package com.ll.stock.service;

import java.sql.Date;
import java.util.Map;

public interface SearcherServiceI {

	Map<String, Object> findIncreaseTopn(Date begin, float limit, Integer searchType);
}
