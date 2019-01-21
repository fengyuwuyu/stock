package com.ll.stock.strategy.model;

public class StockMiddleEntity {

	private int minIndex;
	private int maxIndex;
	private float max;
	private float min;
	private float maxIncrease;

	public StockMiddleEntity() {
		super();
	}

	public StockMiddleEntity(int minIndex, int maxIndex, float max, float min, float maxIncrease) {
		super();
		this.minIndex = minIndex;
		this.maxIndex = maxIndex;
		this.max = max;
		this.min = min;
		this.maxIncrease = maxIncrease;
	}

	public float getMaxIncrease() {
		return maxIncrease;
	}

	public void setMaxIncrease(float maxIncrease) {
		this.maxIncrease = maxIncrease;
	}

	public int getMinIndex() {
		return minIndex;
	}

	public void setMinIndex(int minIndex) {
		this.minIndex = minIndex;
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(int maxIndex) {
		this.maxIndex = maxIndex;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	@Override
	public String toString() {
		return "StockMiddleEntity [minIndex=" + minIndex + ", maxIndex=" + maxIndex + ", max=" + max + ", min=" + min
				+ "]";
	}

}
