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

import com.skpw.bean.MonthReportBean;
import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TSysUserInfo;
import com.skpw.service.MonthStatisticService;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.WryjbxxService;

@Controller
public class MonthStatisticController {
	@Resource
	private WryjbxxService wryjbxxService;
	
	@Resource
	private  MonthStatisticService monthStatisticService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;
	
	@RequestMapping("gas/initJumpToMonthStatistic")
	public String initJumpToMonthStatistic(Model model,HttpServletRequest request,String wry_id) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = calendar.getTime();
		String statisticime = sdf.format(date);
		//List<TBasEnterprise> enterList = wryjbxxService.findAll();
		List<TBasEnterprise> enterList = new ArrayList<TBasEnterprise>();
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
		if(null != wry_id && !"".equals(wry_id.trim())) {
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
		model.addAttribute("statisticime", statisticime);
		model.addAttribute("enterList", enterList);
		return "gasrealtime/gasmonthstastic";
	}
	
	@RequestMapping("gas/statisticMonthReport")
	@ResponseBody
	public List<MonthReportBean> statisticMonthReport(HttpServletRequest request,String controlid ,String statistictime,String facilitieid ){
		List<MonthReportBean> monthReportList=monthStatisticService.monthStatisticReport(statistictime,Integer.valueOf(facilitieid),Integer.valueOf(controlid));
		MonthReportBean monthReportBean=new MonthReportBean();
		
		monthReportList.add(monthReportBean);
		List<MonthReportBean> mmasList=monthStatisticService.findMMAS(Integer.valueOf(controlid),Integer.valueOf(facilitieid),statistictime);
		
		monthReportList.addAll(mmasList);
		return  monthReportList;
	}
}
