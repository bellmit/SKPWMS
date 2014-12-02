<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dialog').dialog('open').dialog('setTitle','添加');
            $('#form').form('clear');
            url = '<%=path%>/pfkxxController/save';
        }
        function editUser(){
            var row = $('#datagrid').datagrid('getSelected');
            if (row){
                $('#dialog').dialog('open').dialog('setTitle','编辑');
                $('#form').form('load',row);
                url = '<%=path%>/pfkxxController/save';
            }else{
            	alert("请选择需要编辑的行！");
            }
        }
        function saveUser(){
            $('#form').form('submit',{
                url: url,
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
        function destroyUser(){
            var row = $('#datagrid').datagrid('getSelected');
            if (row){
                $.messager.confirm('删除确认','确认删除?',function(b){
                    if (b){
                    	alert(row.foutPid);
                        $.post('<%=path%>/pfkxxController/del', {"id":row.foutSewageId} ,function(map){
                            if (map.result){
                                $('#datagrid').datagrid('reload');    // reload the user data
                                $.messager.show({    // show error message
                                    msg: "操作成功！"
                                });
                            } else {
                                $.messager.show({    // show error message
                                    msg: "操作失败，请重试！"
                                });
                            }
                        },'json');
                    }
                });
            }else{
            	alert("请选择需要删除的行！");
            }
        }
    </script>
    <style type="text/css">
        #form{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:120px;
            text-align: right;
        }
    </style>
</head>
<body>

    <table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%"
            url="<%=path%>/pfkxxController/findByPage"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
            <tr>
            	<th field="ck" checkbox="true"></th>
            	<th field="foutPid">许可证ID</th>
                <th field="fenterId" width="50">企业ID</th>
                <th field="foutSewageCode" width="50">排污口编号</th>
                <th field="foutSewageName" width="50">排污口名称</th>
                <th field="foutWhereId" width="50">排放去向ID</th>
                <th field="foutStandId" width="50">排放标准ID</th>
                <th field="fsewageTypeId" width="50">废水种类</th>
                <th field="fmaxOut" width="50">废水排放量</th>
                <th field="fdayMaxOut" width="50">允许最大日排放量</th>
                <th field="fstatus" width="50">状态</th>
            </tr>
        </thead>
    </table>
    
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div>
    
    
    
    <div id="dialog" class="easyui-dialog" style="width:600px;height:350px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form id="form" method="post">
            <div class="fitem">
                <label>许可证ID:</label>
                <input type="hidden" name=foutSewageId class="easyui-validatebox" required="true">
                <input name="foutPid" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>企业ID:</label>
                <input name="fenterId" class="easyui-validatebox" >
            </div>
            <div class="fitem">
                <label>排污口编号:</label>
                <input name="foutSewageCode" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>排污口名称:</label>
                <input name="foutSewageName" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label >排放去向ID:</label>
                <input name="foutWhereId" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>排放标准ID:</label>
                <input name="foutStandId" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>废水种类:</label>
                <input name="fsewageTypeId" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>废水排放量:</label>
                <input name="fmaxOut" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>允许最大日排放量:</label>
                <input name="fdayMaxOut" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>状态:</label>
                <input name="fstatus" class="easyui-validatebox">
            </div>
        </form>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dialog').dialog('close')">取消</a>
    </div>
    
</body>
</html>