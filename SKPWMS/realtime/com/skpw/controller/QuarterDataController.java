package com.skpw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.ReportData;
import com.skpw.service.QuarterDataService;

@Controller
public class QuarterDataController {
	@Resource
	private QuarterDataService quarterDataService;

	// 跳转到季度报表统计
	@RequestMapping("realtime/initQuarterDataToList")
	public String initQuarterDataToList(Model model) {

		return "realtime/quarterData";
	}

	// 统计季度数据
	@RequestMapping("/realtime/statisticQuarter")
	@ResponseBody
	List<ReportData> statisticQuarter(int cId, String time) {
		// 小时数据详细信息
		List<ReportData> list = quarterDataService.statisticQuarter(cId,time);
		// 空行
		ReportData hd = new ReportData();
		// 小时数据的最大值，最小值，平均值、总和
		List<ReportData> listMMAS = quarterDataService.findMMAS(cId, time);
		// 相加
		list.add(hd);
		list.addAll(listMMAS);
		return list;
	}

}
