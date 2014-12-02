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
$(document).ready(function(){
	
    var color="#e8e8e8"
    $("table tr:odd td").css("background-color",color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $("table tr:odd").attr("bg",color);
    $("table tr:even").attr("bg","#fff");
        
	/* 当鼠标移到表格上是，当前一行背景变色 */
	$("table tr td").mouseover(function(){
    	$(this).parent().find("td").css("background-color","#e0ecff");
    });
	
	/* 当鼠标在表格上移动时，离开的那一行背景恢复 */
	$("table tr td").mouseout(function(){
        var bgc = $(this).parent().attr("bg");
    	$(this).parent().find("td").css("background-color",bgc);
    });

})
	//保存
	function save(){
         $('#form').form('submit',{
             url: '<%=path%>/wryglsxController/save',
             onSubmit: function(){
                 return $(this).form('validate');
             },
             success: function(result){
                 if (result != "true"){
                     $.messager.show({
                         msg: "操作失败，请重试！"
                     });
                 } else {
                     $('#dialog').dialog('close');        // close the dialog
                     $('#datagrid').datagrid('reload');    // reload the user data
                     $.messager.show({
                         msg: "操作成功！"
                     });
                 }
             }
         });
     }
     
	//格式化eayui datebox时间格式
	$('#datebox').datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	});
	
	function add(){
    	var name = "水污染物排放情况";
        var src = "sewagelicense/addSwr";
        /* console.log(window.parent); */
        window.parent.addTab(name,src);
    }
</script>
<style type="text/css">
.Fname{
	text-align: right;
}
.Fcontent{

}
tr{
	height: 35px;
	border:1px solid black;
	border-bottom: 1px solid red;
}
input{
	height: 25px;
	width: 220px;
}

</style>

</head>
<body>
<div style="height: 100%; width: 100%; cellSpacing=0; cellPadding=0; ">
	
	<div id="toolbar" style="background-color: #F4F4F4">
    	<div>
	        <a href="<%=path%>/sewagelicense/addSwr" class="easyui-linkbutton" iconCls="icon-add" plain="true" ">添加</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="newLicense()">修改</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyLicense()">删除</a>
    	</div>
    </div>
	
	<form class="easyui_form" id="form" method="post">
    	<table width="100%" toolbar="#toolbar">
    		<tr>
    			<td class="Fname">排污口名称:</td>
    			<td class="Fcontent">
	    			<input name="fisWaterArea" value="${tbe.fisWaterArea}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
						data-options="
						valueField: 'value',
						textField: 'label',
						data: [{
							label: '排污口A',
							value: 'true'
						},{
							label: '排污口B',
							value: 'false'
						}]"/>
    			</td>
    			<td class="Fname">排污口编号:</td><td class="Fcontent"><input name="fcountyId" value="" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">排放去向（受纳水体名称）:</td>
    			<td class="Fcontent">
	    			<input name="fisWaterArea" value="${tbe.fisWaterArea}" class="easyui-combobox" panelHeight="auto" style="height: 31px;"
						data-options="
						valueField: 'value',
						textField: 'label',
						data: [{
							label: 'A污水处理厂',
							value: 'true'
						},{
							label: 'B污水处理厂',
							value: 'false'
						}]"/>
    			</td>
    			<td class="Fname">废水排放执行标准:</td><td class="Fcontent"><input name="ftownId" value="" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">废水排放量限值(吨/日):</td><td class="Fcontent"><input name="fcountyId" value="" class="easyui-validatebox"></td>
    			<td class="Fname"></td><td class="Fcontent"></td>
    		</tr>
    	</table>
    </form>
    
  <%--   <table id="datagrid1" class="easyui-datagrid" width="2056px"
            url="<%=path %>/wryjbxxController/findPollutionBasInfo"
            fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
        	<!-- <tr><th colspan="2" width="1000px"><a>主要污染物</a></th></tr> -->
            <tr>
            	
            </tr>
        </thead>
    </table> --%>
    
    
    <table id="datagrid1" class="easyui-datagrid" style="width:100%;height:100%"
          <%--   url="<%=path %>/wryjbxxController/findPollutionBasInfo" --%>
              fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
        	<tr><th colspan="2">主要污染物</th></tr>
            <tr>
            	<th data-options="field:'11'"  width="50%">主要污染物名称</th>
            	<th data-options="field:'11'"  width="500px">排放浓度限值(mg/L)</th>
            
            </tr>
        </thead>
    </table>
    
    <table id="datagrid2" class="easyui-datagrid" style="width:100%;height:100%"
          <%--   url="<%=path %>/wryjbxxController/findPollutionBasInfo" --%>
              fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
        	<tr><th colspan="3">有效期限内各年度污染物排放量限值（吨/年）</th></tr>
            <tr>
            	<th data-options="field:'12'"  width="200px" >年度</th>
            	<th data-options="field:'23'"  width="300px" >污染物名称</th>
            	<th data-options="field:'5'"  width="300px" >排放限值</th>
            </tr>
        </thead>
    </table>
    
  	
</div>
</body>
</html>