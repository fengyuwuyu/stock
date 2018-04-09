package com.stock.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.JunXianDayMapper;
import com.stock.dao.StockFirstSelectMapper;
import com.stock.dao.StockMainMapper;
import com.stock.model.CurStock;
import com.stock.model.CurStock.Stock;
import com.stock.model.JunXianDay;
import com.stock.model.StockAnalyseBase;
import com.stock.model.StockFilterBean;
import com.stock.model.StockFirstSelect;
import com.stock.model.StockMain;
import com.stock.model.StockMainAnalyse;
import com.stock.model.StockQuery;
import com.stock.service.SearchMachineI;
import com.stock.util.CommonsUtil;
import com.stock.util.MapUtils;

@Service
public class SearchMachineImpl implements SearchMachineI {

	Logger log = Logger.getLogger(getClass());
	private StockMainMapper stockMainMapper;
	
	@Autowired
	private JunXianDayMapper junXianDayMapper;
	
	@Autowired
	private StockFirstSelectMapper firstSelectMapper;

	@Autowired
	public void setStockMainMapper(StockMainMapper stockMainMapper) {
		this.stockMainMapper = stockMainMapper;
	}
	
	public void initData(Date day) {
		StockQuery query = new StockQuery();
		query.setBegin(day);
		
		find(query);
	}

