<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>污染源地图显示</title>
<style type="text/css">
.anchorBL{  
       display:none;  
   }

html {
	height: 100%
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#container {
	height: 100%
}
td.a{ 
border-left:1px solid #C1DAD7; 
border-top:1px solid #C1DAD7; 
border-right: 1px solid #C1DAD7; 
border-bottom: 1px solid #C1DAD7; 
background: #fff; 
font-size:13px; 
padding: 6px 6px 6px 12px; 
color: #4f6b72; 
position: relative;
align:"center";
}
</style>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.5&ak=Dm4Zn7wbCYG41tKbj9D9EOZc"></script>
<script type="text/javascript">
	
	//重置Datagrid查询条件
	function resetDatagridSearch() {
		$("#fenterName").val("");

	}
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height: 50px">
		<form style="margin-top: 10px"  action="<%=path%>/map/initToMapForWry" method="post">
			<table>
				<tr>
					<td >企业名称：</td>
					<td ><input id="fenterName" name="fenterName"  value="${fentername}"
						class="easyui-validatebox" /></td>

					<td>
					<button class="easyui-linkbutton" type="submit" iconCls="icon-search">查询</button>
					<td><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-reload"
						onclick="resetDatagridSearch()">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'">
	<div id="container"></div>	
	</div>
	<script type="text/javascript">
		var map = new BMap.Map("container"); // 创建地图实例  
		var point = new BMap.Point(114.052757, 22.654278); // 创建点坐标  
		map.centerAndZoom(point, 12); // 初始化地图，设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		map.setCurrentCity("深圳市");          // 设置地图显示的城市 此项是必须设置的
		//添加缩放控件  
	    map.addControl(new BMap.NavigationControl());    
	    map.addControl(new BMap.ScaleControl());    
	    map.addControl(new BMap.OverviewMapControl());
	    
	    <c:forEach items="${wryList }" var="wryList">
	    var jd=${wryList.flongitude};	    
	    var wd=${wryList.flatitude};
	    var imgurl = "<%=path%>/frame/img/mapgreen.png";
	    if('${wryList.faState}'==1) {
	    	imgurl = "<%=path%>/frame/img/mapred.png";
	    }
	    var icon = new BMap.Icon(imgurl, new BMap.Size(21, 36), {
	    	anchor: new BMap.Size(6, 6)
	    });
	    var marker = new BMap.Marker(new BMap.Point(jd, wd),{
	    	icon : icon
	    });
	    map.addOverlay(marker);
	    setTimeout(function(){
			map.panTo(new BMap.Point(jd,wd));
		}, 2000);
	  	//创建信息窗口
	    marker.addEventListener("click", function(){
	    	
	    	var codFASStr = "${wryList.codFAS}";
	    	var codCStr = "${wryList.codC}";
	    	if(codFASStr.indexOf("cons=1") != -1) {
	    		codCStr = "<span style='color:red'>${wryList.codC}</span>";
	    	}
	    	var codSStr = "${wryList.codS}";
	    	if(codFASStr.indexOf("Surplus=1") != -1) {
	    		codSStr = "<span style='color:red'>${wryList.codS}</span>";
	    	}
	    	
	    	
	    	var nh3FASStr = "${wryList.nh3FAS}";
	    	var nh3CStr = "${wryList.nh3C}";
	    	if(nh3FASStr.indexOf("cons=1") != -1) {
	    		nh3CStr = "<span style='color:red'>${wryList.nh3C}</span>";
	    	}
	    	var nh3SStr = "${wryList.nh3S}";
	    	if(nh3FASStr.indexOf("Surplus=1") != -1) {
	    		nh3SStr = "<span style='color:red'>${wryList.nh3S}</span>";
	    	}
	    	
	    	var html_str = "<div style='height:360px;overflow-x: hidden;overflow-y:auto'>";
	    	var fpollTypeCode_str = "${wryList.fpollTypeCode}";
	    	if(fpollTypeCode_str && fpollTypeCode_str=="B01") {
	    		html_str = "  <table width=400 height=131  align='center'>" +
	  			"  <tr class='DHBT'> <td class='a' align='right'>企业名称</td>"+
	  		    "  <td class='a' align='center'> ${wryList.wry}</td> "+ 
	  		    "  </tr>" +
	  		  	" <tr class='DHBT'>"+
	  		    "  <td class='a' align='right'>监测时间</td>" +
	  		    "  <td class='a' align='center'>${wryList.time}</td>"+
	  		    " </tr>" +
	  		    " <tr class='DHBT'>"+
	  		    "  <td class='a' align='right'>COD浓度</td>" +
	  		    "  <td class='a' align='center'>"+codCStr+"</br>(mg/L)</td>"+
	  		    " </tr>" +
	  		    " <tr class='DHBT'>"+
	  		    "  <td class='a' align='right'>COD年度累计排放量</td>" +
	  		    "  <td class='a' align='center'>${wryList.codD}</br>(kg)</td>"+
	  		    " </tr>" +
	  		    " <tr class='DHBT'>"+
	  		    "  <td class='a' align='right'>COD余量</td>" +
	  		    "  <td class='a' align='center'>"+codSStr+"</br>(t)</td>"+
	  		    " </tr>" +
	  		  	" <tr class='DHBT' >"+
	  		    "  <td class='a' align='right'>NH3-N浓度</td>" +
	  		    "  <td class='a' align='center'>"+nh3CStr+"</br>(mg/L)</td>"+
	  		    " </tr>" +
	  		 	" <tr class='DHBT'>"+
	  		    "  <td class='a' align='right'>NH3-N年度累计排放量</td>" +
	  		    "  <td class='a' align='center'>${wryList.nh3D}</br>(kg)</td>"+
	  		    " </tr>" +
	  		  	" <tr class='DHBT'>"+
	  		    "  <td class='a' align='right'>NH3-N余量</td>" +
	  		    "  <td class='a' align='center'>"+nh3SStr+"</br>(t)</td>"+
	  		    " </tr>" +
	  		  	" <tr class='DHBT'>"+
	  		    "  <td class='a' align='right'>实时流量</td>" +
	  		    "  <td class='a' align='center'>${wryList.flow}</br>(L/s)</td>"+
	  		    " </tr>" +
	  		    " </table>";
	    	} else {
	    		html_str += "  <table width=400 height=131  align='center'>" +
				"  <tr class='DHBT'> <td class='a' align='right'>企业名称</td>"+
			    "  <td class='a' colspan='2' align='center'> ${wryList.wry}</td> "+ 
			    "  </tr>" ;
			    
			    if("${wryList.fqlist}") {
			    	<c:forEach items="${wryList.fqlist}" var="fqjclist">
			    	
			    	var o2FASStr = "${fqjclist.o2FAS}";
			    	var o2RtdStr = "${fqjclist.o2Rtd}";
			    	if(o2FASStr.indexOf("cons=1") != -1) {
			    		o2RtdStr = "<span style='color:red'>${fqjclist.o2Rtd}</span>";
			    	}
			    	
			    	var o1FASStr = "${fqjclist.o1FAS}";
			    	var o1RtdStr = "${fqjclist.o1Rtd}";
			    	if(o1FASStr.indexOf("cons=1") != -1) {
			    		o1RtdStr = "<span style='color:red'>${fqjclist.o1Rtd}</span>";
			    	}
			    	
			    	var o3FASStr = "${fqjclist.o3FAS}";
			    	var o3RtdStr = "${fqjclist.o3Rtd}";
			    	if(o3FASStr.indexOf("cons=1") != -1) {
			    		o3RtdStr = "<span style='color:red'>${fqjclist.o3Rtd}</span>";
			    	}
			    	
			    	
			    	html_str += " <tr class='DHBT'>"+
				  	"  <td class='a' rowspan='4' align='right'>${fqjclist.pfkname}</td>" +
				    "  <td class='a' align='right'>监测时间</td>" +
				    "  <td class='a' colspan='2' align='center'>${fqjclist.monitertime}</td>"+
				    " </tr>" +
				    " <tr class='DHBT'>"+
				    "  <td class='a' align='right'>实测SO2</td>" +
				    "  <td class='a' align='center'>"+o2RtdStr+"</br>(mg/m<sup>2</sup>)</td>"+
				    " </tr>" +
				    " <tr class='DHBT'>"+
				    "  <td class='a' align='right'>实测烟尘</td>" +
				    "  <td class='a' align='center'>"+o1RtdStr+"</br>(mg/m<sup>2</sup>)</td>"+
				    " </tr>" +
				    " <tr class='DHBT'>"+
				    "  <td class='a' align='right'>实测NOX</td>" +
				    "  <td class='a' align='center'>"+o3RtdStr+"</br>(mg/m<sup>2</sup>)</td>"+
				    " </tr>" ;
			    	</c:forEach> 
			    }
			    
			    html_str += " </table></div>";
	    	}
  		    
	    	this.openInfoWindow(new BMap.InfoWindow(html_str,{enableMessage:false}));});
	  	
	    </c:forEach> 
	</script>
</body>
</html>