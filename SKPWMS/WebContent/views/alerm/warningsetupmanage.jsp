<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
var warningid='${warningSetup.id}';

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

	/* fOutPID=$("[name='fOutPID']").val(); */
	if(""==warningid){
		$("#WarningModel").hide();
		$("#divWarningObject").hide();
	}
	
})
     
	//格式化eayui datebox时间格式
	$("[class='easyui-validatebox']").datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	});
	
//保存
function save(){
     $('#form').form('submit',{
         url: '<%=path%>/alerm/saveWarningSetup',
         onSubmit:function(){
             return $(this).form('validate');
         },
         success: function(result){
             if (result == "false"){
                 $.messager.show({
                     msg: "操作失败，请重试！"
                 });
             } else {
            	
            	 $("[name='warningid']").val(result);
            	 warningid=result;
            	 
            	 $("#WarningModel").show();
         		 $("#divWarningObject").show();
            	 
            	 var url_model='<%=path%>/alerm/showwarningModelByWarnid?warningid='+warningid;
            	 var url_object='<%=path%>/alerm/showEwsWarningObjectByWarnId?warningid='+warningid;
         		 $('#sDatagrid1').datagrid({
         			url:url_model
         		 });
         		$('#sDatagrid').datagrid({
         			url:url_object
         		 });
                 $.messager.show({
                     msg: "操作成功！"
                 });
             }
         }
     });
 }
 

	/* function addTab(name,src){
		src=src+"?fOutPID="+fOutPID;
		var row = $('#sDatagrid').datagrid('getSelected');
		if(null != row){
		src=src+"&fOutSewageID="+row.fOutSewageID;
		}
        window.parent.addTab(name,src);
    } */
	
	var url_object;
	
	function addWarningObject(){
		$('#dlg_warnObject').dialog('open').dialog('setTitle','报警对象添加');
        $('#fm1').form('clear');
        url_object = '<%=path%>/alerm/saveEwsWarningObject?warningSetup.id='+warningid;
	}
	function editWarningObject(){
		 var row = $('#sDatagrid').datagrid('getSelected');
         if (row){
             $('#dlg_warnObject').dialog('open').dialog('setTitle','修改报警对象');
             $('#fm1').form('load',row);
             if(row.warningObject!=null){
     			$('#warningObject').combobox('setValue', row.warningObject.id);
     			}
             url_object = '<%=path%>/alerm/saveEwsWarningObject?id='+row.id;
         }
	}
	
	function saveWarningObject(){
		$("#fm1").form("submit", {
			url : url_object,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result == "1") {
					$.messager.alert("提示信息", "操作成功");
					$("#dlg_warnObject").dialog("close");
					$("#sDatagrid").datagrid("reload");
				} else {
					$.messager.alert("提示信息", "操作失败");
					$("#dlg_warnObject").dialog("close");
					$("#sDatagrid").datagrid("reload");
				}
			}
		});
	}
	function delWarningObject() {
		var row = $('#sDatagrid').datagrid('getSelected');

		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('<%=path%>/alerm/deleteEwsWarningObject', {
						id : row.id
					}, function(result) {
						if (result.success) {
							$('#sDatagrid').datagrid('reload'); // reload the user data 
							$('#sDatagrid').datagrid('clearSelections');
						} else {
							$.messager.show({ // show error message 
								title : 'Error',
								msg : '删除失败'
							});
						}
					}, 'json');
				}
			});
		}
	}
	
	
	var url_model;
	
	function addWarningModel(){
		$('#dlg_warnModel').dialog('open').dialog('setTitle','报警方式添加');
        $('#fm').form('clear');
        url_model = '<%=path%>/alerm/saveEwsWarningModel?warningSetup.id='+warningid;
	}
	function editWarningModel(){
		 var row = $('#sDatagrid1').datagrid('getSelected');
         if (row){
             $('#dlg_warnModel').dialog('open').dialog('setTitle','修改报警方式');
             $('#fm').form('load',row);
             if(row.warningModel!=null){
      			$('#warningmodel').combobox('setValue',row.warningModel.id);
      		 }
             url_model = '<%=path%>/alerm/saveEwsWarningModel?id='+row.id;
         }
	}
	
	function saveWarningModel(){
		$("#fm").form("submit", {
			url : url_model,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result == "1") {
					$.messager.alert("提示信息", "操作成功");
					$("#dlg_warnModel").dialog("close");
					$("#sDatagrid1").datagrid("reload");
				} else {
					$.messager.alert("提示信息", "操作失败");
					$("#dlg_warnModel").dialog("close");
					$("#sDatagrid1").datagrid("reload");
				}
			}
		});
	}
	 	function delWarningModel(){
		var row = $('#sDatagrid1').datagrid('getSelected');

		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('<%=path%>/alerm/deleteEwsWarningModel', {
						id : row.id
					}, function(result) {
						if (result.success) {
							$('#sDatagrid1').datagrid('reload'); // reload the user data 
							$('#sDatagrid1').datagrid('clearSelections');
						} else {
							$.messager.show({ // show error message 
								title : 'Error',
								msg : '删除失败'
							});
						}
					}, 'json');
				}
			});
		}

	}
</script>
<style type="text/css">
.Fname {
	text-align: right;
}

.Fcontent {
	
}

tr {
	height: 35px;
	border: 1px solid black;
	border-bottom: 1px solid red;
}

input {
	height: 25px;
	width: 220px;
}

[type=checkbox] {
	width: 15px;
}
.formName {
	width: 70px;
}

