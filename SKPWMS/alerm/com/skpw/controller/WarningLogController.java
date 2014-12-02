package com.skpw.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.skpw.bean.TSysUserInfo;
import com.skpw.bean.WarningLog;
import com.skpw.common.Page;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.WarningLogService;
import com.skpw.service.WarningLogSpecs;

@Controller
public class WarningLogController {
	@Resource
	private WarningLogService warningLogService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;

	// 跳转到报警实时界面
	@RequestMapping("alerm/initWarningLogToList")
	public String initWarningLogToList(Model model) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = calendar.getTime();
		String endTime = sdf.format(date);
		Calendar calendar1 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		calendar1.add(calendar1.DATE, -1);
		String startTime = sdf.format(calendar1.getTime());
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);

		return "alerm/warninglog_list";

	}

	@RequestMapping("alerm/showWarningLog")
	@ResponseBody
	public Map showWarningLog(WarningLog warningLog,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage,
			HttpServletRequest request) {
		
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = 0;
		List warningLogs = new ArrayList();
		
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
		if(null != l1 && l1.size() > 0) {
			String enterid = l1.get(0).get("enterid").toString();
			if(enterid != null && !"".equals(enterid.trim())) {
				TBasEnterprise tbe3 = new TBasEnterprise();
				tbe3.setFenterId(enterid);
				warningLog.setEnterprise(tbe3);
				count = warningLogService.count(WarningLogSpecs.queryCondition(warningLog, start_time,
						end_time));
				Page page = new Page(pageNum, numPerPage, count);
				warningLogs = warningLogService.showWarningLognew(page.getCurrentPage()-1,
						numPerPage,start_time,end_time,enterid,null);
				
			}
		} else {
			List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
			if(null != longcodelist && longcodelist.size() > 0) {
				List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
				if(null != orgidList && orgidList.size() > 0) {
					TBasEnterprise tbe6 = new TBasEnterprise();
					tbe6.setFlcList(orgidList);
					warningLog.setEnterprise(tbe6);
					count = warningLogService.count(WarningLogSpecs.queryCondition(warningLog, start_time,
							end_time));
					Page page = new Page(pageNum, numPerPage, count);
					warningLogs = warningLogService.showWarningLognew(page.getCurrentPage()-1,
							numPerPage,start_time,end_time,null,orgidList);
				}
				
			}
		}
		
		map.put("total", count);
		map.put("rows", warningLogs);
		return map;
	}

	// 条件查询显示所有
	@RequestMapping("alerm/showWarningLogByCondition")
	@ResponseBody
	public Map showWarningLogByCondition(WarningLog warningLog,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage,
			HttpServletRequest request) {

		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = warningLogService.count(WarningLogSpecs.queryCondition(warningLog, start_time,
				end_time));
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<WarningLog> warningLogs = warningLogService
				.showWarningLogByCondition(
						pageRequest,
						WarningLogSpecs.queryCondition(warningLog, start_time,
								end_time)).getContent();

		map.put("total", count);
		map.put("rows", warningLogs);
		return map;

	}

}
