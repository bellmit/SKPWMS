<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	
    var color="#FAFAFA"
    $("table tr:odd td").css("background-color",color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $("table tr:odd").attr("bg",color);
    $("table tr:even").attr("bg","#fff");
        
	/* 当鼠标移到表格上是，当前一行背景变色 */
	$("table tr td").mouseover(function(){
    	$(this).parent().find("td").css("background-color","#e0ecff");
    });
	
	/* 当鼠标在表格上移动时，离开的那一行背景恢复 */
	$("table tr td").mouseout(function(){
        var bgc = $(this).parent().attr("bg");
    	$(this).parent().find("td").css("background-color",bgc);
    });

})
	//保存
	function save(){
         $('#form').form('submit',{
             url: '<%=path%>/wryglsxController/save',
             onSubmit: function(){
                 return $(this).form('validate');
             },
             success: function(result){
                 if (result != "true"){
                     $.messager.show({
                         msg: "操作失败，请重试！"
                     });
                 } else {
                     $('#dialog').dialog('close');        // close the dialog
                     $('#datagrid').datagrid('reload');    // reload the user data
                     $.messager.show({
                         msg: "操作成功！"
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
	border:1px solid black;
	border-bottom: 1px solid red;
}
input{
	height: 25px;
	width: 250px;
}

</style>
</head>
<body>
<div style="height: 100%; width: 100%; cellSpacing=0; cellPadding=0; ">
	<form class="easyui_form" id="form" method="post">
    	<table width="100%">
    		<tr>
    			<td class="Fname">环保监管级别:</td><td class="Fcontent"><input name="fctrlLevelId" value="${tbe.fctrlLevelId}" class="easyui-validatebox">
    								 <input type="hidden" name="fenterId" value="${tbe.fenterId}">
    								 <input type="hidden" name="forgUnitId" value="${tbe.forgUnitId}">
    								 <input type="hidden" name="fcreatorId" value="${tbe.fcreatorId}">
    								 <input type="hidden" name="fcreatTime" value="${tbe.fcreatTime}">
    								 <input type="hidden" name="flastEditId" value="${tbe.flastEditId}">
    								 <input type="hidden" name="flastEditTime" value="${tbe.flastEditTime}">
    								 </td>
    			<td class="Fname">是重点源:</td><td class="Fcontent"><input name="fisStressSource" value="${tbe.fisStressSource}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">是废水重点企业:</td><td class="Fcontent"><input name="fisKeySewage" value="${tbe.fisKeySewage}" class="easyui-validatebox"></td>
    			<td class="Fname">是废气重点企业:</td><td class="Fcontent"><input name="fisKeyGas" value="${tbe.fisKeyGas}" class="easyui-validatebox" style="padding-top: 0px; padding-bottom: 0px;"></td>
    		</tr>
    		<tr>
    			<td class="Fname">是固废重点企业:</td><td class="Fcontent"><input name="fisKeySolid" value="${tbe.fisKeySolid}" class="easyui-validatebox"></td>
    			<td class="Fname">是否风险源:</td><td class="Fcontent"><input name="fisDanger" value="${tbe.fisDanger}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">是否污水治理处理厂:</td><td class="Fcontent"><input name="fisSewageFactory" value="${tbe.fisSewageFactory}" class="easyui-validatebox" style="padding-top: 0px; padding-bottom: 0px;"></td>
    			<td class="Fname">是否在线监测企业:</td><td class="Fcontent"><input name="fisOlmonitor" value="${tbe.fisOlmonitor}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">是否排污申报:</td><td class="Fcontent"><input name="fisOutSubmit" value="${tbe.fisOutSubmit}" class="easyui-validatebox"></td>
    			<td class="Fname">是否固废经营单位:</td><td class="Fcontent"><input name="fisSolidManager" value="${tbe.fisSolidManager}" class="easyui-validatebox" style="padding-top: 0px; padding-bottom: 0px;"></td>
    		</tr>
    		<tr>
    			<td class="Fname">是否开征排污费:</td><td class="Fcontent"><input name="fisOutFee" value="${tbe.fisOutFee}" class="easyui-validatebox"></td>
    			<td class="Fname"></td><td class="Fcontent"></td>
    		</tr>
    	</table>
    </form>
    <div id="dlg-buttons" align="right" style="margin-top:40px;">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()" style="margin-right: 50px;">保存</a>
    </div>
</div>
</body>
</html>