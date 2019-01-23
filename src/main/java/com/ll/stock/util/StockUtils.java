package com.ll.stock.util;

import java.sql.Date;
import java.util.List;

import com.ll.stock.strategy.impl.MakeMoneyStrategy;
import com.ll.stock.strategy.model.StockMiddleEntity;
import com.stock.model.ResultCompare;
import com.stock.model.ResultDetail;
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

	public static ResultCompare createResultCompare(List<ResultDetail> result, Date day) {
		if (result == null || result.size() == 0) {
			return null;
		}
		Integer checkDay = MakeMoneyStrategy.CHECK_DAY;
		Integer increaseDay = MakeMoneyStrategy.INCREASE_DAY;
		Float minIncrease = MakeMoneyStrategy.MIN_INCREASE;
		Float minTotalIncrease = MakeMoneyStrategy.INCREASE;
		
		Integer increaseCount = 0;
		Integer decreaseCount = 0;
		Float average = 0F;
		Float max = Float.MIN_VALUE;
		Float min = Float.MAX_VALUE;
		float total = 0;
		for (ResultDetail s : result) {
			float tmp = s.getFutureIncrease();
			if (tmp > 0) {
				increaseCount++;
			} else {
				decreaseCount++;
			}
			
			if (tmp > max) {
				max = tmp;
			} 
			if (tmp < min) {
				min = tmp;
			}
			
			total += s.getFutureIncrease();
		}
		average = total / result.size();
		return new ResultCompare(checkDay, increaseDay, minIncrease, minTotalIncrease, increaseCount, decreaseCount, average, max, min, day);
	}

	public static int getIndex(List<StockMain> stockMains, Date date) {
		for (int i = 0; i < stockMains.size(); i++) {
			if (date.getTime() == stockMains.get(i).getDay().getTime()) {
				return i;
			}
		}
		return -1;
	}
}
