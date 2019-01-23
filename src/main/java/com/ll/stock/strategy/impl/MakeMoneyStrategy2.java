package com.ll.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ll.stock.strategy.BaseAnalysisStrategy;
import com.ll.stock.strategy.model.StockMiddleEntity;
import com.ll.stock.util.StockUtils;
import com.stock.model.ResultDetail;
import com.stock.model.StockMain;
import com.stock.util.CommonsUtil;

@Service
public class MakeMoneyStrategy2 extends BaseAnalysisStrategy {
	
	public static int CHECK_DAY = 8;
	public static int INCREASE_DAY = 6;
	public static float MIN_INCREASE = -3F;
	public static float INCREASE = 10F;
	
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result,
			int maxIndex, Date begin, float limit) throws Exception {
		StockMain curr = stockMains.get(index);
		float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
		
		// 1.
		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - CHECK_DAY, index);
		if (entity.getMaxIncrease() < INCREASE) {
			return ;
		}
		
		// 2.
		int increaseCount = 0;
		for (int i = index - CHECK_DAY + 1; i <= index; i++) {
			if (stockMains.get(i).getIncrease() > 0) {
				increaseCount++;
			} else if (stockMains.get(i).getIncrease() < MIN_INCREASE) {
				return ;
			}
		}
		
		if (increaseCount < INCREASE_DAY) {
			return;
		}
		
		if (stockMains.size() > 0) {
			if (stockMains.get(index - 1).getIncrease() > 0 && stockMains.get(index).getIncrease() < 0) {
				return ;
			} 
		}
		
		ResultDetail analysisResult = createResultDetail(curr, maxIncrease, index, stockMains);
		result.add(analysisResult);
	}

}
