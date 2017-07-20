package com.stock.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.StockMainMapper;
import com.stock.model.CurStock;
import com.stock.model.CurStock.Stock;
import com.stock.model.StockAnalyseBase;
import com.stock.model.StockFilterBean;
import com.stock.model.StockMainAnalyse;
import com.stock.model.StockQuery;
import com.stock.service.SearchMachineI;
import com.stock.util.CommonsUtil;
import com.stock.util.MapUtils;

@Service
public class SearchMachineImpl implements SearchMachineI {

	private StockMainMapper stockMainMapper;

	@Autowired
	public void setStockMainMapper(StockMainMapper stockMainMapper) {
		this.stockMainMapper = stockMainMapper;
	}

	public Map<String, Object> find(StockQuery query) {
		if (query.getBegin() == null) {
			return MapUtils.createFailedMap("msg", "请选择起始时间。。。");
		}
		String begin = CommonsUtil.formatDateToString1(query.getBegin());
		List<StockMainAnalyse> list = this.stockMainMapper.selectAnalyse(MapUtils.createMap("begin",begin,"remainDays",10));
		List<StockMainAnalyse> inserts = new ArrayList<StockMainAnalyse>();
		if (list != null && list.size() > 0) {
			for (StockMainAnalyse analyse : list) {
				boolean insert = analyse.analyse(begin, 0);
				if (insert && analyse.getLastIncrease() >= 15) {
					inserts.add(analyse);
				}
			}
		}
		// Collections.sort(list, new Comparator<StockMainAnalyse>() {
		//
		// @Override
		// public int compare(StockMainAnalyse o1, StockMainAnalyse o2) {
		// if(o1.getLastIncrease()-o2.getLastIncrease()>0){
		// return 1;
		// }else if(o1.getLastIncrease()-o2.getLastIncrease()<0){
		// return -1;
		// }
		// return 0;
		// }
		// });
		return MapUtils.createSuccessMap("rows", inserts, "total",
				inserts.size());
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

	@Override
	public List<StockAnalyseBase> findHighVolume(StockQuery query,
			List<StockAnalyseBase> list) {
		if (list != null) {

		} else {

		}
		return null;
	}

	@Override
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

	@Override
	public Map<String, Object> queryCur(Date end) {
		List<String> days = this.stockMainMapper.selectByDay(end);
		if(days == null || days.size() == 0){
			return MapUtils.createFailedMap("msg", "数据库中无数据或程序异常！");
		}
		List<CurStock> curStocks = this.stockMainMapper.selectCurStock(days);
		List<CurStock> list = new ArrayList<>();
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
