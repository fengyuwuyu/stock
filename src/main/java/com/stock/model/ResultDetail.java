package com.stock.model;

import java.util.Date;

public class ResultDetail {
    private Integer id;

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

    private Double volumeRate;

    private Float hasIncrease;

    private String closes;

    public ResultDetail() {
		super();
	}

	public ResultDetail(StockMain curr, Float maxIncrease, String increases, String volumes, String closes, Float volumeRatio, Float futureIncrease, Float hasIncrease) {
		this.symbol = curr.getSymbol();
		this.day = curr.getDay();
		this.open = curr.getOpen();
		this.close = curr.getClose();
		this.max = curr.getMax();
		this.min = curr.getMin();
		this.volume = curr.getVolume();
		this.increase = curr.getIncrease();
		this.volumeRatio = volumeRatio;
		this.maxIncrease = maxIncrease;
		this.futureIncrease = futureIncrease;
		this.increases = increases;
		this.volumes = volumes;
		this.hasIncrease = hasIncrease;
		this.closes = closes;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
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

    public Float getVolumeRatio() {
        return volumeRatio;
    }

    public void setVolumeRatio(Float volumeRatio) {
        this.volumeRatio = volumeRatio;
    }

    public Float getIncrease() {
        return increase;
    }

    public void setIncrease(Float increase) {
        this.increase = increase;
    }

    public Float getMaxIncrease() {
        return maxIncrease;
    }

    public void setMaxIncrease(Float maxIncrease) {
        this.maxIncrease = maxIncrease;
    }

    public Float getFutureIncrease() {
        return futureIncrease;
    }

    public void setFutureIncrease(Float futureIncrease) {
        this.futureIncrease = futureIncrease;
    }

    public String getIncreases() {
        return increases;
    }

    public void setIncreases(String increases) {
        this.increases = increases == null ? null : increases.trim();
    }

    public String getVolumes() {
        return volumes;
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes == null ? null : volumes.trim();
    }

    public Double getVolumeRate() {
        return volumeRate;
    }

    public void setVolumeRate(Double volumeRate) {
        this.volumeRate = volumeRate;
    }

    public Float getHasIncrease() {
        return hasIncrease;
    }

    public void setHasIncrease(Float hasIncrease) {
        this.hasIncrease = hasIncrease;
    }

    public String getCloses() {
        return closes;
    }

    public void setCloses(String closes) {
        this.closes = closes == null ? null : closes.trim();
    }
}