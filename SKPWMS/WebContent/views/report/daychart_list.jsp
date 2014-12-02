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
	<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="<%=path%>/frame/js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

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
	       url: '${path}/SKPWMS/report/statisticDayReport',
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
	    	   	            zoomType: 'xy'
	    	   	        },
	    	   	        title: {
	    	   	            text: '日报表统计'
	    	   	        },
	    	   	        subtitle: {
	    	   	            text: '数据来源：小时报表'
	    	   	        },
	    	   	     	credits: {
	        		        enabled : false, //设置false就不会显示右下角的官网链接
	        				//右下角连接的显示位置         
	        				position:{ align: 'right',x: -8, verticalAlign: 'bottom',y: -390 },
	        				text:'区域图表',//右下角连接的名字
	        		  	    style : {cursor:'pointer',color:'#909090',fontSize:'20px'}
	        		      },
	    	   	        xAxis: [{
	    	   	            categories:[],
	    	   	         labels:{
	        		        	rotation:0,
	        		        	style:{
	        		        	fontSize:12, //刻度字体大小
	        		        	fontWeight:"normal"//字体不加粗 默认是设置为bold
	        		        	}
	        		        	}
	    	   	        }],
	    	   	        yAxis: [{ // Primary yAxis
	    	   	            labels: {
	    	   	                format: '{value}mg/L',
	    	   	                style: {
	    	   	                    color: '#89A54E'
	    	   	                }
	    	   	            },
	    	   	            title: {
	    	   	                text: '浓度',
	    	   	                style: {
	    	   	                    color: '#89A54E'
	    	   	                }
	    	   	            }
	    	   	        }, { // Secondary yAxis
	    	   	            title: {
	    	   	                text: '排放量',
	    	   	                style: {
	    	   	                    color: '#4572A7'
	    	   	                }
	    	   	            },
	    	   	            labels: {
	    	   	                format: '{value} kg',
	    	   	                style: {
	    	   	                    color: '#4572A7'
	    	   	                }
	    	   	            },
	    	   	            opposite: true
	    	   	        }],
	    	   	        tooltip: {
	    	   	            shared: true
	    	   	        },
	    	   	        
	    	   	        series: [{
	    	   	            name: data.paramtype+'排放量',
	    	   	            color: '#4572A7',
	    	   	            type: 'column',
	    	   	            yAxis: 1,
	    	   	            data:data.datalist[1].datalist,
	    	   	            tooltip: {
	    	   	                valueSuffix: ' kg'
	    	   	            }

	    	   	        }, {
	    	   	            name: data.paramtype+'浓度',
	    	   	            color: '#89A54E',
	    	   	            type: 'spline',
	    	   	            data: data.datalist[0].datalist,
	    	   	            tooltip: {
	    	   	                valueSuffix: 'mg/l'
	    	   	            }
	    	   	        }],
	    	   	        
	    	   	     
	    	   	    });
	    	   	}
	    	   	
	           }
	           });
	
}

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
					<td class="b"><input id="ctime" name="ctime" type="text" class="Wdate"  value="${statisticime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1990',maxDate:'2099'})" readonly="readonly" style="width: 150px;"/></td>

					<td class="a"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchDatagrid()">统计</a></td>
				</tr>
			</table>
		</form>
	</div>
<div data-options="region:'center'">
<div id="container" style="min-width:800px;height:600px"></div>
</div>
</body>
</html>