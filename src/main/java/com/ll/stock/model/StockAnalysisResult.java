package com.ll.stock.model;

import java.util.Date;

import com.stock.model.StockMain;

public class StockAnalysisResult {

	private String symbol;

	private Date day;

	private Float open;

	private Float close;

	private Float max;

	private Float min;

	private Long volume;
	private Float volumeRatio;

	private Float increase;
	private Float maxIncrease;
	private Float futureIncrease;

	private String increases;
	private String volumes;
	private String closes;
	private double volumeRate;
	private float hasIncrease;

	public StockAnalysisResult() {
		super();
	}

	public StockAnalysisResult(StockMain stockMain, Float maxIncrease, String increases, String volumes, String closes,
			Float volumeRatio, Float futureIncrease, Float hasIncrease) {
		this.symbol = stockMain.getSymbol();
		this.day = stockMain.getDay();
		this.open = stockMain.getOpen();
		this.close = stockMain.getClose();
		this.max = stockMain.getMax();
		this.min = stockMain.getMin();
		this.volume = stockMain.getVolume();
		this.increase = stockMain.getIncrease();
		this.increases = increases;
		this.volumes = volumes;
		this.closes = closes;
		this.maxIncrease = maxIncrease;
		this.volumeRatio = volumeRatio;
		this.futureIncrease = futureIncrease;
		this.hasIncrease= hasIncrease;
	}

	public StockAnalysisResult(String symbol, Date day, Float open, Float close, Float max, Float min, Long volume,
			Float increase, String increases, String volumes, String closes) {
		super();
		this.symbol = symbol;
		this.day = day;
		this.open = open;
		this.close = close;
		this.max = max;
		this.min = min;
		this.volume = volume;
		this.increase = increase;
		this.increases = increases;
		this.volumes = volumes;
		this.closes = closes;
	}

	public Float getFutureIncrease() {
		return futureIncrease;
	}

	public void setFutureIncrease(Float futureIncrease) {
		this.futureIncrease = futureIncrease;
	}

	public float getHasIncrease() {
		return hasIncrease;
	}

	public void setHasIncrease(float hasIncrease) {
		this.hasIncrease = hasIncrease;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
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

	public String getIncreases() {
		return increases;
	}

	public void setIncreases(String increases) {
		this.increases = increases;
	}

	public String getVolumes() {
		return volumes;
	}

	public void setVolumes(String volumes) {
		this.volumes = volumes;
	}

	public String getCloses() {
		return closes;
	}

	public void setCloses(String closes) {
		this.closes = closes;
	}

	public Float getMaxIncrease() {
		return maxIncrease;
	}

	public void setMaxIncrease(Float maxIncrease) {
		this.maxIncrease = maxIncrease;
	}

	public double getVolumeRate() {
		return volumeRate;
	}

	public void setVolumeRate(double volumeRate) {
		this.volumeRate = volumeRate;
	}

	public Float getVolumeRatio() {
		return volumeRatio;
	}

	public void setVolumeRatio(Float volumeRatio) {
		this.volumeRatio = volumeRatio;
	}

	@Override
	public String toString() {
		return "StockAnalysisResult [symbol=" + symbol + ", day=" + day + ", open=" + open + ", close=" + close
				+ ", max=" + max + ", min=" + min + ", volume=" + volume + ", volumeRatio=" + volumeRatio
				+ ", increase=" + increase + ", maxIncrease=" + maxIncrease + ", futureIncrease=" + futureIncrease
				+ ", increases=" + increases + ", volumes=" + volumes + ", closes=" + closes + ", volumeRate="
				+ volumeRate + ", hasIncrease=" + hasIncrease + "]";
	}

}
