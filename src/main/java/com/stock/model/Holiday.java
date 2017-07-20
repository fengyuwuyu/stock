package com.stock.model;

public class Holiday {
	private Integer id;

	private String holidayTime;

	public Holiday() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Holiday(Integer id, String holidayTime) {
		super();
		this.id = id;
		this.holidayTime = holidayTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHolidayTime() {
		return holidayTime;
	}

	public void setHolidayTime(String holidayTime) {
		this.holidayTime = holidayTime == null ? null : holidayTime.trim();
	}

}