package com.stock.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.model.StockQuery;
import com.stock.service.MaxIncreaseSericeI;
import com.stock.util.MapUtils;

@Controller
@RequestMapping("/maxIncrease")
public class MaxIncreaseController {
	
	@Autowired
	private MaxIncreaseSericeI maxIncreaseSericeI;
	
	@RequestMapping("/datalist.do")
	@ResponseBody
	public Map<String, Object> datalist(StockQuery query) {
		return maxIncreaseSericeI.datalist(query);
	}
	
}
