package com.stock.controller;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.dao.StockFirstSelectMapper;
import com.stock.model.StockQuery;
import com.stock.service.SearchMachineI;
import com.stock.util.MapUtils;

@RequestMapping("searcher")
@Controller
public class SearchMachineController {

	private SearchMachineI searchMachineI;
	@Autowired
	StockFirstSelectMapper firstSelectMapper;

	@Autowired
	public void setSearchMachineI(SearchMachineI searchMachineI) {
		this.searchMachineI = searchMachineI;
	}
	
	@RequestMapping("find.do")
	@ResponseBody
	public Map<String,Object> find(StockQuery query){
		if(query == null || query.getBegin() == null) {
			return MapUtils.createSuccessMap("rows", Collections.emptyList(), "total", 0);
		}
		return searchMachineI.find(query);
	}
	
	@RequestMapping("query.do")
	@ResponseBody
	public Map<String,Object> query(StockQuery query){
		return searchMachineI.query(query);
	}
	
	@RequestMapping("initData.do")
	@ResponseBody
	public Map<String,Object> initData(StockQuery query){
		List<Date> days = firstSelectMapper.selectWorkerDay();
		for (Date date : days) {
			searchMachineI.initData(date);
		}
		return MapUtils.createSuccessMap();
	}
	
	private List<Date> getWorkDays(Date begin) {
		
		return null;
	}

	@RequestMapping("queryCur.do")
	@ResponseBody
	public Map<String,Object> queryCur(Date end){
		end = end == null ? new Date(new java.util.Date().getTime()) : end;
		return searchMachineI.queryCur(end);
	}
}
