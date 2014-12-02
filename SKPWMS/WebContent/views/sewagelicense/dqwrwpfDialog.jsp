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

})
	//保存
	function save(){
         $('#form').form('submit',{
             url: '<%=path%>/wryglsxController/save',
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
     }
     
	//格式化eayui datebox时间格式
	$('#datebox').datebox({
	    formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();},
	});
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

</style>

<script>  
        var products = [  
            {productid:'FI-SW-01',name:'总镍'},  
            {productid:'K9-DL-01',name:'PH'},  
            {productid:'RP-SN-01',name:'CODcr'},  
            {productid:'RP-LI-02',name:'总铜'},  
            {productid:'FL-DSH-01',name:'氨氮'},  
        ];  
        function productFormatter(value){  
            for(var i=0; i<products.length; i++){  
                if (products[i].productid == value) return products[i].name;  
            }  
            return value;  
        }  
        $(function(){  
            var lastIndex;  
            $('#tt').datagrid({  
                toolbar:[{  
                    text:'新建',  
                    iconCls:'icon-add',  
                    handler:function(){  
                        $('#tt').datagrid('endEdit', lastIndex);  
                        $('#tt').datagrid('appendRow',{  
                            itemid:'',  
                            productid:'',  
                            listprice:'',  
                            unitprice:'',  
                            attr1:'',  
                            status:'P'  
                        });  
                        lastIndex = $('#tt').datagrid('getRows').length-1;  
                        $('#tt').datagrid('selectRow', lastIndex);  
                        $('#tt').datagrid('beginEdit', lastIndex);  
                    }  
                },'-',{  
                    text:'删除',  
                    iconCls:'icon-remove',  
                    handler:function(){  
                        var row = $('#tt').datagrid('getSelected');  
                        if (row){  
                            var index = $('#tt').datagrid('getRowIndex', row);  
                            $('#tt').datagrid('deleteRow', index);  
                        }  
                    }  
                },'-',{  
                    text:'保存',  
                    iconCls:'icon-save',  
                    handler:function(){  
                        $('#tt').datagrid('acceptChanges');  
                    }  
                },'-',{  
                    text:'撤消',  
                    iconCls:'icon-undo',  
                    handler:function(){  
                        $('#tt').datagrid('rejectChanges');  
                    }  
                }
                /* ,'-',{  
                    text:'取得新建行数',  
                    iconCls:'icon-search',  
                    handler:function(){  
                        var rows = $('#tt').datagrid('getChanges');  
                        alert('新建: ' + rows.length + ' 行');  
                    }  
                } */
                ],  
                onBeforeLoad:function(){  
                    $(this).datagrid('rejectChanges');  
                },  
                onClickRow:function(rowIndex){  
                    if (lastIndex != rowIndex){  
                        $('#tt').datagrid('endEdit', lastIndex);  
                        $('#tt').datagrid('beginEdit', rowIndex);  
                    }  
                    lastIndex = rowIndex;  
                }  
            });  
        });  
        $(function(){  
            var lastIndex;  
            $('#tt2').datagrid({  
                toolbar:[{  
                    text:'新建',  
                    iconCls:'icon-add',  
                    handler:function(){  
                        $('#tt2').datagrid('endEdit', lastIndex);  
                        $('#tt2').datagrid('appendRow',{  
                            itemid:'',  
                            productid:'',  
                            listprice:'',  
                            unitprice:'',  
                            attr1:'',  
                            status:'P'  
                        });  
                        lastIndex = $('#tt2').datagrid('getRows').length-1;  
                        $('#tt2').datagrid('selectRow', lastIndex);  
                        $('#tt2').datagrid('beginEdit', lastIndex);  
                    }  
                },'-',{  
                    text:'删除',  
                    iconCls:'icon-remove',  
                    handler:function(){  
                        var row = $('#tt2').datagrid('getSelected');  
                        if (row){  
                            var index = $('#tt2').datagrid('getRowIndex', row);  
                            $('#tt2').datagrid('deleteRow', index);  
                        }  
                    }  
                },'-',{  
                    text:'保存',  
                    iconCls:'icon-save',  
                    handler:function(){  
                        $('#tt2').datagrid('acceptChanges');  
                    }  
                },'-',{  
                    text:'撤消',  
                    iconCls:'icon-undo',  
                    handler:function(){  
                        $('#tt2').datagrid('rejectChanges');  
                    }  
                }
                /* ,'-',{  
                    text:'取得新建行数',  
                    iconCls:'icon-search',  
                    handler:function(){  
                        var rows = $('#tt2').datagrid('getChanges');  
                        alert('新建: ' + rows.length + ' 行');  
                    }  
                } */
                ],  
                onBeforeLoad:function(){  
                    $(this).datagrid('rejectChanges');  
                },  
                onClickRow:function(rowIndex){  
                    if (lastIndex != rowIndex){  
                        $('#tt2').datagrid('endEdit', lastIndex);  
                        $('#tt2').datagrid('beginEdit', rowIndex);  
                    }  
                    lastIndex = rowIndex;  
                }  
            });  
        });  
    </script>
</head>
<body>
<div style="height: 100%; width: 100%; cellSpacing=0; cellPadding=0; ">
	
	<div id="toolbar" style="background-color: #F4F4F4">
    	<div>
	        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newLicense()">添加</a> -->
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="newLicense()">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="destroyLicense()">撤销</a>
    	</div>
    </div>
	
	<form class="easyui_form" id="form" method="post">
    	<table width="100%" toolbar="#toolbar">
    		<tr>
    			<td class="Fname">排污口编号:</td><td class="Fcontent"><input name="fcountyId" value="" class="easyui-validatebox"></td>
    			<td class="Fname">排污口名称:</td><td class="Fcontent"><input name="ftownId" value="" class="easyui-validatebox"></td>
    		</tr>
    		<tr>
    			<td class="Fname">废气排放执行标准:</td><td class="Fcontent"><input name="ftownId" value="" class="easyui-validatebox"></td>
    			<td class="Fname">年废气排放量限值(万标立方米/年):</td><td class="Fcontent"><input name="fcountyId" value="" class="easyui-validatebox"></td>
    		</tr>
    	</table>
    </form>
    
    <table id="tt" style="width:auto;height:auto"  
            data-options="iconCls:'icon-edit',singleSelect:true,idField:'itemid',url:'datagrid_data2.json'"  
            title="主要污染物">  
        <thead>  
            <tr>  
                <!-- <th data-options="field:'itemid',width:80">Item ID</th>  
                <th data-options="field:'productid',width:100,formatter:productFormatter,  
                        editor:{  
                            type:'combobox',  
                            options:{  
                                valueField:'productid',  
                                textField:'name',  
                                data:products,  
                                required:true  
                            }  
                        }">Product</th>  
                <th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>  
                <th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>  
                <th data-options="field:'attr1',width:250,editor:'text'">Attribute</th>  
                <th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>   -->
                <th data-options="field:'productid',width:250,formatter:productFormatter,  
                        editor:{  
                            type:'combobox',  
                            options:{  
                                valueField:'productid',  
                                textField:'name',  
                                data:products,  
                                required:true  
                            }  
                        }">主要污染物名称</th>  
                <th data-options="field:'attr1',width:250,editor:'text'">排放浓度限值(mg/L)</th>  
                <!-- <th data-options="field:'attr1',width:250,editor:'text'">废气排放量限值(吨/日)</th>  --> 
            </tr>  
        </thead>  
    </table>  
    
    <table id="tt2" style="width:auto;height:auto"  
            data-options="iconCls:'icon-edit',singleSelect:true,idField:'itemid',url:'datagrid_data2.json'"  
            title="有效期限内各年度污染物排放量限值（吨/年）">  
        <thead>  
            <tr>  
            	<th data-options="field:'attr1',width:200,editor:'text'">年度</th>  
            	<th data-options="field:'attr1',width:200,editor:'text'">污染物名称</th> 
            	<th data-options="field:'attr1',width:200,editor:'text'">排放限值</th> 
            </tr>  
        </thead>  
    </table> 
  
  	
</div>
</body>
</html>