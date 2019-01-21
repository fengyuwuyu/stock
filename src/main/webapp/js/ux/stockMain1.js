$package("YiYa.stockMain");

YiYa.stockMain = function(){
	_box = null;
	_this = {
		config : {
			dataGrid : {
				url : urls['msUrl']+'searcher/findIncreaseTopn.do',
				idField : 'symbol',
				columns:[[
							{field:'ck',checkbox:true},
							{field : 'symbol',title:'股票编号',align:'center',width:80},
							{field : 'hasIncrease',title:'过去十天增长',align:'center',width:80,sortable : true},
							{field : 'maxIncrease',title:'最大增长',align:'center',width:80,sortable : true, hidden: true},
							{field : 'futureIncrease',title:'未來十天增长',align:'center',width:80,sortable : true},
							{field : 'increase',title:'增长比',align:'center',width:80,sortable : true},
							{field : 'volumeRatio',title:'成交量比率',align:'center',width:80,sortable : true},
							/*{field : 'open',title:'开盘价',align:'center',width:80},
							{field : 'close',title:'收盘价',align:'center',width:80},
							{field : 'volume',title:'成交量',align:'center',width:80},*/
							{field : 'increases',title:'历史增长比',align:'center',width:200},
							{field : 'volumes',title:'历史成交量',align:'center',width:200}/*,
							{field : 'closes',title:'历史收盘价',align:'center',width:200}*/
						]],
						onDblClickRow : function(index,row){
//					var config = YiYa.stockMain.getQueryTime();
					var begin = $('#begin').datebox('getValue');
					window.open(urls['msUrl']+'view/stockChart.jsp?symbol='+row.symbol+'&begin='+begin);
//					$('#stock-win').window({
//						title : '个股详情',
//						width : 1200,
//						height : 500,
//						href : urls['msUrl']+'view/stockChart.jsp?symbol='+row.symbol+'&begin='+begin+'&end='+end,
//						draggable : false,
//						minimizable : false,
//						maximizable : false,
//						closable : true,
//						modal : true
//					});
//					$('#stock-win').window('show');
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
			_box = new YDataGrid(this.config);
			_box.init();
		}
	};
	return _this;
}();

$(function(){
	YiYa.stockMain.init();
});