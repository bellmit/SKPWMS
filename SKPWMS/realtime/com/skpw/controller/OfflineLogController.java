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

import com.skpw.bean.OfflineLog;
import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TSysUserInfo;
import com.skpw.common.Page;
import com.skpw.service.OfflineLogService;
import com.skpw.service.OfflineLogSpecs;
import com.skpw.service.TBasEnterPollService;

/**
 * @author hjy 脱机日志
 */
@Controller
public class OfflineLogController {

	@Resource
	private OfflineLogService offlineLogService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;

	// 跳转到脱机日志界面
	@RequestMapping("/offline/initOfflineLogToList")
	public String initOfflineLogToList(Model model) {
		
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

		return "realtime/offline_list";
	}

	@RequestMapping("/offline/showOfflineLogList")
	@ResponseBody
	public List showOfflineLogList() {

		List<OfflineLog> logs = offlineLogService.showOfflineLog();

		return logs;
	}

	@RequestMapping("/log/showOfflineLogListByCondition")
	@ResponseBody
	public Map showOfflineLogListByCondition(OfflineLog offlineLog,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		
		String start_time=request.getParameter("start_time");
		String end_time=request.getParameter("end_time");
		//long count = offlineLogService.count(OfflineLogSpecs.queryCondition(offlineLog,start_time,end_time));
		long count = 0;
		List<OfflineLog> offlineLogs = new ArrayList<OfflineLog>();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,page.getPageSize());
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
		if(null != l1 && l1.size() > 0) {
			String enterid = l1.get(0).get("enterid").toString();
			if(enterid != null && !"".equals(enterid.trim())) {
				if(null == offlineLog.getEnterprise()) {
					offlineLog.setEnterprise(new TBasEnterprise());
				}
				offlineLog.getEnterprise().setFenterId(enterid);
				org.springframework.data.domain.Page<OfflineLog> p = offlineLogService.showOfflineLogByCondition(OfflineLogSpecs.queryCondition(offlineLog,start_time,end_time), pageRequest);
				offlineLogs = p.getContent();
				count = p.getTotalElements();
			}
		} else {
			List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
			if(null != longcodelist && longcodelist.size() > 0) {
				List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
				if(null != orgidList && orgidList.size() > 0) {
					if(null == offlineLog.getEnterprise()) {
						offlineLog.setEnterprise(new TBasEnterprise());
					}
					offlineLog.getEnterprise().setFlcList(orgidList);
					org.springframework.data.domain.Page<OfflineLog> p = offlineLogService.showOfflineLogByCondition(OfflineLogSpecs.queryCondition(offlineLog,start_time,end_time), pageRequest);
					offlineLogs = p.getContent();
					count = p.getTotalElements();
				}
				
			}
		}
		
		map.put("total", count);
		map.put("rows", offlineLogs);
		return map;
	}

}
