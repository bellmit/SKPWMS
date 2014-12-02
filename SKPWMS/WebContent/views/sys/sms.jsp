<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
var fOutPID='${tPsOutPermit.fOutPID}';

$(document).ready(function(){
	$("li").click(
			function(){
				console.info($(this).attr('value'));
				insertText(document.getElementById('text'),$(this).attr('value'));
			}
	);
	
});
	
//保存
function save(){
     $('#form').form('submit',{
         url: '<%=path%>/outPermit/save',
         onSubmit:function(){
             return $(this).form('validate');
         },
         success: function(result){
             if (result == "false"){
                 $.messager.show({
                     msg: "操作失败，请重试！"
                 });
             } else {
            	 /* window.parent.addMenu(result); */
            	 $("[name='fOutPID']").val(result);
            	 fOutPID=result;
            	 $("#divOutSewage").show();
            	 var sUrl='<%=path %>/outSewage/list?tPsOutPermit.fOutPID='+fOutPID;
         		 $('#sDatagrid').datagrid({
         			url:sUrl
         		 });
                 $.messager.show({
                     msg: "操作成功！"
                 });
             }
         }
     });
 }
 
function insertText(obj,str) {
    if (document.selection) {
        var sel = document.selection.createRange();
        sel.text = str;
    } else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
        var startPos = obj.selectionStart,
            endPos = obj.selectionEnd,
            cursorPos = startPos,
            tmpStr = obj.value;
        obj.value = tmpStr.substring(0, startPos) + str + tmpStr.substring(endPos, tmpStr.length);
        cursorPos += str.length;
        obj.selectionStart = obj.selectionEnd = cursorPos;
    } else {
        obj.value += str;
    }
}
function moveEnd(obj){
    obj.focus();
    var len = obj.value.length;
    if (document.selection) {
        var sel = obj.createTextRange();
        sel.moveStart('character',len);
        sel.collapse();
        sel.select();
    } else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') {
        obj.selectionStart = obj.selectionEnd = len;
    }
} 

</script>
<style type="text/css">
/* div{
	margin: 0;
	padding: 0;
} */
form{
	margin: 0;
	padding: 0;
	height: 100%;
	text-align:center;
}

textarea{
	
	margin: 0;
	padding: 0;
	width: 99%;
	height: 98%;
}

#list{
	border:1px solid #A9A9A9;
	width: 99%;
	height: 98%;
	overflow: scroll;
}

</style>
</head>
<body class="easyui-layout" data-options="fit:'true',border:false">  

<div data-options="region:'north',border:false" style="height:30px;">
	<div id="fToolbar" style="background-color: #F4F4F4">
		<div>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save()">保存</a>
		</div>
	</div>
</div>

<div id="divZ" data-options="region:'east',border:false" style="width:200px;">
	<div id="list">
		<ul>
			<li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li>
			<li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li><li value="aaa">企业名称</li>
			<li value="bbb">企业简称</li>
		</ul>
	</div>
</div>

<div id="divOutSewage" data-options="region:'center',border:false">
		<form class="easyui_form" id="form" method="post" >
	    
	    
	    				<%-- <input name="fOutPCode" value="${tPsOutPermit.fOutPCode}" class="easyui-validatebox" data-options="required:true"> --%>
	    				<textarea id="text" ></textarea>
	    </form>
</div>

</body>
</html>