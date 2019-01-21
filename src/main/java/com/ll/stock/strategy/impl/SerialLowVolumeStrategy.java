package com.ll.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ll.stock.model.StockAnalysisResult;
import com.ll.stock.strategy.BaseAnalysisStrategy;
import com.stock.model.StockMain;
import com.stock.util.CommonsUtil;

@Service
public class SerialLowVolumeStrategy extends BaseAnalysisStrategy {
	
	private int dayCount = 10;

	@Override
	public void analysis(List<StockMain> stockMains, int index, List<StockAnalysisResult> result, int maxIndex,
			Date begin, float limit) throws Exception {
		dayCount = 5;
		StockMain curr = stockMains.get(index);
		float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
		long volume = curr.getVolume();
		boolean found = true;
		Long totalVolume = 0L;
		for (int i = index - dayCount; i < index; i++) {
			if(stockMains.get(i).getVolume() > volume) {
				found = false;
				break;
			}
			totalVolume += stockMains.get(i).getVolume();
		}
		
		if (found && totalVolume / dayCount < curr.getVolume() / 3) {
			StockAnalysisResult analysisResult = createStockAnalysisResult(curr, maxIncrease, maxIndex, stockMains);
			analysisResult.setVolumeRate(curr.getVolume().doubleValue() / (totalVolume.doubleValue() / dayCount));
			result.add(analysisResult);
		}
	}

}
