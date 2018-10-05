package com.ll.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ll.stock.model.StockAnalysisResult;
import com.ll.stock.strategy.BaseAnalysisStrategy;
import com.stock.model.StockMain;
import com.stock.util.CommonsUtil;

@Service
public class MaxIncreaseStrategy extends BaseAnalysisStrategy {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<StockAnalysisResult> result,
			int maxIndex, Date begin, float limit) {
		try {
			StockMain curr = stockMains.get(index);
			float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
			if (maxIncrease >= limit) {
				StockAnalysisResult analysisResult = createStockAnalysisResult(curr, maxIncrease, index, stockMains);
				result.add(analysisResult);
			}
		} catch (Exception e) {
			log.warn("symbol = " + stockMains.get(0).getSymbol() + ", maxIndex = " + maxIndex);
		}
	}

}
