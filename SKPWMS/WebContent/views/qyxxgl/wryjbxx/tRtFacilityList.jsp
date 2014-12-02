<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
    <script type="text/javascript">
		
    var wry_id = '${param.wry_id}';
		//设置分页控件  
		$(function(){
		    var p = $('#tRtFacilitydatagrid').datagrid('getPager');  
		    $(p).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		    });  
		});
		
		function searchtRtFacilitydatagrid() {
			$("#tRtFacilitydatagrid").datagrid("load", {
				"_ffacilityNo" : $("#searchtRtFacilityForm input[name=_ffacilityNo]").val(),
				"_ffacilityName" : $("#searchtRtFacilityForm input[name=_ffacilityName]").val()
			});
		}
		
		function resettRtFacilitydatagridSearch() {
			$("#searchtRtFacilityForm input[name=_ffacilityNo]").val('');
			$("#searchtRtFacilityForm input[name=_ffacilityName]").val('');
		}
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px" id="searchtRtFacilityForm">
			<table>
				<tr>
					<td>设备编号：</td>
					<td><input name="_ffacilityNo"
						class="easyui-validatebox" /></td>
					
					<td>设备名称：</td>
					<td><input name="_ffacilityName"
						class="easyui-validatebox" /></td>
					<td><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchtRtFacilitydatagrid()">搜索</a></td>
					<td><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-reload"
						onclick="resettRtFacilitydatagridSearch()">清空</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div  data-options="region:'center'">
    <table id="tRtFacilitydatagrid" class="easyui-datagrid" style="width:100%;height:100%"
            url="<%=path %>/wry/tRtFacility/list?wry_id=${param.wry_id}"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" striped="true" border="false">
        <thead>
            <tr>
            	<th data-options="field:'ffacilityId',checkbox:'true'">id</th>
            	<th data-options="field:'ffacilityNo'" width="50%" align="center">设备编号</th>
				<th data-options="field:'ffacilityName'" width="50%" align="center">设备名称</th>
            </tr>
        </thead>
    </table>
    </div>
    <div id="toolbar">
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a> <a
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
    </div>
 
 <jsp:include page="tRtFacilityEdit.jsp"></jsp:include>
</body>
</html>