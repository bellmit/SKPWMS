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
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/util/easyuiUitl.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

var fOutPID='${fOutPID}';//排污许可证ID
var _url;
var outSewageID='${tPsOutSewage.fOutSewageID}';//排污端口ID

var outSPollYear;

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

	//新增排污口，先隐藏
	if(""==outSewageID){
		$("#divZ").hide();
		$("#divN").hide();
	};
	
	//datagrid合并单元格
	$('#nDatagrid').datagrid({
		onLoadSuccess: function(data){
			//console.info(data);
            if (data.rows.length > 0) {
                //调用mergeCellsByField()合并单元格
                mergeCellsByField("nDatagrid", "tBasPollutant.fPollutantName,fYearID");
            }
            outSPollYear=data.rows;
            //console.info(outSPollYear);
		}
	});
	
	/* //格式化eayui datebox时间格式
	$('#datebox').datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	}); */
	
	
	/* console.info($("#bodyId").width());
	
	var bodyWidth=$("#bodyId").width()/2;
	
	console.info(bodyWidth);
	
	$("#bodyId").layout('panel','east').panel('resize',{width: bodyWidth});
	
	$("#bodyId").resizable({
		onStopResize: function(e){
			console.info("resize");
			console.info(e);
		}
	}); */

});


	//保存排污口
	function saveOutSewage(){
	     $('#form').form('submit',{
	         url: '<%=path%>/outSewage/save',
	         onSubmit:function(){
	             return $(this).form('validate');
	         },
	         success: function(result){
	             if (result == "false"){
	                 $.messager.show({
	                     msg: "操作失败，请重试！"
	                 });
	             } else {
	            	 var isNew=false;//是否新增的排污口
	            	 if(''==outSewageID){
	            	 	outSewageID=result;
	            	 	$("[name='fOutSewageID']").val(result);
	            		isNew=true;
	            	 }
	            	 if(isNew){
		            	 $("#divZ").show();
		         		 $("#divN").show();
		         		 var zUrl='<%=path %>/outSPoll/list?tPsOutSewage.fOutSewageID='+outSewageID;
		         		 $('#zDatagrid').datagrid({
		         			url:zUrl
		         		 });
		         		
		         		 var nUrl='<%=path %>/outSPollYear/list?tPsOutSewage.fOutSewageID='+outSewageID;
		         		 $('#nDatagrid').datagrid({
		         			url:nUrl
		         		 });
	            	 }
	                 $.messager.show({
	                     msg: "操作成功！"
	                 });
	             }
	         }
	     });
	 }

	//弹出主要污染物对话框
	function addOutSPoll(){
		$("#dlg_outSPoll").dialog("open").dialog('setTitle', '添加主要污染物');
		$("#fm_outSPoll").form("clear");
		$("#zSelect").combobox("enable");
		_url = '<%=path%>/outSPoll/save?tPsOutSewage.fOutSewageID='+outSewageID;
	}  
	function editOutSPoll() {
		var row = $('#zDatagrid').datagrid('getSelected');
		if (row) {
			$("#dlg_outSPoll").dialog("open").dialog('setTitle', '修改主要污染物');
			
			$("#fm_outSPoll").form("load", row);
			$("#zSelect").combobox("select", row.tBasPollutant.fPollutantName);
			$("#zSelect").combobox("disable");
			_url = '<%=path%>/outSPoll/save?tPsOutSewage.fOutSewageID='+outSewageID+'&fOutSPollID=' + row.fOutSPollID;
		}
	}
	function saveOutSPoll() {
		$("#fm_outSPoll").form("submit", {
			url : _url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				/* var result = eval('(' + result + ')'); */
				if (result != "") {
					/* $.messager.alert("提示信息", "操作成功"); */
					$.messager.show({
	                     msg: "操作成功！"
	                 });
					$("#dlg_outSPoll").dialog("close");
					$("#zDatagrid").datagrid("reload");
				} else {
					/* $.messager.alert("提示信息", "操作失败"); */
					$.messager.show({
	                     msg: "操作失败，请重试！"
	                 });
					$("#dlg_outSPoll").dialog("close");
					$("#zDatagrid").datagrid("reload");
				}
			}
		});
	}
	function delOutSPoll() {
		var row = $('#zDatagrid').datagrid('getSelected');

		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('<%=path%>/outSPoll/delete', {
						id : row.fOutSPollID
					}, function(result) {
						if (result.success) {
							$('#zDatagrid').datagrid('reload');  
							$('#zDatagrid').datagrid('clearSelections');
						} else {
							$.messager.show({  
								title : 'Error',
								msg : '删除失败'
							});
						}
					}, 'json');
				}
			});
		}
	}
	
	//弹出各年度污染物排放量限值对话框
	function addOutSPollYear(){
		$("#dlg_outSPollYear").dialog("open").dialog('setTitle', '添加年度污染物排放量限值');
		$("#fm_outSPollYear").form("clear");
		_url = '<%=path%>/outSPollYear/save?tPsOutSewage.fOutSewageID='+outSewageID;
		$("#nSelect").combobox("enable");
		$("#nSelect").combobox('reload', '<%=path%>/pollutant/findByOutSewage?tPsOutSewage.fOutSewageID='+outSewageID);//加载排污口对应的主要污染物
		$('#fm_outSPollYear [name=fYearID]').removeAttr("readonly");
		
	}  
	function editOutSPollYear() {
		var row = $('#nDatagrid').datagrid('getSelected');
		if (row) {
			$("#dlg_outSPollYear").dialog("open").dialog('setTitle', '修改年度污染物排放量限值');
			
			
			$("#fm_outSPollYear").form("load", row);
			/* $("[name='tBasPollutant.fPollutantID']").combobox("select", row.fOutSPollYearID); */
			
			$("#nSelect").combobox('reload', '<%=path%>/pollutant/findByOutSewage?tPsOutSewage.fOutSewageID='+outSewageID);//加载排污口对应的主要污染物
			$("#nSelect").combobox("select", row.tBasPollutant.fPollutantName);//选中污染物
			$("#nSelect").combobox("disable");
			$('#fm_outSPollYear [name=fYearID]').attr("readonly","readonly");
			_url = '<%=path%>/outSPollYear/save?tPsOutSewage.fOutSewageID='+outSewageID+'&fOutSPollYearID=' + row.fOutSPollYearID;
		}
	}
	function saveOutSPollYear() {
		if(_url.indexOf("&fOutSPollYearID=")<0){//新增，判断污染物和年度组合是否重复
			var vFYear=$("#fm_outSPollYear [name=fYearID]").val();
			var vNSelect=$("#fm_outSPollYear [name='tBasPollutant.fPollutantID']").val();
			for(var i=0;i<outSPollYear.length;i++){
				var v=outSPollYear[i];
				if(vFYear==v.fYearID&&vNSelect==v.tBasPollutant.fPollutantID){
					$.messager.show({
	                     msg: "主要污染物和年度组合已存在！"
	                 });
					return;
				}
			}
			/* for(var v in outSPollYear){
				if(vFYear==v.fYearID&&vNSelect==v.tBasPollutant.fPollutantID){
					$.messager.show({
	                     msg: "主要污染物和年度组合已存在！"
	                 });
					return;
				}
			} */
		}
		
		
		$("#fm_outSPollYear").form("submit", {
			url : _url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				/* var result = eval('(' + result + ')'); */
				if (result != "") {
					/* $.messager.alert("提示信息", "操作成功"); */
					$.messager.show({
	                     msg: "操作成功！"
	                 });
					$("#dlg_outSPollYear").dialog("close");
					$("#nDatagrid").datagrid("reload");
				} else {
					/* $.messager.alert("提示信息", "操作失败"); */
					$.messager.show({
	                     msg: "操作失败，请重试！"
	                 });
					$("#dlg_outSPollYear").dialog("close");
					$("#nDatagrid").datagrid("reload");
				}
			}
		});
	}
	function delOutSPollYear() {
		var row = $('#nDatagrid').datagrid('getSelected');

		if (row) {
			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
				if (r) {
					$.post('<%=path%>/outSPollYear/delete', {
						id : row.fOutSPollYearID
					}, function(result) {
						if (result.success) {
							$('#nDatagrid').datagrid('reload');  
							$('#nDatagrid').datagrid('clearSelections');
						} else {
							$.messager.show({  
								title : 'Error',
								msg : '删除失败'
							});
						}
					}, 'json');
				}
			});
		}
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

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 180px;
}
</style>

