<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
    <script type="text/javascript">
        var url;
        
        function newUser(){
        	var name = "添加企业";
            var src = "views/qyxxgl/wryjbxx/enterpriseInfo.jsp?tbeid=new";
            window.parent.addTab(name,src);
        }
        
        function destroyUser()
        {
            var row = $('#datagrid').datagrid('getSelected');
            if (row)
            {
                $.messager.confirm('删除确认','确认删除?',function(b)
                {
                    if (b)
                    {
                    	$.ajax({
                			type: "POST",
                			url: "<%=path%>/wryjbxxController/delflag",
                			async: false,
                			data : "id="+row.fenterId+"&time="+new Date().getTime(),  
                			success:function(msg)
                			{
                				if(msg.flag)
                				{
                					$.post('<%=path%>/wryjbxxController/del', {"id":row.fenterId} ,function(map){
                                        if (map.result)
                                        {
                                            $('#datagrid').datagrid('reload');
                                            $.messager.show({msg: "操作成功！"});
                                        }
                                        else
                                        {
                                            $.messager.show({msg: "操作失败，请重试！"});
                                        }
                                    },'json');
                				}
                				else
                				{
                					$.messager.show({msg:"当前选中企业有排污许可证或者IC卡信息,不可删除!"});
                				}
                			}
                		});
                    }
                });
            }
            else
            {
            	alert("请选择需要删除的行！");
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
        
        //添加超链接，跳转到企业详细信息页面
        function jumpToEnterpriseInfo(val,row){
        	var ahref = "<a href=\"javascript:void(0)\" onclick=\"addTab('"+row.fenterName+"','"+row.fenterId+"')\">"+val+"</a>";
        	return ahref;
        }
      	//显示行政区域的名称
        function showCountyName(value,row){
        	if (row.tBasCounty){
				return row.tBasCounty.fcountyName;
			} else {
				return value;
			}
        }
      //显示行业类别的名称
        function showHylb(value,row){
        	if (row.tBasIndustryType){
				return row.tBasIndustryType.findustryTypeName;
			} else {
				return value;
			}
        }
      //显示管辖归属的名称
      function showGxgs(value,row)
      {
        	if (true)
        	{
        		if(row.tSysorgUnit) return row.tSysorgUnit.orgUnitName;
			}
        	else
        	{
				return value;
			}
        }
      	 //格式化投产日期
        function formatDate(value,row)
      	{
      		if(value)
      		{
      			var now = new Date(value);
      			var year=now.getYear()+1900;
                var month=now.getMonth()+1;
                var date=now.getDate();
                return year+"-"+month+"-"+date;  
      		}
      		else
      		{
      			return value;
      		}
        } 
        //新增tab页
        function addTab(name, id){
        	var src = "views/qyxxgl/wryjbxx/enterpriseInfo.jsp?tbeid="+id;
        	window.parent.addTab(name,src);
        }

        
		//条件查询Datagrid
		function searchDatagrid(){
			var s_fenterName = $("#s_fenterName").val();
			var s_forgUnitId = $("#s_forgUnitId").val();
			var s_forgCode = $("#s_forgCode").val();
			var s_fbasinId = $("#s_fbasinId").val();
			var s_fcountyId = $("#s_fcountyId").val();
			var s_findustryTypeId = $("#s_findustryTypeId").val();
			
			$("#datagrid").datagrid("load",{
				"fenterName":s_fenterName,
				"forgUnitId":s_forgUnitId,
				"forgCode":s_forgCode,
				"fbasinId":s_fbasinId,
				"fcountyId":s_fcountyId,
				"findustryTypeId":s_findustryTypeId
			});
		}
		
		//重置Datagrid查询条件
		function resetDatagridSearch(){
			$("#s_fenterName").val("");
			$("#gxgsname").val("");			
			$("#s_forgUnitId").val("");
			$("#s_forgCode").val("");
			$("#s_fbasinId").val("");
			$("#s_fcountyId").val("");
			$("#s_findustryTypeId").val("");
			$("#hylbname").val("");
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
	    	$("#menuparentid").val(treeNode.id);
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
</head>
<body>

    <table id="datagrid" class="easyui-datagrid" style="width:100%;height:100%"
            url="<%=path %>/wryjbxxController/findPollutionBasInfo"
            toolbar="#toolbar" pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
        <thead>
            <tr>
            	<th field="ck" checkbox="true"></th>
            	<th data-options="field:'fenterCode'" width="10px" align="center">企业编号</th>
				<th data-options="field:'fenterName'" width="30px" align="center" formatter="jumpToEnterpriseInfo">企业名称</th>
				<th data-options="field:'fstartUpDate',hidden:true" width="10px" align="center" formatter="formatDate">投产日期</th>
				<th data-options="field:'fcountyId'" width="10px" align="center" formatter="showCountyName">行政区域</th>
				<th data-options="field:'forgUnitId'" width="10px" align="center" formatter="showGxgs">管辖归属</th>
				<th data-options="field:'findustryTypeId'" width="10px" align="center" formatter="showHylb">行业类别</th>
				<th data-options="field:'fbasinId',hidden:true" width="10px" align="center">流域</th>
				<th data-options="field:'fcorpName'" width="10px" align="center">法人代表</th>
				<th data-options="field:'forgCode'" width="10px" align="center">组织机构代码</th>
				<th data-options="field:'fenvironMan'" width="10px" align="center">企业环保联系人</th>
				<th data-options="field:'fempId'" width="10px" align="center">专管职员</th>
				<th data-options="field:'fempMobile'" width="10px" align="center">专管职员电话</th>
            </tr>
        </thead>
    </table>
    
    <div id="toolbar">
    	<div>
	    	<form>
	    		<table>
	    			<tr>
	    				<td style="text-align: right;">企业名称：</td>
	    				<td><input id="s_fenterName" name="s_fenterName" class="easyui-validatebox" /></td>
	    				<td style="text-align: right;">管辖归属：</td>
	    				<td>
	    					<input type="text" id="gxgsname" onclick="showGxgsDialog()" readonly="readonly">
	    					<input type="hidden" id="s_forgUnitId" name="s_forgUnitId" >
	    				</td>
	    				<td style="text-align: right;">组织机构代码：</td>
	    				<td><input id="s_forgCode" name="s_forgCode" class="easyui-validatebox" /></td>
						<td style="text-align: right;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchDatagrid()" >搜索</a>
						</td>
	    			</tr>
	    			<tr>
	    				<td style="text-align: right;">流域：</td>
	    				<td><input id="s_fbasinId" name="s_fbasinId" class="easyui-validatebox" /></td>
	    				<td style="text-align: right;">行政区域：</td>
	    				<td>
	    					<select id="s_fcountyId" name="s_fcountyId" style="width:154px;height:23px;">
	    						<option value="">  </option>
	    						<c:forEach items="${tBasCountyList}" var="item">
		    						<option value="${item.fcountyId}">${item.fcountyName}</option>
	    						</c:forEach>
	    					</select>
	    				</td>
	    				<td style="text-align: right;">行业类别：</td>
	    				<td>
		    				<input type="text" id="hylbname" onfocus="showDialog()">
	    					<input type="hidden" id="s_findustryTypeId" name="s_findustryTypeId" >
	    				</td>
						<td style="text-align: right;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetDatagridSearch()" >重置</a>
						</td>
	    			</tr>
	    		</table>
	    	</form>
    	</div>
    	<div>
    	<sec:authorize url="/views/qyxxgl/wryjbxx/enterpriseInfo.jsp">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
	    </sec:authorize>
	    <sec:authorize url="/wryjbxxController/delflag">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
	    </sec:authorize>
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