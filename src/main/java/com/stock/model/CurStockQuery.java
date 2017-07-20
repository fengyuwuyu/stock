package com.stock.model;

import java.util.List;

public class CurStockQuery {

	private List<String> days;

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "CurStockQuery [days=" + days + "]";
	}

}
