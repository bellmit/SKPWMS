<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="<%=path%>/frame/js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>废气月统计</title>
<style type="text/css">
.formName {
	width: 60px;
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
	//条件查询Datagrid
	function searchDatagrid() {
		if($("#enterId").val()==""){
			
			alert("请选择企业");
			
			return false;
		}	
		
		var controlid=$("#ttcid").val();
		var facilitieid=$("#facilitieid").val();
		var statistictime=$('#time_static').val();
		$('#datagrid').datagrid({  
		    url:'<%=path %>/gas/statisticMonthReport', 
		    queryParams:{  
		        "controlid":controlid,
		        "statistictime":statistictime,
		        "facilitieid":facilitieid
		    }
		}); 
		
	}

	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$('#time_static').val('setValue', '');
	}

	function enterChange(changeId,relId){  
		
	    var enterid=$("#"+changeId+" option:selected").val();
		$("#"+relId).html("");
		if(enterid!=""){
		    var url="${path}/SKPWMS/report/getTTCList";
		    $.post(url,{id:enterid},function(data){
		     $.each(data,function(i,n){
		    	 
		        $("#"+relId).append("<option value='"+n[0]+"'>"+n[1]+"</option>"); 
		           });
		     		ctrlChange('ttcid' ,'facilitieid');
		          },'json');
		  }else{
		      $("#"+relId).append("<option value=''>---请选择总量控制器---</option>"); 
		   }
		   }
	function ctrlChange(changeId,relId){
		 var ctrlid=$("#"+changeId+" option:selected").val();
		   $("#"+relId).html("");
			if(ctrlid!=""){
			    var url="${path}/SKPWMS/gas/getFacility";
			    $.post(url,{id:ctrlid},function(data){
			     $.each(data,function(i,n){
			        $("#"+relId).append("<option value='"+n.ffacilityId+"'>"+n.ffacilityName+"</option>"); 
			           })
			          },'json');
			  }else{
			      $("#"+relId).append("<option value=''>---请选择监控设备---</option>"); 
			   }
		
	}
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 70px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">企业名称：</td>
					<td class="b">
					<select name="enterid" id="enterId" onchange="enterChange('enterId' ,'ttcid')" style="width: 222px">
						<option value="">---请选择企业---</option>
						<c:forEach items="${enterList}" var="enterlist">
						<option value="${enterlist.fenterId}">${enterlist.fenterName}</option>
						</c:forEach>
					</select>					
					</td>
					
					<td class="a">总量控制器名称：</td>
					<td class="b"><select name="ttcid" id="ttcid" style="width: 222px" onchange="ctrlChange('ttcid' ,'facilitieid')">
						<option value="">---请选择总量控制器---</option>
						</select>
					</td>
					<td class="a">监控点名称：</td>
					<td class="b"><select name=facilitieid id="facilitieid" style="width: 222px">
						<option value="">---请选择监控点设备---</option>
						<%-- <c:forEach items="${facilities}" var="facilities">
						<option value="${facilities.ffacilityId}">${facilities.ffacilityName}</option>
						</c:forEach> --%>
						</select>
					</td>
					</tr>
					<tr>
					<td class="a">统计时间：</td>
					<td class="b"><input id="time_static" name="ctime" type="text" class="Wdate"  value="${statisticime}" onclick="WdatePicker({dateFmt:'yyyy-MM',minDate:'1990',maxDate:'2099'})" readonly="readonly" style="width: 222px;"/></td>
					<td class="a"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchDatagrid()">统计</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'">
	<table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%"
            toolbar="#toolbar" pagination="false" fit="true" rownumbers="true"  singleSelect="true" striped="true">
        
        <thead frozen="true">    
	        <tr>    
	            <th rowspan="2" data-options="field:'monitertime'" width="160px" align="center">监测时间</th>  
	        </tr>    
	   	</thead>
	   	
        <thead>
				<tr>
				<th colspan="2">实测SO2</th>
				<th colspan="2">实测烟尘</th>
				<th colspan="2">实测NOX</th>
						
				<th rowspan="2" data-options="field:'bo2RtdPfl',formatter:function(value,row,index) {
					var a = new Number(value);
					if(!isNaN(a) && value) {
						return	new Number(value).toFixed(2);
					}
					else if(value == null){
						return '';
					}
					else{
						return '-';
					}
					
					
				}" width="70px" align="center">废气排放量<br>(m³)</th>
				<th rowspan="2" data-options="field:'so1Rtd'" width="70px" align="center">氧气百分比<br>(%)</th>
				<th rowspan="2" data-options="field:'so2Rtd'" width="70px" align="center">流速<br>(m/s)</th>
				<th rowspan="2" data-options="field:'so3Rtd'" width="70px" align="center">烟气温度<br>(℃)</th>
				<th rowspan="2" data-options="field:'so4Rtd'" width="70px" align="center">烟气动压<br>(MPa)</th>
				<th rowspan="2" data-options="field:'so5Rtd'" width="70px" align="center">烟气湿度<br>(%)</th>
				<th rowspan="2" data-options="field:'so6Rtd'" width="70px" align="center">制冷温度<br>(℃)</th>
				<th rowspan="2" data-options="field:'so7Rtd'" width="70px" align="center">烟道截面积<br>()</th>
				<th rowspan="2" data-options="field:'so8Rtd'" width="70px" align="center">烟气压力<br>(MPa)</th>
				<th colspan="2">折算SO2</th>
				<th colspan="2">折算烟尘</th>
				<th colspan="2">折算NOX</th>
				</tr>
				<tr>
					<th data-options="field:'o2Rtd'" width="60px" align="center">浓度<br>(mg/m³)</th>
					<th data-options="field:'o2Rtdpfl'" width="60px" align="center">排放量<br>(kg)</th>
					<th data-options="field:'o1Rtd'" width="60px" align="center">浓度<br>(mg/m³)</th>
					<th data-options="field:'o1Rtdpfl'" width="60px" align="center">排放量<br>(kg)</th>
					<th data-options="field:'o3Rtd'" width="60px" align="center">浓度<br>(mg/m³)</th>
					<th data-options="field:'o3Rtdpfl'" width="60px" align="center">排放量<br>(kg)</th>
					
					<th data-options="field:'o2ZSRtd'" width="70px" align="center">浓度<br>(mg/m³)</th>
					<th data-options="field:'o2ZSRtdPPfl'" width="70px" align="center">排放量<br>(kg)</th>
					<th data-options="field:'o1ZSRtd'" width="70px" align="center">浓度<br>(mg/m³)</th>
					<th data-options="field:'o1ZSRtdPfl'" width="70px" align="center">排放量<br>(kg)</th>
					<th data-options="field:'o3ZSRtd'" width="70px" align="center">浓度<br>(mg/m³)</th>
					<th data-options="field:'o3ZSRtdPfl'" width="70px" align="center">排放量<br>(kg)</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>