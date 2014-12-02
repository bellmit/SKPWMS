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
			url : '${path}/SKPWMS/authority/showAuthoritysbyCondition',
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
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	})
	var url;
	var type;
	function add() {
		$("#dlg").dialog("open").dialog('setTitle', '权限添加');
		$("#fm").form("clear");
		url = '${path}/SKPWMS/authority/addAuthority';
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
			$("#dlg").dialog("open").dialog('setTitle', '修改权限');
			$("#fm").form("load", row);
			if(row.sysMenu!=null){
				$('#menuparentname').val(row.sysMenu.menuName);
				$('#cc').combotree('setValue',row.sysMenu.id);

				}
			url = '${path}/SKPWMS/authority/addAuthority?id=' + row.id;
		}
	}
	function saveAuthority() {
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
					$.post('${path}/SKPWMS/authority/delAuthority', {
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
		var authorityName = $("#sys_authorityName").val();

		$("#list_data").datagrid("load", {
			"authorityName" : authorityName

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#sys_authorityName").val("");

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

	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">权限名：</td>
					<td class="b"><input id="sys_authorityName"
						name="authorityName" class="easyui-validatebox" /></td>

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
					<th data-options="field:'id',hidden:true" width="100px" align="center">ID</th>
					<th data-options="field:'authorityName',width:200" align="center">权限名</th>
					<th
						data-options="field:'sysMenu',width:200,formatter: function(value,row,index){
				if (row.sysMenu){
					return row.sysMenu.menuName;
				} else {
					return value;
				}}" align="center">关联菜单</th>

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
				<label> 权限名： </label> <input class="easyui-validatebox" type="text"
					name="authorityName" required="true"  style="width:190px;"/>
			</div>
			
			<div class="fitem">
				<label> 关联菜单:</label>
				
				<input id="cc" name="sysMenu.id" class="easyui-combotree" data-options="url:'${path}/SKPWMS/authority/menuComboxTree',method:'get',required:true" style="width:200px;">
			</div>
			


		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveAuthority()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>

</body>
</html>