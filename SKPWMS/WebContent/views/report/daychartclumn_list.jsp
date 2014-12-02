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
<script type="text/javascript"
	src="<%=path%>/frame/js/highchart/highcharts.js"></script>
<script type="text/javascript"
	src="<%=path%>/frame/js/highchart/modules/exporting.js"></script>
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/curdtools.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/css/common.css"></script>
<script type="text/javascript" src="<%=path%>/frame/js/lhgDialog/lhgdialog.min.js"></script>
<title>日图表柱状图</title>
<script type="text/javascript">
$(function () {
$('#time_static').datebox({
    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); },
    parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
	}); 
});

//条件查询Datagrid
function searchDatagrid() {
	if($("#enterId").val()==""){
		
		alert("请选择企业");
		
		return false;
	}
	
	if($('#time_static').datebox('getValue')==""){
		alert("请选择统计时间");
		return false;		
	}	
	
	$.ajax({  
	       type: 'get',  
	       dataType: 'JSON',  
	       url: '${path}/SKPWMS/report/statisticDayReportByColumn',
	       data: {ttcid:$("#ttcid").val(), time_static:$('#time_static').datebox('getValue')},
	       error:function(e){
	    	   alert(e);
	       },
	       success:function(data,status){
	    	   	if(status=="success"){
	        	    $('#container').highcharts({

     chart: {
         type: 'column'
     },

     title: {
         text: '排放总量柱状图'
     },
		credits: {
	        		        enabled : false, //设置false就不会显示右下角的官网链接
	        				//右下角连接的显示位置         
	        				position:{ align: 'right',x: -8, verticalAlign: 'bottom',y: -390 },
	        				//右下角链接的地址href:'<%=basePath%>shop/newOrder/orderPre/orderSearch4HighCharts.do?type=1',
	        				text:'区域图表',//右下角连接的名字
	        		  	    style : {cursor:'pointer',color:'#909090',fontSize:'20px'}
	        		      },
     xAxis: {
         categories:[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
     },

     yAxis: {
         allowDecimals: false,
         min: 0,
         title: {
             text: '实际排放量(kg)'
         }
     },

     tooltip: {
			valueSuffix: 'kg',
         formatter: function() {
             return '<b>'+ this.x +'</b><br/>'+
                 this.series.name +': '+ this.y +'<br/>';
         }
     },

     plotOptions: {
         column: {
             pointPadding: 0.2,
             borderWidth: 0
         }
     },

     series:data.datalist
 	});
	  }
	    	   	
	     }
	        });
	
}

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

</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">企业名称：</td>
					<td class="b"><select name="enterid" id="enterId"
						onchange="enterChange('enterId' ,'ttcid')">
							<option value="">---请选择企业---</option>
							<c:forEach items="${enterList}" var="enterlist">
								<option value="${enterlist.fenterId}">${enterlist.fenterName}</option>
							</c:forEach>
					</select></td>
					<td class="a">总量控制器名称：</td>
					<td class="b"><select name="ttcid" id="ttcid"
						style="width: 222px">
							<option value="">---请选择总量控制器---</option>
					</select></td>

					<td class="a">统计时间：</td>
					<td class="b"><input type="text" id="time_static"
						value="${statisticime}" class="easyui-datebox" name="time_static"
						editable="false" style="width: 150px;"></td>

					<td class="a"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchDatagrid()">统计</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'">
		<div id="container" style="min-width: 800px; height: 400px"></div>
	</div>
</body>
</html>