package com.skpw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TSysLog;
import com.skpw.common.Page;
import com.skpw.service.LogService;
import com.skpw.service.LogSpecs;

@Controller
public class LogController {

	@Resource
	private LogService logService;

	// 跳转到日志登陆界面
	@RequestMapping("/log/initLogToList")
	public String initLogToList(Model model) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = calendar.getTime();
		String endtime = sdf.format(date);

		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date date1 = calendar.getTime();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String starttime = sdf1.format(date1);
		
		model.addAttribute("endtime", endtime);
		model.addAttribute("starttime", starttime);

		return "sys/log_list";
	}

	@RequestMapping("/log/showLogList")
	@ResponseBody
	public List showLogList() {

		List<TSysLog> logs = logService.showLogList();

		return logs;
	}

	@RequestMapping("/log/showLogListByCondition")
	@ResponseBody
	public Map showLogListByCondition(TSysLog log,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage,
			HttpServletRequest request) {
		String start_time=request.getParameter("start_time");
		String end_time=request.getParameter("end_time");
		Map<String, Object> map=new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		long count = logService.count(LogSpecs.queryCondition(log,start_time,end_time));
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		
		List<TSysLog> logs =logService.showLogListByCondition(LogSpecs.queryCondition(log,start_time,end_time), pageRequest).getContent();
		map.put("total", count);
		map.put("rows", logs);
		return map;
	}
}
