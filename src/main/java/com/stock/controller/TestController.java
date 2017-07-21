package com.stock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.dao.StockMainMapper;
import com.stock.model.StockBuySell;
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
		initStockServiceI.initBuyAndSell("2017-07-21");
		return MapUtils.createFailedMap();
	}

	@RequestMapping("test1.do")
	@ResponseBody
	public Map<String, Object> test1() throws Exception {
//		initStockServiceI.initCjmxPerWeek();

		List<StockBuySell> list = new ArrayList<StockBuySell>();
		String day = "2017-07-21";
		String code = "000001";
		String symbol = RandomUtil.randomString(6);
		double percent = RandomUtil.randomFloat(0, 10);
		double high = RandomUtil.randomFloat(0, 10);
		double price = RandomUtil.randomFloat(0, 10);
		double open = RandomUtil.randomFloat(0, 10);
		double low = RandomUtil.randomFloat(0, 10);
		String time = RandomUtil.randomString(10);
		double yestclose = RandomUtil.randomFloat(0, 10);
		long turnover = RandomUtil.randomLong();
		double updown = RandomUtil.randomFloat(0, 10);
		int volume = RandomUtil.randomInt(0, 10);
		double ask1 = RandomUtil.randomFloat(0, 10);
		double ask2 = RandomUtil.randomFloat(0, 10);
		double ask3 = RandomUtil.randomFloat(0, 10);
		double ask4 = RandomUtil.randomFloat(0, 10);
		double ask5 = RandomUtil.randomFloat(0, 10);
		int askvol1 = RandomUtil.randomInt(0, 12);
		int askvol2 = RandomUtil.randomInt(0, 12);
		int askvol3 = RandomUtil.randomInt(0, 12);
		int askvol4 = RandomUtil.randomInt(0, 12);
		int askvol5 = RandomUtil.randomInt(0, 12);
		double bid1 = RandomUtil.randomFloat(0, 10);
		double bid2 = RandomUtil.randomFloat(0, 10);
		double bid3 = RandomUtil.randomFloat(0, 10);
		double bid4 = RandomUtil.randomFloat(0, 10);
		double bid5 = RandomUtil.randomFloat(0, 10);
		int bidvol1 = RandomUtil.randomInt(0, 12);
		int bidvol2 = RandomUtil.randomInt(0, 12);
		int bidvol3 = RandomUtil.randomInt(0, 12);
		int bidvol4 = RandomUtil.randomInt(0, 12);
		int bidvol5 = RandomUtil.randomInt(0, 12);
		StockBuySell buySell = new StockBuySell(code, symbol, percent, high, price, open, low, time, yestclose, turnover, updown, volume, ask1, ask2, ask3, 
				ask4, ask5, askvol1, askvol2, askvol3, askvol4, askvol5, bid1, bid2, bid3, bid4, bid5, bidvol1, bidvol2, bidvol3, bidvol4, bidvol5, day);
		list.add(buySell);
		stockMainMapper.insertStockBuySell(MapUtils.createMap("list", list,"day",day));
//		stockAnalyseJobI.initStockAnalyse();
//		List<String> symbols = stockMainMapper.selectAll();
//		int index = 0;
//		if(symbols!=null&&symbols.size()>0){
//			for (String symbol : symbols) {
//				initStockServiceI.initJunX(symbol);
//				log.info("--------------------------"+(++index));
//			}
//		}
		return MapUtils.createSuccessMap();
		// this.detailSaveServiceI.volBigIncrease();
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("asf"+i);
		}
		System.out.println(list.toString());
	}
}
