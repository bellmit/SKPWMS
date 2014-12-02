<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<form name="form1">
<center><h3> 设备基本操作 </h3></center>
<center>
<input type="button" onclick="javascript:RfInit()" value="打开设备" >
<input type="button" onclick="javascript:RfExit()" value="关闭设备" >
<input type="button" onclick="javascript:DevBeep()" value="蜂鸣" >
</center>
</form>
<center>转换值：<input type="text" value="C9EEDBDAC3F7CCA9" id="Account" name="cval"  maxlength="16" size="16"> </center>
<form name="form2">
<center><input type="button" onclick="javascript:RfHex()" value="Hex_Asc" >
<input type="button" onclick="javascript:RfAsc()" value="Asc_Hex" ></center>
</form>
<form name="form3">
<center><h3> 数据操作 </h3></center>
<center>
<input type="button" onclick="javascript:RfCard()" value="寻卡" >
<input type="button" onclick="javascript:RfLoadkey()" value="装载密钥" >
<input type="button" onclick="javascript:RfAuthentication()" value="校验密码" >
<input type="button" onclick="javascript:RfRead()" value="读数据" > 
<input type="button" onclick="javascript:RfWrite()" value="写数据" >
<input type="button" onclick="javascript:RfChangekey()" value="修改卡密钥" >
<input type="button" onclick="javascript:RfHalt()" value="中止卡片" ></center>
</form>

<center>
	<label>密钥Mode：
  <select name="keyMode" id="keyMode" >
  <option value="0" SELECTED>KeyA</option>
  <option value="4">KeyB</option>
  </select>
</label>
密钥Key:<input type="text" value="FFFFFFFFFFFF" name="keyin"  maxlength="12" size="12"></center>

<center>扇区号：<input type="text" value="1" name="secin"  maxlength="2" size="2"> </center>
<center>
块0：<input type="text" value="" name="RData"  maxlength="32" size="32"></center>
<center>
块1：<input type="text" value="" name="RData1" maxlength="32" size="32"></center>
<center>
块2：<input type="text" value="" name="RData2" maxlength="32" size="32"></center>
<center>
块3：<input type="text" value="" name="RData3" maxlength="32" size="32"> </center>
<br>


<OBJECT id=MWRFATL style="WIDTH: 0px; HEIGHT: 0px" 
codeBase=<%=path %>frame/js/CardReader/MwRFReader.cab#version=2,0,0,1
data=data:application/x-oleobject;base64,VPpLUhUXNkSyudxeJIvBwwADAAAAAAAAAAAAAA== 
classid=CLSID:BDE9B6B3-4C1D-4C05-8A71-3696F3BF81F5></OBJECT> 
<script src="<%=path %>/frame/js/CardReader/CardReader2.js"></script>
</body>
</html>