#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',fit:'true',border:false">
		
		<div>

			<div id="fToolbar" style="background-color: #F4F4F4">
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-save" plain="true" onclick="save()">保存</a>
				</div>
			</div>

			<form:form class="easyui_form" id="form" method="post"
				commandName="tPsOutPermit">
				<input type="hidden" id="warningid"  name="warningid" value="${warningSetup.id}" class="easyui-validatebox">
				<table width="100%" toolbar="#fToolbar">
					<tr>
						<td class="Fname">预警编号:</td>
						<td class="Fcontent"><input id="setupno"
							class="easyui-validatebox" type="text" name="setupno"
							required="true" value="${warningSetup.setupno}" missingMessage="编号不能为空"/></td>
					</tr>
					<tr>
						<td class="Fname">预警项目:</td>
						<td class="Fcontent"><input id="warningItem"
							class="easyui-combobox" name="warningItem.id" value="${warningSetup.warningItem.id}"
							data-options="valueField:'id',textField:'warningItemName',url:'${path}/SKPWMS/alerm/showWarningItem'" style="height: 28PX"/></td>
						<td class="Fname">污染源因子:</td>
						<td class="Fcontent"><input id="pollutant"
							class="easyui-combobox" name="pollutant.fPollutantID" value="${warningSetup.pollutant.fPollutantID}"
							data-options="valueField:'fPollutantID',textField:'fPollutantName',url:'${path}/SKPWMS/alerm/showPolluntant'" /></td>
					</tr>
					<tr>
						<td class="Fname">预警阀值:</td>
						<td class="Fcontent"><input id="threshold"
							class="easyui-validatebox" type="text" name="threshold"
							required="true" value="${warningSetup.threshold}"/></td>
						<td class="Fname">报警类型:</td>
						<td class="Fcontent"><select name="warningType"
							style="width:226px,height:28PX">
								<option>---请选择类型---</option>
								<option value="1" <c:if test="${warningSetup.warningType==1}">selected</c:if>>黄色</option>
								<option value="2" <c:if test="${warningSetup.warningType==2}">selected</c:if>>橙色</option>
								<option value="3" <c:if test="${warningSetup.warningType==3}">selected</c:if>>红色</option>
						</select></td>
					</tr>
				</table>
			</form:form>
		</div>



		<div id="divWarningObject" style="height: 349px">
			<table id="sDatagrid" class="easyui-datagrid" title="预警对象"
				url="<%=path%>/alerm/showEwsWarningObjectByWarnId?warningid=${warningSetup.id}" toolbar="#toolbar"
				pagination="true" fit="true" rownumbers="true" fitColumns="true"
				singleSelect="true" striped="true">
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th data-options="field:'id',width:100,hidden:true">ID</th>
						<th
							data-options="field:'warningObject',width:100,formatter: function(value,row,index){
				if (row.warningObject){
					return row.warningObject.warningObjName;
				} else {
					return value;
				}}">预警对象名称</th>


					</tr>
				</thead>
			</table>
			<div id="toolbar">
				<div>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="addWarningObject()">添加</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editWarningObject()">修改</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delWarningObject()">删除</a>
    	</div>
			</div>
		</div>


		<div id="WarningModel" style="height: 349px">
			<table id="sDatagrid1" class="easyui-datagrid" title="预警方式"
				url="<%=path%>/alerm/showwarningModelByWarnid?warningid=${warningSetup.id}" toolbar="#toolbar1"
				pagination="true" fit="true" rownumbers="true" fitColumns="true"
				singleSelect="true" striped="true">
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th data-options="field:'id',width:100,hidden:true">ID</th>
						<!-- <th data-options="field:'fOutSewageName'" width="100">预警设置名称</th> -->
						<th
							data-options="field:'warningModel',width:100,formatter: function(value,row,index){
				if (row.warningModel){
					return row.warningModel.warningModelName;
				} else {
					return value;
				}}">预警方式</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar1">
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addWarningModel()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="editWarningModel()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true" onclick="delWarningModel()">删除</a>
				</div>

			</div>
		</div>	
		
		  <!-- 预警对象 -->
		 <div id="dlg_warnObject" class="easyui-dialog" style="width:500px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons_warnObject">
        
        <form id="fm1" method="post">
			
			<div class="fitem">
				
				<label> 预警对象:</label> <input id="warningObject" class="easyui-combobox"
					name="warningObject.id"
					data-options="valueField:'id',textField:'warningObjName',url:'${path}/SKPWMS/alerm/showWarningObject'" />
			</div>
			
		</form>
    </div>
    <div id="dlg-buttons_warnObject">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveWarningObject()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_warnObject').dialog('close')">取消</a>
    </div>
    
    <!-- 预警方式 -->
    <!-- 添加 -->
	<div id="dlg_warnModel" class="easyui-dialog" style="width:500px;height:300px;padding:10px 20px"
            closed="true"  buttons="#dlg-buttons_warnModel">
		<form id="fm" method="post">
			<div class="fitem">
				
				<label> 预警方式:</label> <input id="warningmodel" class="easyui-combobox"
					name="warningModel.id"
					data-options="valueField:'id',textField:'warningModelName',url:'${path}/SKPWMS/alerm/showwarningModel'" />
			</div>
			
		</form>
	</div>
    <div id="dlg-buttons_warnModel">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveWarningModel()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_warnModel').dialog('close')">取消</a>
    </div>	

	</div>
	



</body>
</html>