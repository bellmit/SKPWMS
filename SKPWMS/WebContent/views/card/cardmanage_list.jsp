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
	<script src="<%=path %>/frame/js/CardReader/CardReader2.js"></script>

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
			url : '${path}/SKPWMS/card/showCardinfoByCondition',
			/* sortName: 'menuCode',  
			sortOrder: 'asc',   */
			remoteSort : false,
			idField : 'id',
			singleSelect : false,//是否单选  
			pagination : true,//分页控件  
			singleSelect : true,
			rownumbers : true,//行号  
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			toolbar : [ 
			<sec:authorize url="/card/initToCard">     
			            {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					newadd();
				}
			}, '-', 
			</sec:authorize>
			<sec:authorize url="/card/initupdateToCard">
			{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					newedit();
				}
			}, '-', 
			</sec:authorize>
			<sec:authorize url="/card/delCardinfo">
			{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			}, '-',
			</sec:authorize>
			<sec:authorize url="/card/cancelCardinfo">
			{
				text : '销卡',
				iconCls : 'icon-cancel',
				handler : function() {
					cancelCard();
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
	})
	var url;
	var type;

	function newadd() {
		var name = "IC卡信息";
		var src = "card/initToCard";
		window.parent.addTab(name, src);
	}
	
	function newedit() {
		var name = "IC信息修改";
		var row = $('#list_data').datagrid('getSelected');
		if(!row){
			
		$.messager.alert('提示：','请选择一行');
		}else{
		var src = 'card/initupdateToCard?cardid='+row.cardinfoid;
		window.parent.addTab(name, src);
		}
	}

	function add() {
		$("#dlg").dialog("open").dialog('setTitle', 'IC卡信息');
		$("#fm").form("clear");
		url = '${path}/SKPWMS/menu/addMenu';
	}
	function edit() {
		var row = $('#list_data').datagrid('getSelected');

		if (row) {
			$("#dlg").dialog("open").dialog('setTitle', '修改卡的信息');
			$("#fm").form("load", row);
			$('#cc').combobox('setValue', row.parentMenu.id);
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
			$.ajax({
				url : '${pageContext.request.contextPath}/card/findTicRechargeBycardinfoId',
				data : {iccid : row.cardinfoid},
				type : 'post',
				dataType : 'json',
				success : function(d) {
					if(d && d.length>0) {
						$.messager.alert('提示','该卡已存在充值信息，不能删除！');
					} else {
						$.messager.confirm('请确认', '确定要删除吗?', function(r) {
							if (r) {
								$.post('${path}/SKPWMS/card/delCardinfo', {
									id : row.cardinfoid
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
			});
		}
	}

	//条件查询Datagrid
	function searchDatagrid() {
		/* alert("Hello");
		$("#list_data").datagrid("load",$.serializeObject($('#searchForm'))); */
		 var enterid = $("#enterid").val();
		$("#list_data").datagrid("load", {
			"enterprise.fenterName" : enterid

		});
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#enterid").val("");

	}
	
	function cancelCard() {
		var cardPhyBh=readCardNo();
		if(cardPhyBh) {
			$.ajax({
				url : '${pageContext.request.contextPath}/card/cancelCardinfo',
				data : {cardPhyBh:cardPhyBh},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if(data && data.status==1){
						$.messager.alert('提示','该卡已注销！');
					} else if(data) {
						$.ajax({
							url : '${pageContext.request.contextPath}/card/findCardInfo',
							data : {cardPhyNO:cardPhyBh},
							type : 'post',
							dataType : 'json',
							success : function(r) {
								var contextStr = "您确定要注销该卡吗？<br/>卡号："+cardPhyBh+"<br/>企业名称："+r.enterprise.fenterName;
								$.messager.confirm('请确认',contextStr,function(b) {
									if(b){
										$.ajax({
											url : '${pageContext.request.contextPath}/card/updateIccStateByphyNo',
											data : {cardPhyBh:cardPhyBh},
											type : 'post',
											dataType : 'json',
											success :function(result) {
												if(result == 1) {
													$.messager.alert('提示','销卡成功！');
												} else {
													$.messager.alert('提示','销卡失败，请重试！');
												}
												
											}
										});
									}
								});
							}
						});
					} else {
						$.messager.alert('提示','该卡未激活！');
					}
				}
			});
		}
		else {
			$.messager.alert('提示','找不到卡，清检查卡是否放好？');
		}
	}
</script>
</head>
<body class="easyui-layout">

	<OBJECT id=MWRFATL style="WIDTH: 0px; HEIGHT: 0px" 
	codeBase=<%=path %>frame/js/CardReader/MwRFReader.cab#version=2,0,0,1
	data=data:application/x-oleobject;base64,VPpLUhUXNkSyudxeJIvBwwADAAAAAAAAAAAAAA== 
	classid=CLSID:BDE9B6B3-4C1D-4C05-8A71-3696F3BF81F5></OBJECT>

	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px" id="searchForm">
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
					<th data-options="field:'cardinfoid',hidden:true">ID</th>
					<!-- <th data-options="field:'enterprise',width:200,formatter: function(value,row,index){
				if (row.enterprise){
					return row.enterprise.fenterName;
				} else {
					return value;
				}}" align="center">企业名称</th> -->

					<!-- <th data-options="field:'userInfo',width:200,formatter: function(value,row,index){
				if (row.userInfo){
					return row.userInfo.username;
				} else {
					return value;
				}}" align="center">经办人</th>	 -->
					<th data-options="field:'entername',width:200" align="center">企业名称</th>
					<th data-options="field:'ttcname',width:200" align="center">总量控制器</th>
					<th data-options="field:'username',width:200" align="center">经办人</th>
					<th data-options="field:'flinkMan',width:200" align="center">企业经办人</th>
					<th data-options="field:'date',width:200" align="center">发卡日期</th>
					<th data-options="field:'zxStatus',width:60" align="center">是否注销</th>
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
</body>
</html>