package com.ll.stock.comparator;

import java.util.Comparator;

import com.stock.model.StockMain;

public class StockMainComparator implements Comparator<StockMain> {

	@Override
	public int compare(StockMain o1, StockMain o2) {
		if(o1.getDay().getTime() == o2.getDay().getTime()) {
			return 0;
		}
		return o1.getDay().before(o2.getDay()) ? -1 : 1;
	}

}
