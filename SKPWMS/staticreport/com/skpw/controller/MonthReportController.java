package com.skpw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.service.MonthReportService;
import com.skpw.service.TTcControlerService;

/**
 * @author hjy 月图形报表统计
 */
@Controller
public class MonthReportController {

	@Resource
	private TTcControlerService tTcControlerService;

	@Resource
	private MonthReportService monthReportService;

//	@RequestMapping("/report/initMonthReportToList")
//	public String initDayReportToList(Model model) {
//
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//		Date date = calendar.getTime();
//		String statisticime = sdf.format(date);
//		model.addAttribute("statisticime", statisticime);
//		return "report/monthchart_list";
//	}
//
//	@RequestMapping("/report/statisticMonthReport")
//	@ResponseBody
//	public Map statisticMonthReport(HttpServletRequest request, Model model) {
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		String ttcid = request.getParameter("ttcid");
//		String time_static = request.getParameter("time_static");
//
//		// String ttcfid = tTcControlerService.findtccontrollerById(ttcid)
//		// .getFid();
//		List datalist = monthReportService.statisticMonthReport(
//				Integer.valueOf(ttcid), time_static);
//
//		List xdatalist = monthReportService.getXData(Integer.valueOf(ttcid),
//				time_static);
//		map.put("datalist", datalist);
//		map.put("xdatalist", xdatalist);
//		return map;
//	}
	
	@RequestMapping("/report/initMonthReportToList")
	public String initDayReportToList(Model model) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = calendar.getTime();
		String statisticime = sdf.format(date);
		model.addAttribute("statisticime", statisticime);
		return "report/monthchart_list";
	}
	
	@RequestMapping("/report/statisticMonthReport")
	@ResponseBody
	public Map statisticMonthReport(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		String ttcid = request.getParameter("ttcid");
		String time_static = request.getParameter("time_static");
		String paramtype=request.getParameter("paramtype");
		List  datalist1=monthReportService.statisticComplexMonthReport(Integer.valueOf(ttcid), time_static,paramtype);
		List xdatalist = monthReportService.getXData(Integer.valueOf(ttcid),
				time_static);
		map.put("paramtype", paramtype);
		map.put("xdatalist", xdatalist);
		map.put("datalist1", datalist1);
		
		return map;
	}
}
