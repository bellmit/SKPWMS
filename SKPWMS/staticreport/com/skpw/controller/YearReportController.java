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

import com.skpw.service.TTcControlerService;
import com.skpw.service.YearReportService;

/**
 * @author hjy 年图形报表统计
 */
@Controller
public class YearReportController {

	@Resource
	private TTcControlerService tTcControlerService;

	@Resource
	private YearReportService yearReportService;

	@RequestMapping("/report/initYearReportToList")
	public String initYearReportToList(Model model) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = calendar.getTime();
		String statisticime = sdf.format(date);
		model.addAttribute("statisticime", statisticime);
		return "report/yearchart_list";
	}

	@RequestMapping("/report/statisticYearReport")
	@ResponseBody
	public Map statisticYearReport(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		String ttcid = request.getParameter("ttcid");
		String time_static = request.getParameter("time_static");
		String paramtype = request.getParameter("paramtype");

		List datalist = yearReportService.statisticYearReport(
				Integer.valueOf(ttcid), time_static, paramtype);

		map.put("datalist", datalist);

		return map;
	}

}
