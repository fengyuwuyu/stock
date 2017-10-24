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
import com.stock.service.InitStockServiceI;
import com.stock.service.SearchMachineI;
import com.stock.service.StockAnalyseJobI;
import com.stock.service.StockMainServiceI;
import com.stock.service.StockServiceI;
import com.stock.service.TestServiceI;
import com.stock.task.DownloadDetail;
import com.stock.util.MapUtils;

@Controller
public class TestController {

	private StockServiceI detailSaveServiceI;

	private StockMainServiceI stockMainServiceI;
	private InitStockServiceI initStockServiceI;
	private StockAnalyseJobI stockAnalyseJobI;
	private StockMainMapper stockMainMapper;
	private SearchMachineI searchMachineI;

	@Autowired
	StockBuySellMapper buySellMapper;
	@Autowired
	private TestServiceI testServiceI;
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
	
	@Autowired
	private DownloadDetail downloadDetail;

	@RequestMapping("test.do")
	@ResponseBody
	public Map<String, Object> test() throws Exception {
//		log.info("test");
//		initStockServiceI.initStockEveryDay();
		downloadDetail.execute();
		return MapUtils.createSuccessMap();
	}

	@RequestMapping("test1.do")
	@ResponseBody
	public Map<String, Object> test1() throws Exception {
		initStockServiceI.initCjmxPerWeek();
		stockAnalyseJobI.initStockAnalyse();
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
		for (int i = 2016; i <= 2018; i++) {
			for(int j = 1; j <= 12; j++){
				for(int k = 1; k <= 31; k++){
					int t = 0;
					int v = 0;
					if(j == 2){
						if(k > 28){
							continue;
						}
					}
					if(!nums.contains(j)){
						if(k > 30){
							continue;
						}else if(k == 30){
							t = i * 10000 + j *100 + k;
							if(j == 12){
								v = (i + 1) * 10000 + 1 *100 + 1;
							}else{
								v = i * 10000 + (j + 1) *100 + 1;
							}
							System.out.println("PARTITION t"+ t +" VALUES LESS THAN ("+ v +"),");
							continue;
						}
					}else if(k == 31){
						t = i * 10000 + j *100 + k;
						if(j == 12){
							v = (i + 1) * 10000 + 1 *100 + 1;
						}else{
							v = i * 10000 + (j + 1) *100 + 1;
						}
						System.out.println("PARTITION t"+ t +" VALUES LESS THAN ("+ v +"),");
						continue;
					}
					t = i * 10000 + j *100 + k;
					v = t + 1;
					System.out.println("PARTITION t"+ t +" VALUES LESS THAN ("+ v +"),");
				}
			}
		}
	}
	
	private static List<Integer> nums = new ArrayList<>();
	static {
		nums.add(1);
		nums.add(3);
		nums.add(5);
		nums.add(7);
		nums.add(8);
		nums.add(10);
		nums.add(12);
		
	}

	private static int chargeDay(int i, int j, int k) {
		if((i % 100 == 0 && i % 400 ==0) || (i % 100 != 0 && i % 4 == 0)){
			if(j == 2 && k == 29){
				return i * 10000 + 300 + 1;
			}
		}else if(j == 2 && k == 28){
			return i * 10000 + 300 + 1;
		}
		if(nums.contains(j)){
			if(k == 31){
				return i * 10000 + (j + 1) * 100 + 1;
			}
		}else if(k == 30){
			return i * 10000 + (j + 1) * 100 + 1;
		}
		return i * 10000 + j * 100 + k + 1;
	}
}
