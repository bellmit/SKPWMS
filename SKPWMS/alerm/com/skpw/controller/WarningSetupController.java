package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.WarningSetup;
import com.skpw.common.Page;
import com.skpw.service.TBasPollutantService;
import com.skpw.service.WarningItemService;
import com.skpw.service.WarningSetupService;
import com.skpw.service.WarningSetupSpecs;

@Controller
public class WarningSetupController {

	@Resource
	private WarningSetupService warningSetupService;

	@Resource
	private WarningItemService warningItemService;

	@Resource
	private TBasPollutantService tBasPollutantService;

	// 跳转到报警参数设置界面
	@RequestMapping("alerm/initWarningSetupToList")
	public String initWarningSetupToList() {

		return "alerm/warningsetup_list";

	}

	// 跳转到报警管理界面
	@RequestMapping("alerm/waringsetupadd")
	public String waringsetupadd(Model model) {

		model.addAttribute("warningSetup", new WarningSetup());
		return "alerm/warningsetupmanage";
	}

	// 获取污染源因子
	@RequestMapping("alerm/showPolluntant")
	@ResponseBody
	public List showPolluntant() {

		List list = tBasPollutantService.findAll(null);

		return list;
	}

	@RequestMapping("alerm/showWarningItem")
	@ResponseBody
	public List showWarningItem() {
		List list = warningItemService.showWarningItem();
		return list;
	}

	// 显示所有
	@RequestMapping("alerm/showWarningSetup")
	@ResponseBody
	public List showWarningSetup() {
		List list = warningSetupService.showWarningSetup();
		return list;
	}

	// 条件查询显示所有
	@RequestMapping("alerm/showWarningSetupByCondition")
	@ResponseBody
	public Map showWarningSetupByCondition(WarningSetup warningSetup,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = warningSetupService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<WarningSetup> warningSetups = warningSetupService
				.showWarningSetupByCondition(
						WarningSetupSpecs.queryCondition(warningSetup),
						pageRequest).getContent();

		map.put("total", count);
		map.put("rows", warningSetups);
		return map;

	}

	// 保存和修改
	@RequestMapping("/alerm/saveWarningSetup")
	@ResponseBody
	public String saveWarningSetup(WarningSetup warningSetup) {

		String result = "";
		try {
			WarningSetup warningSetup2 = warningSetupService
					.saveWarningSetup(warningSetup);

			result = warningSetup2.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除参数设置
	@RequestMapping("/alerm/deleteWarningSetup")
	@ResponseBody
	public Map<String, Boolean> deleteWarningSetup(String id) {

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			warningSetupService.delWarningSetup(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

	@RequestMapping("/alerm/waringsetupfind")
	public String waringsetupfind(HttpServletRequest request, Model model) {

		String id = request.getParameter("id");
		
		WarningSetup warningSetup=warningSetupService.findOne(id);
		
		model.addAttribute("warningSetup", warningSetup);

		return "alerm/warningsetupmanage";
	}

}
