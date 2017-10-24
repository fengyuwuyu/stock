package com.stock.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.stock.dao.ExceptionLogMapper;
import com.stock.dao.HolidayMapper;
import com.stock.dao.StockMainMapper;
import com.stock.dao.StockTableInfoMapper;
import com.stock.model.ExceptionLog;
import com.stock.model.StockTableInfo;
import com.stock.service.InitStockServiceI;
import com.stock.util.CommonsUtil;
import com.stock.util.MapUtils;

public class DownloadDetail {

	private InitStockServiceI initStockServiceI;
	private ExceptionLogMapper exceptionLogMapper;
	private HolidayMapper holidayMapper;
	@Autowired
	StockMainMapper stockMainMapper;
	@Autowired
	StockTableInfoMapper stockTableInfoMapper;

	private Logger log = Logger.getLogger(DownloadDetail.class);

	@Autowired
	public void setHolidayMapper(HolidayMapper holidayMapper) {
		this.holidayMapper = holidayMapper;
	}

	@Autowired
	public void setInitStockServiceI(InitStockServiceI initStockServiceI) {
		this.initStockServiceI = initStockServiceI;
	}

	@Autowired
	public void setExceptionLogMapper(ExceptionLogMapper exceptionLogMapper) {
		this.exceptionLogMapper = exceptionLogMapper;
	}

	public void execute() {
		Date now = new Date();
		if(CommonsUtil.isHoliday(now, holidayMapper)){
			return;
		}
		long time = 30000;
		log.info("开始下载委卖委买数据，每30s一次");
		// 根据当天时间创建表
		String day = CommonsUtil.formatDateToString1(now);
		String tableName = "stock_" + day.replaceAll("-", "_");
		Map<String, String> map = new HashMap<>(1);
		map.put("tableName", tableName);
		stockMainMapper.createTable(map);
		StockTableInfo info = new StockTableInfo();
		info.setTableName(tableName);
		stockTableInfoMapper.insert(info);

		while (CommonsUtil.checkTime(holidayMapper)) {
			long begin = System.currentTimeMillis();
			initStockServiceI.initBuyAndSell(day, tableName);
			long remain = System.currentTimeMillis() - begin;
			long sleep = time - remain;
			if (sleep > 0) {
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					log.info(CommonsUtil.join(e.getStackTrace(), ","));
					ExceptionLog record = new ExceptionLog(CommonsUtil.formatDateToString3(new Date()), //
							this.getClass().getName(), "initBuyAndSell", e.getMessage(), ""); //
					this.exceptionLogMapper.insert(record);
				}
			}
		}
		log.info("下载委卖委买数据结束。。。");
	}

}
