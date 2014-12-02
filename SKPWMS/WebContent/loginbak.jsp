<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<title>刷卡式排污总量监控平台</title>

<style type="text/css">
body {
    background:url(<%=path%>/frame/img/1.jpg) center no-repeat; 
	background-size:cover;
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

.fKongbai{width: 50px}
.fName{width: 70px}
.fFont{font-size: 16px; color: black; font-weight: bold;}
.bg_form{background:url(<%=path%>/frame/img/bg_form2.png) no-repeat left top;}

</style>

<script type="text/javascript">
	
	function login(){
		//判断账号是否为空
		var username = $.trim($("#username").val());
		var password = $.trim($("#password").val());
		if(username == ""){
			$("#loginInf").html("请输入账号！");
			return false;
		}		
		//判断密码是否为空
		if(password == ""){
			$("#loginInf").html("请输入密码！");
			return false;
		}
		
		var url = "<%=path%>/sys/login";
		var args = {"username": username, "password":password};
		$.post(url, args, function(data){
			if(data == "true"){
				window.location.href="<%=path%>/index.jsp";
			}else{
				$("#loginInf").html("账号/密码错误！");
			}
		});
		return false;
	}

</script>


</head>
<body>
<table align="center" >
	<tr height="100">
		<td>
		</td> 
	</tr>
	<tr height="60">
		<td>
		<center><font size="6" color="#15428B">刷卡式排污总量监控平台</font></center>
		</td>
	</tr>
	<tr height="50">
		<td>
		</td>
	</tr>
	<tr>
		<td class="bg_form" width="421px">
			<form id="loginForm"  method="post"  onSubmit="return login()">
				<table>
					<tr height="30px">
					</tr>
					<tr>
						<td class="fKongbai"></td>
						<td class="fName">
							<span class="fFont">用&nbsp;户&nbsp;名：</span>
						</td>
						<td><input type="text" id="username" name="username" class="textfile"/></td>
						<td class="fKongbai"></td>
					</tr>
					<tr height="15px">
						<td colspan="4"></td>
					</tr>
					<tr>
						<td class="fKongbai"></td>
						<td class="fName">
							<span class="fFont">&nbsp;密&nbsp;&nbsp;&nbsp;码&nbsp;：</span>
						</td>
						<td><input type="password" id="password" name="password" class="textfile"/></td>
						<td class="fKongbai"></td>
					</tr>
					<tr height="5px">
						<td colspan=4></td>
					</tr>
					<tr>
						<td class="fKongbai"></td>
						<td class="fName"></td>
						<td><div id="loginInf" style="border: 1px soild red;color:red; height: 40px;"></div></td>
						<td class="fKongbai"></td>
					</tr>
					<tr height="5px">
						<td colspan=4></td>
					</tr>
					<tr>
						<td class="fKongbai"></td>
						<td class="fName"></td>
						<td><input type="submit" value="登录" />&nbsp;&nbsp;<input type="reset" value="重置"/></td>
						<td class="fKongbai"></td>
					</tr>
					<tr height="30px">
					</tr>
				</table>
			</form>
		</td>
	</tr>
</table>
</body>
</html>