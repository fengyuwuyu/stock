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
public class NearlyTenDayStrategy extends BaseAnalysisStrategy {
	
	Logger log = Logger.getLogger(getClass());

	@Override
	public void analysis(List<StockMain> stockMains, int index, List<StockAnalysisResult> result, int maxIndex,
			Date begin, float limit) {
		try {
			StockMain curr = stockMains.get(index);
			float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
			
			float min = Float.MAX_VALUE;
			float max = Float.MIN_VALUE;
			int minIndex = -1;
			for (int i = index - 15; i < index; i++) {
				StockMain main = stockMains.get(i);
				float close = main.getClose();
				if (close < min) {
					min = close;
					minIndex  = i;
				} else if (close > max) {
					max = close;
					maxIndex = i;
				}
			}
			
			
			long totalVolume = 0L;
			for (int i = maxIndex - 4; i <= maxIndex; i++) {
				totalVolume += stockMains.get(i).getVolume();
			}
			long avgVolume = totalVolume / 5;
			
			boolean isLowVolume = false;
			for (int i = maxIndex + 1; i <= index; i++) {
				if (stockMains.get(i).getIncrease() < 0 && stockMains.get(i).getVolume() < avgVolume * 0.3) {
					isLowVolume = true;
					break;
				}
			}
			
			float hasIncrease = (max - min) * 100 / min;
			int count = 0;
			boolean found = false;
			for (int i = maxIndex + 1; i < index; i++) {
				StockMain main = stockMains.get(i);
				if (main.getIncrease() > 0) {
					count++;
					if (count >= 7) {
						found = true;
						break;
					}
				} else {
					if (main.getIncrease() < -8) {
						found = false;
						break;
					}
				}
			}
//			if (hasIncrease >= 10 && hasIncrease < 15) {
			if (/*found &&*/ maxIndex > minIndex && hasIncrease > 9 && isLowVolume /*&& hasIncrease < 15*/) {
				StockAnalysisResult analysisResult = createStockAnalysisResult(curr, maxIncrease, index, stockMains);
				analysisResult.setHasIncrease(hasIncrease);
				result.add(analysisResult);
			}
		} catch (Exception e) {
			log.warn("symbol = " + stockMains.get(0).getSymbol() + ", maxIndex = " + maxIndex);
		}
	}

}
