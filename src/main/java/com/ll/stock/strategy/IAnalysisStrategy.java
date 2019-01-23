package com.ll.stock.strategy;

import java.sql.Date;
import java.util.List;

import com.stock.model.ResultDetail;
import com.stock.model.StockMain;

public interface IAnalysisStrategy {

	void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex, Date begin, float limit) throws Exception;
}
