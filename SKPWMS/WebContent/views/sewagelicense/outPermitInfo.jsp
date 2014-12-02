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
	
	var id = '${id}';

	function editTitle(name){
		$("#lay").layout("panel", "center").panel({ title: name});
	}

	
	$(document).ready(function(){
		if(id!="new"){
			$('#oth').tree({
				data: [{
					text: "<a target='showCenter' href='<%=path %>/outPermit/find?id="+id+"' onclick=\"editTitle('污染源基本信息')\">申请单位基本情况</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/sewagelicense/findSwr?id="+id+"' onclick=\"editTitle('水污染物排放情况')\">水污染物排放情况</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/sewagelicense/findDqwr?id="+id+"' onclick=\"editTitle('大气污染物排放情况')\">大气污染物排放情况</a>"
		        },{
				    text: "<a target='showCenter' href='<%=path %>/sewagelicense/findYj?id="+id+"' onclick=\"editTitle('发证单位审批意见')\">发证单位审批意见</a>"
		        }]
			});
		}else{
			$('#oth').tree({
				data: [{
					text: "<a target='showCenter' href='<%=path %>/outPermit/find?id="+id+"' onclick=\"editTitle('污染源基本信息')\">申请单位基本情况</a>"
		        }]
			});
		}
	});
	
	function addMenu(str){
		$('#oth').tree({
			data: [{
			    text: "<a target='showCenter' href='<%=path %>/outPermit/find?id="+str+"' onclick=\"editTitle('污染源基本信息')\">申请单位基本情况</a>"
	        },{
			    text: "<a target='showCenter' href='<%=path %>/sewagelicense/findSwr?id="+str+"' onclick=\"editTitle('水污染物排放情况')\">水污染物排放情况</a>"
	        },{
			    text: "<a target='showCenter' href='<%=path %>/sewagelicense/findDqwr?id="+str+"' onclick=\"editTitle('大气污染物排放情况')\">大气污染物排放情况</a>"
	        },{
			    text: "<a target='showCenter' href='<%=path %>/sewagelicense/findYj?id="+str+"' onclick=\"editTitle('发证单位审批意见')\">发证单位审批意见</a>"
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
    <div data-options="region:'east',title:'排污许可证信息菜单',split:true" style="width:200px;height: 100%;" scrolling="no" frameborder="0" >
    	<%-- <ul class="easyui-tree" >
        	<li>   
                <span><a target="showCenter" href="<%=path %>/sewagelicense/findOne?id=<%=request.getParameter("tbeid")%>" onclick="editTitle('污染源基本信息')">申请单位基本情况</a></span>   
            </li>
        </ul>    --%>
        <ul id="oth" style="">   
	           <%--  <li>   
	                <span><a target="showCenter" href="<%=path %>/wryhbsxController/findOne?id=<%=request.getParameter("tbeid")%>" onclick="editTitle('污染源环保属性')">污染源环保属性</a></span>   
	            </li>   
	            <li>   
	                <span><a target="showCenter" href="<%=path %>/wryglsxController/findOne?id=<%=request.getParameter("tbeid")%>" onclick="editTitle('污染源管理属性')">污染源管理属性</a></span>   
	            </li>   
	            <li>   
	                <span>排污许可证</span>   
	            </li>   
	            <li>   
	                <span>废水排放口信息</span>   
	            </li>   
	            <li>   
	                <span>污水主要污染物</span>   
	            </li>    --%>
        </ul>   
      
    </div>   
    <div id="showCenter" data-options="region:'center',title:'申请单位基本情况'" style="overflow-y: hidden;">
    
    	<iframe name="showCenter" this.style.height=mainFrame.document.body.scrollHeight style="width:100%;height:100%;" scrolling="no" frameborder="0" src="<%=path %>/outPermit/find?id=${id}">
    	</iframe>
    </div>   
</body>
</html>