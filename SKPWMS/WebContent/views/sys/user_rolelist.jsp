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
	src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>角色管理</title>
<script type="text/javascript">
function getroleListSelections(field){
	var ids = [];
	var rows = $("#user_roleList").datagrid('getSelections');
	
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i][field]);
	}
		ids.join(',');
	
	return ids;
	
	}
function   rolecheck(data){		
	var useid='${param.userid}';
	var rows = data.rows;
	$.ajax({
		url : '<%=request.getContextPath()%>/user/findRoleByUserId',
		type : 'post',
		data : {useid:useid},
		dataType : 'json',
		success : function(r) {
			if(r) {
				for(var i=0; i<rows.length; i++) {
					for(var j=0; j<r.length; j++) {
						if(rows[i].id==r[j].id) {
						var rowIndex = $('#user_roleList').datagrid('getRowIndex',rows[i]);
							$('#user_roleList').datagrid('selectRow',rowIndex);
						}
					}
				}
			
			}
		}
	});
}


</script>
</head>
<body class="easyui-layout">
	<table id="user_roleList" name="user_roleList" class="easyui-datagrid"
		style="width: 400px; height: 350px"
		data-options="rownumbers:true,pagination:false,singleSelect:false,url:'${path}/SKPWMS/role/showRole',
		onLoadSuccess : function(data){
				
				rolecheck(data);
			
			}
		
		">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:200,hidden:true">id</th>
				<th data-options="field:'rolename',width:200" align="center">角色名称</th>
			</tr>
		</thead>
	</table>

</body>
</html>