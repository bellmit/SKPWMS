<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#tRtFacilityForm').form({
			url : '${pageContext.request.contextPath}/wry/tRtFacility/save',
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success == "true") {
					$('#tRtFacilityDialog').dialog('close');
					$('#tRtFacilitydatagrid').datagrid('load'); 
				}
				window.parent.$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
		$('#tRtFacilityForm input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$('#tRtFacilityForm').submit();
			}
		});
	});
	var add = function() {
		$('#tRtFacilityForm input[name=ffacilityNo]').val('');
		$('#tRtFacilityForm input[name=ffacilityName]').val('');
		$('#tRtFacilityDialog').dialog('open');
	}
	var edit = function() {
		var rows = $('#tRtFacilitydatagrid').datagrid('getSelections');
		if(rows.length == 1) {
			$.ajax({
				url : '${pageContext.request.contextPath}/wry/tRtFacility/edit',
				data : {id : rows[0].ffacilityId},
				tpye : 'post',
				dataType : 'json',
				success : function(data) {
					$('#tRtFacilityForm input[name=ffacilityId]').val(data.ffacilityId);
					$('#tRtFacilityForm input[name=ffacilityNo]').val(data.ffacilityNo);
					$('#tRtFacilityForm input[name=ffacilityName]').val(data.ffacilityName);
					$('#tRtFacilityDialog').dialog('open');
				}
			});
		} else {
			window.parent.$.messager.show({title:'提示',msg:'请选择一行进行编辑？'});
		}		
	}
	
	var del = function() {
		var delrows = $('#tRtFacilitydatagrid').datagrid('getSelections');
		if(delrows.length > 0) {
			if($.messager.confirm('请确认','确定要删除吗？', function(b){
				if(b) {
					var arr = [];
					for (var i=0; i<delrows.length; i++) {
						arr.push(delrows[i].ffacilityId);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/wry/tRtFacility/del',
						data : {ids : arr.join(",")},
						tpye : 'post',
						dataType : 'json',
						success : function(data) {
							if(data.success == "true"){
								$('#tRtFacilitydatagrid').datagrid('load');
								$('#tRtFacilitydatagrid').datagrid('unselectAll');
							}
							window.parent.$.messager.show({
								title : '提示',
								msg : data.msg
							});
						}
					});
				}
			})) {
				
			}
		}
		else {
			$.messager.alert('提示','请选择要删除的行！');
		}
	}
</script>
<div id="tRtFacilityDialog" style="width: 250px;" class="easyui-dialog" data-options="title:'监测设备',closed:true,modal:true,buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					$('#tRtFacilityForm').submit();
				}
			}]">
	<form id="tRtFacilityForm" method="post">
		<input name="ffacilityId" type="hidden"/>
		<input name="fenterId" type="hidden" value="${param.wry_id}"/>
		<table>
			<tr>
				<th>设备编号</th>
				<td><input name="ffacilityNo" class="easyui-validatebox" data-options="required:true,missingMessage:'设备编号必填'" /></td>
			</tr>
			<tr>
				<th>设备名称</th>
				<td><input name="ffacilityName" class="easyui-validatebox" data-options="required:true,missingMessage:'设备名称必填'" /></td>
			</tr>
		</table>
	</form>
</div>