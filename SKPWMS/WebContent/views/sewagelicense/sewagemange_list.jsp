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
<link rel="stylesheet" href="<%=path%>/frame/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=path%>/frame/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
    <script type="text/javascript">
        var url;
        
        $.serializeObject = function(form) {
        	var o = {};
        	$.each(form.serializeArray(), function(index) {
        		if (o[this['name']]) {
        			o[this['name']] = o[this['name']] + "," + this['value'];
        		} else {
        			o[this['name']] = this['value'];
        		}
        	});
        	return o;
        };
        
        function newLicense(){
        	var name = "排污许可证管理";
            var src = "outPermit/add?wrybh=${param.wry_id}";
            window.parent.parent.addTab(name,src);
        }
        
        function destroyLicense(){
            var row = $('#datagrid').datagrid('getSelected');
            if (row){
                $.messager.confirm('删除确认','确认删除?',function(b){
                    if (b){
                        $.post('<%=path%>/wryjbxxService/del', {"id":row.fenterId} ,function(map){
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
        
        function delLicense() {
    		var row = $('#datagrid').datagrid('getSelected');

    		if (row) {
    			$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
    				if (r) {
    					$.post('<%=path%>/outPermit/delete', {
    						id : row.fOutPID
    					}, function(result) {
    						if (result.success) {
    							$('#datagrid').datagrid('reload'); // reload the user data 
    							$('#datagrid').datagrid('clearSelections');
    						} else {
    							$.messager.show({ // show error message 
    								title : 'Error',
    								msg : '删除失败'
    							});
    						}
    					}, 'json');
    				}
    			});
    		}
    	}
        
        //格式化Datagrid列
        function formatterCol(val,row){  
        	if(val == true){  
        	   return "是";  
        	}
			if(val == false){  
        	   return "否";  
        	}  
        }  
        
        //新增tab页
        function addTab(name, id){
        	/* var src = "views/qyxxgl/wryjbxx/enterpriseInfo.jsp?tbeid="+id; */
        	var src = "outPermit/find?id="+id;
        	window.parent.parent.addTab(name,src);
        }
        
        /* function addTab(){
        	var src = "views/sewagelicense/enterpriseInfo.jsp?tbeid=123"; 
        	var src = "views/sewagelicense/enterpriseInfo.jsp?tbeid=123";
        	window.parent.addTab("排污许可证申请",src);
        } */

        
		//条件查询Datagrid
		function searchDatagrid(){
			console.info($.serializeObject($('#searchForm')));
			$("#datagrid").datagrid("load",$.serializeObject($('#searchForm')));
		}
		
		//重置Datagrid查询条件
		function resetDatagridSearch(){
			$("#searchForm input").not("[name^='fIs']").not("[name^='_fIs']").val('');
			$("input[type='checkbox']").each(function(){
				   $(this).attr("checked",false);
				   $(this).attr("value","true");
				  });
			$("#s_fcountyId").combobox('clear');
			$("#datagrid").datagrid('load', {});
		}
		
		//设置分页控件  
		$(function(){
		    var p = $('#datagrid').datagrid('getPager');  
		    $(p).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		    });  
		})
		
		//行字段格式化checkbox显示
        function fIsSewageToCheckbox(val,row){
			var result;
			if(row.fIsSewage){
				result='<input type="checkbox" disabled="disabled" checked="checked"></input>';
			} else{
				result='<input type="checkbox" disabled="disabled" ></input>';
			}
        	return result;
        }
		
        function fIsWasteGasToCheckbox(val,row){
			var result;
			if(row.fIsWasteGas){
				result='<input type="checkbox" disabled="disabled" checked="checked"></input>';
			} else{
				result='<input type="checkbox" disabled="disabled" ></input>';
			}
        	return result;
        }
		
        function fIsNoiseToCheckbox(val,row){
			var result;
			if(row.fIsNoise){
				result='<input type="checkbox" disabled="disabled" checked="checked"></input>';
			} else{
				result='<input type="checkbox" disabled="disabled" ></input>';
			}
        	return result;
        }
        
      //添加超链接，跳转到详细信息页面
        function jumpToOutPermit(val,row){
        	var ahref = "<a href=\"javascript:void(0)\" onclick=\"addTab('排污许可证管理','"+row.fOutPID+"')\">"+val+"</a>";
        	return ahref;
        }
      //点击行业类别，弹出弹出框
	     function showDialog(){
			 //弹出弹出框
	    	 $('#dialog').dialog('open').dialog('setTitle','请选择行业类别');
	    	 //加载tree
	    	 var setting = {
						async: {  
	    		     		enable: true,  
	    		            url:"<%=path%>/tBasIndustryTypeController/findAllOrderByID"
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
	    	$("#hylbname").val(treeNode.name);
	    	$("#s_findustryTypeId").val(treeNode.id);
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
	    	$("#s_forgUnitId").val(treeNode.id);
	    	//关闭弹出框
	    	$('#gxgsDialog').dialog('close');
	     };
    </script>
    <style type="text/css">
    th{align:'center'}
    </style>
</head>
<body>

    <table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%"
            url="<%=path %>/outPermit/list?tBasEnterprise.fenterId=${param.wry_id}"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
            <tr>
            	<th field="fOutPIDIndex" checkbox="true" rowspan="2"></th>
            	<!-- <th data-options="field:'wef'" width="10px">申报登记代码</th> --><!-- formatter="jumpToEnterpriseInfo" -->
            	<!-- <th data-options="field:'fOutPID'" width="10px" rowspan="2" formatter="jumpToOutPermit" align='center'>许可证ID</th> -->
            	<th data-options="field:'fOutPCode'" width="10px" rowspan="2" formatter="jumpToOutPermit" align='center'>许可证编号</th>
            	<th data-options="field:'tBasEnterprise.fenterName'" width="10px" rowspan="2" align='center'>企业名称</th>
            	<th data-options="field:'tBasEnterprise.forgCode'" width="10px" rowspan="2" align='center'>组织机构代码</th>
            	<th data-options="field:'tBasEnterprise.fcorpName'" width="10px" rowspan="2" align='center'>法定代表人</th>
            	<th data-options="field:'tBasEnterprise.fenvironMan'" width="10px" rowspan="2" align='center'>环保联系人</th>
            	<!-- <th data-options="field:'jy'" width="10px" rowspan="2">行业类别</th> -->
            	<th data-options="" width="10px" colspan="3" align='center'>排污种类</th>
            	<!-- <th data-options="field:'wyh'" width="10px" rowspan="2">项目类型</th> -->
            </tr>	
            <tr>
            	<th data-options="field:'fIsSewage'" width="10px" formatter="fIsSewageToCheckbox" align='center'>废水</th>
            	<th data-options="field:'fIsWasteGas'" width="10px" formatter="fIsWasteGasToCheckbox" align='center'>废气</th>
            	<th data-options="field:'fIsNoise'" width="10px" formatter="fIsNoiseToCheckbox" align='center'>噪声</th>
            </tr>
            
        </thead>
    </table>
    
    <div id="toolbar">
    	<div style="display:none">
	    	<form:form id="searchForm" commandName="tPsOutPermit">
	    		<table>
	    			<tr>
	    				<td style="text-align: right;">企业名称：</td>
	    				<td><input id="s_fenterName" name="tBasEnterprise.fenterName" class="easyui-validatebox" /></td>
	    				<td style="text-align: right;">管辖归属：</td>
	    				<td>
	    					<input type="text" id="gxgsname" onclick="showGxgsDialog()" readonly="readonly">
	    					<input type="hidden" id="s_forgUnitId" name="tBasEnterprise.forgUnitId" >
	    				</td>
	    				<td style="text-align: right;">组织机构代码：</td>
	    				<td><input id="s_forgCode" name="tBasEnterprise.forgCode" class="easyui-validatebox" /></td>
						<td style="text-align: right;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchDatagrid()" >搜索</a>
						</td>
	    			</tr>
	    			<tr>
	    				<!-- <td style="text-align: right;">流域：</td>
	    				<td><input id="s_fbasinId" name="s_fbasinId" class="easyui-validatebox" /></td> -->
	    				<td style="text-align: right;">行政区域：</td>
	    				<td>
	    					<select id="s_fcountyId" name="tBasEnterprise.fcountyId" style="width:154px;height:23px;">
	    						<option value="">  </option>
	    						<c:forEach items="${tBasCountyList}" var="item">
		    						<option value="${item.fcountyId}">${item.fcountyName}</option>
	    						</c:forEach>
	    					</select>
	    				</td>
	    				<td style="text-align: right;">行业类别：</td>
	    				<td>
		    				<input type="text" id="hylbname" onfocus="showDialog()">
	    					<input type="hidden" id="s_findustryTypeId" name="tBasEnterprise.findustryTypeId" >
	    				</td>
	    				<td>排污种类：</td>
	    				<td>
	    				<span style="vertical-align:20%">1、废水</span>
	    				<form:checkbox path="fIsSewage"/>
	    				<span style="vertical-align:20%">，2、废气</span>
	    				<form:checkbox path="fIsWasteGas"/>
	    				<span style="vertical-align:20%">，3、噪声</span>
	    				<form:checkbox path="fIsNoise"/>
	    				</td>
						<td style="text-align: right;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetDatagridSearch()" >重置</a>
						</td>
	    			</tr>
	    			<!-- <tr>
	    				<td class="a">申报登记代码：</td>
	    				<td class="b"><input id="s_fenterCode" name="fenterCode" class="easyui-validatebox" /></td>
	    				<td class="a">企业名称：</td>
	    				<td class="b"><input id="s_fenterName" name="tBasEnterprise.fenterName" class="easyui-validatebox" /></td>
	    				<td class="a">法人代表：</td>
	    				<td class="b"><input id="s_fcorpName" name="tBasEnterprise.fcorpName" class="easyui-validatebox" /></td>
	    			</tr> -->
	    			<%-- <tr>
	    				<td>排污种类:</td>
	    				<td>
	    				<span style="vertical-align:20%">1、废水</span>
	    				<form:checkbox path="fIsSewage"/>
	    				<span style="vertical-align:20%">，2、废气</span>
	    				<form:checkbox path="fIsWasteGas"/>
	    				<span style="vertical-align:20%">，3、噪声</span>
	    				<form:checkbox path="fIsNoise"/>
	    				</td>
	    				
						<td class="a">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchDatagrid()" >搜索</a>
						</td>
						<td class="a">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetDatagridSearch()" >重置</a>
						</td>
	    			</tr> --%>
	    		</table>
	    	</form:form>
    	</div>
    	<div>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newLicense()">添加</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delLicense()">删除</a>
    	</div>
    </div>
 
 	<!-- 行业类别弹出框  -->
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
</body>
</html>