<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="<%=path%>/frame/js/zTree_v3/css/demo.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/frame/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=path%>/frame/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
 <link rel="stylesheet" href="<%=path%>/frame/js/tools/css/extEasyUI.css" type="text/css">
 <script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
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
	
	//格式化eayui datebox时间格式
	$('#datebox').datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	});

	
	//省级
	$('#fprovinceId').combogrid({ 
		panelWidth:450, 
		value:'${tbe.fprovinceId}', 
		idField:'fprovinceId', 
		textField:'fprovinceName', 
		url:'<%=path %>/tBasProvinceController/findList', 
		columns:[[ 
			{field:'fprovinceCode',title:'编号',width:60}, 
			{field:'fprovinceName',title:'省级行政区名称',width:100}
		]] 
	}); 
	
})
	//保存
	function save(){
		//拼接污染因子
		var str = "{";
		var r=document.getElementsByName("wryz_check"); 
		    for(var i=0;i<r.length;i++){
		         if(r[i].checked){
		        	 if(i == 0) {
		        		 str += r[i].value;
		        	 }
		        	 else {
		        		 str += "," + r[i].value; 
		        	 }
		       }
		    }
		    str += "}";
		//提交表单
         $('#form').form('submit',{
             url: '<%=path%>/wryjbxxController/save?wryz='+str,
             onSubmit:function(){
                 return $(this).form('validate');
             },
             success: function(result){
                 if (result) {
                	window.parent.frames[0].location.href='${pageContext.request.contextPath}/wryjbxxController/findOne?id='+result;
                	 window.parent.addMenu(result);
                	 window.parent.$.messager.show({
                         msg: "操作成功！"
                     });
                 }
                 else {
                	 window.parent.$.messager.show({
                         msg: "操作失败，请重试！"
                     });
                 }
             }
         });
     }
     
	 //点击行业类别，弹出弹出框
     function showDialog(){
		 //弹出弹出框
    	 $('#dialog').dialog('open').dialog('setTitle','请选择行业类别');
    	 //加载tree
    	 var setting = {
					async: {  
    		     		enable: true,  
    		            url:"<%=path%>/tBasIndustryTypeController/findAllOrderByID",
    		            //dataType: "text" 
    		            //autoParam:["id"]  
    		        } ,
    		        data: {  
    		            simpleData: {  
    		                enable: true,
    		        		pIdKey: "pid",
    		        		idKey: "id",
    		        		rootPId: null
    		            }  
    		        },
    		        callback:{
    		        	onDblClick: zTreeOnDblClick
    		        }
    		};
    	 $.fn.zTree.init($("#ztree"), setting);
     }
    function zTreeOnDblClick(event, treeId, treeNode) {
    	//alert(treeNode ? treeNode.id + ", " + treeNode.name : "isRoot");
    	$("#hylbinput").val(treeNode.name);
    	$("#hylbid").val(treeNode.id);
    	//关闭弹出框
    	$('#dialog').dialog('close');
    };
    
  //点击管辖归属，弹出弹出框
    function showGxgsDialog(){
		 //弹出弹出框
   	 $('#gxgsDialog').dialog('open').dialog('setTitle','请选择管辖归属');
   	 //加载tree
   	 var setting2 = {
					async: {  
   		     		enable: true,  
   		            url:"<%=path%>/orgunit/findAllOrgunitForZtree"
   		            //dataType: "text" 
   		            //autoParam:["id"]  
   		        } ,
   		        data: {  
   		            simpleData: {  
   		                enable: true,
   		        		pIdKey: "pid",
   		        		idKey: "id",
   		        		rootPId: null
   		            }  
   		        },
   		        callback:{
   		        	onDblClick: zTreeOnDblClickForGxgs
   		        }
   		};
   	 $.fn.zTree.init($("#gxgsZtree"), setting2);
    }
    function zTreeOnDblClickForGxgs(event, treeId, treeNode) {
    	//alert(treeNode ? treeNode.id + ", " + treeNode.name : "isRoot");
    	$("#gxgsname").val(treeNode.name);
    	$("#forgUnitId").val(treeNode.id);
    	//关闭弹出框
    	$('#gxgsDialog').dialog('close');
    };
