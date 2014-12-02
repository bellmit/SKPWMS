package com.skpw.hisquery.conrtoller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TSysUserInfo;
import com.skpw.hisquery.bean.RealTimeHisData;
import com.skpw.hisquery.service.RealtimeHisDataService;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.WryjbxxService;

@Controller
public class RealtimeHisDataController {

	@Resource
	private WryjbxxService wryjbxxService;

	@Resource
	private RealtimeHisDataService realtimeHisDataService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;

	@RequestMapping("realtime/initRealtimeDataToList")
	public String initRealtimeDataToList(Model model,HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = calendar.getTime();
		String endtime = sdf.format(date);

		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date date1 = calendar.getTime();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String starttime = sdf1.format(date1);

		//List<TBasEnterprise> enterList = wryjbxxService.findAll();
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
		
		model.addAttribute("endtime", endtime);
		model.addAttribute("starttime", starttime);
		model.addAttribute("enterList", enterList);
		return "realtime/realtimeHisData";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("realtime/queryRealtimeDataList")
	@ResponseBody
	public Map queryRealtimeDataList(
			@RequestParam(value = "page", required = false) int currentPage,
			@RequestParam(value = "rows", required = false) int pageSize,
			String starttime, String endtime, String fid) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<RealTimeHisData> realhisDataList = new ArrayList<RealTimeHisData>();
		long count = realtimeHisDataService.realtimeDataCount(starttime,
				endtime, Integer.valueOf(fid));
		realhisDataList = realtimeHisDataService.queryRealtimeHis(starttime,
				endtime, Integer.valueOf(fid), currentPage, pageSize);
		map.put("total", count);
		map.put("rows", realhisDataList);
		return map;
	}
}
