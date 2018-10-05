package com.ll.stock.strategy;

import java.util.List;

import com.ll.stock.model.StockAnalysisResult;
import com.stock.model.StockMain;
import com.stock.util.CommonsUtil;

public abstract class BaseAnalysisStrategy implements IAnalysisStrategy {
	
	private int count = 10;

	public StockAnalysisResult createStockAnalysisResult(StockMain curr, Float maxIncrease, int index, List<StockMain> stockMains) {
//		if (index - count < 0) {
//			throw new RuntimeException("illegal index ["+index+"]");
//		}
		StringBuilder increases = new StringBuilder();
		StringBuilder volumes = new StringBuilder();
		StringBuilder closes = new StringBuilder();
		int tmp = index - count;
		tmp = tmp >= 0 ? tmp : 0;
		for (int i = tmp ; i < index; i++) {
			StockMain main = stockMains.get(i);
			increases.append(main.getIncrease() + ", ");
			volumes.append(CommonsUtil.formatDecimal(main.getVolume().floatValue() / curr.getVolume().floatValue()) + ", ");
			closes.append(main.getClose() + ", ");
		}
		return new StockAnalysisResult(curr, maxIncrease, increases.toString(),
				volumes.toString(), closes.toString());
	}
}
