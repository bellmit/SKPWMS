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
<script type="text/javascript" src="<%=path%>/frame/js/tools/curdtools.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/css/common.css"></script>
<script type="text/javascript" src="<%=path%>/frame/js/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
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
			url : '${path}/SKPWMS/user/showUserByCondition',
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
			<sec:authorize url="/user/initJumpToUserAdd">          
			            {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					add1();
				}
			}, '-', 
			</sec:authorize>
			<sec:authorize url="/user/initJumpToUserAdd"> 
			{	text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-',
			</sec:authorize>
			<sec:authorize url="/user/delUser">
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
		
		/*onBeforeRefresh:function(){ 
		    $(this).pagination('loading'); 
		    alert('before refresh'); 
		    $(this).pagination('loaded'); 
		}*/
		});
		//设置分页控件  
		var p1 = $('#tt_role').datagrid('getPager');
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
	function add1() {
		/* $("#dlg").dialog("open").dialog('setTitle', '用户添加');
		$("#fm").form("clear");
		url = '${path}/SKPWMS/user/addUser'; */
		add('用户添加','${path}/SKPWMS/user/initJumpToUserAdd','list_data');
	}
	function edit() {	
		var rowsData = $('#list_data').datagrid('getSelections');
		if (!rowsData || rowsData.length==0) {
			tip('请选择编辑项目');
			return;
		}
		if (rowsData.length>1) {
			tip('请选择一条记录再编辑');
			return;
		}
		url='${path}/SKPWMS/user/initJumpToUserAdd?id='+rowsData[0].id;
		add('用户编辑',url,'list_data');
		
	}
	function saveUser() {
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
		 var rowsData = $('#list_data').datagrid('getSelections');
		 if (!rowsData || rowsData.length==0) {
				tip('请选择需要删除的数据');
				return;
			}
			if (rowsData.length>1) {
				tip('请选择一条记录再删除');
				return;
			}
		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('${path}/SKPWMS/user/delUser', {
						id : row.id
					}, function(result) {
						if (result.success) {
							$.messager.show({
								title : '提示信息',
								msg : '删除成功'
							});
							$('#list_data').datagrid('reload'); // reload the user data 
							$('#list_data').datagrid('clearSelections');
						} else {
							$.messager.show({ // show error message 
								title : '提示信息',
								msg : '删除失败'
							});
						}
					}, 'json');
				}
			});
		}
	}
	//选择角色
	function selectRole(){
		var row = $('#tt_role').datagrid('getChecked');		
		
		var ids = [];
		var names = [];
		
		$.each(row, function(index, item){
		
			ids.push(item.id);
			names.push(item.rolename);
		
		});
		$("#sys_roleid").val(ids);
		$("#sys_rolename").val(names);
		
		$('#dlg_role').dialog('close');
		
		
	}
	//弹出角色框
	function showRoleDlg(){
		
		$("#dlg_role").dialog("open").dialog('setTitle', '角色列表');
		
		
	}  
	
	//清空角色信息
	function clearRoleinfo(){
		$("#sys_roleid").val("");
		$("#sys_rolename").val("");
	}

	//条件查询Datagrid
	function searchDatagrid() {
		var username = $("#sys_username").val();
		$("#list_data").datagrid("load", {
			"username" : username

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#sys_username").val("");

	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">用户名：</td>
					<td class="b"><input id="sys_username" name="username"
						class="easyui-validatebox" /></td>

					<td class="a"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchDatagrid()">查询</a></td>
					<td class="a"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-reload"
						onclick="resetDatagridSearch()">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	
	<div data-options="region:'center'">
		<table id="list_data" name="list_data" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'username',width:100" align="center">用户名</th>
					<th data-options="field:'usercount',width:150" align="center">真实姓名</th>					
					<th data-options="field:'password',width:100,hidden:true" align="center">密码</th>
					<th data-options="field:'workUnit',width:200" align="center">工作单位</th>
					<th data-options="field:'phone',width:100" align="center">联系方式</th>
					<th data-options="field:'email',width:150" align="center">邮箱</th>
					<th data-options="field:'roleStr',width:300" align="center">所属角色</th>
				</tr>
			</thead>
		</table>
	</div>
	
	

</body>
</html>