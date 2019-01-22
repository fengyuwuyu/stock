package com.stock.model;

import java.util.Date;

public class ResultCompare {
    private Long id;

    private Integer checkDay;

    private Integer increaseDay;

    private Float minIncrease;

    private Float minTotalIncrease;

    private Integer increaseCount;

    private Integer decreaseCount;

    private Float average;

    private Float max;

    private Float min;

    private Date day;
    
    public ResultCompare() {
	}

	public ResultCompare(Integer checkDay, Integer increaseDay, Float minIncrease, Float minTotalIncrease,
			Integer increaseCount, Integer decreaseCount, Float average, Float max, Float min, Date day) {
		this.checkDay = checkDay;
		this.increaseDay = increaseDay;
		this.minIncrease = minIncrease;
		this.minTotalIncrease = minTotalIncrease;
		this.increaseCount = increaseCount;
		this.decreaseCount = decreaseCount;
		this.average = average;
		this.max = max;
		this.min = min;
		this.day = day;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCheckDay() {
        return checkDay;
    }

    public void setCheckDay(Integer checkDay) {
        this.checkDay = checkDay;
    }

    public Integer getIncreaseDay() {
        return increaseDay;
    }

    public void setIncreaseDay(Integer increaseDay) {
        this.increaseDay = increaseDay;
    }

    public Float getMinIncrease() {
        return minIncrease;
    }

    public void setMinIncrease(Float minIncrease) {
        this.minIncrease = minIncrease;
    }

    public Float getMinTotalIncrease() {
        return minTotalIncrease;
    }

    public void setMinTotalIncrease(Float minTotalIncrease) {
        this.minTotalIncrease = minTotalIncrease;
    }

    public Integer getIncreaseCount() {
        return increaseCount;
    }

    public void setIncreaseCount(Integer increaseCount) {
        this.increaseCount = increaseCount;
    }

    public Integer getDecreaseCount() {
        return decreaseCount;
    }

    public void setDecreaseCount(Integer decreaseCount) {
        this.decreaseCount = decreaseCount;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
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

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
	}

	@Override
	public String toString() {
		return "ResultCompare [id=" + id + ", checkDay=" + checkDay + ", increaseDay=" + increaseDay + ", minIncrease="
				+ minIncrease + ", minTotalIncrease=" + minTotalIncrease + ", increaseCount=" + increaseCount
				+ ", decreaseCount=" + decreaseCount + ", average=" + average + ", max=" + max + ", min=" + min
				+ ", day=" + day + "]";
	}
}