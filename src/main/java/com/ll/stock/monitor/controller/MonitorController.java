package com.ll.stock.monitor.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.util.MapUtils;

@RequestMapping("/monitor")
@Controller
public class MonitorController {
	
	@RequestMapping("/add.do")
	@ResponseBody
	public Map<String, Object> add() {
	
		return MapUtils.createSuccessMap();
	}
	
}
