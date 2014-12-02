package com.skpw.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.ReportData;
import com.skpw.service.MonthDataService;

@Controller
public class MonthDataController {

	@Resource
	private MonthDataService monthDataService;

	// 跳转到年报表统计
	@RequestMapping("realtime/initYearDataToList")
	public String initYearDataToList(Model model) {

		return "realtime/monthData";
	}

	// 分页查询月数据
	@RequestMapping("/monthDataController/findByList")
	public @ResponseBody
	List<ReportData> findByList(int cId, String time) {
		if ((time == null && "".equals(time))) {
			time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		// 小时数据详细信息
		List<ReportData> list = monthDataService.findListByPage(cId, time);
		// 空行
		ReportData hd = new ReportData();
		// 小时数据的最大值，最小值，平均值、总和
		List<ReportData> listMMAS = monthDataService.findMMAS(cId, time);
		// 相加
		list.add(hd);
		list.addAll(listMMAS);
		return list;
	}
}
