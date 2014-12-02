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

import com.skpw.service.QuarterReportService;
import com.skpw.service.TTcControlerService;

@Controller
public class QuarterReportController {

	@Resource
	private TTcControlerService tTcControlerService;

	@Resource
	private QuarterReportService quarterReportService;

	@RequestMapping("/report/initQuarterReportToList")
	public String initQuarterReportToList(Model model) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = calendar.getTime();
		String statisticime = sdf.format(date);
		model.addAttribute("statisticime", statisticime);
		return "report/quarterchart_list";
	}

	@RequestMapping("/report/statisticQuarterReport")
	@ResponseBody
	public Map statisticQuarterReport(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		String ttcid = request.getParameter("ttcid");
		String time_static = request.getParameter("time_static");
		String paramtype = request.getParameter("paramtype");
		List datalist = quarterReportService.statisticQuarterReport(
				Integer.valueOf(ttcid), time_static, paramtype);
		map.put("datalist", datalist);
		return map;
	}

}
