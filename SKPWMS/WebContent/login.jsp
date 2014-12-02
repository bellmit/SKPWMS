<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<title>刷卡式排污总量自动监控平台</title>

<style type="text/css">
body {
	background: url(<%=path%>/frame/img/1.jpg) center no-repeat;
	background-size: cover;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

body,table,td,div {
	font-size: 12px;
	line-height: 24px;
}

.textfile {
	padding: 1px 2px;
	width: 220px;
	border: 1px solid #CCC;
	height: 29px;
	font-size: 14px;
	line-height: 22px;
}

.tishi {
	line-height: 50px;
	height: 5px;
}

.fKongbai {
	width: 50px
}

.fName {
	width: 70px
}

.fFont {
	font-size: 16px;
	color: black;
	font-weight: bold;
}

.bg_form {
	background: url(<%=path%>/frame/img/bg_form2.png) no-repeat left top;
}
</style>

<script type="text/javascript">
$(function(){
	$("#username").focus();
});
 if (top != window){ 
	top.location.href = window.location.href;
 }    
 	function reset(){
 		
 		$("#username").val("");
 		$("#password").val("");
 	}
	
	function login(){
		//判断账号是否为空
		var username = $.trim($("#username").val());
		var password = $.trim($("#password").val());
		if(username == ""){
			$("#loginInf").text("请输入用户名！");
			return false;
		}		
		//判断密码是否为空
		if(password == ""){
			$("#loginInf").text("请输入密码！");
			return false;
		
		}
		/* document.loginForm.submit(); */
	}

</script>


</head>
<body>
	<table align="center">
		<tr height="100">
			<td></td>
		</tr>
		<tr height="60">
			<td>
				<center>
					<font size="6" color="#15428B">刷卡式排污总量自动监控平台</font>
				</center>
			</td>
		</tr>
		<tr height="50">
			<td></td>
		</tr>
		<tr>
			<td class="bg_form" width="100%px">
				<form id="loginForm" method="post" name="loginForm"
					action="<%=path%>/j_spring_security_check" onSubmit="return login()">
					<table>
						<tr height="30px">
						</tr>
						<tr>
							<td class="fKongbai"></td>
							<td class="fName"><span class="fFont">用&nbsp;户&nbsp;名：</span>
							</td>
							<td><input type="text" id="username" name="j_username"
								class="textfile" /></td>
							<td class="fKongbai"></td>
						</tr>
						<tr height="15px">
							<td colspan="4"></td>
						</tr>
						<tr>
							<td class="fKongbai"></td>
							<td class="fName"><span class="fFont">&nbsp;密&nbsp;&nbsp;&nbsp;码&nbsp;：</span>
							</td>
							<td><input type="password" id="password" name="j_password"
								class="textfile" /></td>
							<td class="fKongbai"></td>
						</tr>
						<tr height="5px">
							<td colspan=4></td>
						</tr>
						<tr>
							<td class="fKongbai"></td>
							<td class="fName"></td>
							<td><div id="loginInf"
									style="border: 1px soild red; color: red; height: 20px;">${msg}</div></td>
						</tr>
						<tr class="tishi" style="height: 5px; margin-bottom: 400px"
							align="center">
							<td colspan=8>
						<tr>
							<td class="fKongbai"></td>
							<td class="fName"></td>
							<td><input type="submit"  value="登录"  style="width:70px;height: 30px">
								<input type="reset"  value="重置"  style="width:70px;height: 30px">
							</td>
						</tr>

						<tr height="70px">

						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>


<%
	request.getSession().invalidate();
	Cookie[] cookies = request.getCookies();
	if (null != cookies) {
		for (int i = 0; i < cookies.length; i++) {
			if ("JSESSIONID".equalsIgnoreCase(cookies[i].getName())) {
				cookies[i].setMaxAge(0);
				cookies[i].setSecure(true);
				response.addCookie(cookies[i]);

			}

		}

	}
%>
