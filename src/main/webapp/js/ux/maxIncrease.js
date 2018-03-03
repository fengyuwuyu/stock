$package("YiYa.searcher");

YiYa.searcher = function() {
	_box = null;
	_this = {
		config : {
			dataGrid : {
				url : urls['msUrl'] + 'maxIncrease/datalist.do',
				idField : 'symbol',
				pagination : false,
				columns : [ [ {
					field : 'ck',
					checkbox : true
				}, {
					field : 'symbol',
					title : '股票编号',
					align : 'center',
					width : 200
				}, {
					field : 'increase',
					title : '增长比',
					align : 'center',
					width : 200,
					sortable : true
				}, {
					field : 'maxIncrease',
					title : '近两周最大增长比',
					align : 'center',
					width : 200,
					sortable : true
				}, {
					field : 'open',
					title : '开盘',
					align : 'center',
					width : 200,
					sortable : true
				}, {
					field : 'close',
					title : '收盘',
					align : 'center',
					width : 200,
					sortable : true
				}, {
					field : 'max',
					title : '最高',
					align : 'center',
					width : 200,
					sortable : true
				}, {
					field : 'min',
					title : '最低',
					align : 'center',
					width : 200,
					sortable : true
				}, ] ],
				onDblClickRow : function(index, row) {
					var begin = $('#begin').datebox('getValue'), end = $('#end')
							.datebox('getValue');
					window.open(urls['msUrl'] + 'view/stockChart.jsp?symbol='
							+ row.symbol + '&begin=' + begin + '&end=' + end);
				}
			}
		},
		initDateBox : function() {
		},
		getQueryTime : function() {
			return {
				"begin" : $('#begin').datebox('getValue'),
				"end" : $('#end').datebox('getValue')
			};
		},
		checkTime : function() {
			var time = this.getQueryTime();
			if (!time.begin || !begin.end) {
				return false;
			}
			return true;
		},
		init : function() {
			_box = new YDataGrid(this.config);
			_box.init();
		}
	};
	return _this;
}();

$(function() {
	YiYa.searcher.init();
});