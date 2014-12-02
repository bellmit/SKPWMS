<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/tools/css/extEasyUI.css">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
    var color="#e8e8e8";
    $("form table tr:odd td").css("background-color",color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $("form table tr:odd").attr("bg",color);
    $("form table tr:even").attr("bg","#fff");
        
	/* 当鼠标移到表格上是，当前一行背景变色 */
	$("form table tr td").mouseover(function(){
    	$(this).parent().find("td").css("background-color","#e0ecff");
    });
	
	/* 当鼠标在表格上移动时，离开的那一行背景恢复 */
	$("form table tr td").mouseout(function(){
        var bgc = $(this).parent().attr("bg");
    	$(this).parent().find("td").css("background-color",bgc);
    });
	
	//格式化eayui datebox时间格式
	$("#easyuiDatebox1").datebox({
		panelHeight:260,
	    formatter: function(date){ 
	    	return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
	    	}
	});
	var cur_time = new Date();
	if(!$("#easyuiDatebox1").datebox('getValue')) {
		$("#easyuiDatebox1").datebox('setValue',cur_time.getFullYear()+'-'+(cur_time.getMonth()+1)+'-'+cur_time.getDate());
	}
	$("#easyuiDatebox2").datebox({
		panelHeight:260,
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();}
	});
	if(!$("#easyuiDatebox2").datebox('getValue')) {
		$("#easyuiDatebox2").datebox('setValue',cur_time.getFullYear()+'-'+(cur_time.getMonth()+1)+'-'+cur_time.getDate());
	}
	//console.info($("#easyuiDatebox3").datetimebox('getValue'));
	if(!$("#easyuiDatebox3").datetimebox('getValue')) {
		$("#easyuiDatebox3").datetimebox('setValue',cur_time.getFullYear()+'-'+(cur_time.getMonth()+1)+'-'+cur_time.getDate()+" "+cur_time.getHours()+":"+cur_time.getMinutes()+":"+cur_time.getSeconds());
	}
	
});
	//保存
	function save(){
	var name = "";
         $('#form').form('submit',{
             url: '<%=path%>/tTcControlerController/save',
             onSubmit:function(){
                 return $(this).form('validate');
             },
             success: function(result){
                 if (result == "true"){
                     window.parent.$.messager.show({
                         msg: "操作成功！"
                     });
                 } else {
                	 window.parent.$.messager.show({
                         msg: "操作失败，请重试！"
                     });
                 }
             }
         });
     }
     
</script>
<style type="text/css">
.Fname{
	text-align: right;
}
.Fcontent{
}
tr{
	height: 35px;
	border: 1px solid black;
	border-bottom: 1px solid red;
}
input{
	height: 25px;
	width: 220px;
	background-color: 
}

