<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构管理</title>

<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">

<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<style type="text/css">

.formName{width: 70px;}
#fm{margin: 0;padding: 10px 30px;}
.ftitle {font-size: 14px;font-weight: bold;padding: 5px 0;margin-bottom: 10px;border-bottom: 1px solid #ccc;}
.fitem {margin-bottom: 5px;}
.fitem label {display: inline-block;width: 80px;}

</style>

<script type="text/javascript">
	var url;
	var type;
	function add()
	{
		$("#dlg").dialog("open").dialog('setTitle', '添加组织机构');
		$("#fm").form("clear");
		$("#cc").combobox("reload","${pageContext.request.contextPath}/orgunit/showParentOrgunitList");
		$("#fm input[name=orgUnitCode]").validatebox("enableValidation");
		$("#fm input[name=orgUnitCode]").removeAttr("readonly");
		$("#cc").combobox("readonly",false);
		$("#cc").combobox("setValue","0");
		url = '${path}/SKPWMS/orgunit/addOrgunit';
	}
	
	function edit()
	{	
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
		if (row)
		{
			if ("0"==row.parentId) {
				 $.messager.show({
		    		 title:'提示',
		    		 msg:'顶级组织不能编辑！',
		    		 showType:'show'
		    		 });
				return;
			}
			$("#dlg").dialog("open").dialog('setTitle', '修改组织结构');
			$("#fm").form("clear");
			var cc_url = "${pageContext.request.contextPath}/orgunit/showParentOrgunitList?zzid="+row.id;
			$("#cc").combobox("reload",cc_url);
			$("#fm").form("load", row);
			$("#fm input[name=orgUnitCode]").validatebox("disableValidation");
			$("#fm input[name=orgUnitCode]").attr("readonly","readonly");
			$("#cc").combobox("readonly",false);
			if(row.state=="closed") {
				$("#cc").combobox("readonly",true);
			}
			if(row.children) {
				if(row.children.length > 0) {
					$("#cc").combobox("readonly",true);
				}
			}
			$('#cc').combobox('setValue', row.parentId);
			url = '${path}/SKPWMS/orgunit/addOrgunit?id=' + row.id;
		}
	}
	
	function saveOrg()
	{
		$("#fm").form("submit", {
			url : url,
			onSubmit : function(){
				return $(this).form('validate');
			},
			success : function(result){
				var result = eval('(' + result + ')');
				if (result == "1")
				{
					$.messager.alert("提示信息", "操作成功");
					$("#dlg").dialog("close");
					$('#list_data').treegrid('clearSelections');
					window.location.reload();
				}
				else
				{
					$.messager.alert("提示信息", "操作失败");
					$("#dlg").dialog("close");
					$('#list_data').treegrid('clearSelections');
					window.location.reload();
				}
			}
		});
	}
	
	function del()
	{
		var row = $('#list_data').treegrid('getSelected');
		if (row)
		{
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r)
				{
					$.post('${path}/SKPWMS/orgunit/delOrgunit', {
						id : row.id
					}, function(result) {
						if (result.success)
						{
							$('#list_data').treegrid('clearSelections');
							window.location.reload(); // reload the user data 
						}
						else
						{
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
	function searchDatagrid()
	{
		var orgUnitName = $("#sys_orgname").val();
		$("#list_data").datagrid("load", {
			"orgUnitName" : orgUnitName
		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch()
	{
		$("#sys_orgname").val("");
	}
</script>
</head>

<body class="easyui-layout">
	<!-- 搜索 -->
	<!-- <div data-options="region:'north'" style="height: 50px">
		<form id="searchForm" style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">组织机构名称：</td>
					<td class="b"><input id="sys_orgname" name="orgUnitName" class="easyui-validatebox" /></td>
					<td class="a"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchDatagrid()">搜索</a></td>
					<td class="a"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetDatagridSearch()">重置</a></td>
				</tr>
			</table>
		</form>
	</div> -->
	<div data-options="region:'center'">
		<table id="list_data" class="easyui-treegrid" style="width:100%;height:100%"
            toolbar="#toolbar" pagination="false" idField="id" treeField="orgUnitName" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
			<thead>
				<tr>
					<th field="ck" checkbox="true" width="12%" align="center"></th>
					<th data-options="field:'id',hidden:true" align="center">组织ID</th>
					<th data-options="field:'orgUnitCode',width:100" align="left">组织编码</th>
					<th data-options="field:'orgUnitName',width:200">组织名称</th>
					<th data-options="field:'property',width:100,formatter:function(value){
						var arr = ['厅','市','县','部门'];
						var k = '';
						$.each(arr,function(i,j){
							if(value){
								if(value == i) {
								k = j;
							}
							}
						});
						return k;
					}" align="center">组织属性</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<sec:authorize url="/orgunit/addOrgunit">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">修改</a>
	        </sec:authorize>
	        <sec:authorize url="/orgunit/delOrgunit">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
	        </sec:authorize>
	    </div>
	</div>
	
	<!-- 添加 -->
	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<div class="fitem">
				<label> 组织编码： </label> <input class="easyui-validatebox" type="text" name="orgUnitCode" required="true" validType="remote['<%=request.getContextPath()%>/orgunit/findByOrgUnitCode','orgUnitCode']" invalidMessage="编号已存在，请重新输入！"/>
			</div>
			<div class="fitem">
				<label> 组织名称:</label> <input class="easyui-validatebox" type="text" name="orgUnitName"/>
			</div>
			<div class="fitem">
				<label> 父级组织名称:</label>
				<input id="cc" class="easyui-combobox" name="parentOrgUnit.id"  data-options="valueField:'id',textField:'orgUnitName',url:'<%=request.getContextPath()%>/orgunit/showParentOrgunitList',value:'0' "/>
			</div>
			<div class="fitem">
				<label> 组织属性:</label>
				<%--
				 <input class="easyui-validatebox" type="text" name="property"/>
				  --%>
				  <select id="propertyCc" class="easyui-combobox" name="property" style="width:156px;">
					<option value="0">厅</option>
					<option value="1">市</option>
					<option value="2">县</option>
					<option value="3">部门</option>
				   </select>
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveOrg()" iconcls="icon-save">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
	
<script type="text/javascript">
	$(function() {
		//数据列表
		$('#list_data').treegrid({
			 url : "<%=path%>/orgunit/getOrgunitTreeData?time="+new Date().getTime(),
			 onBeforeExpand:function(node){
				//异步加载
				if(node)
				{
	             	$(this).treegrid('options').url = '<%=path%>/orgunit/findOrgunitTreeNodeList?nodeid='+node.id+'&time='+new Date().getTime();
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