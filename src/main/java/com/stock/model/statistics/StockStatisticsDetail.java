package com.stock.model.statistics;

import java.util.Date;

public class StockStatisticsDetail {

	// base information
	private String symbol;
	private String day;
	private Long volume;
	private Float increase;
	private Float max;
	private Float min;
	private Float open;
	private Float close;

	// statistics information
	private Long perMinuteVolume;
	private Long totalVolume = 0L;
	private Integer count = 0;
	private Long maxMinuteVolume;
	private Date maxMinuteVolumeDate;
	private Float maxMinutePrice;
	private Date maxMinutePriceDate;
	private Long lastVolume;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Float getIncrease() {
		return increase;
	}

	public void setIncrease(Float increase) {
		this.increase = increase;
	}

	public Float getMax() {
		return max;
	}

	public void setMax(Float max) {
		this.max = max;
	}

	public Float getMin() {
		return min;
	}

	public void setMin(Float min) {
		this.min = min;
	}

	public Float getOpen() {
		return open;
	}

	public void setOpen(Float open) {
		this.open = open;
	}

	public Float getClose() {
		return close;
	}

	public void setClose(Float close) {
		this.close = close;
	}

	public Long getPerMinuteVolume() {
		return totalVolume / count;
	}

	public void setPerMinuteVolume(Long perMinuteVolume) {
		this.perMinuteVolume = perMinuteVolume;
	}

	public Long getMaxMinuteVolume() {
		return maxMinuteVolume;
	}

	public void setMaxMinuteVolume(Long maxMinuteVolume) {
		this.maxMinuteVolume = maxMinuteVolume;
	}

	public Float getMaxMinutePrice() {
		return maxMinutePrice;
	}

	public void setMaxMinutePrice(Float maxMinutePrice) {
		this.maxMinutePrice = maxMinutePrice;
	}

	public Date getMaxMinuteVolumeDate() {
		return maxMinuteVolumeDate;
	}

	public void setMaxMinuteVolumeDate(Date maxMinuteVolumeDate) {
		this.maxMinuteVolumeDate = maxMinuteVolumeDate;
	}

	public Date getMaxMinutePriceDate() {
		return maxMinutePriceDate;
	}

	public void setMaxMinutePriceDate(Date maxMinutePriceDate) {
		this.maxMinutePriceDate = maxMinutePriceDate;
	}

	public Long getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(Long totalVolume) {
		this.totalVolume += totalVolume;
		this.count++;
	}

	public Long getLastVolume() {
		return lastVolume;
	}

	public void setLastVolume(Long lastVolume) {
		this.lastVolume = lastVolume;
	}

	@Override
	public String toString() {
		return "StockStatisticsDetail [symbol=" + symbol + ", day=" + day + ", volume=" + volume + ", increase="
				+ increase + ", max=" + max + ", min=" + min + ", open=" + open + ", close=" + close
				+ ", perMinuteVolume=" + perMinuteVolume + ", totalVolume=" + totalVolume + ", count=" + count
				+ ", maxMinuteVolume=" + maxMinuteVolume + ", maxMinuteVolumeDate=" + maxMinuteVolumeDate
				+ ", maxMinutePrice=" + maxMinutePrice + ", maxMinutePriceDate=" + maxMinutePriceDate + ", lastVolume="
				+ lastVolume + "]";
	}

}
