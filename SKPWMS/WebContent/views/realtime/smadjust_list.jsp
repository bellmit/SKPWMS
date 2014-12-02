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
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="<%=path%>/frame/js/DatePicker/WdatePicker.js"></script>
<title>月度数据设置</title>
<style type="text/css">
.formName {
	width: 100px;
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
	width: 100px;
}
</style>
<script type="text/javascript">

	$(function() {
		//给日期设置初始值
		var date = new Date();
		var ctime = date.getFullYear()+'-'+(date.getMonth()+1);
		$("#ctime").val(ctime);
		
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
			url : '${path}/SKPWMS/realtime/showSMAdjustbyCondition',
			/* sortName : 'jobCode',
			sortOrder : 'asc', */
			remoteSort : false,
			idField : 'id',
			singleSelect : false,//是否单选  
			pagination : true,//分页控件  
			queryParams: {
				'enterprise.fenterId' :'${param.wry_id}'
			},
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
		$("#dlg").dialog("open").dialog('setTitle', '月度调整');
		$("#fm").form("clear");
		$('#cc1').combobox('setValue', '${param.wry_id}');
		url = '${path}/SKPWMS/realtime/addSMAdjust';
		if('${param.wry_id}') {
			$('#cc1').combobox('readonly');
		}
	}
	function edit() {
		var row = $('#list_data').datagrid('getSelected');
		if (row) {
			$("#dlg").dialog("open").dialog('setTitle', '修改月度调整');
			$('#cc1').combobox('setValue', row.enterprise.fenterId);
			/* $('#cc2').combobox('setValue', row.ttcControler.fcontrolerId); */
			$('#fPollutantID').combobox('setValue', row.pollutant.fPollutantID);
			$("#fm").form("load", row);
			url = '${path}/SKPWMS/realtime/addSMAdjust?id=' + row.id;
			if('${param.wry_id}') {
				$('#cc1').combobox('readonly');
			}
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
				if(result=="2"){
					$.messager.alert("提示信息", "污染源因子和月度已存在，请重新选择！")
				}else{				
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
			}
		});
	}
	function del() {
		var row = $('#list_data').datagrid('getSelected');

		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('${path}/SKPWMS/realtime/delSMAdjust', {
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
		var enterid = $("#enterid").val();
		$("#list_data").datagrid("load", {
			"enterprise.fenterName" : enterid

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#enterid").val("");

	}
</script>
</head>
<body class="easyui-layout">

	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">企业名称：</td>
					<td class="b"><input id="enterid" name="enterprise.fenterName"
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
					<th
						data-options="field:'ttcControler',width:200,formatter: function(value,row,index){
				if (row.ttcControler){
					return row.ttcControler.fctrlerName;
				} else {
					return value;
				}}">总量控制器</th>
					<th
						data-options="field:'enterprise',width:200,formatter: function(value,row,index){
				if (row.enterprise){
					return row.enterprise.fenterName;
				} else {
					return value;
				}}"
						align="center">企业名称</th>
					<th
						data-options="field:'pollutant',width:200,formatter: function(value,row,index){
				if (row.pollutant){
					return row.pollutant.fPollutantName;
				} else {
					return value;
				}}"
						align="center">污染源因子</th>

					<th data-options="field:'month',width:200 " align="center">月度</th>
					<th data-options="field:'discharge',width:200" align="center">月度排放量</th>
				</tr>

			</thead>
		</table>
	</div>
	<!-- 工具栏  测试下 -->
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
		style="width: 600px; height: 380px; padding: 10px 20px;" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">

			<div class="fitem">
				<label> 企业名称:</label>
				<input id="cc1" class="easyui-combobox" required="true" name="enterprise.fenterId"
					data-options=" valueField: 'fenterId',textField: 'fenterName',
    				url: '${path}/SKPWMS/wryjbxxController/findAllPollutionBasInfo',
				    onChange: function(newValue,oldValue){
				    var url = '<%=path%>/tTcControlerController/findTTcControlersOfWry?id='+newValue;
				    $('#cc2').combobox('reload', url);}">

			</div>
			<div class="fitem">
				<label> 总量控制器:</label> <input id="cc2" class="easyui-combobox"
					name="crtllerid" required="true"
					data-options="valueField:'id',textField:'text',onLoadSuccess:function(){
					 	 var d = $(this).combobox('getData');
					 	 if(d.length>0) {
					 	 	$(this).combobox('setValue', d[0].id); 
					 	 }
					 }
					">

			</div>

			<div class="fitem">
				<label> 污染因子名称:</label> <input id="fPollutantID"
					class="easyui-combobox" name="pollutant.fPollutantID"
					data-options="valueField:'fPollutantID',textField:'fPollutantName',url:'${path}/SKPWMS/realtime/showPolluntant'">
			</div>
			<div class="fitem">
				<label> 月度:</label> 
				<input id="month" name="month" type="text" class="Wdate"  value="${statisticime}" onclick="WdatePicker({dateFmt:'yyyyMM',minDate:'1990',maxDate:'2099'})" readonly="readonly" style="width: 150px;"/>
				<!-- <input id="ctime" name="month" type="text" class="Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM',minDate:'2000-1',maxDate:'2050-12'})" readonly="readonly" style="width: 150px;"/> -->
			</div>
			<div class="fitem">
				<label> 月度排放量:</label> <input class="easyui-validatebox" type="text"
					name="discharge" required="true" />
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