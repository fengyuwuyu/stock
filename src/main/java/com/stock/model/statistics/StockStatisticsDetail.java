package com.stock.model.statistics;

import java.util.Date;

public class StockStatisticsDetail {

	// base information
	private String symbol;
	private String day;
	private Long yesterdayVolume;
	private Double yesterdayIncrease;
	private Double yesterdayClose;

	// statistics information
	private Double max;
	private Double min;
	private Double open;
	private Double increase;
	private Long volume;
	private Long perMinuteVolume;
	private Long totalVolume = 0L;
	private Integer count = 0;
	private Long maxMinuteVolume;
	private Date maxMinuteVolumeDate;
	private Double maxMinutePrice;
	private Date maxMinutePriceDate;
	private StringBuilder volumes = new StringBuilder();

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

	public Double getIncrease() {
		return increase;
	}

	public void setIncrease(Double increase) {
		this.increase = increase;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		if(this.max == null){
			this.max = max;
		}else{
			if(this.max < max){
				this.max = max;
			}
		}
		
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		if(this.min == null){
			this.min = min;
		}else{
			if(this.min > min){
				this.min = min;
			}
		}
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
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

	public Double getMaxMinutePrice() {
		return maxMinutePrice;
	}

	public void setMaxMinutePrice(Double maxMinutePrice) {
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

	public Long getYesterdayVolume() {
		return yesterdayVolume;
	}

	public void setYesterdayVolume(Long yesterdayVolume) {
		this.yesterdayVolume = yesterdayVolume;
	}

	public Double getYesterdayIncrease() {
		return yesterdayIncrease;
	}

	public void setYesterdayIncrease(Double yesterdayIncrease) {
		this.yesterdayIncrease = yesterdayIncrease;
	}

	public Double getYesterdayClose() {
		return yesterdayClose;
	}

	public void setYesterdayClose(Double yesterdayClose) {
		this.yesterdayClose = yesterdayClose;
	}

	public String getVolumes() {
		return volumes.toString().trim();
	}

	public void setVolumes(String volume) {
		this.volumes.append(volume + ", ");
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "StockStatisticsDetail [symbol=" + symbol + ", day=" + day + ", yesterdayVolume=" + yesterdayVolume
				+ ", yesterdayIncrease=" + yesterdayIncrease + ", yesterdayClose=" + yesterdayClose + ", max=" + max
				+ ", min=" + min + ", open=" + open + ", increase=" + increase + ", volume=" + volume
				+ ", perMinuteVolume=" + perMinuteVolume + ", totalVolume=" + totalVolume + ", count=" + count
				+ ", maxMinuteVolume=" + maxMinuteVolume + ", maxMinuteVolumeDate=" + maxMinuteVolumeDate
				+ ", maxMinutePrice=" + maxMinutePrice + ", maxMinutePriceDate=" + maxMinutePriceDate + ", volumes="
				+ volumes + "]";
	}

}
