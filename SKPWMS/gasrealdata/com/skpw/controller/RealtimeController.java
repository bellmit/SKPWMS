package com.skpw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.RealtimeBean;
import com.skpw.bean.TSysUserInfo;
import com.skpw.common.Page;
import com.skpw.service.RealtimeService;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.WryjbxxService;

@Controller
public class RealtimeController {

	@Resource
	private RealtimeService realtimeService;
	
	@Resource
	private WryjbxxService wryjbxxService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;

	@RequestMapping("gas/initJumpToGasRealtimeList")
	public String initJumpToGasRealtimeList(Model model) {

		return "gasrealtime/realTimeData";
	}

	@RequestMapping("gas/showGasRealtime")
	@ResponseBody
	public Map showGasRealtime(Model model,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		long count = realtimeService.realTimeCount();
		Page page = new Page(pageNum, numPerPage, count);

		List<RealtimeBean> realTimeList = realtimeService.showRealtimeByPage(pageNum,numPerPage);

		map.put("total", count);
		map.put("rows", realTimeList);
		return map;
	}

	@RequestMapping("gas/showGasRealtimenew")
	@ResponseBody
	public Map showGasRealtimenew(Model model,HttpServletRequest request,String wry_id,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		long count = 0;
		List<RealtimeBean> realTimeList = new ArrayList<RealtimeBean>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
		
		if(null != wry_id && !"".equals(wry_id.trim())) {
			List<String> enids = new ArrayList<String>();
			enids.add(wry_id);
			count = realtimeService.realTimeCountnew(enids);
			Page page = new Page(pageNum, numPerPage, count);
			realTimeList = realtimeService.showRealtimeByPagenew(pageNum,numPerPage,enids);
		}
		else
		if(null != l1 && l1.size() > 0) {
			String enterid = l1.get(0).get("enterid").toString();
			if(enterid != null && !"".equals(enterid.trim())) {
				List<String> enids = new ArrayList<String>();
				enids.add(enterid);
				count = realtimeService.realTimeCountnew(enids);
				Page page = new Page(pageNum, numPerPage, count);
				realTimeList = realtimeService.showRealtimeByPagenew(pageNum,numPerPage,enids);
			}
		} else {
			List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
			if(null != longcodelist && longcodelist.size() > 0) {
				List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
				if(null != orgidList && orgidList.size() > 0) {
					List<String> enids = wryjbxxService.findenterIdsByzzjgid(orgidList);
					if(null != enids && enids.size() > 0) {
						count = realtimeService.realTimeCountnew(enids);
						Page page = new Page(pageNum, numPerPage, count);
						realTimeList = realtimeService.showRealtimeByPagenew(pageNum,numPerPage,enids);
					}
				}
				
			}
		}
		
		map.put("total", count);
		map.put("rows", realTimeList);
		return map;
	}
}
