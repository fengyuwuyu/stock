package com.stock.model;

import java.util.Date;

public class StockMaxIncrease {
    private Integer id;

    private String symbol;

    private Date day;

    private Float open;

    private Float close;

    private Float max;

    private Float min;

    private Long volume;

    private Float increase;

    private Float maxIncrease;

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
}