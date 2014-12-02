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
<title>废气实时数据显示</title>
    <script type="text/javascript">
   
		//条件查询Datagrid
		function searchDatagrid(){
			
			$("#datagrid").datagrid("reload");
			
			var second = $("#second").val()*1000;
			setTimeout('searchDatagrid()',second);
		}
		
		//重置Datagrid查询条件
		function resetDatagridSearch(){
			
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
		    
		  //定时刷新datagrid
		 setTimeout('searchDatagrid()',30000);
		})
		
		
		
    </script>
</head>
<body>

    <table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%"
            url="<%=path%>/gas/showGasRealtimenew?wry_id=${param.wry_id}"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true"  singleSelect="true" striped="true">
        
	      <thead frozen="true">    
	        <tr>    
	            <th rowspan="2" data-options="field:'ctrlname'" width="150px" align="center">总量控制器</th>
				<th rowspan="2" data-options="field:'pfkname'" width="100px" align="center">排放口</th>
				<th rowspan="2" data-options="field:'monitertime',formatter:function(value,row,index){return value?value.substring(0,19):'';}" width="150px" align="center">监测时间</th>  
	        </tr>    
	   	 </thead>
	   	     
         <thead>
            <tr>
				<th rowspan="2" data-options="field:'o2Rtd',styler:function(value,row,index){
            		if(row.o2FAS) {
            			if ((row.o2FAS).indexOf('cons=1')!=-1){
							return 'color:red;';
							}else{
							return 'color:black;';
							}
            		}
				}" width="100px" align="center">实测SO2<br>(mg/m³)</th>
				<th rowspan="2" data-options="field:'o1Rtd',styler:function(value,row,index){
            		if(row.o1FAS) {
            			if ((row.o1FAS).indexOf('cons=1')!=-1){
							return 'color:red;';
							}else{
							return 'color:black;';
							}
            		}
				} " width="100px" align="center">实测烟尘<br>(mg/m³)</th>
				
				<th rowspan="2" data-options="field:'bo2Rtd'" width="100px" align="center">废气排放量<br>(m³)</th>
				<th rowspan="2" data-options="field:'so1Rtd'" width="100px" align="center">氧气百分比<br>(%)</th>
				<th rowspan="2" data-options="field:'so2Rtd'" width="100px" align="center">流速<br>(m/s)</th>
				<th rowspan="2" data-options="field:'so3Rtd'" width="100px" align="center">烟气温度<br>(℃)</th>
				<th rowspan="2" data-options="field:'so4Rtd'" width="100px" align="center">烟气动压<br>(MPa)</th>
				<th rowspan="2" data-options="field:'so5Rtd'" width="100px" align="center">烟气湿度<br>(%)</th>
				<th rowspan="2" data-options="field:'so6Rtd'" width="100px" align="center">制冷温度<br>(℃)</th>
				<th rowspan="2" data-options="field:'so7Rtd'" width="100px" align="center">烟道截面积<br>()</th>
				<th rowspan="2" data-options="field:'so8Rtd'" width="100px" align="center">烟气压力<br>(MPa)</th>
				<th rowspan="2" data-options="field:'o3Rtd',styler:function(value,row,index){
            		if(row.o3FAS) {
            			if ((row.o3FAS).indexOf('cons=1')!=-1){
							return 'color:red;';
							}else{
							return 'color:black;';
							}
            		}
				} " width="100px" align="center">实测NOX<br>(mg/m³)</th>
				<th rowspan="2" data-options="field:'o2ZSRtd'" width="100px" align="center">折算SO2<br>(mg/m³)</th>
				<th rowspan="2" data-options="field:'o1ZSRtd'" width="100px" align="center">折算烟尘<br>(mg/m³)</th>
				<th rowspan="2" data-options="field:'o3ZSRtd'" width="100px" align="center">折算NOX<br>(mg/m³)</th>
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
	    				<%-- <td class="a">污染源编号：</td>
	    				<td class="b">
							<select id="s_wry" name="s_wry" style="width: 200px;">
									<option value="">--请选择--</option>
								<c:forEach items="${epList }" var="ep">
									<option value="${ep.fenterId }">${ep.fenterName }</option>							
								</c:forEach>
							</select> --%>
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