<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
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
	
	//格式化eayui datebox时间格式
	$('#datebox').datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	});

	
	//省级
	$('#fprovinceId').combogrid({ 
		panelWidth:450, 
		value:'${tbe.fprovinceId}', 
		idField:'fprovinceId', 
		textField:'fprovinceName', 
		url:'<%=path %>/tBasProvinceController/findList', 
		columns:[[ 
			{field:'fprovinceCode',title:'编号',width:60}, 
			{field:'fprovinceName',title:'省级行政区名称',width:100}
		]] 
	}); 
	
})
	//保存
	function save(){
         $('#form').form('submit',{
             url: '<%=path%>/outPermit/save',
             onSubmit:function(){
                 return $(this).form('validate');
             },
             success: function(result){
                 if (result == "false"){
                     $.messager.show({
                         msg: "操作失败，请重试！"
                     });
                 } else {
                	 window.parent.addMenu(result);
                	 $("[name='fOutPID']").val(result);
                     $.messager.show({
                         msg: "操作成功！"
                     });
                 }
             }
         });
     }
     
	//弹出企业选择对话框
	function showDlg(){
		/* $("#dlg_enterprise").dialog("open").dialog('setTitle', '企业列表'); */
		$('#dlg_enterprise').dialog('open'); 
	}  
     
	//选择企业
	function selectEnterprise(){
		var row = $('#t_enterprise').datagrid('getSelected');
		console.info(row);
		$("[name='tBasEnterprise.fenterId']").val(row.fenterId);
		$("[name='tBasEnterprise.fenterName']").val(row.fenterName);
		$("[name='tBasEnterprise.forgCode']").val(row.forgCode);
		$("[name='tBasEnterprise.flongDegree']").val(row.flongDegree);
		$("[name='tBasEnterprise.flongMinute']").val(row.flongMinute);
		$("[name='tBasEnterprise.flongSecond']").val(row.flongSecond);
		$("[name='tBasEnterprise.flatDegree']").val(row.flatDegree);
		$("[name='tBasEnterprise.flatMinute']").val(row.flatMinute);
		$("[name='tBasEnterprise.flatSecond']").val(row.flatSecond);
		$("[name='tBasEnterprise.fprodAddress']").val(row.fprodAddress);
		$("[name='tBasEnterprise.fphone']").val(row.fphone);
		$("[name='tBasEnterprise.fpostCode']").val(row.fpostCode);
		$("[name='tBasEnterprise.fcorpName']").val(row.fcorpName);
		$("[name='tBasEnterprise.fenvironMan']").val(row.fenvironMan);
		
		/* var ids = [];
		var names = [];
		
		$.each(row, function(index, item){
		
			ids.push(item.id);
			names.push(item.rolename);
		
		});               
		console.log(ids);
		$("#sys_roleid").val(ids);
		$("#sys_rolename").val(names); */
		
		$('#dlg_enterprise').dialog('close');
		
		
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
[type=checkbox]{
width: 15px;
}

</style>
</head>
<body class="easyui-layout" data-options="border:false">   
<div data-options="region:'center',border:false" style="padding:5px;">
	<form:form class="easyui_form" id="form" method="post" commandName="tPsOutPermit">
		<input type="hidden" name="fOutPID" value="${fOutPID}" class="easyui-validatebox">
    	<table width="100%">
    		<tr>
    			<!-- <input type="hidden" name="tBasEnterprise.fenterId" value="5c447e77502545abb5d5208b57a1c11b"/> -->
    			<td class="Fname">名称(公章):</td>
    			<td class="Fcontent">
	    			<input type="hidden" name="tBasEnterprise.fenterId" value="${tPsOutPermit.tBasEnterprise.fenterId}" class="easyui-validatebox">
	    			<input type="text" name="tBasEnterprise.fenterName" value="${tPsOutPermit.tBasEnterprise.fenterName}" class="easyui-validatebox">
	    			<!-- <input  id="sys_roleid" easyui-validatebox" type="hidden" name="roleids" />
					<input  id="sys_rolename" easyui-validatebox" type="text" name="rolenames"/> -->
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="showDlg()" iconcls="icon-save"></a>
    			</td>
    			<td class="Fname">行业类别:</td><td class="Fcontent"><input name="" value="" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">组织机构代码:</td><td class="Fcontent"><input name="tBasEnterprise.forgCode" value="${tPsOutPermit.tBasEnterprise.forgCode}" class="easyui-validatebox" readonly="readonly"></td>
    			<td class="Fname">排污申报登记代码:</td><td class="Fcontent"><input name="" value="" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">中心位置经度:</td>
    			<td class="Fcontent">
    				<input name="tBasEnterprise.flongDegree" value="${tPsOutPermit.tBasEnterprise.flongDegree}" class="easyui-validatebox" style="width: 50px" readonly="readonly">度
    				<input name="tBasEnterprise.flongMinute" value="${tPsOutPermit.tBasEnterprise.flongMinute}" class="easyui-validatebox" style="width: 50px" readonly="readonly">分
    				<input name="tBasEnterprise.flongSecond" value="${tPsOutPermit.tBasEnterprise.flongSecond}" class="easyui-validatebox" style="width: 50px" readonly="readonly">秒
    			</td>
    			<td class="Fname">中心位置经度:</td>
    			<td class="Fcontent">
    				<input name="tBasEnterprise.flatDegree" value="${tPsOutPermit.tBasEnterprise.flatDegree}" class="easyui-validatebox" style="width: 50px" readonly="readonly">度
    				<input name="tBasEnterprise.flatMinute" value="${tPsOutPermit.tBasEnterprise.flatMinute}" class="easyui-validatebox" style="width: 50px" readonly="readonly">分
    				<input name="tBasEnterprise.flatSecond" value="${tPsOutPermit.tBasEnterprise.flatSecond}" class="easyui-validatebox" style="width: 50px" readonly="readonly">秒
    			</td>
    		</tr>
    		<tr>
    			<td class="Fname">通信地址:</td><td class="Fcontent" colspan="3" ><input name="tBasEnterprise.fprodAddress" value="${tPsOutPermit.tBasEnterprise.fprodAddress}" class="easyui-validatebox" style="width: 90%" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td class="Fname">联系电话:</td><td class="Fcontent"><input name="tBasEnterprise.fphone" value="${tPsOutPermit.tBasEnterprise.fphone}" class="easyui-validatebox" readonly="readonly"></td>
    			<td class="Fname">邮政编码:</td><td class="Fcontent"><input name="tBasEnterprise.fpostCode" value="${tPsOutPermit.tBasEnterprise.fpostCode}" class="easyui-validatebox" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td class="Fname">法定代表人:</td><td class="Fcontent"><input name="tBasEnterprise.fcorpName" value="${tPsOutPermit.tBasEnterprise.fcorpName}" class="easyui-validatebox" readonly="readonly"></td>
    			<td class="Fname">环保联系人:</td><td class="Fcontent"><input name="tBasEnterprise.fenvironMan" value="${tPsOutPermit.tBasEnterprise.fenvironMan}" class="easyui-validatebox" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td class="Fname">排污种类:</td>
    			<td class="Fcontent">
    				<span style="vertical-align:65%">1、废水</span>
    				<form:checkbox path="fIsSewage"/>
    				<span style="vertical-align:65%">，2、废气</span>
    				<form:checkbox path="fIsWasteGas"/>
    				<span style="vertical-align:65%">，3、噪声</span>
    				<form:checkbox path="fIsNoise"/>
    				<%-- <input type="checkbox" name="fisNoise" value="${tPsOutPermit.true eq fisNoise}" style="width: 15px"> --%>
    			</td>
    			<td class="Fname">项目类型:</td>
    			<td class="Fcontent">
    				<span style="vertical-align:65%">1、新建项目</span><input type="radio"  name="radio1" value="" style="width: 15px">
    				<span style="vertical-align:65%">，2、现有项目</span><input type="radio" name="radio1" value="" style="width: 15px">
    				<span style="vertical-align:65%">，3、改扩建、技改项目</span><input type="radio" name="radio1" value="" style="width: 15px">
    			</td>
    		</tr>
    		<tr>
    			<td class="Fname">主要生产工艺:</td><td class="Fcontent" colspan="3" ><input name="" value="" class="easyui-validatebox" style="width: 90%"></td>
    		</tr>
    		<tr>
    			<td class="Fname">废水处理工艺:</td><td class="Fcontent"><input name="" value="" class="easyui-validatebox"></td>
    			<td class="Fname">处理能力（吨/日）:</td><td class="Fcontent"><input name="" value="" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">废气处理工艺:</td><td class="Fcontent"><input name="" value="" class="easyui-validatebox"></td>
    			<td class="Fname">处理能力（标立方米/小时）:</td><td class="Fcontent"><input name="" value="" class="easyui-validatebox"></td>
    		</tr>
    		<tr style="height: 80px">
    			<td class="Fname">其他:</td>
    			<td class="Fcontent" colspan="3" >
    				<textarea style="width: 90%;height: 100%"></textarea>
    			</td>
    		</tr>
    		
    	</table>    
    </form:form>
    <div id="dlg-buttons" align="right" style="margin-top:40px;">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()" style="margin-right: 50px;">保存</a>
    </div>
    <div style="height: 30px;"></div>
</div>


<div id="dlg_enterprise" class="easyui-dialog" title="企业列表" data-options="iconCls:'icon-save'" 
	style="width:800px;height:500px;padding:10px" closed="true" buttons="#dlg-enterpriseButton"  modal="true">
	<table id="t_enterprise"  class="easyui-datagrid"
		style="width: 385px; height: 224px" url="<%=path%>/wryjbxxController/findPollutionBasInfo"
		singleSelect="true" idField="fenterId" pagination="true" iconCls="icon-save" fit="true" striped="true">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>					
				
				<th data-options="field:'fenterCode',width:80">企业编号</th>
				<th data-options="field:'fenterName',width:80">企业名称</th>
				<th data-options="field:'fcorpName',width:80">法人代表</th>
				<th data-options="field:'forgCode',width:80">组织机构代码</th>
				<th data-options="field:'fenvironMan',width:80">企业环保联系人</th>
			</tr>
		</thead>
	</table>
</div>

<div id="dlg-enterpriseButton">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectEnterprise()" iconcls="icon-save">确定</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_enterprise').dialog('close')" iconcls="icon-cancel">取消</a>
</div>

</body>
</html>