</script>
<style type="text/css">
.Fname{
	text-align: right;
}
.Fcontent{
}
tr{
	height: 35px;
	border: 1px solid black;
	border-bottom: 1px solid red;
}
input{
	height: 25px;
	width: 220px;
}
</style>
</head>
<body class="easyui-layout" data-options="border:false">   
    <div data-options="region:'center',border:false" >
	<form class="easyui_form" id="form" method="post">
    	<table width="100%">
			<tr>
				<td colspan="4" style="font-size: 19px;font-weight:bolder;">基本信息：</td>
			</tr>
    		<tr>
    			<td class="Fname">企业名称:</td><td class="Fcontent"><input name="fenterName" value="${tbe.fenterName}" class="easyui-validatebox" data-options="required:true"></td>
    			<td class="Fname">企业编号:</td><td class="Fcontent"><input name="fenterCode" value="${tbe.fenterCode}" class="easyui-validatebox">
    								 <input type="hidden" name="fenterId" value="${tbe.fenterId}">
    								 <input type="hidden" name="fcreatorId" value="${tbe.fcreatorId}">
    								 <input type="hidden" name="flastEditId" value="${tbe.flastEditId}">
    								 <input type="hidden" name="fcreatTime" value="${tbe.fcreatTime}">
    								 </td>
    		</tr>
    		<tr>
    			<td class="Fname">组织机构代码:</td><td class="Fcontent"><input name="forgCode" value="${tbe.forgCode}" class="easyui-validatebox"></td>
    			<td class="Fname">流域:</td><td class="Fcontent"><input name="fbasinId" value="${tbe.fbasinId}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">行政区域:</td><td class="Fcontent">
    			<%-- <input name="fcountyId" value="${tbe.fcountyId}" class="easyui-validatebox"> --%>
    			<select name="fcountyId" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    				<c:forEach items="${tBasCountyList}"  var="item">
    					<option value="${item.fcountyId}"
	    					<c:if test="${item.fcountyId==tbe.fcountyId}">
	    						selected
	    					</c:if>
    					>${item.fcountyName}</option>
    				</c:forEach>
    			</select>
    			</td>
    			<td></td><td></td>
    		</tr>
    		
    		
    		<tr>
				<td colspan="4" style="font-size: 19px;font-weight:bolder;">监管信息：</td>
			</tr>
			<tr>
    			<td class="Fname">专管职员:</td><td class="Fcontent"><input name="fempId" value="${tbe.fempId}" class="easyui-validatebox"></td>
    			<td class="Fname">专管职员电话:</td><td class="Fcontent"><input name="fempMobile" value="${tbe.fempMobile}" class="easyui-validatebox"></td>
			</tr>
			<tr>
    			<td class="Fname">管辖归属:</td><td class="Fcontent">
    				<%-- <input name="forgUnitId" value="${tbe.forgUnitId}" class="easyui-validatebox"> --%>
					<input type="text" id="gxgsname" value="${tbe.tSysorgUnit.orgUnitName}" onclick="showGxgsDialog()" readonly="readonly" class="easyui-validatebox" data-options="required:true">
	    			<input type="hidden" id="forgUnitId" name="forgUnitId" value="${tbe.forgUnitId}">
    			</td>
			</tr>
			
			
			<tr>
				<td colspan="4" style="font-size: 19px;font-weight:bolder;">企业联系人信息：</td>
			</tr>
			<tr>
    			<td class="Fname">法人代表:</td><td class="Fcontent"><input name="fcorpName" value="${tbe.fcorpName}" class="easyui-validatebox"></td>
    			<%-- <td class="Fname">法人代码:</td><td class="Fcontent"><input name="fcorpCode" value="${tbe.fcorpCode}" class="easyui-validatebox"></td> --%>
    			<td class="Fname">法人代表电话:</td><td class="Fcontent"><input name="fcorpMobile" value="${tbe.fcorpMobile}" class="easyui-validatebox"></td>
			</tr>
			<tr>
    			<td class="Fname">环保负责人:</td><td class="Fcontent"><input name="fenvironLeader" value="${tbe.fenvironLeader}" class="easyui-validatebox"></td>
    			<td class="Fname">环保负责人手机:</td><td class="Fcontent"><input name="fenvironLeaderMobile" value="${tbe.fenvironLeaderMobile}" class="easyui-validatebox"></td>
			</tr>
			<tr>
    			<td class="Fname">环保联系人:</td><td class="Fcontent"><input name="fenvironMan" value="${tbe.fenvironMan}" class="easyui-validatebox"></td>
    			<td class="Fname">环保联系人手机:</td><td class="Fcontent"><input name="fenvironMobile" value="${tbe.fenvironMobile}" class="easyui-validatebox"></td>
    			<%-- <td class="Fname">环保联系人电话:</td><td class="Fcontent"><input name="fenvironTel" value="${tbe.fenvironTel}" class="easyui-validatebox"></td> --%>
			</tr>
			
			
			<tr>
				<td colspan="4" style="font-size: 19px;font-weight:bolder;">污染因子：</td>
			</tr>		
			<tr>
				<td colspan="4">
					<c:forEach items="${tBasEnterPollList}" var="item">
						<input type="checkbox" name="wryz_check" style="width: 50px;" value="${item.fenterPollId}"
						<c:if test="${item.bisChoice}">
	    						checked
	    				</c:if>
						>${item.name}
					</c:forEach>
					<!-- <input type="checkbox" name="wryz_check" style="width: 50px;" value="cod">COD
					<input type="checkbox" name="wryz_check" style="width: 50px;" value="nh3">氨氮
					<input type="checkbox" name="wryz_check" style="width: 50px;" value="so2">二氧化硫 -->
				</td>
			</tr>
			
			
			
			<tr>
			</tr>
    		<%-- <tr>
    			<td class="Fname">市级行政区:</td><td class="Fcontent"><input name="fcityId" value="${tbe.fcityId}" class="easyui-validatebox"></td>
    			<td class="Fname">镇:</td><td class="Fcontent"><input name="ftownId" value="${tbe.ftownId}" class="easyui-validatebox"></td>
    			<td class="Fname">省级行政区ID:</td><td class="Fcontent"><input name="fprovinceId" value="${tbe.fprovinceId}" class="easyui-validatebox"></td>
    			<td class="Fname">村:</td><td class="Fcontent"><input name="fvillageId" value="${tbe.fvillageId}" class="easyui-validatebox"></td>
    		</tr> --%>
    		<tr>
    			<td class="Fname">单位类型:</td><td class="Fcontent">
    			<select name="funitClassId" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    				<c:forEach items="${tBasUnitClassList}"  var="item">
    					<option value="${item.funitClassId}"
	    					<c:if test="${item.funitClassId==tbe.funitClassId}">
	    						selected
	    					</c:if>
    					>${item.funitClassName}</option>
    				</c:forEach>
    			</select>
    			</td>
    			<td class="Fname">行业类别:</td><td class="Fcontent">
    			<%-- <input type="text" id="hylbinput"  value="${tbe.findustryTypeId}"  style="width:226px;height: 30px;" onfocus="showDialog()"> --%>
    			<input type="text" id="hylbinput"  value="${tbe.tBasIndustryType.findustryTypeName}"  style="width:226px;height: 30px;" onfocus="showDialog()">
    			<input type="hidden" id="hylbid" name="findustryTypeId" value="${tbe.tBasIndustryType.findustryTypeId}" >
    			<%-- <input name="fenvironMan" value="${tbe.fenvironMan}" class="easyui-validatebox" ,formatter="function(value,row,index){
							if (row.parentOrgUnit){
								return row.parentOrgUnit.orgUnitName;
							} else {
								return value;
					}}">--%>
    			</td> 
    		</tr>
    		<tr>
    			<td class="Fname">企业类别:</td><td class="Fcontent">
    			<%-- <input name="fpstypeId" value="${tbe.fpstypeId}" class="easyui-validatebox"> --%>
    			<select name="fpstypeId" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    				<c:forEach items="${tBasPollSourceTypeList}"  var="item">
    					<option value="${item.fpstypeId}"
	    					<c:if test="${item.fpstypeId==tbe.fpstypeId}">
	    						selected
	    					</c:if>
    					>${item.fpstypeName}</option>
    				</c:forEach>
    			</select>
    			</td>
    			<td class="Fname">企业级别:</td><td class="Fcontent">
    			<%-- <input name="fpslevelId" value="${tbe.fpslevelId}" class="easyui-validatebox"> --%>
    			<select name="fpslevelId" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    				<c:forEach items="${tBasPollSourceLevelList}"  var="item">
    					<option value="${item.fpslevelId}"
	    					<c:if test="${item.fpslevelId==tbe.fpslevelId}">
	    						selected
	    					</c:if>
    					>${item.fpslevelName}</option>
    				</c:forEach>
    			</select>
    			</td>
    		</tr>
    		<tr>
    			<td class="Fname">注册类型:</td><td class="Fcontent">
    			<%-- <input name="fregisterTypeId" value="${tbe.fregisterTypeId}" class="easyui-validatebox"> --%>
    			<select name="fregisterTypeId" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    				<c:forEach items="${tBasRegisterTypeList}"  var="item">
    					<option value="${item.fregisterTypeId}"
	    					<c:if test="${item.fregisterTypeId==tbe.fregisterTypeId}">
	    						selected
	    					</c:if>
    					>${item.fregisterTypeName}</option>
    				</c:forEach>
    			</select>
    			</td>
    			<td class="Fname">企业规模:</td><td class="Fcontent">
    			<%-- <input name="fcompanyScaleId" value="${tbe.fcompanyScaleId}" class="easyui-validatebox"> --%>
    			<select name="fcompanyScaleId" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    				<c:forEach items="${tBasCompanyScaleList}"  var="item">
    					<option value="${item.fcompanyScaleId}"
	    					<c:if test="${item.fcompanyScaleId==tbe.fcompanyScaleId}">
	    						selected
	    					</c:if>
    					>${item.fcompanyScaleName}</option>
    				</c:forEach>
    			</select>
    			</td>
    		</tr>
    		<tr>
    			<td class="Fname">隶属关系:</td><td class="Fcontent">
    			<%-- <input name="frshipId" value="${tbe.frshipId}" class="easyui-validatebox"> --%>
    			<select name="frshipId" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    				<c:forEach items="${tBasRshipList}"  var="item">
    					<option value="${item.frshipId}"
	    					<c:if test="${item.frshipId==tbe.frshipId}">
	    						selected
	    					</c:if>
    					>${item.frshipName}</option>
    				</c:forEach>
    			</select>
    			</td>
    			<fmt:formatDate value="${tbe.fstartUpDate}" var="fstartUpDateFmt" pattern="yyyy-MM-dd" ></fmt:formatDate>
    			<td class="Fname">投产日期:</td><td class="Fcontent"><input id="datebox" name="fstartUpDate" value="${fstartUpDateFmt}" class="easyui-datebox" style="height: 31px;width: 225px;"></td>
    		</tr>
    		
    		<tr>
    			<td class="Fname">废水:</td><td class="Fcontent">
					<select name="fisSewage" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    					<option value=true
    						<c:if test="${tbe.fisSewage == true}">
	    						selected
	    					</c:if>
    					>是</option>
    					<option value=false
    						<c:if test="${tbe.fisSewage == false}">
	    						selected
	    					</c:if> 
    					>否</option>
    				</select>
				</td>
    			<td class="Fname">废气:</td><td class="Fcontent">
					<select name="fisWasteGas" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    					<option value=true
    						<c:if test="${tbe.fisWasteGas==true}">
	    						selected
	    					</c:if>
    					>是</option>
    					<option value=false
    						<c:if test="${tbe.fisWasteGas==false}">
	    						selected
	    					</c:if>
    					>否</option>
    				</select>
				</td>
    		</tr>
    		<tr>
    			<td class="Fname">固废:</td><td class="Fcontent">
					<select name="fisSolidWaste" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    					<option value="true"
    						<c:if test="${tbe.fisSolidWaste==true}">
	    						selected
	    					</c:if>
    					>是</option>
    					<option value="false"
    						<c:if test="${tbe.fisSolidWaste==false}">
	    						selected
	    					</c:if>
    					>否</option>
    				</select>
				</td>
    			<td class="Fname">噪声:</td><td class="Fcontent">
					<select name="fisNoise" style="width:226px;height: 30px;">
    					<option value="">--请选择--</option>
    					<option value="true"
    						<c:if test="${tbe.fisNoise==true}">
	    						selected
	    					</c:if>
    					>是</option>
    					<option value="false"
    						<c:if test="${tbe.fisNoise==false}">
	    						selected
	    					</c:if>
    					>否</option>
    				</select>
				</td>
    		</tr>
    		<tr>
    			<td class="Fname">生产地址:</td><td class="Fcontent"><input name="fprodAddress" value="${tbe.fprodAddress}" class="easyui-validatebox"></td>
    			<td class="Fname">电话:</td><td class="Fcontent"><input name="fphone" value="${tbe.fphone}" class="easyui-validatebox"></td>
    		</tr>
    		
    		<tr>
    			<td class="Fname">传真:</td><td class="Fcontent"><input name="ffax" value="${tbe.ffax}" class="easyui-validatebox"></td>
    			<td class="Fname">邮编:</td><td class="Fcontent"><input name="fpostCode" value="${tbe.fpostCode}" class="easyui-validatebox" validtype="zipcode"></td>
    		</tr>
    		<tr>
    			<td class="Fname">邮箱地址:</td><td class="Fcontent"><input name="femail" value="${tbe.femail}" class="easyui-validatebox" validtype="email" invalidMessage="邮箱格式不正确"></td>
    			<td class="Fname">手机:</td><td class="Fcontent"><input name="fmobile" value="${tbe.fmobile}" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">经度:</td><td class="Fcontent">
    			<input name="flongDegree" value="${tbe.flongDegree}" class="easyui-numberbox" style="width: 36px;">度
    			<input name="flongMinute" value="${tbe.flongMinute}" class="easyui-numberbox" maxlength="2" max="59" style="width: 36px;">分
    			<input name="flongSecond" value="${tbe.flongSecond}" class="easyui-numberbox" precision="6" maxlength="9" max="59.999999" style="width: 76px;">秒</td>
    			<td class="Fname">纬度:</td><td class="Fcontent">
    			<input name="flatDegree" value="${tbe.flatDegree}" class="easyui-numberbox" style="width: 36px;">度
    			<input name="flatMinute" value="${tbe.flatMinute}" class="easyui-numberbox" maxlength="2" max="59" style="width: 36px;">分
    			<input name="flatSecond" value="${tbe.flatSecond}" class="easyui-numberbox" precision="6" maxlength="9" max="59.999999" style="width: 76px;">秒</td>
    		</tr>
    		<tr>
    			<td class="Fname">注册地址:</td><td class="Fcontent"><input name="fregAddress" value="${tbe.fregAddress}" class="easyui-validatebox"></td>
    			<td></td><td></td>
    		</tr>
    	</table>    
    </form>
    <div align="right" style="margin-top:40px;" class="swFormButtunDiv">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()" style="margin-right: 50px;">保存</a>
    </div>

	<div id="dialog" class="easyui-dialog" style="width:460px;height:400px;padding:0px 10px;" closed="true" buttons="#dlg-buttons" modal="true" >
		<ul class="ztree" id="ztree" style="width: 400px;">
		</ul>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dialog').dialog('close')">取消</a>
    </div>
    
 <!-- 管辖归属弹出框  -->
 	<div id="gxgsDialog" class="easyui-dialog" style="width:460px;height:400px;padding:0px 10px;" closed="true" buttons="#gxgsButtons" modal="true" >
		<ul class="ztree" id="gxgsZtree" style="width: 400px;">
		</ul>
    </div>
    
    <div id="gxgsButtons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#gxgsDialog').dialog('close')">取消</a>
    </div>
</div>




</body>
</html>