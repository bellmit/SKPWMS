<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>报警参数设置</title>
<style type="text/css">
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
<script type="text/javascript">
	$(function() {

		//设置分页控件  
		$('#list_data').datagrid({

			iconCls : 'icon-edit',//图标  
			width : 700,
			height : 'auto',
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,//是否可折叠的  
			fit : true,//自动大小  
			url : '${path}/SKPWMS/alerm/showWarningSetupByCondition',
			//sortName: 'code',  
			//sortOrder: 'desc',  
			remoteSort : false,
			idField : 'id',
			singleSelect : false,//是否单选  
			pagination : true,//分页控件  
			rownumbers : true,//行号  
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			toolbar : [ 
			<sec:authorize url="/alerm/saveWarningSetup"> 
			{
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					newadd();
				}
			}, '-',
			</sec:authorize>
			
			<sec:authorize url="/alerm/deleteWarningSetup"> 
			{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			} 
			</sec:authorize>
			
			],
		});

		//设置分页控件  
		var p = $('#list_data').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',

		});
		//设置分页控件  
		var p1 = $('#dg').datagrid('getPager');
		$(p1).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',

		});
	})
	var url;
	var type;

	function newadd() {
		var name = "报警设置管理";
		var src = "alerm/waringsetupadd";
		window.parent.addTab(name, src);
	}

	function add() {
		$("#dlg").dialog("open").dialog('setTitle', '报警参数添加');
		$("#fm").form("clear");
		url = '${path}/SKPWMS/alerm/saveWarningSetup';
	}
	function edit() {
		var row = $('#list_data').datagrid('getSelected');

		if (row) {
			$("#dlg").dialog("open").dialog('setTitle', '修改报警参数设置');

			$("#fm").form("load", row);
			$("#pollutant").combobox('setValue', row.pollutant.fPollutantID);
			$("#warningItem").combobox('setValue', row.warningItem.id);

			url = '${path}/SKPWMS/alerm/saveWarningSetup?id=' + row.id;
		}
	}
	function saveAlermSetting() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result) {
					$.messager.alert("提示信息", "操作成功");
					$("#dlg").dialog("close");
					$("#list_data").datagrid("reload");
				} else {
					$.messager.alert("提示信息", "操作失败");
					$("#dlg").dialog("close");
					$("#list_data").datagrid("reload");
				}
			}
		});
	}
	function del() {
		var row = $('#list_data').datagrid('getSelected');

		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('${path}/SKPWMS/alerm/deleteWarningSetup', {
						id : row.id
					}, function(result) {
						if (result.success) {
							$('#list_data').datagrid('reload'); // reload the user data 
							$('#list_data').datagrid('clearSelections');
						} else {
							$.messager.show({ // show error message 
								title : 'Error',
								msg : '删除失败'
							});
						}
					}, 'json');
				}
			});//页面我先看下 
		}
	}

	//条件查询Datagrid
	function searchDatagrid() {
		var warningType = $("#warn_warningType").val();
		/* var pollutantid = $("#pollutant").combobox('getValue');
		var warningItem = $("#warningItem").combobox('getValue'); */
		var setupno = $("#setupno").val();
		$("#list_data").datagrid("load", {
			"warningType" : warningType,
			"setupno" : setupno

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#warn_warningType").val("");
		$("#setupno").val("");
		/* $("#pollutant").combobox('setValue',"");
		$("#warningItem").combobox('setValue',""); */

	}

	//添加超链接，跳转到详报警设置管理详细信息页面
	function jumpToOutPermit(val, row) {
		var ahref = "<a href=\"javascript:void(0)\" onclick=\"addTab('报警设置管理','"
				+ row.id + "')\">" + val + "</a>";
		return ahref;
	}
	//新增tab页
	function addTab(name, id) {
		var src = "alerm/waringsetupfind?id=" + id;
		window.parent.addTab(name, src);
	}
</script>
</head>
<body class="easyui-layout">

	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">报警编号：</td>
					<td class="b"><input id="setupno" class="easyui-validatebox"
						type="text" name="setupno" /></td>

					<%-- <td class="a">污染源因子：</td>
					<td class="b"><input id="pollutant" class="easyui-combobox"
						data-options="valueField:'fPollutantID',textField:'fPollutantName',url:'${path}/SKPWMS/alerm/showPolluntant'" /></td>
					<td class="a">预警项目：</td>
					<td class="b"><input id="warningItem" class="easyui-combobox"
						data-options="valueField:'id',textField:'warningItemName',url:'${path}/SKPWMS/alerm/showWarningItem'" /></td> --%>
					<td class="a">报警类型：</td>
					<td class="b"><select id="warn_warningType" name="warningType"
						style="width: 159px">
							<option value="">---请选择类型---</option>
							<option value="1">黄色</option>
							<option value="2">橙色</option>
							<option value="3">红色</option>
					</select></td>

					<td class="a"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchDatagrid()">搜索</a></td>
					<td class="a"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-reload"
						onclick="resetDatagridSearch()">重置</a></td>
				</tr>
			</table>
		</form>
	</div>

	<div data-options="region:'center'">
		<table id="list_data" cellspacing="0" cellpadding="0">

			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'setupno',width:100,formatter:jumpToOutPermit" align="center">报警编号</th>
					<th
						data-options="field:'pollutant',width:200,formatter: function(value,row,index){
				if (row.pollutant){
					return row.pollutant.fPollutantName;
				} else {
					return value;
				}}" align="center">污染源因子</th>
					<th
						data-options="field:'warningItem',width:100,formatter: function(value,row,index){
				if (row.warningItem){
					return row.warningItem.warningItemName;
				} else {
					return value;
				}}" align="center">预警项目</th>
					<th data-options="field:'threshold',width:100" align="center">预警阀值</th>
					<th
						data-options="field:'warningType',formatter: function (val, rec) {
						if(val==1){
						 return '黄';}
						 
						 else if(val==2){
						  return '橙';
						 }						 
						 else{						 
						 	return '红';
						 }						 
						 }" align="center">报警类型</th>
					<!-- <th data-options="field:'a'">报警对象</th>
					<th data-options="field:'a'">报警方式</th> -->
				</tr>
			</thead>
		</table>
	</div>

	<!-- 添加 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 300px; padding: 10px 20px;" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<div class="fitem">
				<label> 预警编号:</label> <input id="setupno" class="easyui-validatebox"
					type="text" name="setupno" required="true" missingMessage="编号不能为空" />
			</div>

			<div class="fitem">
				<label> 预警项目:</label> <input id="warningItem"
					class="easyui-combobox" name="warningItem.id"
					data-options="valueField:'id',textField:'warningItemName',url:'${path}/SKPWMS/alerm/showWarningItem'" />
			</div>
			<div class="fitem">
				<label> 污染源因子:</label> <input id="pollutant" class="easyui-combobox"
					name="pollutant.fPollutantID"
					data-options="valueField:'fPollutantID',textField:'fPollutantName',url:'${path}/SKPWMS/alerm/showPolluntant'" />
			</div>
			<div class="fitem">
				<label> 预警阀值:</label> <input id="threshold"
					class="easyui-validatebox" type="text" name="threshold"
					required="true" />
			</div>
			<div class="fitem">
				<label> 报警类型:</label> <select name="warningType"
					style="width: 159px">
					<option>---请选择类型---</option>
					<option value="1">黄色</option>
					<option value="2">橙色</option>
					<option value="3">红色</option>
				</select>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveAlermSetting()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>



</body>
</html>