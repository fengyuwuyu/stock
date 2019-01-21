package com.ll.stock.strategy;

import java.sql.Date;
import java.util.List;

import com.ll.stock.model.StockAnalysisResult;
import com.stock.model.StockMain;

public interface IAnalysisStrategy {

	void analysis(List<StockMain> stockMains, int index, List<StockAnalysisResult> result, int maxIndex, Date begin, float limit) throws Exception;
}
