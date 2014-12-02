<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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

/* var outSewageId; */
var _url;
var enterpriseID='';//卡对应企业ID
var vFRechargeNo='';//充值单号

$(document).ready(function(){
	

	
	/* $("#zSelect").combobox({
		onSelect: function (record) {
			console.info(record);
			if("按年度充值"==record.id){
				$("#div_fYear").show();
				$("#vb_fYear").combobox({
					required: true
				});
			} else{
				$("#div_fYear").hide();
				$("#vb_fYear").combobox({
					required: false
				});
			}
		}
	}); */
	
	//datagrid合并单元格
	/* $('#datagrid').datagrid({
		onLoadSuccess: function(data){
			console.info(data);
            if (data.rows.length > 0) {
                //调用mergeCellsByField()合并单元格
                mergeCellsByField("datagrid", "tBasPollutant.fPollutantName,fYearID");
            }
		}
	}); */

	//格式化eayui datebox时间格式
	$("#rechargeStart").datebox({
		editable:false,
		panelHeight:260,
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();}
	});
	
	//格式化eayui datebox时间格式
	$("#rechargeEnd").datebox({
		editable:false,
		panelHeight:260,
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();}
	});
	
});

//条件查询Datagrid
function searchDatagrid(){
	console.info($.serializeObject($('#searchForm')));
	$("#datagrid").datagrid("load",$.serializeObject($('#searchForm')));
}

//重置Datagrid查询条件
function resetDatagridSearch(){
	/* $("#searchForm input").not("[name^='fIs']").not("[name^='_fIs']").val('');
	$("input[type='checkbox']").each(function(){
		   $(this).attr("checked",false);
		   $(this).attr("value","true");
		  });
	$("#s_fcountyId").combobox('clear'); */
	$("#searchForm").form('clear');
	$("#datagrid").datagrid('load', {});
}

function newRecharge(){
	var name = "IC卡充值管理";
    var src = "<%=path%>/card/recharge/add";
    window.parent.addTab(name,src);
}
	
</script>
<style type="text/css">
/* .Fname{
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
} */

.search input{
	padding-left:2px;
	padding-right:2px;
	height: 18px; 
	width:97%;
	line-height: 20px;
	border: 1px solid #95B8E7
}

</style>

</head>
<body class="easyui-layout">
<!-- <div style="height: 100%; width: 100%; cellSpacing=0; cellPadding=0; "> -->
<div data-options="region:'center',fit:'true',border:false">
	
    
    
	<table id="datagrid" class="easyui-datagrid" title="充值信息"
           url="<%=path%>/card/recharge/list"<%-- ?fenterId=${tBasEnterprise.fenterId }" --%>
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
            <tr>
            	<!-- <th field="fOutPIDIndex" checkbox="true" ></th> -->
            	<th data-options="field:'tIcRecharge.tBasEnterprise.fenterName'" width="100px"  align='center'>企业名称</th>
            	<th data-options="field:'tIcRecharge.fDate'" width="50px"  align='center'>充值时间</th>
            	<th data-options="field:'tIcRecharge.tBasEnterprise.tBasCounty',
            		formatter : function(value,row,index) {
            			if(row.tIcRecharge.tBasEnterprise.tBasCounty) {
            				return row.tIcRecharge.tBasEnterprise.tBasCounty.fcountyName;
            			}
            		}
            	" width="50px"  align='center'>行政区域</th>
            	<th data-options="field:'tIcRecharge.fEmp.username'" width="50px"  align='center'>经办人</th>
            	<th data-options="field:'tBasPollutant.fPollutantName'" width="100px"  align='center'>污染因子</th>
            	<!-- <th data-options="field:''" width="100" >排放浓度限值（mg/L）</th> -->
            	<th data-options="field:'fYearID'" width="50px"  align='center'>年度</th>
            	<th data-options="field:'fBeforeQty'" width="100px"  align='center'>充值前许可排放量（吨/年）</th>
            	<th data-options="field:'fPrePaid'" width="50px"  align='center'>充值方式</th>
            	<th data-options="field:'fQuantity'" width="100px"  align='center'>本次充值排放量</th>
            	<th data-options="field:'fAfterQty'" width="100px"  align='center'>充值后许可排放量（吨/年）</th>
            	
            	
            </tr>	
        </thead>
    </table>
	
	<div id="toolbar">
		<div>
	    	<form id="searchForm" class="search" >
	    		<%-- <input type="hidden" name="fenterId" value="${tBasEnterprise.fenterId }"> --%>
	    		<table>
	    			<tr>
	    				<td style="text-align: right;">企业名称：</td>
	    				<td><input id="s_fenterName" name="tIcRecharge.tBasEnterprise.fenterName" class="easyui-validatebox" /></td>
	    			
	    				<td style="text-align: right;">充值年度：</td>
						<td>
							<input type="text" id="rechargeYearStart" class="easyui-validatebox" name="rechargeYearStart" editable="false" style="width: 150px;"> --
							<input type="text" id="rechargeYearEnd" class="easyui-validatebox"  name="rechargeYearEnd" editable="false" style="width: 150px;">								
						</td>
	    				
	    				
						<td style="text-align: right;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchDatagrid()" >搜索</a>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">污染因子：</td>
	    				<td>
						<select class="easyui-combobox" id="zSelect" name="tBasPollutant.fPollutantID"
							style="width: 220px;" data-options="panelHeight:200">
							<option value="">--请选择--</option>
		   					<c:forEach items="${tBasPollutantList}"  var="item">
								<option value="${item.fPollutantID}">${item.fPollutantName}</option>
							</c:forEach>
						</select>
						
	    				<td style="text-align: right;">充值日期：</td>
						<td>
							<input type="text" id="rechargeStart" class="easyui-datebox" name="rechargeStart" editable="false" style="width: 150px;"> --
							<input type="text" id="rechargeEnd" class="easyui-datebox"  name="rechargeEnd" editable="false" style="width: 150px;">								
						</td>
						
						<td style="text-align: right;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetDatagridSearch()" >重置</a>
						</td>
	    			</tr>
	    		</table>
	    	</form>
    	</div>
    	<div>
    	<sec:authorize url="/card/recharge/add">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRecharge()">充值</a>
    	</sec:authorize>
    	</div>
  	</div>
</div>
</body>
</html>