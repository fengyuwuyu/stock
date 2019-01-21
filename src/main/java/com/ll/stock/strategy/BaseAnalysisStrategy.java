package com.ll.stock.strategy;

import java.util.List;

import com.ll.stock.model.StockAnalysisResult;
import com.ll.stock.strategy.model.StockMiddleEntity;
import com.ll.stock.util.StockUtils;
import com.stock.model.StockMain;
import com.stock.util.CommonsUtil;

public abstract class BaseAnalysisStrategy implements IAnalysisStrategy {
	
	private int count = 10;
	protected int computeDay = 10;
	
	public StockAnalysisResult createStockAnalysisResult(StockMain curr, Float maxIncrease, int index, List<StockMain> stockMains) {
		int futureCount = 20;
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
		Float volumeRatio = stockMains.get(index).getVolume().floatValue() / stockMains.get(index - 1).getVolume().floatValue();
		
		float max = curr.getClose();
		int futureIndex = (index + futureCount) > stockMains.size() ? stockMains.size() : index + futureCount;
		for (int i = index + 1; i < futureIndex; i++) {
			if (stockMains.get(i).getClose() > max) {
				max = stockMains.get(i).getClose();
			}
		}

		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - computeDay, index);
		float hasIncrease = entity.getMaxIncrease();
		
		Float futureIncrease = 0F;
		if (max > curr.getClose()) {
			futureIncrease = (max - curr.getClose()) * 100 / curr.getClose();
		}
		return new StockAnalysisResult(curr, maxIncrease, increases.toString(),
				volumes.toString(), closes.toString(), volumeRatio, futureIncrease, hasIncrease);
	}
}
