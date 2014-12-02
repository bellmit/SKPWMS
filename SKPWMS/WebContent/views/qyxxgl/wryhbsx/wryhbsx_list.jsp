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
        function add(){
            $('#dialog').dialog('open').dialog('setTitle','添加');
            $('#form').form('clear');
            url = '<%=path%>/wryhbsxController/save';
        }
        function edit(){
            var row = $('#datagrid').datagrid('getSelected');
            if (row){
                $('#dialog').dialog('open').dialog('setTitle','编辑');
                $('#form').form('load',row);
                url = '<%=path%>/wryhbsxController/save';
            }else{
            	alert("请选择需要编辑的行！");
            }
        }
        function save(){
            $('#form').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    if (result == "true"){
                        $('#dialog').dialog('close');        // close the dialog
                        $('#datagrid').datagrid('reload');    // reload the user data
                        $.messager.show({
                            msg: "操作成功！"
                        });
                    } else {
                        $.messager.show({
                            msg: "操作失败，请重试！"
                        });
                    }
                }
            });
        }
        function destroy(){
            var row = $('#datagrid').datagrid('getSelected');
            if (row){
                $.messager.confirm('删除确认','确认删除?',function(b){
                    if (b){
                        $.post('<%=path%>/wryhbsxController/del', {"id":row.fenterId} ,function(map){
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
            url="<%=path%>/wryhbsxController/findByPage"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
            <tr>
            	<th field="ck" checkbox="true"></th>
            	<th field="foneLevBasinId">一级流域</th>
                <th field="ftwoLevBasinId" width="50">二级流域</th>
                <th field="fthreeBasinId" width="50">三级流域</th>
                <th field="fisWaterArea" width="50">是否水源区</th>
                <th field="fwaterAreaId" width="50">水域功能区级别</th>
                <th field="fnoiseAreaId" width="50">噪声功能区级别</th>
                <th field="fgasAreaId" width="50">空气功能区级别</th>
                <th field="fiso2ctrlArea" width="50">是否SO2控制区</th>
                <th field="fisAcidCtrlArea" width="50">是否酸雨控制区</th>
            </tr>
        </thead>
    </table>
    
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroy()">删除</a>
    </div>
    
    
    
    <div id="dialog" class="easyui-dialog" style="width:600px;height:350px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">User Information</div>
        <form id="form" method="post">
            <div class="fitem">
                <label>一级流域:</label>
                <input type="hidden" name=fenterId class="easyui-validatebox">
                <input name="foneLevBasinId" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>二级流域:</label>
                <input name="ftwoLevBasinId" class="easyui-validatebox" >
            </div>
            <div class="fitem">
                <label>三级流域:</label>
                <input name="fthreeBasinId" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>是否水源区:</label>
                <input name="fisWaterArea" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label >水域功能区级别:</label>
                <input name="fwaterAreaId" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>噪声功能区级别:</label>
                <input name="fnoiseAreaId" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>空气功能区级别:</label>
                <input name="fgasAreaId" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>是否SO2控制区:</label>
                <input name="fiso2ctrlArea" class="easyui-validatebox">
            </div>
            <div class="fitem">
                <label>是否酸雨控制区:</label>
                <input name="fisAcidCtrlArea" class="easyui-validatebox">
            </div>
        </form>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dialog').dialog('close')">取消</a>
    </div>
    
</body>
</html>