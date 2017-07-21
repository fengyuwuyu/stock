package com.stock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.dao.StockBuySellMapper;
import com.stock.dao.StockMainMapper;
import com.stock.model.StockBuySell;
import com.stock.model.StockBuySellGroup;
import com.stock.service.InitStockServiceI;
import com.stock.service.SearchMachineI;
import com.stock.service.StockAnalyseJobI;
import com.stock.service.StockMainServiceI;
import com.stock.service.StockServiceI;
import com.stock.util.MapUtils;
import com.stock.util.RandomUtil;

@Controller
public class TestController {

	private StockServiceI detailSaveServiceI;

	private StockMainServiceI stockMainServiceI;
	private InitStockServiceI initStockServiceI;
	private StockAnalyseJobI stockAnalyseJobI;
	private StockMainMapper stockMainMapper;
	@Autowired
	private StockBuySellMapper buySellMapper;
	private SearchMachineI searchMachineI;
	private Logger log = Logger.getLogger(TestController.class);
	
	@Autowired
	public void setSearchMachineI(SearchMachineI searchMachineI) {
		this.searchMachineI = searchMachineI;
	}

	@Autowired
	public void setStockMainMapper(StockMainMapper stockMainMapper) {
		this.stockMainMapper = stockMainMapper;
	}

	@Autowired
	public void setStockAnalyseJobI(StockAnalyseJobI stockAnalyseJobI) {
		this.stockAnalyseJobI = stockAnalyseJobI;
	}

	@Autowired
	public void setInitStockServiceI(InitStockServiceI initStockServiceI) {
		this.initStockServiceI = initStockServiceI;
	}

	@Autowired
	public void setStockMainServiceI(StockMainServiceI stockMainServiceI) {
		this.stockMainServiceI = stockMainServiceI;
	}

	@Autowired
	public void setDetailSaveServiceI(StockServiceI detailSaveServiceI) {
		this.detailSaveServiceI = detailSaveServiceI;
	}

	@RequestMapping("test.do")
	@ResponseBody
	public Map<String, Object> test() {
//		initStockServiceI.initBuyAndSell("2017-07-21");
		List<StockBuySellGroup> buySellGroups = buySellMapper.selectRecord();
		if(buySellGroups != null && buySellGroups.size() > 0){
			for (StockBuySellGroup stockBuySellGroup : buySellGroups) {
				System.out.println(stockBuySellGroup);
			}
		}
		return MapUtils.createFailedMap();
	}

	@RequestMapping("test1.do")
	@ResponseBody
	public Map<String, Object> test1() throws Exception {
		
		return MapUtils.createSuccessMap();
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("asf"+i);
		}
		System.out.println(list.toString());
	}
}