</head>
<body id="bodyId" class="easyui-layout" data-options="fit:true">
<!-- <div style="height: 100%; width: 100%; cellSpacing=0; cellPadding=0; "> -->

<!-- 主要污染物弹窗 S -->
	<div id="dlg_outSPoll" class="easyui-dialog"
		style="width: 600px; height: 380px; padding: 10px 20px;" closed="true"
		buttons="#dlg_outSPollbuttons">
		<form id="fm_outSPoll" method="post">
			
			<div class="fitem">
				<label> 主要污染物：</label> 
				<%-- <select class="easyui-combobox" id="zSelect" name="tBasPollutant.fPollutantID"
					style="width: 220px;" data-options="panelHeight:200">
					<option value="">--请选择--</option>
   					<c:forEach items="${tBasPollutantList}"  var="item">
						<option value="${item.fPollutantID}">${item.fPollutantName}</option>
					</c:forEach>
				</select> --%>
				<input id="zSelect" class="easyui-combobox" name="tBasPollutant.fPollutantID"  style="width: 220px;" 
    			data-options="valueField:'fPollutantID',textField:'fPollutantName',url:'<%=path%>/pollutant/list?tBasPollutantType.fPollTypeCode=B0[1-2]'" />    			
    			<%-- <input id="zSelect" class="easyui-combobox" name="tBasPollutant.fPollutantID"  style="width: 220px;" 
    			data-options="valueField:'fPollutantID',textField:'fPollutantName',url:'<%=path%>/pollutant/list?tBasPollutantType.fPollTypeCode=B01'" /> --%>
			</div>
			
			<div class="fitem">
				<label> 排放浓度限值(mg/L)： </label> 
				<input class="easyui-validatebox" type="text" name="fUpperLimit" required="true" />
			</div>
		</form>
	</div>
	<div id="dlg_outSPollbuttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveOutSPoll()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg_outSPoll').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
	<!-- 主要污染物弹窗 E -->
	
	<!-- 各年度污染物排放量限值  S -->
	<div id="dlg_outSPollYear" class="easyui-dialog"
		style="width: 600px; height: 380px; padding: 10px 20px;" closed="true"
		buttons="#dlg_outSPollYearbuttons">
		<form id="fm_outSPollYear" method="post">
			<div class="fitem">
				<label> 年度： </label> 
				<input class="easyui-validatebox" type="text" name="fYearID" required="true" />
			</div>
			
			<div class="fitem">
				<label> 主要污染物：</label> 
				<%-- <select class="easyui-combobox" id="nSelect" name="tBasPollutant.fPollutantID"
					style="width: 220px;" data-options="panelHeight:200">
					<option value="">--请选择--</option>
   					<c:forEach items="${tBasPollutantList}"  var="item">
						<option value="${item.fPollutantID}">${item.fPollutantName}</option>
					</c:forEach>
				</select> --%>
				<input id="nSelect" class="easyui-combobox" name="tBasPollutant.fPollutantID"  style="width: 220px;" 
    			data-options="valueField:'id',textField:'name'" />
			</div>
			
			<div class="fitem">
				<label> 排放限值： </label> 
				<input class="easyui-validatebox" type="text" name="fDischarge" required="true" />
			</div>
		</form>
	</div>
	<div id="dlg_outSPollYearbuttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveOutSPollYear()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg_outSPollYear').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
	<!-- 各年度污染物排放量限值 E -->
	