	public Map<String, Object> find(StockQuery query) {
		Date begin = query.getBegin() == null ? new Date(System.currentTimeMillis()) : query.getBegin();
		
		List<StockFirstSelect> selectList = findFirstSelectByDay(begin);
		if(selectList != null && selectList.size() > 0) {
			return MapUtils.createSuccessMap("rows", selectList, "total", selectList.size());
		}
		List<StockMain> row = new ArrayList<>();
		query.setBegin(begin);
		List<StockMain> stockInfos = stockMainMapper.findByDay(query);
		row = new ArrayList<>();
		
		for (StockMain stockMain : stockInfos) {
			// 1、股价处在150和200日均线之上
			JunXianDay junXianDay4 = junXianDayMapper.findBySumbolAndTypeAndDay(MapUtils.createMap("symbol", stockMain.getSymbol(), "type", 4, "day", begin));
			JunXianDay junXianDay9 = junXianDayMapper.findBySumbolAndTypeAndDay(MapUtils.createMap("symbol", stockMain.getSymbol(), "type", 9, "day", begin));
			JunXianDay junXianDay13 = junXianDayMapper.findBySumbolAndTypeAndDay(MapUtils.createMap("symbol", stockMain.getSymbol(), "type", 13, "day", begin));
			JunXianDay junXianDay20 = junXianDayMapper.findBySumbolAndTypeAndDay(MapUtils.createMap("symbol", stockMain.getSymbol(), "type", 20, "day", begin));
			JunXianDay junXianDay37 = junXianDayMapper.findBySumbolAndTypeAndDay(MapUtils.createMap("symbol", stockMain.getSymbol(), "type", 37, "day", begin));
			JunXianDay junXianDay49 = junXianDayMapper.findBySumbolAndTypeAndDay(MapUtils.createMap("symbol", stockMain.getSymbol(), "type", 49, "day", begin));
			JunXianDay junXianDay87 = junXianDayMapper.findBySumbolAndTypeAndDay(MapUtils.createMap("symbol", stockMain.getSymbol(), "type", 87, "day", begin));
			
			String symbol = stockMain.getSymbol();
			
			
			//2、150日均线在200日均线之上
//			if(junXianDay4 == null || stockMain.getClose() < junXianDay4.getPrice()) {
////				log.info("symbol = " + symbol + ", 当前价格小于4日均线！");
//				continue;
//			}
			
			if(junXianDay9 == null || junXianDay4.getPrice() < junXianDay9.getPrice()) {
//				log.info("symbol = " + symbol + ", 4日均线小于9日均线！");
				continue;
			}
			
			if(junXianDay13 == null || junXianDay9.getPrice() < junXianDay13.getPrice()) {
//				log.info("symbol = " + symbol + ", 9日均线小于13日均线！");
				continue;
			}
			
			if(junXianDay20 == null || junXianDay13.getPrice() < junXianDay20.getPrice()) {
//				log.info("symbol = " + symbol + ", 13日均线小于20日均线！");
				continue;
			}
			
			if(junXianDay37 == null || junXianDay20.getPrice() < junXianDay37.getPrice()) {
//				log.info("symbol = " + symbol + ", 20日均线小于37日均线！");
				continue;
			}
			
			if(junXianDay49 == null || junXianDay37.getPrice() < junXianDay49.getPrice()) {
//				log.info("symbol = " + symbol + ", 37日均线小于49日均线！");
				continue;
			}
			
			if(junXianDay87 == null || junXianDay49.getPrice() < junXianDay87.getPrice()) {
//				log.info("symbol = " + symbol + ", 49日均线小于87日均线！");
				continue;
			}
			
			
			// 3、200日均线至少涨了1个月
			
			// 4、49日移动平均值高于150及200日移动平均值
			
			query.setSymbol(symbol);
			List<StockMain> stockList = stockMainMapper.findBySymbolAndDay(query);
			if(stockList != null && stockList.size() > 0) {
				float maxPrice = 0f;
				for (StockMain stockMain2 : stockList) {
					if(stockMain2.getClose() > maxPrice) {
						maxPrice = stockMain2.getClose();
					}
				}
				stockMain.setMaxIncrease((maxPrice - stockMain.getClose()) * 100 / stockMain.getClose() ); 
			}
			
			row.add(stockMain);
			// 5、当前价格高于50日移动平均值
			
			// 6、当前股价比最近一年最低股价至少高30%
			
			// 7、当前价格处在最近一年最高股价的70%以内，距离最高价越近越好
			
		}
		try {
			if(row.size() > 0)
				this.firstSelectMapper.insertList(MapUtils.createMap("list", row));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return MapUtils.createSuccessMap("rows", row, "total", row.size());
	}
	
	

	private List<StockFirstSelect> findFirstSelectByDay(Date begin) {
		return this.firstSelectMapper.selectByDay(begin);
	}

	/**
	 * 根据查询条件返回股票 1、找到股价处于相对低点、成交量较大且存在或接近黄金交叉点
	 */
	public Map<String, Object> searcher(StockQuery query) {
		
		return MapUtils.createSuccessMap();
	}

	public List<StockAnalyseBase> findGlodStock(StockQuery query,
			List<StockAnalyseBase> list) {
		if (list != null) {

		} else {

		}
		return null;
	}

	public List<StockAnalyseBase> findLowPoint(StockQuery query,
			List<StockAnalyseBase> list) {
		if (list != null) {
			for (StockAnalyseBase stock : list) {
				StockAnalyseBase stockAnalyseBase = this.stockMainMapper
						.selectStockAnalyse(MapUtils.createMap("symbol",
								stock.getSymbol()));
				
			}

		} else {

		}
		return list;
	}

	
	public List<StockAnalyseBase> findHighVolume(StockQuery query,
			List<StockAnalyseBase> list) {
		if (list != null) {

		} else {

		}
		return null;
	}

	
	public Map<String, Object> query(StockQuery query) {
		if (query.getBegin() == null) {
			return MapUtils.createSuccessMap("rows",new ArrayList<StockMainAnalyse>(),"total",0);
		}
		String begin = CommonsUtil.formatDateToString1(query.getBegin());
		
		List<StockFilterBean> list = this.stockMainMapper.selectAnalyse1(MapUtils.createMap("begin",begin,"remainDays",(query.getRemainDays()==0?10:query.getRemainDays())));
		List<StockFilterBean> inserts = new ArrayList<StockFilterBean>();
		if (list != null && list.size() > 0) {
			for (StockFilterBean analyse : list) {
				boolean insert = analyse.analyse(begin, 0);
				if (insert && analyse.getLastIncrease() >= query.getMinIncrease() && (query.getType()==0||query.getType()==analyse.getType())) {
					inserts.add(analyse);
				}
			}
		}
		return MapUtils.createSuccessMap("rows", inserts, "total",
				inserts.size());
	
	}
	
	public void initData(String symbol){
		
	}

	
	public Map<String, Object> queryCur(Date end) {
		List<String> days = this.stockMainMapper.selectByDay(end);
		if(days == null || days.size() == 0){
			return MapUtils.createFailedMap("msg", "数据库中无数据或程序异常！");
		}
		List<CurStock> curStocks = this.stockMainMapper.selectCurStock(days);
		List<CurStock> list = new ArrayList<CurStock>();
		for (CurStock curStock : curStocks) {
			Stock[] res =  getMaxMin(curStock.getStock());
			
		}
		return null;
	}
	
	private Stock[] getMaxMin(List<Stock> stocks){
		Stock[] result = new Stock[2];
		
		return result;
	}

}
