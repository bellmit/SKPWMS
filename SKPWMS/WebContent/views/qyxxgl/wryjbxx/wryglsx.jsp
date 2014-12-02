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
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/tools/css/extEasyUI.css">
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
                	 window.parent.$.messager.show({
                         msg: "操作失败，请重试！"
                     });
                 } else {
                     $('#dialog').dialog('close');        // close the dialog
                     $('#datagrid').datagrid('reload');    // reload the user data
                     window.parent.$.messager.show({
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
	width: 220px;
}

</style>
</head>
<body>
<div style="height: 100%; width: 100%; cellSpacing=0; cellPadding=0; ">
	<form class="easyui_form" id="form" method="post">
    	<table width="100%">
    		<tr>
    			<td class="Fname">环保监管级别:</td><td class="Fcontent">
    			<%-- <input name="fctrlLevelId" value="${tbe.fctrlLevelId}" class="easyui-validatebox"> --%>
    			<select name="fctrlLevelId" style="width:226px; height: 31px;">
    				<option value="">--请选择--</option>
    				<c:forEach items="${tBasCtrlLevelList }" var="item">
    					<option value="${item.fctrlLevelId }"
    						<c:if test="${item.fctrlLevelId == tbe.fctrlLevelId}">
    							selected
    						</c:if>
    					>${item.fctrlLevelName }</option>
    				</c:forEach>
    			</select>
    			
    								 <input type="hidden" name="fenterId" value="${tbe.fenterId}">
    								 <input type="hidden" name="forgUnitId" value="${tbe.forgUnitId}">
    								 <input type="hidden" name="fcreatorId" value="${tbe.fcreatorId}">
    								 <input type="hidden" name="flastEditId" value="${tbe.flastEditId}">
    								 <c:if test="${tbe.fcreatTime != null}">
    								 	<input type="hidden" name="fcreatTime" value="${tbe.fcreatTime}">
    								 </c:if>
    								 </td>
    			<td class="Fname">环保信用等级:</td><td class="Fcontent">
    			<select name="fcreditGradeID" style="width:226px; height: 31px;">
    				<option value="">--请选择--</option>
    				<c:forEach items="${tBasCreditGradeList }" var="item">
    					<option value="${item.fcreditGradeId }"
    						<c:if test="${item.fcreditGradeId == tbe.fcreditGradeID}">
    							selected
    						</c:if>
    					>${item.fcreditGradeName }</option>
    				</c:forEach>
    			</select>
    		</tr>
    		<tr>
    			<td class="Fname">是废水重点企业:</td><td class="Fcontent">
	    			<%-- <input name="fisKeySewage" value="${tbe.fisKeySewage}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
						data-options="
						valueField: 'value',
						textField: 'label',
						data: [{
							label: '是',
							value: 'true'
						},{
							label: '否',
							value: 'false'
						}]"/> --%>
					<select name="fisKeySewage" style="width:226px; height: 31px;">
						<option value="">--请选择--</option>
    					<option value="true" <c:if test="${'true' == tbe.fisKeySewage}">selected</c:if> >是</option>
    					<option value="false" <c:if test="${'false' == tbe.fisKeySewage}">selected</c:if> >否</option>
    				</select>
				</td>
    			<td class="Fname">是废气重点企业:</td><td class="Fcontent">
	    			<%-- <input name="fisKeyGas" value="${tbe.fisKeyGas}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
						data-options="
						valueField: 'value',
						textField: 'label',
						data: [{
							label: '是',
							value: 'true'
						},{
							label: '否',
							value: 'false'
						}]"/></td> --%>
					<select name="fisKeyGas" style="width:226px; height: 31px;">
						<option value="">--请选择--</option>
    					<option value="true" <c:if test="${'true' == tbe.fisKeyGas}">selected</c:if> >是</option>
    					<option value="false" <c:if test="${'false' == tbe.fisKeyGas}">selected</c:if> >否</option>
    				</select>
				</td>
    		</tr>
    		<tr>
    			<td class="Fname">是固废重点企业:</td><td class="Fcontent">
					<%-- <input name="fisKeySolid" value="${tbe.fisKeySolid}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
						data-options="
						valueField: 'value',
						textField: 'label',
						data: [{
							label: '是',
							value: 'true'
						},{
							label: '否',
							value: 'false'
						}]"/></td> --%>
					<select name="fisKeySolid" style="width:226px; height: 31px;">
						<option value="">--请选择--</option>
    					<option value="true" <c:if test="${'true' == tbe.fisKeySolid}">selected</c:if> >是</option>
    					<option value="false" <c:if test="${'false' == tbe.fisKeySolid}">selected</c:if> >否</option>
    				</select>
				</td>
    		</tr>
    		<%-- <tr>
    			<td class="Fname">是否污水治理处理厂:</td><td class="Fcontent">
    				<input name="fisSewageFactory" value="${tbe.fisSewageFactory}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
					data-options="
					valueField: 'value',
					textField: 'label',
					data: [{
						label: '是',
						value: 'true'
					},{
						label: '否',
						value: 'false'
					}]"/></td>
    			<td class="Fname">是否在线监测企业:</td><td class="Fcontent">
    				<input name="fisOlmonitor" value="${tbe.fisOlmonitor}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
					data-options="
					valueField: 'value',
					textField: 'label',
					data: [{
						label: '是',
						value: 'true'
					},{
						label: '否',
						value: 'false'
					}]"/></td>
    		</tr>
    		<tr>
    			<td class="Fname">是否排污申报:</td><td class="Fcontent">
    				<input name="fisOutSubmit" value="${tbe.fisOutSubmit}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
					data-options="
					valueField: 'value',
					textField: 'label',
					data: [{
						label: '是',
						value: 'true'
					},{
						label: '否',
						value: 'false'
					}]"/></td>
    			<td class="Fname">是否固废经营单位:</td><td class="Fcontent">
    				<input name="fisSolidManager" value="${tbe.fisSolidManager}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
					data-options="
					valueField: 'value',
					textField: 'label',
					data: [{
						label: '是',
						value: 'true'
					},{
						label: '否',
						value: 'false'
					}]"/></td>
    		</tr>
    		<tr>
    			<td class="Fname">是否开征排污费:</td><td class="Fcontent">
    				<input name="fisOutFee" value="${tbe.fisOutFee}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
					data-options="
					valueField: 'value',
					textField: 'label',
					data: [{
						label: '是',
						value: 'true'
					},{
						label: '否',
						value: 'false'
					}]"/></td>
    			<td class="Fname"></td><td class="Fcontent"></td>
    		</tr> --%>
    	</table>
    </form>
    <div id="dlg-buttons" align="right" style="margin-top:40px;" class="swFormButtunDiv">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()" style="margin-right: 50px;">保存</a>
    </div>
  
</div>
</body>
</html>