</style>
</head>
<body class="easyui-layout" data-options="border:false">   
    <div data-options="region:'center',border:false">
	<form class="easyui_form" id="form" method="post">
    	<table width="100%">
			<tr>
				<td colspan="4" style="font-size: 19px;font-weight:bolder;">基本信息：</td>
			</tr>
    		<tr>
    			<td class="Fname">控制器名称:</td><td class="Fcontent"><input name="fctrlerName" value="${tbe.fctrlerName}" class="easyui-validatebox" required="true"></td>
    			<td class="Fname">控制器编号:</td><td class="Fcontent"><input name="fctrlerCode" value="${tbe.fctrlerCode}" class="easyui-validatebox">
    								 <input type="hidden" name="fcontrolerId" value="${tbe.fcontrolerId}">
    								 <input type="hidden" name="fenterId" value="${tbe.fenterId}">
    								 <input type="hidden" name="forgUnitId" value="${tbe.forgUnitId}">
    								 <input type="hidden" name="fcreatorId" value="${tbe.fcreatorId}">
    								 <input type="hidden" name="fcreatTime" value="${tbe.fcreatTime}">
    								 </td>
    		</tr>
    		<tr>
    			<td class="Fname">控制器序列号:</td><td class="Fcontent"><input name="fctrlerSn" value="${tbe.fctrlerSn}" class="easyui-validatebox"></td>
    			<td class="Fname">通讯地址:</td><td class="Fcontent"><input name="faddress" value="${tbe.faddress}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">通讯端口:</td><td class="Fcontent"><input name="fpost" value="${tbe.fpost}" class="easyui-validatebox"></td>
    			<td class="Fname">通讯地址2:</td><td class="Fcontent"><input name="faddress2" value="${tbe.faddress2}" class="easyui-validatebox"></td>
    		</tr>
    		
			<tr>
    			<td class="Fname">端口2:</td><td class="Fcontent"><input name="fpost2" value="${tbe.fpost2}" class="easyui-validatebox"></td>
    			<td class="Fname">通讯地址3:</td><td class="Fcontent"><input name="faddress3" value="${tbe.faddress3}" class="easyui-validatebox"></td>
			</tr>
			
			<tr>
    			<td class="Fname">端口3:</td><td class="Fcontent"><input name="fpost3" value="${tbe.fpost3}" class="easyui-validatebox"></td>
    			<td class="Fname">通讯类型:</td>
    			<td class="Fcontent">
    			<%--
    				<input name="fcommunicationType" value="${tbe.fcommunicationType}" class="easyui-validatebox"> --%>
    				<select name="fcommunicationType" style="width:226px; height: 31px;">
						<option value="">--请选择--</option>
    					<option value="0" <c:if test="${'0' == tbe.fcommunicationType}">selected</c:if> >TCP/IP</option>
    					<option value="1" <c:if test="${'1' == tbe.fcommunicationType}">selected</c:if> >3G网络</option>
    				</select>
    			</td>
			</tr>
			<tr>
    			<td class="Fname">领用人:</td><td class="Fcontent"><input name="fuserName" value="${tbe.fuserName}" class="easyui-validatebox"></td>
    			<td class="Fname">领用人单位:</td><td class="Fcontent"><input name="fuserUnit" value="${tbe.fuserUnit}" class="easyui-validatebox"></td>
			</tr>
			<tr>
    			<td class="Fname">领用日期:</td><td class="Fcontent">
    				<input type="text" id="easyuiDatebox1" name="fuserDate" value="${tbe.fuserDate}" class="easyui-datebox" style="height: 31px;width: 225px;">
    			</td>
    			<td class="Fname">启用日期:</td><td class="Fcontent">
    				<input type="text" id="easyuiDatebox2" name="fstartupDate" value="${tbe.fstartupDate}" class="easyui-datebox" style="height: 31px;width: 225px;">
    			</td>
			</tr>
    		<tr>
    			<td class="Fname">生产厂家:</td><td class="Fcontent"><input name="fmanuFactory" value="${tbe.fmanuFactory}" class="easyui-validatebox"></td>
    			<td class="Fname">联系人:</td><td class="Fcontent"><input name="flinkMan" value="${tbe.flinkMan}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">联系电话:</td><td class="Fcontent"><input name="fphone" value="${tbe.fphone}" class="easyui-validatebox"></td>
    			<td class="Fname">运维单位:</td><td class="Fcontent"><input name="foperateUnit" value="${tbe.foperateUnit}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">运维单位联系人:</td><td class="Fcontent"><input name="foulinkman" value="${tbe.foulinkman}" class="easyui-validatebox"></td>
    			<td class="Fname">访问密码:</td><td class="Fcontent"><input name="fpassword" value="${tbe.fpassword}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">数据上报间隔（秒）:</td><td class="Fcontent"><input name="finterval" value="${tbe.finterval}" class="easyui-numberspinner" data-options="min:0"></td>
    			<td class="Fname">是否上报日数据:</td><td class="Fcontent">
    			<%-- <input name="fisReportDay" value="${tbe.fisReportDay}" class="easyui-validatebox"> --%>
    			<select name="fisReportDay" style="width:226px; height: 31px;">
						<option value="">--请选择--</option>
    					<option value="true" <c:if test="${'true' == tbe.fisReportDay}">selected</c:if> >是</option>
    					<option value="false" <c:if test="${'false' == tbe.fisReportDay}">selected</c:if> >否</option>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td class="Fname">是否上报小时数据:</td><td class="Fcontent">
    			<%-- <input name="fisReportHour" value="${tbe.fisReportHour}" class="easyui-validatebox"> --%>
    			<select name="fisReportHour" style="width:226px; height: 31px;">
						<option value="">--请选择--</option>
    					<option value="true" <c:if test="${'true' == tbe.fisReportHour}">selected</c:if> >是</option>
    					<option value="false" <c:if test="${'false' == tbe.fisReportHour}">selected</c:if> >否</option>
    				</select>
    			</td>
    			<td class="Fname">更新日期:</td><td class="Fcontent">
    				<input type="text" id="easyuiDatebox3" name="fupdateDate" value="${tbe.fupdateDate}" class="easyui-datetimebox" style="height: 31px;width: 225px;">
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="Fname">排污类型:</td><td class="Fcontent">
    			<select name="fpollTypeCode" style="width:226px; height: 31px;" class="easyui-combobox" required="true">
						<option value=""></option>
    					<option value="B01" <c:if test="${'B01' == tbe.fpollTypeCode}">selected</c:if> >废水</option>
    					<option value="B02" <c:if test="${'B02' == tbe.fpollTypeCode}">selected</c:if> >废气</option>
    				</select>
    			</td>
    		</tr>
    		<%-- 
    		<tr>
    			<td class="Fname">状态:</td><td class="Fcontent"><input name="fstatus" value="${tbe.fstatus}" class="easyui-validatebox"></td>
    		</tr>--%>
    	</table>    
    </form>
    <div id="dlg-buttons" align="right" style="margin-top:40px;" class="swFormButtunDiv">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()" style="margin-right: 50px;">保存</a>
    </div>
    <div style="height: 30px;"></div>
</div>
</body>
</html>