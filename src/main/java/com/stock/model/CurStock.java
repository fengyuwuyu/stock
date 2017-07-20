package com.stock.model;

import java.util.List;

public class CurStock {

	private String symbol;
	private Double increase;
	private List<Stock> stock;

	public List<Stock> getStock() {
		return stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getIncrease() {
		return increase;
	}

	public void setIncrease(Double increase) {
		this.increase = increase;
	}

	public static class Stock {

		private double open;
		private double close;
		private long volume;

		public double getOpen() {
			return open;
		}

		public void setOpen(double open) {
			this.open = open;
		}

		public double getClose() {
			return close;
		}

		public void setClose(double close) {
			this.close = close;
		}

		public long getVolume() {
			return volume;
		}

		public void setVolume(long volume) {
			this.volume = volume;
		}

	}

	@Override
	public String toString() {
		return "CurStock [symbol=" + symbol + ", increase=" + increase
				+ ", stock=" + stock + "]";
	}
}
