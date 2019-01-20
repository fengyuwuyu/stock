package com.ll.stock.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ll.stock.model.StockAnalysisResult;
import com.ll.stock.model.type.SearchTypeEnum;
import com.ll.stock.service.SearcherServiceI;
import com.ll.stock.strategy.impl.DecreaseAndSerialLowVolumeStrategy;
import com.ll.stock.strategy.impl.MaxIncreaseStrategy;
import com.ll.stock.strategy.impl.SerialIncreaseAndLowVolumeStrategy;
import com.ll.stock.strategy.impl.SerialIncreaseStrategy;
import com.ll.stock.strategy.impl.SerialLowVolumeStrategy;
import com.stock.dao.StockMainMapper;
import com.stock.model.StockMain;
import com.stock.util.MapUtils;

@Service
public class SearcherServiceImpl implements SearcherServiceI {

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

	@Override
	public Map<String, Object> findIncreaseTopn(Date begin, float limit, Integer searchType) {
		Date query = new Date(begin.getTime() - 86400 * 1000L * 40);
		List<StockMain> stockMainList = stockMainMapper.findAll(query);
		Map<String, List<StockMain>> stockMainMap = stockMainList.stream()
				.collect(Collectors.groupingBy(StockMain::getSymbol));
		List<StockAnalysisResult> increaseTopn = new ArrayList<>(50);
//		List<StockAnalysisResult> list = new ArrayList<>();
		SearchTypeEnum type = SearchTypeEnum.valueOf(searchType);
		if (type == null) {
			return MapUtils.createFailedMap("msg", "illegal searchType [%s]", searchType);
		}
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
			
			switch (type) {
			case MAX_INCREASE:
				maxIncreaseStrategy.analysis(stockMains, index, increaseTopn, maxIndex, begin, limit);
				break;
			case SERIAL_LOW_VOLUME:
				serialLowVolumeStrategy.analysis(stockMains, index, increaseTopn, maxIndex, begin, limit);
				break;
			case SERIAL_INCREASE:
//				MaxIncreaseStrategy.getInstance().analysis(stockMains, index, increaseTopn, max, begin, limit);
//				SerialIncreaseStrategy.getInstance().analysis(stockMains, index, list, max, begin, limit);
				serialIncreaseStrategy.analysis(stockMains, index, increaseTopn, maxIndex, begin, limit);
				break;
			case DECREASE_AND_SERIAL_LOW_VOLUME:
				decreaseAndSerialLowVolumeStrategy.analysis(stockMains, index, increaseTopn, maxIndex, begin, limit);
				break;
			case SERIAL_INCREASE_AND_LOW_VOLUME:
				serialIncreaseAndLowVolumeStrategy.analysis(stockMains, index, increaseTopn, maxIndex, begin, limit);
				break;
			case NEARLY_TEN_DAY:
				
				break;
			default:
				break;
			}
		}
//		if (type == SearchTypeEnum.SERIAL_INCREASE) {
//			CommonsUtil.intersaction(increaseTopn, list);
//		}
		return MapUtils.createSuccessMap("rows", increaseTopn, "total", increaseTopn.size());
	}

}
