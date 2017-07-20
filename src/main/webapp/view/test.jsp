<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<%@include file="commons/resource.jsp"%>
	<script type="text/javascript">
		$(function(){
			YiYa.ajaxJson("holidayController/test.do", {}, function(data){
				content = JSON.stringify(data.data, null, 4);
				$('#content').val(content);
			});
			/* var content = "{'name':'张三'}";
			content = JSON.stringify(content, null, 4);;
			$('#content').val(content); */
		});
	</script>

  </head>
  
  <body>
    <form action="">
    	文本域<textarea id="content" rows="50" cols="100"></textarea>
    </form>
  </body>
</html>
