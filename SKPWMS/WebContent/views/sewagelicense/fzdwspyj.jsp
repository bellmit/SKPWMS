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
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	
    var color="#e8e8e8"
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
     
	//格式化eayui datebox时间格式
	$('#datebox').datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	});
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
	width: 220px;
}

</style>
</head>
<body>
<div style="height: 100%; width: 100%; cellSpacing=0; cellPadding=0; ">
	<form class="easyui_form" id="form" method="post">
    	<table width="100%">
    		<tr>
    			<td class="Fname">许可证编号:</td><td class="Fcontent"><input name="fcountyId" value="" class="easyui-validatebox"></td>
    			<td class="Fname">经办人:</td><td class="Fcontent"><input name="ftownId" value="" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">许可证有效期限 从:</td><td class="Fcontent"><input name="fcountyId" value="" class="easyui-datebox"></td>
    			<td class="Fname">至:</td><td class="Fcontent"><input name="ftownId" value="" class="easyui-datebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">主办部门意见:</td><td class="Fcontent" colspan="3" ><input name="fcountyId" value="" class="easyui-validatebox" style="width: 90%"></td>
    		</tr>
    		<tr>
    			<td class="Fname"></td><td class="Fcontent"></td>
    			<td class="Fname">经办日期:</td><td class="Fcontent"><input name="ftownId" value="" class="easyui-datebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">发证机关意见:</td><td class="Fcontent" colspan="3" ><input name="fcountyId" value="" class="easyui-validatebox" style="width: 90%"></td>
    		</tr>
    		<tr>
    			<td class="Fname"></td><td class="Fcontent"></td>
    			<td class="Fname">发证日期:</td><td class="Fcontent"><input name="ftownId" value="" class="easyui-datebox"></td>
    		</tr>
    	</table>
    </form>
    <div id="dlg-buttons" align="right" style="margin-top:40px;">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()" style="margin-right: 50px;">保存</a>
    </div>
  
</div>
</body>
</html>