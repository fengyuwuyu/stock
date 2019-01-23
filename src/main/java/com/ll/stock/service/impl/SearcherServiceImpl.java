package com.ll.stock.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ll.stock.model.StockAnalysisResult;
import com.ll.stock.model.type.SearchTypeEnum;
import com.ll.stock.service.SearcherServiceI;
import com.ll.stock.strategy.impl.DecreaseAndSerialLowVolumeStrategy;
import com.ll.stock.strategy.impl.MakeMoneyStrategy;
import com.ll.stock.strategy.impl.MaxIncreaseStrategy;
import com.ll.stock.strategy.impl.NearlyTenDayStrategy;
import com.ll.stock.strategy.impl.SerialIncreaseAndLowVolumeStrategy;
import com.ll.stock.strategy.impl.SerialIncreaseStrategy;
import com.ll.stock.strategy.impl.SerialLowVolumeStrategy;
import com.ll.stock.util.StockUtils;
import com.stock.dao.StockMainMapper;
import com.stock.model.StockMain;
import com.stock.util.MapUtils;

@Service
public class SearcherServiceImpl implements SearcherServiceI {
	
	private Logger log = Logger.getLogger(getClass());
	@Autowired
	private StockMainMapper stockMainMapper;
	@Autowired
	MaxIncreaseStrategy maxIncreaseStrategy;
	@Autowired
	SerialLowVolumeStrategy serialLowVolumeStrategy;
	@Autowired
	SerialIncreaseStrategy serialIncreaseStrategy;
	@Autowired
	DecreaseAndSerialLowVolumeStrategy decreaseAndSerialLowVolumeStrategy;
	@Autowired
	SerialIncreaseAndLowVolumeStrategy serialIncreaseAndLowVolumeStrategy;
	@Autowired
	NearlyTenDayStrategy nearlyTenDayStrategy;
	@Autowired
	MakeMoneyStrategy makeMoneyStrategy;

	@Override
	public Map<String, Object> findIncreaseTopn(Date begin, float limit, Integer searchType) {
		SearchTypeEnum type = SearchTypeEnum.valueOf(searchType);
		if (type == null) {
			return MapUtils.createFailedMap("msg", "illegal searchType [%s]", searchType);
		}
		List<StockMain> stockMainList = stockMainMapper.findAll(begin);
		Map<String, List<StockMain>> stockMainMap = stockMainList.stream()
				.collect(Collectors.groupingBy(StockMain::getSymbol));
		List<StockAnalysisResult> result = new ArrayList<>(50);
//		List<StockAnalysisResult> list = new ArrayList<>();
		for (List<StockMain> stockMains : stockMainMap.values()) {
			float max = 0;
			int maxIndex = stockMains.size() - 1;
			int index = -1;
			int count = 0;
			int maxDay = 0;
			for (StockMain stockMain : stockMains) {
				if (stockMain.getDay().after(begin)) {
					
					if (stockMain.getClose() > max) {
						max = stockMain.getClose();
						maxIndex = count;
					}
					
					maxDay++;
					if (maxDay >= 20) {
						break;
					}
					
				} else {
					index++;
				}
				count++;
			}
			
			if (index == -1) {
				index = maxIndex;
			}
			
			try {
				switch (type) {
				case MAX_INCREASE:
					maxIncreaseStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case SERIAL_LOW_VOLUME:
					serialLowVolumeStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case SERIAL_INCREASE:
					serialIncreaseStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case DECREASE_AND_SERIAL_LOW_VOLUME:
					decreaseAndSerialLowVolumeStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case SERIAL_INCREASE_AND_LOW_VOLUME:
					serialIncreaseAndLowVolumeStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case NEARLY_TEN_DAY:
					nearlyTenDayStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case MAKE_MONEY:
					makeMoneyStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				log.warn(String.format("symbol = %s, maxIndex = %d, maxLen = %d, error = %s",  stockMains.get(0).getSymbol(), maxIndex, stockMains.size(), e.getMessage()));
			}
		}
		if (type == SearchTypeEnum.MAKE_MONEY) {
//			StockUtils.statistics(result);
		}
		return MapUtils.createSuccessMap("rows", result, "total", result.size());
	}

}
