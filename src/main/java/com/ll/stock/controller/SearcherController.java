package com.ll.stock.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ll.stock.model.type.SearchTypeEnum;
import com.ll.stock.service.SearcherServiceI;
import com.stock.util.MapUtils;

@Controller
@RequestMapping("searcher")
public class SearcherController {
	
	@Autowired
	SearcherServiceI searcherServiceI;

	@RequestMapping("findIncreaseTopn.do")
	@ResponseBody
	public Map<String, Object> findIncreaseTopn(Date begin, Float limit, Integer searchType) {
		if (begin == null) {
			return MapUtils.createSuccessMap("rows", Collections.emptyList(), "total", 0);
		}
		begin = begin == null ? new Date(System.currentTimeMillis()) : begin;
		limit = limit == null ? 10F : limit;
		searchType = searchType == null ? SearchTypeEnum.MAX_INCREASE.getType() : searchType;
		return searcherServiceI.findIncreaseTopn(begin, limit, searchType);
	}
	
	@RequestMapping("searchTypes.do")
	@ResponseBody
	public List<Map<String, Object>> getSearchTypes() {
		List<Map<String, Object>> result = new ArrayList<>();
		for (SearchTypeEnum type : SearchTypeEnum.values()) {
			Map<String, Object> item = new HashMap<>(2);
			item.put("id", type.getType());
			item.put("text", type.getDesc());
			result.add(item);
		}
		return result;
	}
	
	
}