<div data-options="region:'north',border:false" style="height:150px;">
<!-- 排放口表单 S -->
	<div id="toolbar" style="background-color: #F4F4F4">
    	<div>
	        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newLicense()">添加</a> -->
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveOutSewage()">保存</a>
	        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="destroyLicense()">撤销</a> -->
    	</div>
    </div>
    
	<div>
		<form class="easyui_form" id="form" method="post">
	    	<table width="100%" toolbar="#toolbar">
	    		<input type="hidden" name="tPsOutPermit.fOutPID" value="${tPsOutSewage.tPsOutPermit.fOutPID}" class="easyui-validatebox">
	    		<input type="hidden" name="fOutSewageID" value="${tPsOutSewage.fOutSewageID}" class="easyui-validatebox">
	    		<tr>
	    			<td class="Fname">排污口编号:</td><td class="Fcontent"><input name="fOutSewageName" value="${tPsOutSewage.fOutSewageName }" class="easyui-validatebox"></td>
	    			<td class="Fname">排污口名称:</td><td class="Fcontent"><input name="fOutSewageCode" value="${tPsOutSewage.fOutSewageCode }" class="easyui-validatebox"></td>
	    		</tr>
	    		<tr>
	    			<td class="Fname">排放去向（受纳水体名称）:</td>
	    			<td class="Fcontent">
		    			<select name="tBasOutWhere.foutWhereId" style="width:226px;height: 30px;">
	    					<option value="">--请选择--</option>
	    				<c:forEach items="${tBasOutWhereList}"  var="item">
	    					<option value="${item.foutWhereId}"
		    					<c:if test="${item.foutWhereId==tPsOutSewage.tBasOutWhere.foutWhereId}">
		    						selected
		    					</c:if>
	    					>${item.foutWhereName}</option>
	    				</c:forEach>
	    				</select>
	    			</td>
	    			<td class="Fname">废水排放执行标准:</td>
	    			<td class="Fcontent">
	    				<select name="tBasWaterDisStd.fwaterDisStdId" style="width:226px;height: 30px;">
	    					<option value="">--请选择--</option>
	    				<c:forEach items="${tBasWaterDisStdList}"  var="item">
	    					<option value="${item.fwaterDisStdId}"
		    					<c:if test="${item.fwaterDisStdId==tPsOutSewage.tBasWaterDisStd.fwaterDisStdId}">
		    						selected
		    					</c:if>
	    					>${item.fwaterDisStdName}</option>
	    				</c:forEach>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="Fname">废水排放量限值(吨/日):</td><td class="Fcontent"><input name="fDayMaxOut" value="${tPsOutSewage.fDayMaxOut }" class="easyui-validatebox"></td>
	    			<td class="Fname"></td><td class="Fcontent"></td>
	    		</tr>
	    	</table>
	    </form>
    </div>
    <!-- 排放口表单 E -->
