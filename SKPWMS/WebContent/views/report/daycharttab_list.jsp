<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>日图表统计</title>
</head>
<body class="easyui-layout">
<div class="easyui-tabs" style="width:100px%;height:100%px">
<div title="浓度折线图" style="padding:10px;height: 1000px">
 <iframe scrolling="yes" frameborder="0" src="${path}/SKPWMS/report/initDayReportToList" style="width:100%;height:1000PX;"></iframe>
</div>
<div title="排放量柱状图" style="padding:10px">
<iframe scrolling="yes" frameborder="0" src="${path}/SKPWMS/report/initDayReportByColumn" style="width:100%;height:1000PX;"></iframe>
</div>

</div>
</body>
</html>