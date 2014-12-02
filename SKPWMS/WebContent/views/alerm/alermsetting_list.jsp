<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			url : '${path}/SKPWMS/alerm/showAlermSettingByCondition',
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
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			} ],
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
	function add() {
		$("#dlg").dialog("open").dialog('setTitle', '报警参数添加');
		$("#fm").form("clear");
		url = '${path}/SKPWMS/alerm/saveAlermSetting';
	}
	function edit() {
		var row = $('#list_data').datagrid('getSelected');

		if (row) {
			$("#dlg").dialog("open").dialog('setTitle', '修改报警参数设置');
			$("#fm").form("load", row);
			url = '${path}/SKPWMS/alerm/saveAlermSetting?id=' + row.id;
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
				if (result == "1") {
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
					$.post('${path}/SKPWMS/alerm/deleteAlermSetting', {
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
		var paramname = $("#sys_paramname").val();
		$("#list_data").datagrid("load", {
			"paramname" : paramname

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#sys_paramname").val("");

	}
	
	
</script>
</head>
<body class="easyui-layout">

	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">报警参数名称：</td>
					<td class="b"><input id="sys_paramname" name="paramname"
						class="easyui-validatebox" /></td>

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
					<th data-options="field:'id'">ID</th>
					<th data-options="field:'paramid'">报警参数编号</th>
					<th data-options="field:'paramname'">报警名称</th>
					<th data-options="field:'paramexpress'">报警表达式</th>
				</tr>
			</thead>
		</table>
	</div>



	<div id="tbb">
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a> <a
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-no',plain:true" onclick="del()">删除</a>
	</div>
	<!-- 添加 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 300px; padding: 10px 20px;" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<div class="fitem">
				<label> 参数编号： </label> <input class="easyui-validatebox" type="text"
					name="paramid" required="true" missingMessage="参数编号不能为空" />
			</div>
			<div class="fitem">
				<label> 报警名称:</label> <input class="easyui-validatebox" type="text"
					name="paramname" required="true" missingMessage="报警名称不能为空"/>
			</div>
			<div class="fitem">
				<label> 报警表达式:</label> <input class="easyui-validatebox" type="text"
					name="paramexpress" required="true" missingMessage="报警表达式不能为空" />
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