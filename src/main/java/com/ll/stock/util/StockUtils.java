package com.ll.stock.util;

import java.util.List;

import com.ll.stock.strategy.model.StockMiddleEntity;
import com.stock.model.StockMain;

public class StockUtils {

	/**
	 * 
	 * @param stockMains
	 * @param begin 不包含
	 * @param end  包含
	 * @return
	 */
	public static StockMiddleEntity findMaxIncrease(List<StockMain> stockMains, int begin, int end) {
		int minIndex = 0;
		int maxIndex = 0;
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		float maxIncrease = 0;
		
		float close = 0;
		for (int i = begin + 1; i <= end; i++) {
			close = stockMains.get(i).getClose();
			if (close < min) {
				min = close;
				minIndex = i;
			} else if (close > max) {
				max = close;
				maxIndex = i;
			}
		}
		
		if (minIndex > maxIndex) {
			max = Float.MIN_VALUE;
			for (int i = minIndex; i < end; i++) {
				close = stockMains.get(i).getClose();
				if (close > max) {
					max = close;
					maxIndex = i;
				}
			}
		}
		
		maxIncrease = 0;
		if (minIndex < maxIndex) {
			maxIncrease = (max - min) * 100 / min;
		}
		
		return new StockMiddleEntity(minIndex, maxIndex, max, min, maxIncrease);
	}
}
