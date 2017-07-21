package com.stock.model;

import java.util.LinkedHashMap;

/**
 * code=1002486, percent=0.100338, high=9.76, askvol3=0, askvol2=0, askvol5=0,
 * askvol4=0, price=9.76, open=9.76, bid5=9.72, bid4=9.73, bid3=9.74, bid2=9.75,
 * bid1=9.76, low=9.76, updown=0.89, type=SZ, bidvol1=66359147, status=0,
 * bidvol3=17000, bidvol2=491500, symbol=002486, update=2016/11/04 11:31:36,
 * bidvol5=27600, bidvol4=232700, volume=4237453, askvol1=0, ask5=0.0, ask4=0.0,
 * ask1=0.0, name=嘉麟杰, ask3=0.0, ask2=0.0, arrow=↑, time=2016/11/04 11:31:36,
 * yestclose=8.87, turnover=4.135754128E7
 * 
 * @author ll
 * 
 */
public class StockBuySellItem {

	private double percent;
	private double high;
	private double price;
	private double open;
	private double low;
	private String time;
	private double yestclose;
	private long turnover;
	private double updown;
	private int volume;

	/**
	 * 卖盘价格及数量
	 */
	private double ask1;
	private double ask2;
	private double ask3;
	private double ask4;
	private double ask5;
	private int askvol1;
	private int askvol2;
	private int askvol3;
	private int askvol4;
	private int askvol5;

	/**
	 * 买盘价格及数量
	 */
	private double bid1;
	private double bid2;
	private double bid3;
	private double bid4;
	private double bid5;
	private int bidvol1;
	private int bidvol2;
	private int bidvol3;
	private int bidvol4;
	private int bidvol5;
	private String day;

	public StockBuySellItem() {
	}

	public StockBuySellItem(double percent, double high, double price, double open, double low, String time,
			double yestclose, long turnover, double updown, int volume, double ask1, double ask2, double ask3,
			double ask4, double ask5, int askvol1, int askvol2, int askvol3, int askvol4, int askvol5, double bid1,
			double bid2, double bid3, double bid4, double bid5, int bidvol1, int bidvol2, int bidvol3, int bidvol4,
			int bidvol5, String day) {
		super();
		this.percent = percent;
		this.high = high;
		this.price = price;
		this.open = open;
		this.low = low;
		this.time = time;
		this.yestclose = yestclose;
		this.turnover = turnover;
		this.updown = updown;
		this.volume = volume;
		this.ask1 = ask1;
		this.ask2 = ask2;
		this.ask3 = ask3;
		this.ask4 = ask4;
		this.ask5 = ask5;
		this.askvol1 = askvol1;
		this.askvol2 = askvol2;
		this.askvol3 = askvol3;
		this.askvol4 = askvol4;
		this.askvol5 = askvol5;
		this.bid1 = bid1;
		this.bid2 = bid2;
		this.bid3 = bid3;
		this.bid4 = bid4;
		this.bid5 = bid5;
		this.bidvol1 = bidvol1;
		this.bidvol2 = bidvol2;
		this.bidvol3 = bidvol3;
		this.bidvol4 = bidvol4;
		this.bidvol5 = bidvol5;
		this.day = day;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getAsk1() {
		return ask1;
	}

	public void setAsk1(double ask1) {
		this.ask1 = ask1;
	}

	public double getAsk2() {
		return ask2;
	}

	public void setAsk2(double ask2) {
		this.ask2 = ask2;
	}

	public double getAsk3() {
		return ask3;
	}

	public void setAsk3(double ask3) {
		this.ask3 = ask3;
	}

	public double getAsk4() {
		return ask4;
	}

	public void setAsk4(double ask4) {
		this.ask4 = ask4;
	}

	public double getAsk5() {
		return ask5;
	}

	public void setAsk5(double ask5) {
		this.ask5 = ask5;
	}

	public int getAskvol1() {
		return askvol1;
	}

	public void setAskvol1(int askvol1) {
		this.askvol1 = askvol1;
	}

	public int getAskvol2() {
		return askvol2;
	}

	public void setAskvol2(int askvol2) {
		this.askvol2 = askvol2;
	}

	public int getAskvol3() {
		return askvol3;
	}

	public void setAskvol3(int askvol3) {
		this.askvol3 = askvol3;
	}

	public int getAskvol4() {
		return askvol4;
	}

	public void setAskvol4(int askvol4) {
		this.askvol4 = askvol4;
	}

	public int getAskvol5() {
		return askvol5;
	}

	public void setAskvol5(int askvol5) {
		this.askvol5 = askvol5;
	}

	public double getBid1() {
		return bid1;
	}

	public void setBid1(double bid1) {
		this.bid1 = bid1;
	}

	public double getBid2() {
		return bid2;
	}

	public void setBid2(double bid2) {
		this.bid2 = bid2;
	}

	public double getBid3() {
		return bid3;
	}

	public void setBid3(double bid3) {
		this.bid3 = bid3;
	}

	public double getBid4() {
		return bid4;
	}

	public void setBid4(double bid4) {
		this.bid4 = bid4;
	}

	public double getBid5() {
		return bid5;
	}

	public void setBid5(double bid5) {
		this.bid5 = bid5;
	}

	public int getBidvol1() {
		return bidvol1;
	}

	public void setBidvol1(int bidvol1) {
		this.bidvol1 = bidvol1;
	}

	public int getBidvol2() {
		return bidvol2;
	}

	public void setBidvol2(int bidvol2) {
		this.bidvol2 = bidvol2;
	}

	public int getBidvol3() {
		return bidvol3;
	}

	public void setBidvol3(int bidvol3) {
		this.bidvol3 = bidvol3;
	}

	public int getBidvol4() {
		return bidvol4;
	}

	public void setBidvol4(int bidvol4) {
		this.bidvol4 = bidvol4;
	}

	public int getBidvol5() {
		return bidvol5;
	}

	public void setBidvol5(int bidvol5) {
		this.bidvol5 = bidvol5;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getYestclose() {
		return yestclose;
	}

	public void setYestclose(double yestclose) {
		this.yestclose = yestclose;
	}

	public long getTurnover() {
		return turnover;
	}

	public void setTurnover(long turnover) {
		this.turnover = turnover;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "StockBuySellItem [percent=" + percent + ", high=" + high + ", price=" + price + ", open=" + open
				+ ", low=" + low + ", time=" + time + ", yestclose=" + yestclose + ", turnover=" + turnover
				+ ", updown=" + updown + ", volume=" + volume + ", ask1=" + ask1 + ", ask2=" + ask2 + ", ask3=" + ask3
				+ ", ask4=" + ask4 + ", ask5=" + ask5 + ", askvol1=" + askvol1 + ", askvol2=" + askvol2 + ", askvol3="
				+ askvol3 + ", askvol4=" + askvol4 + ", askvol5=" + askvol5 + ", bid1=" + bid1 + ", bid2=" + bid2
				+ ", bid3=" + bid3 + ", bid4=" + bid4 + ", bid5=" + bid5 + ", bidvol1=" + bidvol1 + ", bidvol2="
				+ bidvol2 + ", bidvol3=" + bidvol3 + ", bidvol4=" + bidvol4 + ", bidvol5=" + bidvol5 + ", day=" + day
				+ "]";
	}

}