<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/menu.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/curdtools.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/tools/css/common.css"></script>
<script type="text/javascript" src="<%=path%>/frame/js/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<title>刷卡式排污总量自动监控平台</title>
<style type="text/css">
html,body {
	margin: 0xp;
	padding: 0px;
	width: 100%;
	height: 100%;
}

.footer {
	width: 100%;
	text-align: center;
	line-height: 35px;
}

.panel layout-panel layout-panel-north {
	color: red;
}

</style>

</head>
<body class="easyui-layout">

	<div data-options="region:'north',split:false" style="height:58px;background: url('<%=path%>/frame/img/top1.jpg') repeat-x scroll 0 0 rgba(0, 0, 0, 0) ;">
		<table style="width: 100%;height: 100%;">
			<tr width="100%">
				<td width="150px;"><img alt="" src="<%=path%>/frame/img/logo.png" style="float: left; margin: auto 5px auto 50px;"></td>
				<td><span style="color: #15428B; float: left; font: bold 30px/50px '微软雅黑'; margin: auto 5px auto 50px;">刷 卡 式 排 污 总 量 自 动 监 控 平 台</span></td>
				<td width="250px;">
					<div style="margin-bottom: -15px;">
						<span ><font color="blue">[当前用户:] <a href="javascript:void(0);" onclick="openwindow('用户信息','<%=path%>/user/showUserInfo?userid=${userinfo.id}')">${userinfo.usercount }</a></font></span>
						<a href="javascript:void(0);" style="margin-left: 15px;margin-right: 10px;" onclick="add('修改密码','sys/initEditPassword?id=${userinfo.id}')">[修改密码]</a>
						<a href="javascript:void(0);" onclick="exit('<%=path%>/j_spring_security_logout','确定要退出系统吗？');">[退出]</a>
					</div>
				</td>
			</tr>
		</table>		
	</div>
	<div data-options="region:'south',split:false,border:true"
		style="overflow: hidden; height: 35px; background-color: #e0ecff;">
		<div class="footer" style="font-size: 13px;">
			Copyright © 2014 : <a href="http://www.szstep.com/cn/index.php"
				style="text-decoration: NONE; color: black;">世纪天源团队</a>
		</div>
	</div>

	<div data-options="region:'west',title:'菜单栏',split:true" style="width: 225px;">
		<div class="easyui-accordion" fit="true" border="false">
			<%-- <div title="污染源地图定位" data-options="iconCls:'icon-save'"
				style="overflow: auto;">
				<ul id="menuTree" class="easyui-tree">

					<li><span><a target="mainFrame"
							onclick="addTab('污染源定位','<%=path%>/map/initToMapForWry')">污染源地图展示</a></span><>

				</ul>
			</div> --%>
			<div title="企业信息管理" data-options="iconCls:'icon-save'"
				style="overflow: auto;">
				<ul id="menuTree" class="easyui-tree">
				<sec:authorize url="/wryjbxxController/jumpToWryList">
					<li><span><a target="mainFrame"
							onclick="addTab('污染源基本信息','<%=path%>/wryjbxxController/jumpToWryList')">污染源基本信息</a></span></li>
				</sec:authorize>
				</ul>
			</div>
		<%--
			<div title="排污许可证管理" data-options="iconCls:'icon-save'"
				style="overflow: auto;">
				<ul id="menuTree" class="easyui-tree">

					<li><span><a target="mainFrame"
							onclick="addTab('排污许可证查询','<%=path%>/outPermit/list_init')">污染物排放许可证</a></span></li>

				</ul>
			</div>
			 --%>
			
			<div title="污染物排放电子卡" data-options="iconCls:'icon-save'"
				style="overflow: auto;">
				<ul id="menuTree" class="easyui-tree">
				<sec:authorize url="/card/initToCardManage">
					<li><span><a target="mainFrame"
							onclick="addTab('IC卡管理','<%=path%>/card/initToCardManage')">IC卡管理</a></span></li>
				</sec:authorize>
				<sec:authorize url="/card/recharge/list_init">
					<li><span><a target="mainFrame"
							onclick="addTab('IC卡充值','<%=path%>/card/recharge/list_init')">IC卡充值</a></span></li>
				</sec:authorize>
				
				<sec:authorize url="/card/recharge/chaxun">
					<li><span><a target="mainFrame"
							onclick="addTab('刷卡查询','<%=path%>/card/recharge/chaxun')">刷卡查询</a></span></li>
				</sec:authorize>
				</ul>
			</div>
			<div title="监控数据统计" data-options="iconCls:'icon-save'"
				style="overflow: auto;">
				<ul class="easyui-tree">
					<li><span>废水实时数据监控</span>
						<ul>
							<sec:authorize url="/realtime/initDayDataToList">
							<li><span><a target="mainFrame"
									onclick="addTab('废水实时数据显示','<%=path%>/realTimeDataController/getWry')">废水实时数据显示</a></span></li>
							</sec:authorize>
							<sec:authorize url="/realtime/initRealtimeDataToList">	
								<li><span><a target="mainFrame"
									onclick="addTab('废水实时数据历史查询','<%=path%>/realtime/initRealtimeDataToList')">废水实时数据历史查询</a></span></li>
							</sec:authorize>
						</ul>
						
					</li>
				</ul>
				<ul class="easyui-tree">
					<li><span>废水报表统计</span>
						<ul>
							<sec:authorize url="/realtime/initDayDataToList">
							<li><span><a target="mainFrame"
									onclick="addTab('日报表','<%=path%>/realtime/initDayDataToList')">日报表</a></span></li>
							</sec:authorize>
							<sec:authorize url="/realtime/initMonthDataToList">
							<li><span><a target="mainFrame"
									onclick="addTab('月报表','<%=path%>/realtime/initMonthDataToList')">月报表</a></span></li>
							</sec:authorize>
							<sec:authorize url="/realtime/initQuarterDataToList">
							<li><span><a target="mainFrame"
									onclick="addTab('季报表','<%=path%>/realtime/initQuarterDataToList')">季报表</a></span></li>
							</sec:authorize>
							<sec:authorize url="/realtime/initYearDataToList">
							<li><span><a target="mainFrame"
									onclick="addTab('年报表','<%=path%>/realtime/initYearDataToList')">年报表</a></span></li>
							</sec:authorize>
							<sec:authorize url="/report/initDayReportToList">
							<li><span><a target="mainFrame"
									onclick="addTab('日图表统计','<%=path%>/report/initDayReportToList')">日图表统计</a></span></li>
							</sec:authorize>
							<%-- <li><span><a target="mainFrame"
									onclick="addTab('日图表统计','<%=path%>/report/initDayReportToList')">日图表统计</a></span></li> --%>
							<sec:authorize url="/report/initMonthReportToList">
							<li><span><a target="mainFrame"
									onclick="addTab('月图表统计','<%=path%>/report/initMonthReportToList')">月图表统计</a></span></li>
							</sec:authorize>
							
							<sec:authorize url="/report/initQuarterReportToList">
							<li><span><a target="mainFrame"
									onclick="addTab('季图表统计','<%=path%>/report/initQuarterReportToList')">季图表统计</a></span></li>
							</sec:authorize>
							<sec:authorize url="/report/initYearReportToList">
							<li><span><a target="mainFrame"
									onclick="addTab('年图表统计','<%=path%>/report/initYearReportToList')">年图表统计</a></span></li>
							</sec:authorize>
						</ul></li>
				</ul>
				<ul class="easyui-tree">
					<li><span>废气监测数据</span>
						<sec:authorize url="/gas/initJumpToGasRealtimeList">	
						<ul>
							<li><span><a target="mainFrame"
									onclick="addTab('废气实时数据显示','<%=path%>/gas/initJumpToGasRealtimeList')">废气实时数据显示</a></span></li>
						</ul>
						</sec:authorize>
						<sec:authorize url="/gas/initRealtimeHisDataToList">
						<ul>
							<li><span><a target="mainFrame"
									onclick="addTab('废气历史数据查询','<%=path%>/gas/initRealtimeHisDataToList')">废气历史数据查询</a></span></li>
						</ul> 
						</sec:authorize>
						<sec:authorize url="/gas/initJumpToDayStatistic">	
						<ul>
							<li><span><a target="mainFrame"
									onclick="addTab('废气日报表','<%=path%>/gas/initJumpToDayStatistic')">废气日报表</a></span></li>
						</ul>
						</sec:authorize>
						<sec:authorize url="/gas/initJumpToMonthStatistic">		
						<ul>
							<li><span><a target="mainFrame"
									onclick="addTab('废气月报表','<%=path%>/gas/initJumpToMonthStatistic')">废气月报表</a></span></li>
						</ul>
						</sec:authorize>
						<sec:authorize url="/gas/initJumpToQuarterStatistic">	
						<ul>
							<li><span><a target="mainFrame"
									onclick="addTab('废气季报表','<%=path%>/gas/initJumpToQuarterStatistic')">废气季报表</a></span></li>
						</ul>
						</sec:authorize>
						<sec:authorize url="/gas/initJumpToYearStatistic">	
						<ul>
							<li><span><a target="mainFrame"
									onclick="addTab('废气年报表','<%=path%>/gas/initJumpToYearStatistic')">废气年报表</a></span></li>
						</ul>		
						</sec:authorize>
						
					</li>
				</ul>
				<ul class="easyui-tree">
					<li><span>报警管理</span>
						<ul>
							<sec:authorize url="/alerm/initWarningLogToList">
							<li><span><a target="mainFrame"
									onclick="addTab('报警查询','<%=path%>/alerm/initWarningLogToList')">报警查询</a></span></li>
							</sec:authorize>
						</ul></li>
				</ul>
				
				<ul class="easyui-tree">
					<li><span>脱机管理</span>
						<ul>
							<sec:authorize url="/offline/initOfflineLogToList">
							<li><span><a target="mainFrame"
									onclick="addTab('脱机日志查询','<%=path%>/offline/initOfflineLogToList')">脱机日志查询</a></span></li>
							</sec:authorize>
						</ul></li>
				</ul>
			</div>
			<div title="系统设置" data-options="iconCls:'icon-save'"
				style="overflow: auto;">
				<ul class="easyui-tree">
							<sec:authorize url="/user/initUserToList1">															
							<li><span><a target="mainFrame"
									onclick="addTab('用户管理','<%=path%>/user/initUserToList1')">用户管理</a></span></li>
							</sec:authorize>
							<sec:authorize url="/role/initRoleToList">
							<li><span><a target="mainFrame"
									onclick="addTab('角色管理','<%=path%>/role/initRoleToList')">角色管理</a></span></li>
							</sec:authorize>
							<sec:authorize url="/menu/initMenuToList">
							<li><span><a target="mainFrame"
									onclick="addTab('菜单管理','<%=path%>/menu/initMenuToList')">菜单管理</a></span></li>
							</sec:authorize>
							<sec:authorize url="/authority/initAuthorityToList">
							<li><span><a target="mainFrame"
									onclick="addTab('权限管理','<%=path%>/authority/initAuthorityToList')">权限管理</a></span></li>
							</sec:authorize>
							<sec:authorize url="/job/initJobToList">
							<li><span><a target="mainFrame"
									onclick="addTab('职位管理','<%=path%>/job/initJobToList')">职位管理</a></span></li>
							</sec:authorize>
							<sec:authorize url="/orgunit/initOrgUnitToList">
							<li><span><a target="mainFrame"
									onclick="addTab('组织机构管理','<%=path%>/orgunit/initOrgUnitToList')">组织机构管理</a></span></li>
							</sec:authorize>
							<sec:authorize url="/log/initLogToList">
							<li><span><a target="mainFrame"
									onclick="addTab('系统日志','<%=path%>/log/initLogToList')">系统日志</a></span></li>
							</sec:authorize>
							<sec:authorize url="/alerm/initWarningSetupToList">
							<li><span><a target="mainFrame"
									onclick="addTab('报警设置','<%=path%>/alerm/initWarningSetupToList')">报警设置</a></span></li>
							</sec:authorize>
							<sec:authorize url="/sys/smsTemplete/list_init">		
							<li><span><a target="mainFrame"
									onclick="addTab('短信模板','<%=path%>/sys/smsTemplete/list_init')">短信模板</a></span></li>
							</sec:authorize>
						
				</ul>
			</div>

		</div>
	</div>


	<div id="mainPanle" data-options="region:'center'"
		style="padding: 5px; background: #eee; padding: 0px;">
		<div id="tabs" class="easyui-tabs" data-options="closable:true"
			fit="true" border="false">
			<div title="首页">
				<iframe id="mainFrame" name="mainFrame" this.style.height=mainFrame.document.body.scrollHeight
					style="width: 100%; height: 100%;" scrolling="no" frameborder="0"
					src="<%=path%>/map/initToMapForWry"></iframe>
			</div>
		</div>
	</div>	
	<!-- tabs右键事件 -->
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="refresh">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
</body>
</html>