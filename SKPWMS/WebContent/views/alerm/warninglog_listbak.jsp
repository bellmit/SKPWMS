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
<title>报警信息表</title>
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
			title : '报警信息表',
			iconCls : 'icon-edit',//图标  
			width : 700,
			height : 'auto',
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,//是否可折叠的  
			fit : true,//自动大小  
			url : '${path}/SKPWMS/alerm/showWarningLogByCondition',
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
			} ] ]
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
		
		$('#time_start').datebox({
	        formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); },
	        parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
	    }) 
		$('#time_end').datebox({
	        formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); },
	        parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
	    }) 
	})
	function formatterCol(val, row) {
		var timestamp = new Date(value);
		return timestamp.toLocaleString();
	}
	
	//条件查询Datagrid
	function searchDatagrid() {
		
		var rt_alermparam = $('#rt_alermparam').val();
		
		var start_time=$('#time_start').datebox('getValue');
		var end_time=$('#time_end').datebox('getValue');

		$("#list_data").datagrid("load", {
			"alermparam" : rt_alermparam,
			"start_time" : start_time,
			"end_time" : end_time

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$('#rt_alermparam').val("");
		$('#time_start').val('setValue','');
		$('#time_end').val('setValue', '');

	}
	
	
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 50px">
		<form>
			<table>
				<tr>
					<td class="a">报警开始时间：</td>
					<td class="b"><input type="text" id="time_start" class="easyui-datebox" name="time_start" editable="false" style="width: 150px;"> --
					<input type="text" id="time_end" class="easyui-datebox"  name="time_end" editable="false" style="width: 150px;">
						
						</td>


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
					<th data-options="field:'enterprise',formatter: function(value,row,index){
				if (row.enterprise){
					return row.enterprise.fenterName;
				} else {
					return value;
				}}">企业名称</th>
					<th data-options="field:'ttcontroler',formatter: function(value,row,index){
				if (row.enterprise){
					return row.ttcontroler.fctrlerName;
				} else {
					return value;
				}}">总量控制器</th>
				<th data-options="field:'pollutant',formatter: function(value,row,index){
				if (row.pollutant){
					return row.pollutant.fPollutantName;
				} else {
					return value;
				}}">参数因子</th>
					<th data-options="field:'warningItem',formatter: function(value,row,index){
				if (row.warningItem){
					return row.warningItem.warningItemName;
				} else {
					return value;
				}}">报警项目</th>
					<th data-options="field:'warningTime',formatter:function(value,row,index){return value?value.substring(0,19):'';}">报警时间</th>
					<th data-options="field:'content'">报警内容</th>
					<th data-options="field:'threshold'">预警阀值</th>
					<th data-options="field:'realValue'">真实值</th>
					<th data-options="field:'stdValue'">标准值</th>
					<th data-options="field:'procDateTime'">处理时间</th>
					<th data-options="field:'status'">报警状态</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>