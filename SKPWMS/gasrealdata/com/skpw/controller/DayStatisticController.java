package com.skpw.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.DayReportBean;
import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TRtFacility;
import com.skpw.service.DayStatisticService;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.TRtFacilityService;
import com.skpw.service.TTcControlerService;
import com.skpw.service.WryjbxxService;
import com.skpw.bean.TSysUserInfo;

@Controller
public class DayStatisticController {

	@Resource
	private TTcControlerService tTcControlerService;
	@Resource
	private WryjbxxService wryjbxxService;

	@Resource
	private DayStatisticService dayStatisticService;

	@Resource
	private TRtFacilityService tRtFacilityService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;

	@RequestMapping("/gas/getFacility")
	@ResponseBody
	public List getTTCList(String id) {

		List list = tRtFacilityService.findFaciltityByControlid(Integer
				.valueOf(id));

		return list;
	}

	@RequestMapping("gas/initJumpToDayStatistic")
	public String initJumpToDayStatistic(Model model,HttpServletRequest request,String wry_id) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = calendar.getTime();
		String statisticime = sdf.format(date);

		//List<TBasEnterprise> enterList = wryjbxxService.findAll();
		List<TBasEnterprise> enterList = new ArrayList<TBasEnterprise>();
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
		if(null != wry_id && !"".equals(wry_id)) {
			List<TBasEnterprise> l3 = wryjbxxService.findAll();
			if(null != l3 && l3.size() > 0) {
				for(int i=0; i<l3.size(); i++) {
					if(wry_id.equals(l3.get(i).getFenterId().trim())) {
						enterList.add(l3.get(i));
					}
				}
			}
		}
		else
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

		@SuppressWarnings("unchecked")
		List<TRtFacility> facilities = tRtFacilityService
				.findFaciltityByControlid(49);

		model.addAttribute("statisticime", statisticime);

		model.addAttribute("enterList", enterList);

		model.addAttribute("facilities", facilities);

		return "gasrealtime/gasdaystatistic";
	}

	@RequestMapping("gas/statisticDayReport")
	@ResponseBody
	public List<DayReportBean> statisticDayReport(HttpServletRequest request,
			String controlid, String facilitieid,String statistictime) {
		List<DayReportBean> dayReportList = dayStatisticService
				.dayStatisticReport(statistictime, Integer.valueOf(facilitieid),Integer.valueOf(controlid));
		// 空行
		DayReportBean dayReportBean = new DayReportBean();
		dayReportList.add(dayReportBean);

		List<DayReportBean> mmasList = dayStatisticService.findMMAS(
				statistictime, Integer.valueOf(facilitieid), Integer.valueOf(controlid));

		dayReportList.addAll(mmasList);
		return dayReportList;
	}

}
