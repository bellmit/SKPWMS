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
<script src="<%=path %>/frame/js/CardReader/CardReader2.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

/* var outSewageId; */
var _url;
var enterpriseID='';//卡对应企业ID
var vFRechargeNo='';//充值单号
var vCardPhyNO='';//物理卡号

$(document).ready(function(){
	
    var color="#e8e8e8";
    $("form table tr:odd td").css("background-color",color);  //改变偶数行背景色
    /* 把背景色保存到属性中 */
    $("form table tr:odd").attr("bg",color);
    $("form table tr:even").attr("bg","#fff");
        
	/* 当鼠标移到表格上是，当前一行背景变色 */
	$("form table tr td").mouseover(function(){
    	$(this).parent().find("td").css("background-color","#e0ecff");
    });
	
	/* 当鼠标在表格上移动时，离开的那一行背景恢复 */
	$("form table tr td").mouseout(function(){
        var bgc = $(this).parent().attr("bg");
    	$(this).parent().find("td").css("background-color",bgc);
    });

	/* if(""==outSewageID){
		$("#divZ").hide();
	}; */
	
	/* $("#aSearch").hide(); */
	
	//充值方式
	$("#zSelect").combobox({
		onSelect: function (record) {
			//console.info(record);
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
	});
	
	/* //充值方式
	$("#pollutant").combobox({
		onSelect: function (record) {
			console.info(record);
			if("按年度充值"==$("#fm_recharge [name=fPrePaid]").val()){
				console.info("yes");
			} else{
			}
			console.info($("#fm_recharge [name=fPrePaid]").val());
		}
	}); */
	
	//datagrid合并单元格
	$('#zDatagrid').datagrid({
		onLoadSuccess: function(data){
			//console.info(data);
            if (data.rows.length > 0) {
                //调用mergeCellsByField()合并单元格
                mergeCellsByField("zDatagrid", "tBasPollutant.fPollutantName,fYearID");
            }
		}
	});

	//格式化eayui datebox时间格式
	$("#fDate").datebox({
		editable:false,
		panelHeight:260,
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();}
	});
	/* console.info(new Date().format('yyyy-MM-dd')); */
	$('#fDate').datebox('setValue', (new Date()).format("yyyy-MM-dd"));
	
	/* var date=Date.parse("2011-11-11");
	var year=date.getFullYear();  */
	/* console.info(Date.parse("Jul 8, 2005")); */
	/* var date = new Date('2013-11-11');
	console.info(date.getFullYear()+1); */
});
	
	//读卡、到后台读取卡号关联的企业和排污许可证信息
	function readCard(){
		//var cardPhyNO=$("[name='cardPhyNo'").val();
		var cardPhyNO=readCardNo();
		vCardPhyNO=cardPhyNO;
		if(cardPhyNO) {
			$.ajax({
				url : '${pageContext.request.contextPath}/card/cardsfcunzai',
				data : {cardPhyBh:vCardPhyNO},
				type : 'post',
				dataType : 'json',
				success : function(r) {
					if(r && r.status==1) {
						$.messager.alert('提示','该卡已注销！');
					} else if(r) {
						$.ajax({
							  type: 'POST',
							  url: '<%=path%>/card/findCardInfo',
							  data: {'cardPhyNO':cardPhyNO},
							  dataType: 'json',
							  success: function (data) {
								  //console.info(data);
								  //填写表单中企业相关的信息
								  $('#form').form('load',{
									  	'tBasEnterprise.fenterId':data.enterprise.fenterId,
									  	'tBasEnterprise.fenterName':data.enterprise.fenterName,
									  	'tBasEnterprise.tBasCounty.fcountyName':data.enterprise.tBasCounty==null?'':data.enterprise.tBasCounty.fcountyName,
										'tBasEnterprise.tBasIndustryType.findustryTypeName':data.enterprise.tBasIndustryType==null?'':data.enterprise.tBasIndustryType.findustryTypeName,
										'cardInfo.cardinfoid':data.cardinfoid
									});
								  
								  //显示企业查询按钮
								  /* $("#aSearch").show(); */
								  
								  //获取排污许可证信息
								  readOutPermit(data.enterprise.fenterId);
					          },
					          error: function (msg) {
					        	  $.messager.show({    // show error message
					                  msg: "查找不到该卡对应的信息！"
					              });
					              /* alert("查找不到该卡对应的信息"); */
					          }
							});
					} else {
						$.messager.alert('提示','该卡未激活！');
					}
				}
			});
		} else {
			$.messager.alert('提示','找不到卡，请检查卡是否放好？');
		}
		
	     <%-- $('#form').form('submit',{
	         url: '<%=path%>/recharge/save',
	         onSubmit:function(){
	             return $(this).form('validate');
	         },
	         success: function(result){
	             if (result == "false"){
	                 $.messager.show({
	                     msg: "操作失败，请重试！"
	                 });
	             } else {
	            	 outSewageID=result;
	            	 $("[name='fOutSewageID']").val(result);
	            	 $("#divZ").show();
	         		 $("#divN").show();
	         		 var zUrl='<%=path %>/recharge/list?tPsOutSewage.fOutSewageID='+outSewageID;
	         		 $('#zDatagrid').datagrid({
	         			url:zUrl
	         		 });
	         		
	                 $.messager.show({
	                     msg: "操作成功！"
	                 });
	             }
	         }
	     }); --%>
	 };

	//查找企业当前有效的排污许可证
	function readOutPermit(enterpriseId){
		enterpriseID=enterpriseId;
		$.ajax({
			  type: 'POST',
			  url: '<%=path%>/outPermit/findByEnterpriseId',
			  data: {'enterpriseId':enterpriseId},
			  dataType: 'json',
			  success: function (data) {
				  //console.info(data);
				  $('#form').form('load',{
					  	'tPsOutPermit.fOutPID':data.tPsOutPermit.fOutPID,
					  	'tPsOutPermit.fOutPCode':data.tPsOutPermit.fOutPCode
					});
				  
				  //排污许可证填写的污染物
				  var _url='<%=path%>/outSPoll/distinctPollutant?fOutPID='+data.tPsOutPermit.fOutPID;
				  /* $("[name='tBasPollutant.fPollutantID']").combobox('reload',_url); */
				  $("#pollutant").combobox('reload',_url);
				  
				  //许可证有效年限，年度充值
				  var endDate = new Date(data.tPsOutPermit.fEndDate);
				  var nowDate = new Date();
				  var yearJsonData=yearToJson(nowDate,endDate);
				  $("#vb_fYear").combobox({
					  data:yearJsonData  
				  });

				  
				  //刷新datagrid
				  <%-- <%-- _url='<%=path%>/card/recharge/list?fenterId='+enterpriseId; --% >
				  _url='<%=path%>/card/recharge/orderList?fenterId='+enterpriseId;
				  //page=1&rows=10&
				  $('#zDatagrid').datagrid({
	         			url:_url
	         		}); --%>
	          },
	          error: function (msg) {
	        	  $.messager.show({    // show error message
	                  msg: "查找不到有效的排污许可证信息！"
	              });
	              /* alert("查找不到有效的排污许可证信息"); */
	              
	          }
			});
	};

	//弹出污染因子充值对话框
	function addRecharge(){
		if($("#form").form("validate")){
			if(readCardNo()==vCardPhyNO){
				$("#dlg_recharge").dialog("open").dialog('setTitle', '充值');
				$("#fm_recharge").form("clear");
				$("#zSelect").combobox('select','按周期充值');
				<%-- _url = '<%=path%>/recharge/save?tPsOutSewage.fOutSewageID='+enterpriseID; --%>
				vFRechargeNo=$("[name='fRechargeNo']").val();
			}else{
				$.messager.show({    // show error message
					  title:'提示',
	                  msg: "查找不到有效IC卡！"
	              });
			}
		}
	};  
	//保存充值信息
	function saveRecharge() {
		if(readCardNo()==vCardPhyNO){
			var _url='<%=path%>/card/recharge/save';
			/* _url=_url+'?cardInfo.cardid='+$("[name='cardInfo.cardid']").val(); */
			_url=_url+'?tBasEnterprise.fenterId='+$("[name='tBasEnterprise.fenterId']").val();
			_url=_url+'&fRechargeNo='+$("[name='fRechargeNo']").val();
			_url=_url+'&fDate='+$("[name='fDate']").val();
			_url=_url+'&tPsOutPermit.fOutPID='+$("[name='tPsOutPermit.fOutPID']").val();
			_url=_url+'&fEmp.id='+$("[name='fEmp.id']").val();
			_url=_url+'&cardInfo.cardinfoid='+$("[name='cardInfo.cardinfoid']").val();
			$("#fm_recharge").form("submit", {
				url : _url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					/* var result = eval('(' + result + ')'); */
					if (result != "false") {
						
						/* $.messager.alert("提示信息", "操作成功"); */
						$("#dlg_recharge").dialog("close");
						
						//刷新datagrid
						  <%-- _url='<%=path%>/card/recharge/list?fenterId='+enterpriseId; --%>
						  _url='<%=path%>/card/recharge/list?fenterId='+enterpriseID+'&tIcRecharge.fRechargeNo='+vFRechargeNo;
						  //page=1&rows=10&
						  $('#zDatagrid').datagrid({
			         			url:_url
			         		});
						//刷新datagrid
						$('#zDatagrid').datagrid('reload');
						
						//写卡
						var writeCard;
						if(writeRechargeNo(result)){
							writeCard="";
						}else{
							writeCard="但写卡失败！";
						};
						$.messager.show({    // show error message
			                  msg: "操作成功！"+writeCard
			              });
					} else {
						$.messager.show({    // show error message
			                  msg: "操作失败！"
			              });
						/* $.messager.alert("提示信息", "操作失败"); */
						$("#dlg_recharge").dialog("close");
						$("#zDatagrid").datagrid("reload");
					}
				}
			});
		}else{
			$.messager.show({    // show error message
				  title:'提示',
                  msg: "查找不到有效IC卡！"
              });
		}
		
	};
	
	function searchRecharge() {
		var url='<%=path%>/card/recharge/search?fenterId='+enterpriseID;
		window.parent.addTab("IC卡充值查询",url);
	};
	
	function yearToJson(startDate,endDate){
		var startYear=startDate.getFullYear();
		var endYear=endDate.getFullYear();
		var years=new Array();
		while(startYear<=endYear){
			years.push('{"id":"'+startYear+'","text":"'+startYear+'"}');
			startYear++;
		};
		var jsonStr="["+years.join(",")+"]";
		//console.info(jsonStr);
		return jQuery.parseJSON(jsonStr);
	};
	
	function pringtRecharge(){
		if($("[name='fRechargeNo']").val()){
			var name = "IC卡充值打印";
		    var src = "<%=path%>/card/recharge/download?fRechargeNo="+$("[name='fRechargeNo']").val();
		    window.parent.addTab(name,src);
		}else{
			$.messager.show({    // show error message
                msg: "请输入充值单号！"
            });
		}
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
<body class="easyui-layout" data-options="fit:true">

	<OBJECT id=MWRFATL style="WIDTH: 0px; HEIGHT: 0px" 
	codeBase=<%=path %>frame/js/CardReader/MwRFReader.cab#version=2,0,0,1
	data=data:application/x-oleobject;base64,VPpLUhUXNkSyudxeJIvBwwADAAAAAAAAAAAAAA== 
	classid=CLSID:BDE9B6B3-4C1D-4C05-8A71-3696F3BF81F5></OBJECT> 

	<!-- 主要污染物弹窗 S -->
	<div id="dlg_recharge" class="easyui-dialog"
		style="width: 600px; height: 380px; padding: 10px 20px;" closed="true"
		buttons="#dlg_rechargebuttons">
		<form id="fm_recharge" method="post">
			<div class="fitem">
				<label> 充值方式：</label> 
				<select id="zSelect" class="easyui-combobox" name="fPrePaid" 
				data-options="valueField:'id',textField:'text',required:true"
					style="width: 224px;" >
					<option value="按周期充值">按周期充值</option>
					<option value="按年度充值">按年度充值</option>
				</select>
			</div>
			
			<div class="fitem">
				<label> 污染因子：</label> 
				<input id="pollutant" class="easyui-combobox" name="tBasPollutant.fPollutantID"  
    				data-options="valueField:'id',textField:'name',url:'',required:true" />  
    				
				<!-- <select class="easyui-combobox" id="zSelect" name="tBasPollutant.fPollutantID"
					style="width: 220px;" data-options="panelHeight:200">
				</select> -->
			</div>
			
			<div class="fitem">
				<label> 充值排放量： </label> 
				<input class="easyui-validatebox" type="text" name="fQuantity" required="true" />
			</div>
			
			<div class="fitem" id="div_fYear">
				<label> 充值年度： </label> 
				<input class="easyui-combobox" id="vb_fYear" name="fYearID" data-options="valueField:'id',textField:'text'" />
			</div>
		</form>
	</div>
	<div id="dlg_rechargebuttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveRecharge()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg_recharge').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
	<!-- 主要污染物弹窗 E -->

<div data-options="region:'north',border:false" style="height:175px;">
	<div id="toolbar" style="background-color: #F4F4F4">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="readCard()">读卡</a>
        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="readOutPermit('2aeca06be3eb49da933cb0f0071005e8')">读卡</a> -->
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRecharge()">充值</a>
        <!-- <a id="aSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchRecharge()">查询</a> -->
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="pringtRecharge()">打印</a>
    </div>
    
	<form class="easyui_form" id="form" method="post">
    	<table width="100%" toolbar="#toolbar">
    		<!-- <tr>
    			<td class="Fname">卡号:</td><td class="Fcontent"><input name="cardPhyNo" value="" class="easyui-validatebox" ></td>
    			<input name="cardInfo.cardinfoid" type="hidden" >
    			<td class="Fname"></td><td class="Fcontent"></td>
    		
    		</tr> -->
    		<input type="hidden" name="cardInfo.cardinfoid" value="">
    		<tr>
    			<td class="Fname">充值单号:</td><td class="Fcontent"><input name="fRechargeNo" value="" class="easyui-validatebox" data-options="required:true"></td>
    			<td class="Fname">充值日期:</td><td class="Fcontent"><input id="fDate" name="fDate" value="${fDate }" class="easyui-datebox" data-options="required:true"></td>
    		</tr>
    		<tr>
    			<td class="Fname">企业名称:</td><td class="Fcontent"><input name="tBasEnterprise.fenterName" value="" class="easyui-validatebox" data-options="required:true" readonly="readonly"></td>
    			<td class="Fname">行业类别:</td><td class="Fcontent"><input name="tBasEnterprise.tBasIndustryType.findustryTypeName" value="" class="easyui-validatebox" readonly="readonly"></td>
    			<input type="hidden" name="tBasEnterprise.fenterId" >
    			<!-- <td class="Fname">企业编号:</td><td class="Fcontent"><input name="tBasEnterprise.fenterId" value="" class="easyui-validatebox" readonly="readonly"></td> -->
    		</tr>
    		<tr>
    			<td class="Fname">排污许可证编号:</td><td class="Fcontent"><input name="tPsOutPermit.fOutPCode" value="" class="easyui-validatebox" data-options="required:true" readonly="readonly"></td>
    			<input type="hidden" name="tPsOutPermit.fOutPID" >
    			<!-- <input type="hidden" name="fEmp.id" value="" > -->
    			<td class="Fname">经办人:</td><td class="Fcontent">
    				<input class="easyui-combobox" name="fEmp.id"  
    				data-options="valueField:'id',textField:'username',url:'<%=path%>/user/showUser',required:true" />  
    			</td>
    		</tr>
    		<tr>
    			<td class="Fname">行政区域:</td><td class="Fcontent"><input name="tBasEnterprise.tBasCounty.fcountyName" value="" class="easyui-validatebox" readonly="readonly"></td>
    			<td class="Fname"></td><td class="Fcontent"></td>
    		</tr>
    	</table>
    </form>
</div>


<div id="divZ" data-options="region:'center',border:false">
    <!-- <div id="divZ" style="height: 349px"> -->
		<table id="zDatagrid" class="easyui-datagrid" title="充值信息"
            <%-- url="<%=path %>/recharge/list?tPsOutSewage.fOutSewageID=${tPsOutSewage.fOutSewageID}" toolbar="#zToolbar" --%>
            pagination="true" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
	        <thead>
	            <tr>
	            	<!-- <th field="fOutPIDIndex" checkbox="true" ></th> -->
	            	<th data-options="field:'tBasPollutant.fPollutantName'" width="100" align='center'>污染因子</th>
	            	<!-- <th data-options="field:''" width="100" >排放浓度限值（mg/L）</th> -->
	            	<th data-options="field:'fYearID'" width="100" align='center'>年度</th>
	            	<th data-options="field:'fBeforeQty'" width="100" align='center'>充值前许可排放量（吨/年）</th>
	            	<th data-options="field:'fPrePaid'" width="100" align='center'>充值方式</th>
	            	<th data-options="field:'fQuantity'" width="100" align='center'>本次充值排放量</th>
	            	<th data-options="field:'fAfterQty'" width="100" align='center'>充值后许可排放量（吨/年）</th>
	            	
	            	
	            </tr>	
	        </thead>
	    </table>
	    <!-- <div id="zToolbar">
	    	<div>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRecharge()">添加</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRecharge()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRecharge()">删除</a>
	    	</div>
	    </div> -->
	<!-- </div> -->
  	
</div>
</body>
</html>