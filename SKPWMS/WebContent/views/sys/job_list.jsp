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
<title>职位列表</title>
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
			url : '${path}/SKPWMS/job/showJobbyCondition',
			sortName: 'jobCode',  
			sortOrder: 'asc',  
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
		/*onBeforeRefresh:function(){ 
		    $(this).pagination('loading'); 
		    alert('before refresh'); 
		    $(this).pagination('loaded'); 
		}*/
		});
	})
	var url;
	var type;
	function add() {
		$("#dlg").dialog("open").dialog('setTitle', '职位添加');
		$("#fm").form("clear");
		url = '${path}/SKPWMS/job/addJob';
	}
	function edit() {
		var row = $('#list_data').datagrid('getSelected');
	    var rowsData = $('#list_data').datagrid('getSelections');
	    if (!rowsData || rowsData.length==0) {
	    	 $.messager.show({
	    		 title:'提示',
	    		 msg:'请选择编辑项目',
	    		 showType:'show'
	    		 });
			return;
		}
		if (rowsData.length>1) {
			 $.messager.show({
	    		 title:'提示',
	    		 msg:'请选择一条记录再编辑',
	    		 showType:'show'
	    		 });
			return;
		}

		if (row) {

			$("#dlg").dialog("open").dialog('setTitle', '修改职位');
			
			$("#fm").form("load", row);
			/* $('#cc').combobox('setValue', row.parentJob.id); */
			url = '${path}/SKPWMS/job/addJob?id=' + row.id;
		}
	}
	function saveJob() {
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
					$.post('${path}/SKPWMS/job/delJob', {
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
			});
		}
	}

	//条件查询Datagrid
	function searchDatagrid() {
		var jobname = $("#sys_jobname").val();
		$("#list_data").datagrid("load", {
			"jobname" : jobname

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#sys_jobname").val("");

	}
</script>
</head>
<body class="easyui-layout">

	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">职位名称：</td>
					<td class="b"><input id="sys_jobname" name="jobname"
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
					<th data-options="field:'id',hidden:true">ID</th>
					<!-- <th data-options="field:'depid'">部门ID</th> -->
					<th data-options="field:'jobCode',width:200 " align="center">职位编号</th>
					<th data-options="field:'jobname',width:200" align="center">职位名称</th>
					<th
						data-options="field:'parentJob',formatter: function(value,row,index){
				if (row.parentJob){
					return row.parentJob.jobname;
				} else {
					return value;
				}}">父级职位</th>
					<th data-options="field:'longCode',hidden:true">职务长编码</th>
					<th
						data-options="field:'orgUnit',width:200,formatter: function(value,row,index){
				if (row.orgUnit){
					return row.orgUnit.orgUnitName;
				} else {
					return value;
				}}" align="center">所属组织</th>
				</tr>
			</thead>
		</table>
	</div>
	<!-- 添加 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px;" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<!-- <div class="fitem">
				<label> 部门ID： </label> <input type="text" name="depid" />
			</div> -->
			<div class="fitem">
				<label> 职位编号:</label> <input class="easyui-validatebox" type="text"
					name="jobCode" required="true" missingMessage="职位编号不能为空"/>
			</div>
			<div class="fitem">
				<label> 职位名称:</label> <input class="easyui-validatebox" type="text"
					name="jobname" required="true" missingMessage="职位名称不能为空"/>
			</div>
			<!-- <div class="fitem">
				<label> 父级职位:</label> <input class="easyui-validatebox" type="text"
					name="parentJob.jobname" data-options="" />
			</div> -->
			<div class="fitem">
				<label> 父级职位:</label> <input id="cc" class="easyui-combobox"
					name="parentJob.id"
					data-options="valueField:'id',textField:'jobname',url:'${path}/SKPWMS/job/showJob'" />
			</div>
			<!-- <div class="fitem">
				<label> 职务长编码:</label> <input class="easyui-validatebox" type="text"
					name="longCode" data-options="" />
			</div> -->
			<div class="fitem">
				<label> 所属组织:</label> <input id="userid" class="easyui-combobox"
					name="orgUnit.id"
					data-options="valueField:'id',textField:'orgUnitName',url:'${path}/SKPWMS/orgunit/showOrgunit'">
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveJob()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>


</body>
</html>