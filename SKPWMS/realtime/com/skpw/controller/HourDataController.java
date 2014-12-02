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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.ReportData;
import com.skpw.bean.TSysUserInfo;
import com.skpw.service.HourDataService;
import com.skpw.service.TBasCityService;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.TTcControlerService;
import com.skpw.service.WryjbxxService;

@Controller
public class HourDataController {

	@Resource
	private HourDataService hourDataService;

	@Resource
	private TBasCityService tBasCityService;

	@Resource
	private TTcControlerService tTcControlerService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;
	
	@Resource
	private WryjbxxService wryjbxxService;
	
	//跳转到日报表统计
	@RequestMapping("realtime/initDayDataToList")
	public String initDayDataToList(Model model) {		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = calendar.getTime();
		String statisticime = sdf.format(date);
		model.addAttribute("statisticime", statisticime);

		return "realtime/hourData";
	}

	// 分页查询小时数据
	@RequestMapping("/hourDataController/findByList")
	public @ResponseBody
	List<ReportData> findByList(int cId, String time) {
		if ((time == null && "".equals(time))) {
			time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		// 小时数据详细信息
		List<ReportData> list = hourDataService.findListByPage(cId, time);
		// 空行
		ReportData hd = new ReportData();
		// 小时数据的最大值，最小值，平均值、总和
		List<ReportData> listMMAS = hourDataService.findMMAS(cId, time);
		// 相加
		list.add(hd);
		list.addAll(listMMAS);
		return list;
	}

	// 获取污染源列表
	@RequestMapping("/hourDataController/getCities")
	public @ResponseBody
	List<Map<String, Object>> getCities() {
		List<Map<String, Object>> list = tBasCityService.findList();
		// 设置初始值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "");
		map.put("text", "--请选择--");
		map.put("selected", "true");
		list.add(0, map);
		return list;

	}

	// 获取总量控制器
	@RequestMapping("/hourDataController/getControler")
	public @ResponseBody
	List<Map<String, Object>> getControler(String id,HttpServletRequest request) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> enterList = tBasEnterPollService.findEnterByUserid(userid);
		if(null != enterList && enterList.size() > 0) {
			String enterid = enterList.get(0).get("enterid").toString();
			if(null != enterid && !"".equals(enterid.trim())) {
				list = tTcControlerService.getMapList(id);
				List<Map<String, Object>> l1 = new ArrayList<Map<String,Object>>();
				if(null != list && list.size() > 0) {
					for(int i=0; i<list.size(); i++) {
						if(enterid.trim().equals(list.get(i).get("qyid").toString().trim())) {
							l1.add(list.get(i));
						}
					}
				}
				list = l1;
			}
		}
		else {
			List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
			if(null != longcodelist && longcodelist.size() > 0) {
				List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
				if(null != orgidList && orgidList.size() > 0) {
					List<String> qyidList = wryjbxxService.findenterIdsByzzjgid(orgidList);
					if(null != qyidList && qyidList.size() > 0) {
						list = tTcControlerService.getMapList(id);
						List<Map<String, Object>> l2 = new ArrayList<Map<String,Object>>();
						if(list != null && list.size() > 0) {
							for(int i=0; i<list.size(); i++) {
								for(int j=0; j<qyidList.size(); j++) {
									if(qyidList.get(j).equals(list.get(i).get("qyid").toString())) {
										l2.add(list.get(i));
										break;
									}
								}
							}
						}
						list=l2;
					}
				}
				
			}
		}
		
		return list;
	}
}
