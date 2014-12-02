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
<title>Insert title here</title>
<script type="text/javascript">
	
	var tbeid = '${param.tbeid}';

	//设置标题
	function editTitle(name){
		$("#lay").layout("panel", "center").panel({ title: name});
	}

	//判断企业id是否存在
	$(document).ready(function(){
		if(tbeid!="new"){
			$('#oth').tree({
				data: [{
				    text: "<a target='showCenter' href='<%=path %>/wryjbxxController/findOne?id="+tbeid+"' onclick=\"editTitle('企业基本信息')\">企业基本信息</a>"
		        },
		        <%-- {text: "<a target='showCenter' href='<%=path %>/wryhbsxController/findOne?id="+tbeid+"' onclick=\"editTitle('企业环保属性')\">企业环保属性</a>"}, --%>
		        {
				    text: "<a target='showCenter' href='<%=path %>/wryglsxController/findOne?id="+tbeid+"' onclick=\"editTitle('企业管理属性')\">企业管理属性</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/tTcControlerController/findByFenterId?wry_id="+tbeid+"' onclick=\"editTitle('总量控制器')\">总量控制器</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/tRtFacilityList.jsp?wry_id="+tbeid+"' onclick=\"editTitle('监测设备')\">监测设备</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/outPermit/pwxkzList?wry_id="+tbeid+"' onclick=\"editTitle('排污许可证信息')\">排污许可证信息</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/realtime/initSMAdjustToList?wry_id="+tbeid+"' onclick=\"editTitle('月度数据设置')\">月度数据设置</a>"
		        },{
				    text: "<a target='showCenter' onclick=\"\">废水</a>",
				    children:[{
					    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/realTimeDataForWry.jsp?wry_id="+tbeid+"' onclick=\"editTitle('实时数据显示')\">实时数据显示</a>"
			        },{
					    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/hourDataForWry.jsp?wry_id="+tbeid+"' onclick=\"editTitle('日报表')\">日报表</a>"
			        },{
					    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/dayDataForWry.jsp?wry_id="+tbeid+"' onclick=\"editTitle('月报表')\">月报表</a>"
			        },{
					    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/monthDataForWry.jsp?wry_id="+tbeid+"' onclick=\"editTitle('年报表')\">年报表</a>"
			        }]
		        },{
				    text: "<a target='showCenter' onclick=\"\">废气</a>",
				    children:[{
					    text: "<a target='showCenter' href='<%=path %>/gas/initJumpToGasRealtimeList?wry_id="+tbeid+"' onclick=\"editTitle('实时数据显示')\">实时数据显示</a>"
			        },{
					    text: "<a target='showCenter' href='<%=path %>/gas/initJumpToDayStatistic?wry_id="+tbeid+"' onclick=\"editTitle('日报表')\">日报表</a>"
			        },{
					    text: "<a target='showCenter' href='<%=path %>/gas/initJumpToMonthStatistic?wry_id="+tbeid+"' onclick=\"editTitle('月报表')\">月报表</a>"
			        },{
					    text: "<a target='showCenter' href='<%=path %>/gas/initJumpToYearStatistic?wry_id="+tbeid+"' onclick=\"editTitle('年报表')\">年报表</a>"
			        }]
		        }]
			});
		}else{
			$('#oth').tree({
				data: [{
				    text: "<a target='showCenter' href='<%=path %>/wryjbxxController/findOne?id="+tbeid+"' onclick=\"editTitle('企业基本信息')\">企业基本信息</a>"
		        }]
			});
		}
	});
	
	//保存企业信息后，显示菜单
	function addMenu(str){
		$('#oth').tree({ 
			data: [{
			    text: "<a target='showCenter' href='<%=path %>/wryjbxxController/findOne?id="+str+"' onclick=\"editTitle('企业基本信息')\">企业基本信息</a>"
	        },
	        <%-- ,{ text: "<a target='showCenter' href='<%=path %>/wryhbsxController/findOne?id="+tbeid+"' onclick=\"editTitle('企业环保属性')\">企业环保属性</a>"} --%>
	        {
			    text: "<a target='showCenter' href='<%=path %>/wryglsxController/findOne?id="+str+"' onclick=\"editTitle('企业管理属性')\">企业管理属性</a>"
	        },{
			    text: "<a target='showCenter' href='<%=path %>/tTcControlerController/findByFenterId?wry_id="+str+"' onclick=\"editTitle('总量控制器')\">总量控制器</a>"
	        },{
			    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/tRtFacilityList.jsp?wry_id="+str+"' onclick=\"editTitle('监测设备')\">监测设备</a>"
	        },{
			    text: "<a target='showCenter' href='<%=path %>/outPermit/pwxkzList?wry_id="+str+"' onclick=\"editTitle('排污许可证信息')\">排污许可证信息</a>"
	        },{
			    text: "<a target='showCenter' href='<%=path %>/realtime/initSMAdjustToList?wry_id="+str+"' onclick=\"editTitle('月度数据设置')\">月度数据设置</a>"
	        },{
			    text: "<a target='showCenter' onclick=\"\">废水</a>",
			    children:[{
				    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/realTimeDataForWry.jsp?wry_id="+str+"' onclick=\"editTitle('实时数据显示')\">实时数据显示</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/hourDataForWry.jsp?wry_id="+str+"' onclick=\"editTitle('日报表')\">日报表</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/dayDataForWry.jsp?wry_id="+str+"' onclick=\"editTitle('月报表')\">月报表</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/views/qyxxgl/wryjbxx/monthDataForWry.jsp?wry_id="+str+"' onclick=\"editTitle('年报表')\">年报表</a>"
		        }]
	        },{
			    text: "<a target='showCenter' onclick=\"\">废气</a>",
			    children:[{
				    text: "<a target='showCenter' href='<%=path %>/gas/initJumpToGasRealtimeList?wry_id="+str+"' onclick=\"editTitle('实时数据显示')\">实时数据显示</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/gas/initJumpToDayStatistic?wry_id="+str+"' onclick=\"editTitle('日报表')\">日报表</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/gas/initJumpToMonthStatistic?wry_id="+str+"' onclick=\"editTitle('月报表')\">月报表</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/gas/initJumpToYearStatistic?wry_id="+str+"' onclick=\"editTitle('年报表')\">年报表</a>"
		        }]
	        }]
		});
	}
	
</script>
<style type="text/css">
.panel-body panel-body-noheader panel-body-noborder layout-body{
padding: 0px;
}

</style>
</head>
<body class="easyui-layout" id="lay" style="width: 100%;height: 100%;">   
    <div data-options="region:'east',title:'企业信息菜单',split:true" style="width:200px;height: 100%;" scrolling="no" frameborder="0" >
    	<ul id="oth">
        	<%-- <li>   
                <span><a target="showCenter" href="<%=path %>/wryjbxxController/findOne?id=<%=request.getParameter("tbeid")%>" onclick="editTitle('企业基本信息')">企业基本信息qq</a></span>   
            </li>  --%>
        </ul>   
    </div>   
    <div id="showCenter" data-options="region:'center',title:'企业基本信息'" style="overflow-y: hidden;">
    
    	<iframe name="showCenter" this.style.height=mainFrame.document.body.scrollHeight style="width:100%;height:100%;" scrolling="no" frameborder="0" src="<%=path %>/wryjbxxController/findOne?id=<%=request.getParameter("tbeid")%>">
    	</iframe>
    </div>   
</body>
</html>