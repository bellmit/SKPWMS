<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<script src="<%=path %>/frame/js/CardReader/CardReader2.js"></script>
<title>IC卡信息管理</title>
<script type="text/javascript">
var vCardPhyNO='';//物理卡号
var vEnterId='';//企业Id
var vOrgNo='';//组织机构代码
var vCtrId='';//总量控制器Id
$(document).ready(function(){
	var cardInfo='${cardInfo}';
    var color="#e8e8e8";
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
	if(cardInfo==""){
	$('#date').datebox({
		panelHeight:260,
        formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); },
        parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
    });
	$("#pyeardetail").hide();
	}else{
		
		$("#controllerids").combobox('setValue', '${fcontrollername}'); 
		$("#date").datebox("setValue", '${cardInfo.date}'); 
	}
	
	if('${cardPhyNo}') {
		$('#enter').combobox('readonly',true);
		$('#controllerids').combobox('readonly',true);
	}

})
//修改时间
$(function(){
	var cardInfo='${cardInfo}';
	if(cardInfo==""){
		 var curr_time = new Date();
		   var strDate = curr_time.getFullYear()+"-";
		   strDate += curr_time.getMonth()+1+"-";
		   strDate += curr_time.getDate();
		  $("#date").datebox("setValue", strDate); 
	}
  
  });
//发卡

function  faka(){	
	//vCardPhyNO=readCardNo();
	//$("[name='FCardPhyNo']").val(vCardPhyNO);
	
	var  cardinfoid=$('#cardinfoid').val();
	
     if(cardinfoid!=""){
    	 //alert("修改绑定");
    	 $('#form').form('submit',{
             url: '<%=path%>/card/saveCardInfo',
             onSubmit: function(){
                 return $(this).form('validate');
             },
             success: function(result){
                 if (result != "true"){
                     $.messager.show({
                         msg: "操作失败，请重试！"
                     });
                 } else {  
                	 var vWriteCard;
                	 if(cardIssuers(vEnterId,vOrgNo,vCtrId)){
                		 vWriteCard="";
                	 }else{
                		 vWriteCard="但写卡失败！";
                	 };
                     $.messager.show({
                         msg: "修改成功！"+vWriteCard
                     });
                 }
             }
         });
    	 
     }else{
     //alert("发卡")
	 $('#form').form('submit',{
         url: '<%=path%>/card/saveCardInfo',
         onSubmit: function(){
             return $(this).form('validate');
         },
         success: function(result){
             if (result == "yjh"){
                 $.messager.show({
                     msg: "该卡已被激活！"
                 });
             } else if(result == "true") {
            	 var vWriteCard;
            	 if(cardIssuers(vEnterId,vOrgNo,vCtrId)){
            		 vWriteCard="";
            	 }else{
            		 vWriteCard="但写卡失败！";
            	 }
                 $.messager.show({
                     msg: "激活成功！"+vWriteCard
                 });
             } else {
            	  $.messager.show({
                      msg: "操作失败，请重试！"
                  });
             }
         }
     });
     }
	
}


//补卡

function  buka(){
	
	alert("补卡");
	
}
//验卡

function  yanka(){
	
	alert("验卡");
	
	
}

//消卡

function  xiaoka(){
	
	alert("消卡");
	
}

//弹出角色框
function showControllerDlg(){
	
	$("#dlg_controller").dialog("open").dialog('setTitle', '控制器信息');
	
	
}

//选择控制器
function selectController(){
	var row = $('#tt_controller').datagrid('getChecked');		
	
	var ids = [];
	var names = [];
	
	$.each(row, function(index, item){
	
		ids.push(item.fcontrolerId);
		names.push(item.fctrlerName);
	
	});               
	
	$("#controllerids").val(ids);
	$("#controllernames").val(names);
	
	$('#dlg_controller').dialog('close');
	
	
}

