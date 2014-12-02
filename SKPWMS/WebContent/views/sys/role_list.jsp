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
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>角色管理</title>
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
			url : '${path}/SKPWMS/role/showrolebyCondition',
			//sortName: 'code',  
			//sortOrder: 'desc',  
			remoteSort : false,
			idField : 'id',
			singleSelect : true,//是否单选  
			pagination : true,//分页控件  
			rownumbers : true,//行号  
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			toolbar : [ 
			 <sec:authorize url="/role/addRole"> 
			    {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-',
			</sec:authorize>
			<sec:authorize url="/role/addRole"> 
			{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-',
			</sec:authorize>
			<sec:authorize url="/role/delRole"> 
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
		/* $(p1).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 10, 50, 100 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',

		}); */
	});
	var roleRow;
	var url;
	var type;
	function add() {
		$("#dlg").dialog("open").dialog('setTitle', '角色添加');
		$("#fm").form("clear");
		url = '${path}/SKPWMS/role/addRole';
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
			$("#dlg").dialog("open").dialog('setTitle', '修改角色');
			$("#fm").form("load", row);
			url = '${path}/SKPWMS/role/addRole?id=' + row.id;
		}
	}
	function saveRole() {
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
					$.post('${path}/SKPWMS/role/delRole', {
						id : row.id
					}, function(result) {
						if (result.success) {
							$("#dlg").dialog("close");
							$('#list_data').datagrid('clearSelections');
							$("#list_data").datagrid("reload");
							$.messager.show({ 
								title : '提示信息',
								msg : '删除成功'
							});
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

	//条件查询Datagrid
	function searchDatagrid() {
		var rolename = $("#sys_rolename").val();
		$("#list_data").datagrid("load", {
			"rolename" : rolename

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#sys_rolename").val("");

	}

	//绑定角色权限
	function addRole_menu() {
		var row = $('#dg').datagrid('getChecked');
		
		
		var ids = '';
		
		
		if (row) {
			$.each(row, function(i, rowval) {
				if (i)
					ids += ',';
				ids += rowval.id;
			});
			
			$.get('${path}/SKPWMS/role/addRoleAuthority', {
				
				authorityids : ids,
				roleid : roleRow

			},function(result) {
				
				$('#function_east').dialog('close');
				$('#dg').datagrid('clearSelections');
				if (result) {
					$.messager.show({ 
						title : '提示信息',
						msg : '绑定成功'
					});
				} else {
					$.messager.show({ // show error message 
						title : '提示信息',
						msg : '绑定失败'
					});
				}
			}, 'json');
		}

	}

	//操作连接
	function formatOper(val, row, index) {
		return '<a href="#" onclick="showFunction_List(\''+row.id+'\')">绑定权限</a>';
	}

	//弹出权限列表

	function showFunction_List(index) {
		$("#dg").datagrid('load');
		roleRow=index;
		$("#function_east").dialog("open").dialog('setTitle', '权限列表');
	}
	
	
	function   auhrotycheck(data){		
		
		var rows = data.rows;
		var listdatarow = $('#list_data').datagrid('getSelected');
		var listdatarowid='';
		if(listdatarow) {
			listdatarowid=listdatarow.id;
		}
		$.ajax({
			url : '<%=request.getContextPath()%>/role/findAuthorityByroleId',
			type : 'post',
			data : {rid:listdatarowid},
			dataType : 'json',
			success : function(r) {
				if(r) {
					for(var i=0; i<rows.length; i++) {
						for(var j=0; j<r.length; j++) {
							if(rows[i].id==r[j].id) {
							var rowIndex = $('#dg').datagrid('getRowIndex',rows[i]);
								$('#dg').datagrid('selectRow',rowIndex);
							}
						}
					}
				
				}
			}
		});
	}
</script>
</head>
<body class="easyui-layout">

	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">角色名称：</td>
					<td class="b"><input id="sys_rolename" name="rolename"
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
	<div id="function_east" class="easyui-dialog"
		style="width: 600px; height: 300px;" buttons="#menu_dlg-buttons"
		closed="true">
		<table id="dg" class="easyui-datagrid" style="width: 586px; height: 227px" data-options="rownumbers:true,singleSelect:false,url:'${path}/SKPWMS/authority/showAuthoritysbyConditionall',method:'get',
			onLoadSuccess : function(data){
				
				auhrotycheck(data);
			
			}">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:400,hidden:true">id</th>
					<th data-options="field:'authorityName',width:400" align="center">权限名称</th>
				</tr>
			</thead>
		</table>
	</div>
	</div>
	<div data-options="region:'center'">
		<table id="list_data" cellspacing="0" cellpadding="0">

			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'rolecode',width:200" align="center">角色编码</th>
					<th data-options="field:'rolename',width:200" align="center">角色名称</th>
					<th
						data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	
	
	<!-- 添加 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px;" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<div class="fitem">
				<label> 角色编号： </label> <input class="easyui-validatebox" type="text"
					name="rolecode" required="true" />
			</div>
			<div class="fitem">
				<label> 角色名称:</label> <input class="easyui-validatebox" type="text"
					name="rolename" required="true" />
			</div>
		</form>
	</div>
	<div id="menu_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="addRole_menu()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#function_east').dialog('close')"
			iconcls="icon-cancel">取消</a>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveRole()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>


</body>
</html>