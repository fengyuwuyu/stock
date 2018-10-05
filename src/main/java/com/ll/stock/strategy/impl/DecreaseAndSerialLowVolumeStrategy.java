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
public class DecreaseAndSerialLowVolumeStrategy extends BaseAnalysisStrategy {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<StockAnalysisResult> result, int maxIndex,
			Date begin, float limit) {
		try {
			StockMain curr = stockMains.get(index);
			float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
			boolean found = true;
			
			float maxClose = Float.MIN_VALUE;
			float minClose = Float.MAX_VALUE;
			int maxInd = -1;
			int minInd = -1;
			for (int i = index - 10; i < index + 1; i++) {
				float close = stockMains.get(i).getClose();
				if (close > maxClose) {
					maxClose = close;
					maxInd = i;
				}	else if (minClose > close) {
					minClose = close;
					minInd = i;
				}
			}
			
			if (maxInd > minInd && (maxClose - minClose) * 100 / minClose > 10) {
//				long maxVolume = stockMains.get(maxInd).getVolume();
//				for (int i = maxInd + 1; i < maxInd + 4; i++) {
//					StockMain stockMain = stockMains.get(i);
//					if (stockMain.getIncrease() < -3 || stockMain.getVolume() > maxVolume) {
//						found = false;
//						break;
//					}
//				}
			} else {
				found = false;
			}
			
			if (found) {
				StockAnalysisResult analysisResult = createStockAnalysisResult(curr, maxIncrease, index, stockMains);
				result.add(analysisResult);
			}
		} catch (Exception e) {
			log.warn("symbol = " + stockMains.get(0).getSymbol() + ", maxIndex = " + maxIndex);
		}
	}

}
