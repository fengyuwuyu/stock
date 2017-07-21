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
public class StockBuySell {

	private String code;
	private String symbol;
	private Double percent;
	private Double high;
	private Double price;
	private Double open;
	private Double low;
	private String time;
	private Double yestclose;
	private Long turnover;
	private Double updown;
	private Long volume;

	/**
	 * 卖盘价格及数量
	 */
	private Double ask1;
	private Double ask2;
	private Double ask3;
	private Double ask4;
	private Double ask5;
	private Integer askvol1;
	private Integer askvol2;
	private Integer askvol3;
	private Integer askvol4;
	private Integer askvol5;

	/**
	 * 买盘价格及数量
	 */
	private Double bid1;
	private Double bid2;
	private Double bid3;
	private Double bid4;
	private Double bid5;
	private Integer bidvol1;
	private Integer bidvol2;
	private Integer bidvol3;
	private Integer bidvol4;
	private Integer bidvol5;
	private String day;

	public StockBuySell() {
	}

	public StockBuySell(String code, String symbol, Double percent, Double high, Double price, Double open, Double low,
			String time, Double yestclose, Long turnover, Double updown, Long volume, Double ask1, Double ask2,
			Double ask3, Double ask4, Double ask5, Integer askvol1, Integer askvol2, Integer askvol3, Integer askvol4, Integer askvol5,
			Double bid1, Double bid2, Double bid3, Double bid4, Double bid5, Integer bidvol1, Integer bidvol2, Integer bidvol3,
			Integer bidvol4, Integer bidvol5, String day) {
		this.code = code;
		this.symbol = symbol;
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

	public StockBuySell(LinkedHashMap<String, Object> o) {
		this.symbol = convertString(o, "symbol");
		this.percent = convertDouble(o, "percent");
		this.high = convertDouble(o, "high");
		this.ask1 = convertDouble(o, "ask1");
		this.ask2 = convertDouble(o, "ask2");
		this.ask3 = convertDouble(o, "ask3");
		this.ask4 = convertDouble(o, "ask4");
		this.ask5 = convertDouble(o, "ask5");
		this.askvol1 = convertInteger(o, "askvol1");
		this.askvol2 = convertInteger(o, "askvol2");
		this.askvol3 = convertInteger(o, "askvol3");
		this.askvol4 = convertInteger(o, "askvol4");
		this.askvol5 = convertInteger(o, "askvol5");
		this.bid1 = convertDouble(o, "bid1");
		this.bid2 = convertDouble(o, "bid2");
		this.bid3 = convertDouble(o, "bid3");
		this.bid4 = convertDouble(o, "bid4");
		this.bid5 = convertDouble(o, "bid5");
		this.bidvol1 = convertInteger(o, "bidvol1");
		this.bidvol2 = convertInteger(o, "bidvol2");
		this.bidvol3 = convertInteger(o, "bidvol3");
		this.bidvol4 = convertInteger(o, "bidvol4");
		this.bidvol5 = convertInteger(o, "bidvol5");
		this.price = convertDouble(o, "price");
		this.open = convertDouble(o, "open");
		this.time = convertString(o, "time");
		this.yestclose = convertDouble(o, "yestclose");
		this.turnover = convertLong(o, "turnover");
		this.low = convertDouble(o, "low");
		this.updown = convertDouble(o, "updown");
		this.volume = convertLong(o, "volume");
		this.code = convertString(o, "code");
	}

	private Long convertLong(LinkedHashMap<String, Object> o, String column) {
		Long l = 0L;
		try {
			l = (Long) o.get(column);
		} catch (Exception e) {
			l = 0L;
		}
		return l;
	}

	private Integer convertInteger(LinkedHashMap<String, Object> o, String column) {
		Integer i = 0;
		try {
			i = (Integer) o.get(column);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	private Double convertDouble(LinkedHashMap<String, Object> o, String column) {
		Double d = 0D;
		try {
			d = (Double) o.get(column);
		} catch (Exception e) {
			d = 0D;
		}
		return d;
	}

	private String convertString(LinkedHashMap<String, Object> o, String column) {
		String s = "";
		try {
			s = o.get(column) + "";
		} catch (Exception e) {
			s = "";
		}
		return s;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getUpdown() {
		return updown;
	}

	public void setUpdown(Double updown) {
		this.updown = updown;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getAsk1() {
		return ask1;
	}

	public void setAsk1(Double ask1) {
		this.ask1 = ask1;
	}

	public Double getAsk2() {
		return ask2;
	}

	public void setAsk2(Double ask2) {
		this.ask2 = ask2;
	}

	public Double getAsk3() {
		return ask3;
	}

	public void setAsk3(Double ask3) {
		this.ask3 = ask3;
	}

	public Double getAsk4() {
		return ask4;
	}

	public void setAsk4(Double ask4) {
		this.ask4 = ask4;
	}

	public Double getAsk5() {
		return ask5;
	}

	public void setAsk5(Double ask5) {
		this.ask5 = ask5;
	}

	public Integer getAskvol1() {
		return askvol1;
	}

	public void setAskvol1(Integer askvol1) {
		this.askvol1 = askvol1;
	}

	public Integer getAskvol2() {
		return askvol2;
	}

	public void setAskvol2(Integer askvol2) {
		this.askvol2 = askvol2;
	}

	public Integer getAskvol3() {
		return askvol3;
	}

	public void setAskvol3(Integer askvol3) {
		this.askvol3 = askvol3;
	}

	public Integer getAskvol4() {
		return askvol4;
	}

	public void setAskvol4(Integer askvol4) {
		this.askvol4 = askvol4;
	}

	public Integer getAskvol5() {
		return askvol5;
	}

	public void setAskvol5(Integer askvol5) {
		this.askvol5 = askvol5;
	}

	public Double getBid1() {
		return bid1;
	}

	public void setBid1(Double bid1) {
		this.bid1 = bid1;
	}

	public Double getBid2() {
		return bid2;
	}

	public void setBid2(Double bid2) {
		this.bid2 = bid2;
	}

	public Double getBid3() {
		return bid3;
	}

	public void setBid3(Double bid3) {
		this.bid3 = bid3;
	}

	public Double getBid4() {
		return bid4;
	}

	public void setBid4(Double bid4) {
		this.bid4 = bid4;
	}

	public Double getBid5() {
		return bid5;
	}

	public void setBid5(Double bid5) {
		this.bid5 = bid5;
	}

	public Integer getBidvol1() {
		return bidvol1;
	}

	public void setBidvol1(Integer bidvol1) {
		this.bidvol1 = bidvol1;
	}

	public Integer getBidvol2() {
		return bidvol2;
	}

	public void setBidvol2(Integer bidvol2) {
		this.bidvol2 = bidvol2;
	}

	public Integer getBidvol3() {
		return bidvol3;
	}

	public void setBidvol3(Integer bidvol3) {
		this.bidvol3 = bidvol3;
	}

	public Integer getBidvol4() {
		return bidvol4;
	}

	public void setBidvol4(Integer bidvol4) {
		this.bidvol4 = bidvol4;
	}

	public Integer getBidvol5() {
		return bidvol5;
	}

	public void setBidvol5(Integer bidvol5) {
		this.bidvol5 = bidvol5;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getYestclose() {
		return yestclose;
	}

	public void setYestclose(Double yestclose) {
		this.yestclose = yestclose;
	}

	public Long getTurnover() {
		return turnover;
	}

	public void setTurnover(Long turnover) {
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
		return "StockBuySell [code=" + code + ", symbol=" + symbol + ", percent=" + percent + ", high=" + high
				+ ", price=" + price + ", open=" + open + ", low=" + low + ", time=" + time + ", yestclose=" + yestclose
				+ ", turnover=" + turnover + ", updown=" + updown + ", volume=" + volume + ", ask1=" + ask1 + ", ask2="
				+ ask2 + ", ask3=" + ask3 + ", ask4=" + ask4 + ", ask5=" + ask5 + ", askvol1=" + askvol1 + ", askvol2="
				+ askvol2 + ", askvol3=" + askvol3 + ", askvol4=" + askvol4 + ", askvol5=" + askvol5 + ", bid1=" + bid1
				+ ", bid2=" + bid2 + ", bid3=" + bid3 + ", bid4=" + bid4 + ", bid5=" + bid5 + ", bidvol1=" + bidvol1
				+ ", bidvol2=" + bidvol2 + ", bidvol3=" + bidvol3 + ", bidvol4=" + bidvol4 + ", bidvol5=" + bidvol5
				+ ", day=" + day + "]";
	}

}