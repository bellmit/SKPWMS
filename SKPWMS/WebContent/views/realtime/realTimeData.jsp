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
<title>废水实时数据显示</title>
    <script type="text/javascript">
   
		//条件查询Datagrid
		function searchDatagrid(){
			var s_wry = $("#s_wry").val();
			$("#datagrid").datagrid("reload",{
				"s_wry":s_wry
			});
			
			var second = $("#second").val()*1000;
			setTimeout("searchDatagrid()",second);
		}
		
		//重置Datagrid查询条件
		function resetDatagridSearch(){
			$("#s_wry").val("");
		}
		
		//设置分页控件  
		$(function(){
		    var p = $('#datagrid').datagrid('getPager');  
		    $(p).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		    });  
		})
		
		//定时刷新datagrid
		setTimeout("searchDatagrid()",300000);
		
    </script>
</head>
<body>

    <table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%"
            url="<%=path %>/realTimeDataController/findRealtimeData"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
            <tr>
            	<th rowspan="2" data-options="field:'wry'" width="100px" align="center"><a>污染源</a></th>
				<th rowspan="2" data-options="field:'name'" width="100px" align="center">总量控制器</th>
				<th rowspan="2" data-options="field:'time',formatter:function(value,row,index){return value?value.substring(0,19):'';}" width="100px" align="center">监测时间</th>
				<th colspan="3">COD</th>
				<th colspan="3">氨氮</th>
				<th rowspan="2" data-options="field:'flow'" width="50px" align="center">实时流量<br>(L/s)</th>
				<!-- <th rowspan="2" data-options="field:'faState'" width="50px" align="center">报警状态<br>(L/s)</th>
				<th rowspan="2" data-options="field:'codFAS'" width="50px" align="center">cod报警值<br>(L/s)</th>
				<th rowspan="2" data-options="field:'nh3FAS'" width="50px" align="center">nh3报警值<br>(L/s)</th> -->
            </tr>
            <tr>
            	<th data-options="field:'codC',styler:function(value,row,index){
            		if(row.codFAS) {
            			if ((row.codFAS).indexOf('cons=1')!=-1){
							return 'color:red;';
							}else{
							return 'color:black;';
							}
            		}
				} " width="50px" align="center">COD浓度<br>(mg/L)</th>
				<th data-options="field:'codD'" width="100px" align="center">COD年度累计排放量<br>(kg)</th>
				<th data-options="field:'codS',styler:function(value,row,index){
					if(row.codFAS) {
						if ((row.codFAS).indexOf('Surplus=1')!=-1){
							return 'color:red;';
							}else{
							return 'color:black;';
							}
					}
				} " width="50px" align="center">COD余量<br>(t)</th>
				<th data-options="field:'nh3C',styler:function(value,row,index){
				if(row.nh3FAS){
				if ((row.nh3FAS).indexOf('cons=1')!=-1){
				return 'color:red;';
				}else{
				return 'color:black;';
				}}
				}
				" width="50px" align="center">氨氮浓度<br>(mg/L)</th>
				<th data-options="field:'nh3D'" width="100px" align="center">氨氮年度累计排放量<br>(kg)</th>
				<th data-options="field:'nh3S',styler:function(value,row,index){
				if(row.nh3FAS){
				if ((row.nh3FAS).indexOf('Surplus=1')!=-1){
				return 'color:red;';
				}else{
				return 'color:black;';
				}}
				}" width="50px" align="center">氨氮余量<br>(t)</th>
            </tr>
        </thead>
    </table>
    
    <div id="toolbar">
    	<div>
	    	<form>
	    		<table>
	    			<tr>
	    				<td class="a">刷新频率(秒)：</td>
	    				<td class="a">
				    		<select id="second">
				    			<option>30</option>
				    			<option>60</option>
				    			<option>180</option>
				    			<option>300</option>
				    		</select>
			    		</td>
	    				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    				<td class="a">污染源编号：</td>
	    				<td class="b">
							<select id="s_wry" name="s_wry" style="width: 200px;">
									<option value="">--请选择--</option>
								<c:forEach items="${epList }" var="ep">
									<option value="${ep.fenterId }">${ep.fenterName }</option>							
								</c:forEach>
							</select>
						<td class="a">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchDatagrid()" >搜索</a>
						</td>
	    			</tr>
	    		</table>
	    	</form>
    	</div>
    </div>
 
</body>
</html>