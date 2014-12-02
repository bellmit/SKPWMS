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
<link rel="stylesheet" href="<%=path%>/frame/js/zTree_v3/css/demo.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=path%>/frame/js/zTree_v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="<%=path%>/frame/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
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
	var url;
	var type;
	function add() {
		$("#dlg").dialog("open").dialog('setTitle', '菜单添加');
		$("#fm").form("clear");
		url = '${path}/SKPWMS/menu/addMenu';
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
			$("#dlg").dialog("open").dialog('setTitle', '修改菜单');
			$("#fm").form("clear");
			$("#fm").form("load", row);
			
			if(row.parentId!=null)
			{
				if(row.parentId!="")
				{	
					$("#menuparentname").val(row.parentName);
					$('#menuparentid').val(row.parentId);
				}
			}
			url = '${path}/SKPWMS/menu/addMenu?id=' + row.id;
		}
	}
	function saveMenu() {
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
					$('#list_data').treegrid('clearSelections');
					window.location.reload();
				} else {
					$.messager.alert("提示信息", "操作失败");
					$("#dlg").dialog("close");
					$('#list_data').treegrid('clearSelections');
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
					$.post('${path}/SKPWMS/menu/delMenu', {
						id : row.id
					}, function(result) {
						if (result.success) {
							$('#list_data').treegrid('clearSelections');
							window.location.reload(); // reload the user data 
						} else {
							$.messager.show({ 
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
		var menuName = $("#sys_menuName").val();
		$("#list_data").datagrid("load", {
			"menuName" : menuName

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#sys_menuName").val("");

	}
	
	//点击菜单列表，弹出弹出框
    function showMenuDialog(){
		 //弹出弹出框
   	 $('#dialog').dialog('open').dialog('setTitle','请选择菜单');
   	 //加载tree
   	 var setting = {
					async: {  
   		     		enable: true,  
   		            url:"<%=path%>/menu/findAllMenuItemForZtree"
   		        } ,
   		        data: {  
   		            simpleData: {  
   		                enable: true,
   		        		pIdKey: "pid",
   		        		idKey: "id",
   		        		rootPId: null
   		            }  
   		        },
   		        callback:{
   		        	onDblClick: zTreeOnDblClick
   		        }
   		};
   	 $.fn.zTree.init($("#menutree"), setting);
    }
   
	function zTreeOnDblClick(event, treeId, treeNode) {
   	
   	$("#menuparentname").val(treeNode.name);
   	$("#menuparentid").val(treeNode.id);
   	//关闭弹出框
   	$('#dialog').dialog('close');
   }
</script>
</head>
<body class="easyui-layout">

	<!-- <div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">菜单名称：</td>
					<td class="b"><input id="sys_menuName" name="menuName"
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
	</div> -->

	<div data-options="region:'center'">
		<table id="list_data" class="easyui-treegrid"
			style="width: 100%; height: 100%" toolbar="#toolbar"
			pagination="false" idField="id" treeField="menuName" fit="true"
			rownumbers="true" fitColumns="true" singleSelect="true"
			striped="true">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'parentId',hidden:true">ID</th>
					<th data-options="field:'parentName',hidden:true">ID</th>
					<th data-options="field:'menuCode',width:200" align="center">菜单编码</th>
					<th data-options="field:'menuName',width:200" align="center">菜单名称</th>
					<th data-options="field:'url',width:200" align="center">url</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
		<sec:authorize url="/menu/addMenu">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="add()">添加</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="edit()">修改</a>
		</sec:authorize> 
		<sec:authorize url="/menu/delMenu">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="del()">删除</a>
		</sec:authorize>
		</div>
	</div>

	<!-- 添加 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px;" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<div class="fitem">
				<label> 菜单编码： </label> <input class="easyui-validatebox" type="text"
					name="menuCode" required="true" />
			</div>
			<div class="fitem">
				<label> 菜单名称:</label> <input class="easyui-validatebox" type="text"
					name="menuName" required="true" />
			</div>
			<!-- <div class="fitem">
				<label> 父菜单:</label> <input class="easyui-validatebox" type="text"
					name="parentMenu.menuName" data-options="" />
			</div> -->
			<div class="fitem">
				<label> 父菜单:</label> <input type="text" id="menuparentname"
					onclick="showMenuDialog()" readonly="readonly"> <input
					type="hidden" id="menuparentid" name="parentMenu.id">
				<%--  <input id="cc" class="easyui-combobox" name="parentMenu.id"
    data-options="valueField:'id',textField:'menuName',url:'${path}/SKPWMS/menu/showMenu'"> --%>
			</div>
			<div class="fitem">
				<label> url:</label> <input class="easyui-validatebox" type="text"
					name="url" data-options="" />
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveMenu()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>

	<!-- 菜单列表  -->
	<div id="dialog" class="easyui-dialog"
		style="width: 460px; height: 400px; padding: padding: 10px 20px;" closed="true"
		buttons="#menutreeButtons" modal="true">
		<div class="ztree" id="menutree" style="width: 200px;">
		</div>
	</div>
	
	 <div id="menutreeButtons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dialog').dialog('close')">取消</a>
    </div>
	<script type="text/javascript">
	$(function() {
		//数据列表
		$('#list_data').treegrid({
			 url : "<%=path%>/menu/getMenuItemTreeData?time="+new Date().getTime(),
			 onBeforeExpand:function(node){
				//异步加载
				if(node)
				{
	             	$(this).treegrid('options').url = '<%=path%>/menu/findMenuItemNodeListByParentId?nodeid='+node.id+'&time='+new Date().getTime();
				}
			},
			onClickRow:function(){
				 $('#searchForm').form('clear');
			}
		});
		});
</script>

</body>
</html>