function readIccbh() {
	var iccbh = readCardNo();
	if(iccbh) {
		$.ajax({
			url : '${pageContext.request.contextPath}/card/cardsfcunzai',
			data : {cardPhyBh:iccbh},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if(data && data.status==1){
					$.messager.alert('提示','该卡已注销！');
				} else if(data) {
					$.messager.alert('提示','该卡已激活！');
				} else {
					vCardPhyNO = iccbh;
					$("[name='FCardPhyNo']").val(iccbh);
				}
			}
		});
	} else {
		$.messager.alert('提示','找不到卡，请检查卡是否放好？');
	}
}
	//保存
	<%-- function save(){
         $('#form').form('submit',{
             url: '<%=path%>/wryhbsxController/save',
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
     } --%>
</script>
<style type="text/css">
.Fname {
	width: 175px;
	text-align: right;
}

.Fcontent {
	
}

tr {
	height: 35px;
	border: 1px solid black;
	border-bottom: 1px solid red;
}

input {
	height: 25px;
	width: 220px;
}
</style>
</head>
<body class="easyui-layout" data-options="border:false">

	<OBJECT id=MWRFATL style="WIDTH: 0px; HEIGHT: 0px" 
	codeBase=<%=path %>frame/js/CardReader/MwRFReader.cab#version=2,0,0,1
	data=data:application/x-oleobject;base64,VPpLUhUXNkSyudxeJIvBwwADAAAAAAAAAAAAAA== 
	classid=CLSID:BDE9B6B3-4C1D-4C05-8A71-3696F3BF81F5></OBJECT> 

	<div data-options="region:'center',border:false" style="padding: 5px;">
		<form class="easyui_form" id="form" method="post">
			<table width="100%">
				<tr height="15">
					<td
						style="font-size: 19px; font-weight: bolder; background-color: rgb(255, 255, 255);"
						colspan="4">IC卡信息:</td>
				</tr>
				
					<!-- <td class="Fname">IC卡ID:</td> -->
					<input id="cardinfoid" name="cardinfoid"
						value="${cardInfo.cardinfoid}" class="easyui-validatebox"
						type="hidden">
				
				<tr>
					<td class="Fname">IC卡物理编号:</td>
					<td class="Fcontent"><input name="FCardPhyNo"
						value="${cardPhyNo}" <%--class="easyui-validatebox" required="true" --%> readonly="readonly"></td>
					<td class="Fname">污染源企业:</td>
					<td class="Fcontent"><input id="enter" class="easyui-combobox"
						required="true" name="enterprise.fenterId"
						value="${cardInfo.enterprise.fenterId}"
						data-options="valueField:'fenterId',textField:'fenterName',url:'${path}/SKPWMS/wryjbxxController/findAllPollutionBasInfo',
						onSelect: function(rec){						
						vEnterId=rec.fid;
						vOrgNo=rec.forgCode;
						
						 $('#pyeardetail').show();
						 
						 $('#sDatagrid').datagrid({
         					url:'<%=path%>/card/showPolltantYearDetail?enterid='+rec.fenterId
         		 		});
         		 		
         		 		 var url1 = '<%=path%>/tTcControlerController/findTTcControlersOfWry?id='+rec.fenterId
         		 		 $('#controllerids').combobox('reload', url1);
						 
					}"
						style="height: 28PX"></td>
				</tr>

				<tr>
					<td class="Fname">经办人:</td>
					<td class="Fcontent"><input id="userid"
						class="easyui-combobox" name="userInfo.id"
						value="${cardInfo.userInfo.id}"
						data-options="valueField:'id',textField:'username',url:'${path}/SKPWMS/user/showUser'"
						style="height: 28PX"></td>
					<td class="Fname">企业经办人:</td>
					<td class="Fcontent"><input id="FLinkMan" name="FLinkMan"
						value="${cardInfo.FLinkMan}" class="easyui-validatebox"></td>

				</tr>
				<tr>
					<td class="Fname">总量控制器:<input name="fcid" value="${fcontrollerid}" type="hidden"/>   </td>
					<td class="Fcontent"><input id="controllerids"
						name="controllerids" class="easyui-combobox" required="true"
						data-options="valueField:'id',textField:'text',
						onSelect: function(rec){
						vCtrId=rec.mn;
						}"
						style="height: 28PX"></td>
					<!-- <td class="Fcontent"><input id="controllerids"
						easyui-validatebox" type="hidden" name="controllerids" /> <input
						id="controllernames" name="controllernames" value=""
						class="easyui-validatebox"><a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="showControllerDlg()"
						iconcls="icon-search">绑定</a></td> -->

					<td class="Fname">发卡日期:</td>
					<td class="Fcontent"><input id="date" class="easyui-datebox"
						type="text" name="date" editable="false"
						style="height: 31px; width: 225px;" /></td>

				</tr>

			</table>

		</form>
	</div>
	<!-- 污染源因子 -->
	<div style="height: 160px"></div>
	<div id="pyeardetail" style="height: 310px">
		<table id="sDatagrid" class="easyui-datagrid" title="污染因子信息"
			url="<%=path%>/card/showPolltantYearDetail?enterid=${cardInfo.enterprise.fenterId}" toolbar="#toolbar1"
			pagination="true" fit="true" rownumbers="true" fitColumns="true"
			singleSelect="true" striped="true">
			<thead>
				<tr>
					<th data-options="field:'fpollutantName',width:50">污染因子</th>
					<th data-options="field:'fupperLimit',width:50">排放浓度限值</th>
					<th data-options="field:'fyearID',width:50">年度</th>
					<th data-options="field:'fdischarge',width:50">许可排放限值</th>
				</tr>
			</thead>
		</table>
	</div>
	<!-- 添加总量控制器 -->
	<%-- <div id="dlg_controller" class="easyui-dialog"
		style="width: 500px; height: 300px;" closed="true"
		buttons="#dlg-controllerbutton">

		<table id="tt_controller" class="easyui-datagrid"
			style="width: 500px; height: 400px"
			url="${path}/SKPWMS/tTcControlerController/showTTcControlersByPage"
			idField="id" iconCls="icon-save" fit="true" singleSelect=true>
			<thead>
				<tr>
					<th field="ck" checkbox="true"></th>
					<th data-options="field:'fcontrolerId',width:200,hidden:true">ID</th>
					<th data-options="field:'fctrlerCode',width:80">控制器编码</th>
					<th data-options="field:'fctrlerName',width:80">控制器名称</th>

				</tr>
			</thead>
		</table>

	</div>

	<div id="dlg-controllerbutton">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="selectController()" iconcls="icon-save">确定</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg_controller').dialog('close')"
			iconcls="icon-cancel">取消</a>
	</div> --%>
	<div data-options="region:'south',split:false" style="height: 45px;">
		<div id="dlg-buttons" align="center"
			style="margin-top: 10px; margin-bottom: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="readIccbh()" style="margin-right: 50px;">读卡</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-save" onclick="faka()" style="margin-right: 50px;">保存</a>
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="buka()" style="margin-right: 50px;">补卡</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="xiaoka()" style="margin-right: 50px;">消卡</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="yanka()" style="margin-right: 50px;">验卡</a> -->
		</div>

	</div>

</body>
</html>