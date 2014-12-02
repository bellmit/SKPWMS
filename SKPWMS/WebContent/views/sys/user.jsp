<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/easyui-tags" prefix="t"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/syUtil.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/curdtools.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/jquery-hftable.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/css/common.css"></script>
<script type="text/javascript" src="<%=path%>/frame/js/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		var aid = "orgunitid";
		var bid = "enterprise.fenterId";
		if(document.getElementById(aid).value) {
			selChange(aid,bid);
		}
		if(document.getElementById(bid).value) {
			selChange(bid,aid);
		}
	});

	function selChange(a,b) {
		var obja = document.getElementById(a);
		var objb = document.getElementById(b);
		if(obja.value) {
			objb.disabled = true;
			if($("#zzspan").hasClass("Validform_wrong")) {
				$("#zzspan").removeClass("Validform_wrong");
				$("#zzspan").html("请选择组织");
			}
		} else {
			objb.disabled = false;
		}
	}
	
	function submityz() {
		var zzval = document.getElementById("orgunitid").value;
		var qyval = document.getElementById("enterprise.fenterId").value;
		if(!zzval && !qyval) {
			$("#zzspan").html("所属组织和所属企业必选一个！");
			$("#zzspan").addClass("Validform_wrong");
			return false;
		} else {
			$("#zzspan").html("通过信息验证！");
			$("#zzspan").addClass("Validform_right");
		}
		
	}
</script>

</head>
<body>
 
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="${path}/user/saveUser" refresh="true" beforeSubmit="submityz();">
	<input id="id" name="id" type="hidden" value="${user.id}">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 用户名: </label></td>
			<td class="value" width="85%"><c:if test="${user.id!=null }">
     		${user.username }
     		</c:if> <c:if test="${user.id==null }">
				<input  class="inputxt" name="username"  value="${user.username}" datatype="s2-10" />
				<span class="Validform_checktip">用户名范围在2~10位字符</span>
			</c:if></td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 真实姓名: </label></td>
			<td class="value" width="10%"><input class="inputxt" name="usercount" value="${user.usercount}" datatype="s2-10"> <span class="Validform_checktip">填写个人真实姓名</span></td>
		</tr>

		
		<c:if test="${user.id==null }">
			<tr>
				<td align="right"><label class="Validform_label"> 密码: </label></td>
				<td class="value"><input type="password" class="inputxt" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="" /> <span class="passwordStrength"
					style="display: none;"><span>弱</span><span>中</span><span class="last">强</span> </span> <span class="Validform_checktip">密码至少6个字符,最多18个字符</span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 重复密码: </label></td>
				<td class="value"><input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！"> <span
					class="Validform_checktip">重复个人密码</span></td>
			</tr>
		</c:if>
		
		<tr>
			<td align="right"><label class="Validform_label"> 所属组织:</label></td>
			<td class="value"><select id="orgunitid" name="orgunitid" onchange="selChange('orgunitid','enterprise.fenterId')">
				<option value="">请选择组织</option>
				<c:forEach items="${orgUnitlist}" var="orgunit">
					<option value="${orgunit.id }" <c:if test="${orgunit.id==orgunitid}">selected="selected"</c:if>>${orgunit.orgUnitName}</option>
				</c:forEach>
			</select> <span class="Validform_checktip" id="zzspan">请选择组织</span></td>
		</tr>
		
		<tr>
			<td align="right"><label class="Validform_label"> 所属企业: </label></td>
			<td class="value"><select id="enterprise.fenterId" name="enterprise.fenterId" onchange="selChange('enterprise.fenterId','orgunitid')">
				<option value="">请选择企业</option>
				<c:forEach items="${enterlist}" var="enter">
					<option value="${enter.fenterId }" <c:if test="${enter.fenterId==user.enterprise.fenterId}">selected="selected"</c:if>>${enter.fenterName}</option>
				</c:forEach>
			</select> <span class="Validform_checktip">请选择企业</span></td>
		</tr>
		
		<tr>
			<td align="right"><label class="Validform_label"> 角色: </label></td>
			<td class="value" nowrap><input name="roleid" name="roleid" type="hidden" value="${roleids}" id="roleid"> 
			<input name="roleName" class="inputxt" value="${rolenames}" id="roleName"
				readonly="readonly" datatype="*" /> <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onClick="choose_40284824492e19d501492e26cd320002()">选择</a>
				
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" onClick="clearAll_40284824492e19d501492e26cd320002();">清空</a>
				
				<script type="text/javascript">
				
					var windowapi = frameElement.api, 
					W = windowapi.opener;
					var iframe;// iframe操作对象
					function choose_40284824492e19d501492e26cd320002(){
						
					if(typeof(windowapi) == 'undefined'){
						$.dialog(
								{content: 'url:${path}/SKPWMS/user/selectrole?userid=${user.id}',
								 zIndex: 2100,title: '角色列表',
								 lock : true,
								 width :400,
								 height :350,
								 left :'85%',
								 top :'65%',
								 opacity : 0.4,
								 button : [ 
								            {
								            name : '确认',
								            callback : clickcallback_40284824492e19d501492e26cd320002,
								            focus : true
								            }, 
								           {name : '取消',
								            callback : function() {}
								            } 
								            ]
								})
						}
					else{						
									$.dialog({
									content: 'url:${path}/SKPWMS/user/selectrole?userid=${user.id}',
									zIndex: 2100,
									title: '角色列表',
									lock : true,
									parent:windowapi,width :400,
									height :350,
									left :'85%',
									top :'65%',
									opacity : 0.4,
									button : [ 
									           {name : '确认',
									        	callback : clickcallback_40284824492e19d501492e26cd320002,
									        	focus : true}, 
									           {name : '取消',callback : function() {}} 
												]
									});
									}
					}
				
								function clearAll_40284824492e19d501492e26cd320002(){
									if($('#roleName').length>=1){
									$('#roleName').val('');
									$('#roleName').blur();}
									if($("input[name='roleName']").length>=1){
									$("input[name='roleName']").val('');
									$("input[name='roleName']").blur();}
									$('#roleid').val("");
						}
									
						function clickcallback_40284824492e19d501492e26cd320002(){
							iframe = this.iframe.contentWindow;
							var roleName=iframe.getroleListSelections('rolename');	
							if($('#rolename').length>=1){
							$('#roleName').val(roleName);
							$('#roleName').blur();}
							if($("input[name='roleName']").length>=1){
							$("input[name='roleName']").val(roleName);
							$("input[name='roleName']").blur();							
							}							
							var id =iframe.getroleListSelections('id');
							if (id!== undefined &&id!=""){
								$('#roleid').val(id);
								}
							}
			</script>
									
			<span class="Validform_checktip">角色可多选</span></td>
		</tr>
		
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 工作单位: </label></td>
			<td class="value" width="10%"><input class="inputxt" name="workUnit" value="${user.workUnit}"> </td>
		</tr>
		
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 手机号码: </label></td>
			<td class="value"><input class="inputxt" name="phone" value="${user.phone}" datatype="m" errormsg="手机号码不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 常用邮箱: </label></td>
			<td class="value"><input class="inputxt" name="email" value="${user.email}" datatype="e" errormsg="邮箱格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
		</tr>
	</table>
</t:formvalid>

	
</body>