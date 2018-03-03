package com.stock.analysis.service.impl;

import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stock.analysis.service.ISearchService;

@Service
public class SearchServiceImpl implements ISearchService {

	@Override
	public Map<String, Object> findTopN(Date day, float lowLimit) {
		
		return null;
	}

}
