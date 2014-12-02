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
	src="<%=path%>/frame/js/highchart/highcharts.js"></script>
<script type="text/javascript"
	src="<%=path%>/frame/js/highchart/modules/exporting.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="<%=path%>/frame/js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(function () {
$('#time_static').datebox({
    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); },
    parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
}); 
});
//条件查询Datagrid
function searchDatagrid() {
	
	if($('#cc1').combobox('getValue')==""){
		
		alert("请选择企业名称");
		
		return false;
	}
	
	if($('#cc2').combobox('getValue')==""){
		
		alert("请选择总量控制器");
		
		return false;
	}
	$.ajax({  
	       type: 'get',  
	       dataType: 'JSON',  
	       url: '${path}/SKPWMS/report/statisticQuarterReport',
	       data: {
	    	   	  ttcid:$('#cc2').combobox('getValue'), 
	    	   	  time_static:$('#ctime').val(),
	    	   	  paramtype:$('#paramtype').val()
	       		  },
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
	                 text: '季图表统计'
	             },
	        		credits: {
	        	        		        enabled : false, //设置false就不会显示右下角的官网链接
	        	        				//右下角连接的显示位置         
	        	        				position:{ align: 'right',x: -8, verticalAlign: 'bottom',y: -390 },
	        	        				text:'区域图表',//右下角连接的名字
	        	        		  	    style : {cursor:'pointer',color:'#909090',fontSize:'20px'}
	        	        		      },
	             xAxis: {
	            	 categories: ['第一季度', '第二季度', '第三季度', '第四季度']
	             },
					
	             yAxis: {
	                 allowDecimals: false,
	                 min: 0,
	                 title: {
	                     text: '排放量(kg)'
	                 }
	             },
	             plotOptions:{
	            	column:{
	            	 dataLabels:{
	            	 enabled:true //是否显示数据标签
	            	 }
	            	}
	            },
	             tooltip: {
	        			valueSuffix: 'kg',
	                 formatter: function() {
	                     return '<b>'+ this.x +'</b><br/>'+
	                         this.series.name +': '+ this.y +'<br/>';
	                 }
	             },

	             series:data.datalist
	         	});
	    	   	}
	    	   	
	           }
	           });
}

//重置Datagrid查询条件
/* function resetDatagridSearch() {
	$("#sys_username").val("");

} */
</script>

</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px">
			<table>
				<tr>
					<td class="a">企业名称：</td>
					<td class="b">
					<input id="cc1" class="easyui-combobox" required="true" name="enterprise.fenterId"
					data-options=" valueField: 'fenterId',textField: 'fenterName',
    				url: '${path}/SKPWMS/wryjbxxController/findAllPollutionBasInfonew',
				    onSelect: function(rec){
				    var url = '<%=path%>/tTcControlerController/findTTcControlersOfWry?id='+rec.fenterId;
				    $('#cc2').combobox('reload', url);
				    $('#cc2').combobox('setValue',''); 
				    },
                        onLoadSuccess:function(){  
                          
                            var enterid= $('#cc1').combobox('getValue');  
                            var url2='<%=path%>/tTcControlerController/findTTcControlersOfWry?id='+enterid;  
                            $('#cc2').combobox('reload', url2);  
                        }   
				    
				    ">
					
					</td>
					<td class="a">总量控制器名称：</td>
					<td class="b">
					 <input class="easyui-combobox" id="cc2" name="crtllerid" required="true" data-options="valueField:'id',textField:'text',onLoadSuccess:function(){
					 	 var d = $(this).combobox('getData');
					 	 if(d.length>0) {
					 	 	$(this).combobox('setValue', d[0].id); 
					 	 }
					 }
					 ">
					</td>
					
					<td class="a">参数类型：</td>
					<td class="b">
					<select id="paramtype" style="width:100px">
						<option value="COD">COD</option>
						<option value="NH3-N">NH3-N</option>
					</select>
					</td>
					
					<td class="a">统计时间：</td>
					<td class="b"><input id="ctime" name="ctime" type="text" class="Wdate" value="${statisticime}" onclick="WdatePicker({dateFmt:'yyyy',minDate:'1990',maxDate:'2099'})" readonly="readonly" style="width: 150px;"/></td>

					<td class="a"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchDatagrid()">统计</a></td>
				</tr>
			</table>
		</form>
	</div>
<div data-options="region:'center'">
<div id="container" style="min-width:800px;height:400px"></div>
</div>
</body>
</html>