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
var fOutPID='${tPsOutPermit.fOutPID}';

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

	//格式化easyui datebox时间格式
	$("#fVaildDate").datebox({
		panelHeight:260,
        formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
    });
	
	$("#fEndDate").datebox({
		panelHeight:260,
        formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
    });
	
	$("#fIssuingDate").datebox({
		panelHeight:260,
        formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
    });
	
	/* fOutPID=$("[name='fOutPID']").val(); */
	if(""==fOutPID){
		$("#divOutSewage").hide();
		$("#divOutWastegas").hide();
	}
	
})
     
	//格式化eayui datebox时间格式
	$("[class='easyui-validatebox']").datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	});
	
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
            	 /* window.parent.addMenu(result); */
            	 $("[name='fOutPID']").val(result);
            	 fOutPID=result;
            	 $("#divOutSewage").show();
            	 $("#divOutWastegas").show();
            	 var sUrl='<%=path %>/outSewage/list?tPsOutPermit.fOutPID='+fOutPID;
         		 $('#sDatagrid').datagrid({
         			url:sUrl
         		 });
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
		
		$('#dlg_enterprise').dialog('close');
	}
	
	function addTab(name,src){
		src=src+"?fOutPID="+fOutPID;
		var row = $('#sDatagrid').datagrid('getSelected');
		if(null != row){
		src=src+"&fOutSewageID="+row.fOutSewageID;
		}
        window.parent.addTab(name,src);
    }
	
	function delSwrwpfk() {
		var row = $('#sDatagrid').datagrid('getSelected');

		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('<%=path%>/outSewage/delete', {
						id : row.fOutSewageID
					}, function(result) {
						if (result.success) {
							$('#sDatagrid').datagrid('reload'); // reload the user data 
							$('#sDatagrid').datagrid('clearSelections');
						} else {
							$.messager.show({ // show error message 
								//title : 'Error',
								msg : '删除失败'
							});
						}
					}, 'json');
				}
			});
		}
	}
	
	function update(){
		var row = $('#sDatagrid').datagrid('getSelected');
		if(row){
			addTab('水污染物排放口管理','outSewage/find');
		}else{
			$.messager.show({ // show error message 
				title : '提示',
				msg : '请选择数据'
			});
		}
	}
	
	function delWastegas() {
		var row = $('#wastegasDatagrid').datagrid('getSelected');

		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('<%=path%>/tPsWasteGasOutlet/del', {
						id : row.fWGOutletID
					}, function(result) {
						if (result.success) {
							$('#wastegasDatagrid').datagrid('reload'); // reload the user data 
							$('#wastegasDatagrid').datagrid('clearSelections');
						} else {
							$.messager.show({ // show error message 
								//title : 'Error',
								msg : '删除失败'
							});
						}
					}, 'json');
				}
			});
		}
	}
	
	function updateWastegas(){
		var row = $('#wastegasDatagrid').datagrid('getSelected');
		if(row){
			addTabWastegas('大气污染物排放口管理','tPsWasteGasOutlet/find');
		}else{
			$.messager.show({ // show error message 
				title : '提示',
				msg : '请选择数据'
			});
		}
	}
	
	function addTabWastegas(name,src){
		src=src+"?fOutPID="+fOutPID;
		var row = $('#wastegasDatagrid').datagrid('getSelected');
		if(null != row){
		src=src+"&fWGOutletID="+row.fWGOutletID;
		}
        window.parent.addTab(name,src);
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
[type=checkbox]{
width: 15px;
}
</style>
</head>
<body class="easyui-layout" data-options="fit:'true',border:false">  
<!-- <div style="height: 100%; width: 100%; cellSpacing=0; cellPadding=0; "> -->
	<!-- 企业选择弹窗 S -->
	<div id="dlg_enterprise" class="easyui-dialog" title="企业列表" data-options="iconCls:'icon-search'" 
		style="width:800px;height:500px;padding:10px" closed="true" buttons="#dlg-enterpriseButton"  modal="true">
		<table id="t_enterprise"  class="easyui-datagrid"
			style="width: 385px; height: 224px" url="<%=path%>/wryjbxxController/findPollutionBasInfo"
			singleSelect="true" idField="fenterId" pagination="true" iconCls="icon-save" fit="true" striped="true">
			<thead>
				<tr>
					<th field="ck" checkbox="true"></th>					
					
					<th data-options="field:'fenterCode',width:150">企业编号</th>
					<th data-options="field:'fenterName',width:200">企业名称</th>
					<th data-options="field:'fcorpName',width:100">法人代表</th>
					<th data-options="field:'forgCode',width:150">组织机构代码</th>
					<th data-options="field:'fenvironMan',width:100">企业环保联系人</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="dlg-enterpriseButton">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectEnterprise()" iconcls="icon-save">确定</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_enterprise').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
	<!-- 企业选择弹窗 E -->

<div data-options="region:'north',border:false" style="height:180px;">
		<!-- <div id="dlg-buttons" align="right" style="margin-top:0px;">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()" style="margin-right: 50px;">保存</a>
	    </div> -->
	    
	    <div id="fToolbar" style="background-color: #F4F4F4">
	    	<div>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save()">保存</a>
	    	</div>
	    </div>
	    
		<form:form class="easyui_form" id="form" method="post" commandName="tPsOutPermit">
			<input type="hidden" name="fOutPID" value="${tPsOutPermit.fOutPID}" class="easyui-validatebox">
	    	<table width="100%" toolbar="#fToolbar">
	    		<tr>
	    			<td class="Fname">许可证编号:</td><td class="Fcontent"><input name="fOutPCode" value="${tPsOutPermit.fOutPCode}" class="easyui-validatebox" data-options="required:true"></td>
	    			<td class="Fname">经办人:</td><td class="Fcontent"><input name="fEmpID" value="${tPsOutPermit.fEmpID}" class="easyui-validatebox"></td>
	    		</tr>
	    		<tr>
	    			<td class="Fname">许可证有效期限 从:</td><td class="Fcontent"><input type="text" id="fVaildDate" name="fVaildDate" value="${tPsOutPermit.fVaildDate}" class="easyui-datebox" data-options="required:true"></td>
	    			<td class="Fname">至:</td><td class="Fcontent"><input type="text" id="fEndDate" name="fEndDate" value="${tPsOutPermit.fEndDate}" class="easyui-datebox" data-options="required:true"></td>
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
	    			<td class="Fname">单位名称:</td>
	    			<td class="Fcontent">
		    			<input type="hidden" name="tBasEnterprise.fenterId" value="${tPsOutPermit.tBasEnterprise.fenterId}" class="easyui-validatebox">
		    			<input type="text" name="tBasEnterprise.fenterName" value="${tPsOutPermit.tBasEnterprise.fenterName}" class="easyui-validatebox" data-options="required:true" readonly="readonly">
						<%--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="showDlg()" iconcls="icon-search"></a> --%>
	    			</td>
	    		</tr>
	    		<%-- <tr>
	    			<td class="Fname">主办部门意见:</td><td class="Fcontent" colspan="3" ><input name="fcountyId" value="${}" class="easyui-validatebox" style="width: 90%"></td>
	    		</tr>
	    		<tr>
	    			<td class="Fname"></td><td class="Fcontent"></td>
	    			<td class="Fname">经办日期:</td><td class="Fcontent"><input name="ftownId" value="${}" class="easyui-datebox"></td>
	    		</tr>
	    		<tr>
	    			<td class="Fname">发证机关意见:</td><td class="Fcontent" colspan="3" ><input name="fcountyId" value="${}" class="easyui-validatebox" style="width: 90%"></td>
	    		</tr> --%>
	    		<tr>
	    			<td class="Fname">发证日期:</td><td class="Fcontent"><input type="text" id="fIssuingDate" name="fIssuingDate" value="${tPsOutPermit.fIssuingDate}" class="easyui-datebox" data-options="required:true"></td>
	    			<td class="Fname"></td><td class="Fcontent"></td>
	    		</tr>
	    		
	    		
	    	</table>
	    </form:form>
</div>

<div id="divOutSewage" data-options="region:'center',border:false">
	<!-- <div style="border-bottom: 1px solid #95B8E7">  -->
	<!-- <div> 
		
    </div> -->
    
	<!-- <div id="divOutSewage" style="height: 349px"> -->
		<table id="sDatagrid" class="easyui-datagrid" title="水污染物排放口"
            url="<%=path %>/outSewage/list?tPsOutPermit.fOutPID=${tPsOutPermit.fOutPID}"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
	        <thead>
	            <tr>
	            	<th field="fOutPIDIndex" checkbox="true" ></th>
	            	<th data-options="field:'fOutSewageName'" width="100" >排污口名称</th>
	            	<th data-options="field:'fOutSewageCode'" width="100" >排污口编号</th>
	            	<th data-options="field:'tBasOutWhere.foutWhereName'" width="100" >排放去向（受纳水体名称）</th>
	            	<th data-options="field:'tBasWaterDisStd.fwaterDisStdName'" width="100" >废水排放执行标准</th>
	            	<th data-options="field:'fDayMaxOut'" width="100" >废水排放量限值(吨/日)</th>
	            </tr>	
	        </thead>
	    </table>
	    <div id="toolbar">
	    	<div>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addTab('水污染物排放口管理','outSewage/add')">添加</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delSwrwpfk()">删除</a>
	    	</div>
	    </div>
	<!-- </div> -->
</div>

<div id="divOutWastegas" data-options="region:'south',border:false" style="height: 165px">
    
		<table id="wastegasDatagrid" class="easyui-datagrid" title="大气污染物排放口"
            url="<%=path %>/tPsWasteGasOutlet/list?tPsOutPermit.fOutPID=${tPsOutPermit.fOutPID}"
            toolbar="#wastegastoolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
	        <thead>
	            <tr>
	            	<th field="fOutPIDIndex" checkbox="true" ></th>
	            	<th data-options="field:'fWGoutletName'" width="100" >排污口名称</th>
	            	<th data-options="field:'fWGOutletCode'" width="100" >排污口编号</th>
	            	<th data-options="field:'tBasWaterDisStd.fwaterDisStdName'" width="100" >废水排放执行标准</th>
	            	<th data-options="field:'fDayMaxOut'" width="100" >废水排放量限值(吨/日)</th>
	            </tr>	
	        </thead>
	    </table>
	    <div id="wastegastoolbar">
	    	<div>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addTab('大气污染物排放口管理','tPsWasteGasOutlet/add')">添加</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateWastegas()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delWastegas()">删除</a>
	    	</div>
	    </div>
</div>
</body>
<script type="text/javascript">
	$("#divOutWastegas").height(($(window).height()-186)/2);
</script>
</html>