</div>	

<div id="divZ" data-options="region:'west',border:false" style="width:500px;">
	<!-- <div id="divZ" style="height: 349px"> -->
		<table id="zDatagrid" class="easyui-datagrid" title="主要污染物"
            url="<%=path %>/outSPoll/list?tPsOutSewage.fOutSewageID=${tPsOutSewage.fOutSewageID}"
            toolbar="#zToolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
	        <thead>
	            <tr>
	            	<th field="fOutPIDIndex" checkbox="true" ></th>
	            	<th data-options="field:'tBasPollutant.fPollutantName'" width="100" >主要污染物名称</th>
	            	<th data-options="field:'fUpperLimit'" width="100" >排放浓度限值(mg/L)</th>
	            </tr>	
	        </thead>
	    </table>
	    <div id="zToolbar">
	    	<div>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addOutSPoll()">添加</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOutSPoll()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delOutSPoll()">删除</a>
	    	</div>
	    </div>
	<!-- </div> -->
	
</div>

<div id="divN" data-options="region:'center',border:false">
	<!-- <div id="divN" style="height: 349px"> -->
		<table id="nDatagrid" class="easyui-datagrid" title="有效期限内各年度污染物排放量限值（吨/年）"
            url="<%=path %>/outSPollYear/list?tPsOutSewage.fOutSewageID=${tPsOutSewage.fOutSewageID}"
            toolbar="#nToolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
	        <thead>
	            <tr>
	            	<th field="fOutPIDIndex" checkbox="true" ></th>
	            	<th data-options="field:'tBasPollutant.fPollutantName'" width="100" >主要污染物名称</th>
	            	<th data-options="field:'fYearID'" width="100" >年度</th>
	            	<th data-options="field:'fDischarge'" width="100" >排放限值</th>
	            </tr>	
	        </thead>
	    </table>
	    <div id="nToolbar">
	    	<div>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addOutSPollYear()">添加</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOutSPollYear()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delOutSPollYear()">删除</a>
	    	</div>
	    </div>
	<!-- </div> -->
</div>

</body>
</html>