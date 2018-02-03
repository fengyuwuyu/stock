package com.stock.model;

public class StockInfo {
	private Integer id;

	private String code;

	private String symbol;

	private String sname;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname == null ? null : sname.trim();
	}

	@Override
	public String toString() {
		return "StockInfo [id=" + id + ", code=" + code + ", sname=" + sname + ", type=" + type + "]";
	}

}