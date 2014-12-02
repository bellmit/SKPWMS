<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>废水历史数据查询</title>
    <script type="text/javascript">
    
   
		//条件查询Datagrid
		function searchDatagrid(){
			if($("#enterId").val()==""){
				
				alert("请选择企业");
				
				return false;
			}
			
			var controlid=$("#ttcid").val();
			
			var starttime=$('#starttime').datebox('getValue');
			var endtime=$('#endtime').datebox('getValue');
			
			$('#datagrid').datagrid({  
			    url:'<%=path %>/realtime/queryRealtimeDataList', 
			    queryParams:{  
			        "fid":controlid,
			        "starttime":starttime,
			        "endtime":endtime
			    }
			}); 
			
		}
		
		//重置Datagrid查询条件
		function resetDatagridSearch(){
			$('#starttime').val('setValue', '');
			$('#endtime').val('setValue', '');
		}
		
		//通过企业级联控制器
		function enterChange(changeId,relId){  
			
		    var enterid=$("#"+changeId+" option:selected").val();
			$("#"+relId).html("");
			if(enterid!=""){
			    var url="${path}/SKPWMS/report/getTTCList";
			    $.post(url,{id:enterid},function(data){
			     $.each(data,function(i,n){
			        $("#"+relId).append("<option value='"+n[0]+"'>"+n[1]+"</option>"); 
			           })
			          },'json');
			  }else{
			      $("#"+relId).append("<option value=''>---请选择企业---</option>"); 
			   }
			   }
		//设置分页控件  
		$(function(){
			$('#starttime').datebox({
			    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); },
			    parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
			}); 
			$('#endtime').datebox({
			    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); },
			    parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
			}); 
		    var p = $('#datagrid').datagrid('getPager');  
		    $(p).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		    });  
		})
		
    </script>
</head>
<body>

    <table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
            <tr>
            	<th rowspan="2" data-options="field:'fconname'" width="50px" align="center">总量控制器</th>
				<th rowspan="2" data-options="field:'fid',hidden:true" width="50px" align="center">总量控制器</th>
				<th rowspan="2" data-options="field:'time',formatter:function(value,row,index){return value?value.substring(0,19):'';}" width="75px" align="center">监测时间</th>
				<th colspan="3">COD</th>
				<th colspan="3">氨氮</th>
				<th rowspan="2" data-options="field:'flow'" width="50px" align="center">实时流量<br>(L/s)</th>
            </tr>
            <tr>
            	<th data-options="field:'codC'" width="50px" align="center">COD浓度<br>(mg/L)</th>
				<th data-options="field:'codD'" width="50px" align="center">COD年度累计排放量<br>(kg)</th>
				<th data-options="field:'codS'" width="50px" align="center">COD余量<br>(t)</th>
				<th data-options="field:'nh3C'" width="50px" align="center">氨氮浓度<br>(mg/L)</th>
				<th data-options="field:'nh3D'" width="50px" align="center">氨氮年度累计排放放量<br>(kg)</th>
				<th data-options="field:'nh3S'" width="50px" align="center">氨氮余量<br>(t)</th>
            </tr>
        </thead>
    </table>
    
    <div id="toolbar">
    	<div>
	    	<form>
	    		<table>
	    			<tr>
	    			<td class="a">企业名称：</td>
					<td class="b">
					<select name="enterid" id="enterId" onchange="enterChange('enterId' ,'ttcid')">
						<option value="">---请选择企业---</option>
						<c:forEach items="${enterList}" var="enterlist">
						<option value="${enterlist.fenterId}">${enterlist.fenterName}</option>
						</c:forEach>
					</select>	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
					</td>
					
					<td class="a">总量控制器名称：</td>
					<td class="b"><select name="ttcid" id="ttcid" style="width: 222px">
						<option value="">---请选择总量控制器---</option>
						</select>
					</td>
					</tr>
					<tr>						
						<td class="a">开始时间：</td>
	    				<td class="b">
							<input type="text" id="starttime" class="easyui-datebox" name="starttime" editable="false" style="width: 222px;" value="${starttime}">
						</td>
						
						<td class="a">结束时间：</td>
	    				<td class="b">
							<input type="text" id="endtime" class="easyui-datebox"  name="endtime" editable="false" style="width: 222px;" value="${endtime}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td class="a">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchDatagrid()" >查询</a>
						</td>
	    			</tr>
	    		</table>
	    	</form>
    	</div>
    </div>
 
</body>
</html>