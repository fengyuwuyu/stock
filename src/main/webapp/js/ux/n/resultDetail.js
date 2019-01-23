$package("YiYa.stockMain");

YiYa.stockMain = function(){
	_box = null;
	_this = {
		config : {
			dataGrid : {
				url : urls['msUrl']+'statisticsQuery/detail.do',
				idField : 'symbol',
				pageSize: 20,
				pageList: [20,40,60,60,100],
				columns:[[
							{field:'ck',checkbox:true},
							{field : 'symbol',title:'股票编号',align:'center',width:60},
							{field : 'day',title:'日期',align:'center',width:60},
							{field : 'hasIncrease',title:'过去十天增长',align:'center',width:60,sortable : true},
							{field : 'maxIncrease',title:'最大增长',align:'center',width:60,sortable : true, hidden: true},
							{field : 'futureIncrease',title:'未來五天增长',align:'center',width:60,sortable : true},
							{field : 'increase',title:'增长比',align:'center',width:60,sortable : true},
							{field : 'volumeRatio',title:'成交量比率',align:'center',width:60,sortable : true},
							/*{field : 'open',title:'开盘价',align:'center',width:60},
							{field : 'close',title:'收盘价',align:'center',width:60},
							{field : 'volume',title:'成交量',align:'center',width:60},*/
							{field : 'increases',title:'历史涨幅',align:'center',width:200},
							{field : 'futureIncreases',title:'未来涨幅',align:'center',width:200},
							{field : 'volumes',title:'历史成交量',align:'center',width:200}/*,
							{field : 'closes',title:'历史收盘价',align:'center',width:200}*/
						]],
						onDblClickRow : function(index,row){
					var begin = $('#begin').datebox('getValue');
					window.open(urls['msUrl']+'view/stockChart.jsp?symbol='+row.symbol+'&begin='+begin);
				}
			}
		},
		initDateBox : function(){
		},
		getQueryTime : function(){
			return {"begin" : $('#begin').datebox('getValue'),"end":$('#end').datebox('getValue')};
		},
		checkTime : function(){
			var time = this.getQueryTime();
			if(!time.begin||!begin.end){
				return false;
			}
			return true;
		},
		init : function(){
			$('#begin').datebox('setValue', YiYa.formatDate())
			_box = new YDataGrid(this.config);
			_box.init();
		}
	};
	return _this;
}();

$(function(){
	YiYa.stockMain.init();
});