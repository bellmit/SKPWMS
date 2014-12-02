<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/easyui-tags" prefix="t"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<title>用户信息</title>
</head>
<body>
	<t:formvalid formid="formobj" layout="div" dialog="true">
	<fieldset class="step"><legend> 用户信息 </legend>
	<div class="form"><label class="form"> 用户名: </label> ${user.username }</div>
	<div class="form"><label class="form"> 姓名: </label> ${user.usercount }</div>
	<div class="form"><label class="form"> 手机号码: </label> ${user.phone}</div>
	<div class="form"><label class="form"> 邮箱: </label> ${user.email}</div>
	</fieldset>
	</t:formvalid>
</body>
</html>