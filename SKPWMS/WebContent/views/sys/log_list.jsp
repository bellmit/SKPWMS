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
<title>系统日志列表</title>
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
			title : '日志列表',
			iconCls : 'icon-edit',//图标  
			width : 700,
			height : 'auto',
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,//是否可折叠的  
			fit : true,//自动大小  
			url : '${path}/SKPWMS/log/showLogListByCondition',
			sortName: 'operatetime',  
			sortOrder: 'desc',  
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
		
		var operatetype = $('#sys_operatetype').combobox('getValue');
		
		var start_time=$('#time_start').datebox('getValue');
		var end_time=$('#time_end').datebox('getValue');

		$("#list_data").datagrid("load", {
			"operatetype" : operatetype,
			"start_time" : start_time,
			"end_time" : end_time

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$('#time_start').val('setValue','');
		$('#time_end').val('setValue', '');

	}
	
	
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">日志类型：</td>
					<td class="b"><select id="sys_operatetype" class="easyui-combobox" name="operatetype"
						style="width: 200px;">
							<option value="">选择日志类型</option>
							<option value="1">登陆</option>
							<option value="2">退出</option>
							<option value="3">插入</option>
							<option value="4">删除</option>
							<option value="5">更新</option>
							<option value="6">上传</option>
							<option value="7">其它</option>
					</select></td>
					<td class="a">操作时间：</td>
					<td class="b"><input type="text" id="time_start" class="easyui-datebox" name="time_start" editable="false" style="width: 150px;" value="${starttime}">--
					<input type="text" id="time_end" class="easyui-datebox"  name="time_end" editable="false" style="width: 150px;" value="${endtime}">
						
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
					<th data-options="field:'id',hidden:true" align="center">ID</th>
					<th data-options="field:'logcontent',width:300" align="center">日志内容</th>
					<th data-options="field:'note',width:100" align="center">操作人IP</th>
					<th data-options="field:'broswer',width:100" align="center">浏览器</th>
					<th
						data-options="field:'operatetime',width:200,formatter:function(value,row,index){return value?value.substring(0,19):'';}" align="center">操作时间</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>