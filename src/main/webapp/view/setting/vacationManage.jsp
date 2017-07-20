<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>假期控制</title>
<link id="theme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.0/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.0/themes/icon.css">		
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/jquery-1.11.0-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.0/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.0/locale/easyui-lang-zh_CN.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/urls.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/package.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/base.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commons/YDataGrid.js"></script>
<link href="${pageContext.request.contextPath}/js/ux/calendar/images/base.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/js/ux/calendar/images/all.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/js/ux/calendar/images/skin.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/js/ux/calendar/images/fontSize12.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/js/ux/calendar/images/calendar.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ux/calendar/calendar.js"></script>
</head>
<body class="loginbg" style="height:98%;" background="">
	<div id="myrl"
		style="width:820px; margin-left:auto; margin-right:auto; height:420px; overflow:hidden;">
		<form name=CLD>
			<TABLE class="biao" width="800px" id="huiyi8">
				<TBODY>
					<TR>
						<TD class="calTit" colSpan=7
							style="height:30px;padding-top:3px;text-align:center;"><a
							href="#" title="上一年" id="nianjian" class="ymNaviBtn lsArrow"></a>
							<a href="#" title="上一月" id="yuejian" class="ymNaviBtn lArrow"></a>
						<div style="width:250px; float:left; padding-left:230px;">
								<span id="dateSelectionRili" class="dateSelectionRili"
									style="cursor:hand;color: white; border-bottom: 1px solid white;"
									onclick="dateSelection.show()"> <span id="nian"
									class="topDateFont"></span><span class="topDateFont">年</span><span
									id="yue" class="topDateFont"></span><span class="topDateFont">月</span>
									<span class="dateSelectionBtn cal_next"
									onclick="dateSelection.show()">▼</span></span> &nbsp;&nbsp;<font id=GZ
									class="topDateFont"></font>
							</div> <!--新加导航功能-->
							<div style="left: 250px; display: none;" id="dateSelectionDiv">
								<div id="dateSelectionHeader"></div>
								<div id="dateSelectionBody">
									<div id="yearList">
										<div id="yearListPrev" onclick="dateSelection.prevYearPage()">&lt;</div>
										<div id="yearListContent"></div>
										<div id="yearListNext" onclick="dateSelection.nextYearPage()">&gt;</div>
									</div>
									<div id="dateSeparator"></div>
									<div id="monthList">
										<div id="monthListContent">
											<span id="SM0" class="month"
												onclick="dateSelection.setMonth(0)">1</span> <span id="SM1"
												class="month" onclick="dateSelection.setMonth(1)">2</span> <span
												id="SM2" class="month" onclick="dateSelection.setMonth(2)">3</span>
											<span id="SM3" class="month"
												onclick="dateSelection.setMonth(3)">4</span> <span id="SM4"
												class="month" onclick="dateSelection.setMonth(4)">5</span> <span
												id="SM5" class="month" onclick="dateSelection.setMonth(5)">6</span>
											<span id="SM6" class="month"
												onclick="dateSelection.setMonth(6)">7</span> <span id="SM7"
												class="month" onclick="dateSelection.setMonth(7)">8</span> <span
												id="SM8" class="month" onclick="dateSelection.setMonth(8)">9</span>
											<span id="SM9" class="month"
												onclick="dateSelection.setMonth(9)">10</span> <span
												id="SM10" class="month" onclick="dateSelection.setMonth(10)">11</span>
											<span id="SM11" class="month curr"
												onclick="dateSelection.setMonth(11)">12</span>
										</div>
										<div style="clear:both;"></div>
									</div>
									<div id="dateSelectionBtn">
										<div id="dateSelectionTodayBtn"
											onclick="dateSelection.goToday()">今天</div>
										<div id="dateSelectionOkBtn" onclick="dateSelection.go()">确定</div>
										<div id="dateSelectionCancelBtn"
											onclick="dateSelection.hide()">取消</div>
									</div>
								</div>
								<div id="dateSelectionFooter"></div>
							</div> <a href="#" id="nianjia" title="下一年" class="ymNaviBtn rsArrow"
							style="float:right;"></a> <a href="#" id="yuejia" title="下一月"
							class="ymNaviBtn rArrow" style="float:right;"></a></TD>
					</TR>
					<TR class="calWeekTit"
						style="font-size:12px; height:20px;text-align:center;">
						<TD width="100" class="red">星期日</TD>
						<TD width="100">星期一</TD>
						<TD width="100">星期二</TD>
						<TD width="100">星期三</TD>
						<TD width="100">星期四</TD>
						<TD width="100">星期五</TD>
						<TD width="100" class="red">星期六</TD>
					</TR>
					<SCRIPT language="JavaScript">
						var gNum;
						for (var i = 0; i < 6; i++) {
							document
									.write('<tr align=center height="50" id="tt">');
							for (var j = 0; j < 7; j++) {
								gNum = i * 7 + j;
								document
										.write('<td  id="GD' + gNum + '" on="0" ><font  id="SD'
												+ gNum
												+ '" style="font-size:22px;"  face="Arial"');
								if (j == 0)
									document.write('color=red');
								if (j == 6)
									if (i % 2 == 1)
										document.write('color=red');
									else
										document.write('color=red');
								document
										.write('  TITLE="">  </font><br><font  id="LD' + gNum + '"  size=2  style="white-space:nowrap;overflow:hidden;cursor:default;">  </font></td>');
							}
							document.write('</tr>');
						}
					</SCRIPT>
				</tbody>
			</TABLE>
			<!--  <table class="biao" width="800px">
        <tr>
          <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr align="center">
                <td><input type=button value="元旦节" class="button6"  onclick=dateSelection.goHoliday(0)></td>
                <td><input type=button value='春  节' class="button6"  onclick=dateSelection.goHoliday(1)></td>
                <td><input type=button value='清明节' class="button6"  onclick=dateSelection.goHoliday(3)></td>
                <td><input type=button value='五一节' class="button6"  onclick=dateSelection.goHoliday(4)></td>
                <td><input type=button value='端午节' class="button6"  onclick=dateSelection.goHoliday(5)></td>
                <td><input type=button value='中秋节' class="button6"  onclick=dateSelection.goHoliday(8)></td>
                <td><input type=button value="国庆节" class="button6"  onclick=dateSelection.goHoliday(9)></td>
              </tr>
            </table></td>
        </tr>
      </table> -->

			<td width="100%" align="center"><table border="1"
					cellpadding="5" cellspacing="5">
					<tr align="center">
						<td><input type="button" value="提交" class="button6"
							onclick="h_submit();"></td>
						<td><input type="button" value="重置" class="button6"
							onclick="rebuild();"></td>
						<td width="25" height="25" bgcolor="#FBBB67">&nbsp;</td>
						<td>选 中&nbsp;&nbsp;</td>
						<td width="25" bgcolor="#FFFFFF">&nbsp;</td>
						<td>工作日&nbsp;&nbsp;</td>
						<td width="25" bgcolor="#CFDFF0">&nbsp;</td>
						<td>今 日</td>
					</tr>
				</table></td>

		</form>
	</div>

	<SCRIPT language="JavaScript">
		//提交
		function h_submit() {
			if (hDays == "") {
				YiYa.alert("消息", "没有选中任何日期！");
			} else {
				YiYa.confirm("消息","确定将这些日期设为节假日吗?" + hDays,function(r){
					if (r) {
						$.ajax({
							url : urls['msUrl'] + "holidayController/save.do",
							type : "post",
							dataType : "json",
							data : {
								'vacations' : hDays + ""
							},
							success : function(data) {
								YiYa.alert("消息", data.message);
							}
						});
					}
				});
			}
		}
		//重置
	</SCRIPT>
	<div id="details" style="margin-top:-1px;"></div>
</body>
</html>
