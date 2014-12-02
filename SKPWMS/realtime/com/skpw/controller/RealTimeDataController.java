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
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TSysUserInfo;
import com.skpw.service.RealTimeDataService;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.WryjbxxService;

@Controller
public class RealTimeDataController {

	@Resource
	private RealTimeDataService realTimeDataService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;
	
	@Resource
	private WryjbxxService wryjbxxService;
	
	//查询
	@RequestMapping("/realTimeDataController/findRealtimeData")
	public @ResponseBody Map<String , Object> findRealtimeData(int page, int rows, String s_wry,HttpServletRequest request) {
		//Map<String, Object> map = realTimeDataService.findRealtimeData(page-1, rows, s_wry);
		Map<String, Object> map = new HashMap<String, Object>();
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
		if(null != s_wry && !"".equals(s_wry.trim())) {
			map = realTimeDataService.findRealtimeDatanew(page-1, rows, s_wry,null);
		}
		else
		if(null != l1 && l1.size() > 0) {
			String enterid = l1.get(0).get("enterid").toString();
			if(enterid != null && !"".equals(enterid.trim())) {
				map = realTimeDataService.findRealtimeDatanew(page-1, rows, enterid,null);				
			}
		} else {
			List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
			if(null != longcodelist && longcodelist.size() > 0) {
				List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
				if(null != orgidList && orgidList.size() > 0) {
					map = realTimeDataService.findRealtimeDatanew(page-1, rows, null,orgidList);
				}
				
			}
		}
		return map;
	}
	
	@RequestMapping("/realTimeDataController/getWry")
	public String getWry(Model model,HttpServletRequest request){
		//List<TBasEnterprise> list = realTimeDataService.findWry();
		List<TBasEnterprise> enterList = new ArrayList<TBasEnterprise>();
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
		if(null != l1 && l1.size() > 0) {
			String enterid = l1.get(0).get("enterid").toString();
			if(enterid != null && !"".equals(enterid.trim())) {
				List<TBasEnterprise> l3 = wryjbxxService.findAll();
				if(null != l3 && l3.size() > 0) {
					for(int i=0; i<l3.size(); i++) {
						if(enterid.equals(l3.get(i).getFenterId().trim())) {
							enterList.add(l3.get(i));
						}
					}
				}
			}
		} else {
			List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
			if(null != longcodelist && longcodelist.size() > 0) {
				List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
				if(null != orgidList && orgidList.size() > 0) {
					enterList = wryjbxxService.findqylistByzzjgid(orgidList);
				}
				
			}
		}
		
		model.addAttribute("epList", enterList);
		return "realtime/realTimeData";
	}
	
}
