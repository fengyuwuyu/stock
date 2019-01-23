package com.ll.stock.strategy;

import java.util.List;

import com.ll.stock.strategy.model.StockMiddleEntity;
import com.ll.stock.util.StockUtils;
import com.stock.model.ResultDetail;
import com.stock.model.StockMain;
import com.stock.util.CommonsUtil;

public abstract class BaseAnalysisStrategy implements IAnalysisStrategy {
	
	private int count = 10;
	protected int computeDay = 10;
	
	public ResultDetail createResultDetail(StockMain curr, Float maxIncrease, int index, List<StockMain> stockMains) {
		int futureCount = 5;
		StringBuilder increases = new StringBuilder();
		StringBuilder volumes = new StringBuilder();
		StringBuilder futureIncreases = new StringBuilder();
		StringBuilder futureVolumes = new StringBuilder();
		StringBuilder closes = new StringBuilder();
		int tmp = index - count;
		tmp = tmp >= 0 ? tmp : 0;
		for (int i = tmp ; i <= index; i++) {
			StockMain main = stockMains.get(i);
			increases.append(main.getIncrease() + ", ");
			volumes.append(CommonsUtil.formatDecimal(main.getVolume().floatValue() / curr.getVolume().floatValue()) + ", ");
			closes.append(main.getClose() + ", ");
		}

		if (index + 1 < stockMains.size()) {
			int a = index + 5 >= stockMains.size() ? stockMains.size() - 1 : index + 5;
			for (int i = index + 1; i <= a; i++) {
				StockMain main = stockMains.get(i);
				futureIncreases.append(main.getIncrease() + ", ");
				futureVolumes.append(CommonsUtil.formatDecimal(main.getVolume().floatValue() / curr.getVolume().floatValue()) + ", ");
			}
		}
		
		Float volumeRatio = stockMains.get(index).getVolume().floatValue() / stockMains.get(index - 1).getVolume().floatValue();
		
		float max = 0;
		Float futureIncrease = 0F;
		if (index != stockMains.size() - 1) {
			int futureIndex = (index + futureCount) > stockMains.size() ? stockMains.size() : index + futureCount;
			for (int i = index + 1; i < futureIndex; i++) {
				if (stockMains.get(i).getClose() > max) {
					max = stockMains.get(i).getClose();
				}
			}
			futureIncrease = (max - curr.getClose()) * 100 / curr.getClose();
		}

		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - computeDay, index);
		float hasIncrease = entity.getMaxIncrease();
		
//		if (max > curr.getClose()) {
//		}
		return new ResultDetail(curr, maxIncrease, increases.toString(),
				volumes.toString(), closes.toString(), volumeRatio, futureIncrease, hasIncrease, futureIncreases.toString(), futureVolumes.toString());
	}
}
