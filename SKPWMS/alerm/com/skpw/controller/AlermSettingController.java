package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.AlermSetting;
import com.skpw.common.Page;
import com.skpw.service.AlermSettingService;
import com.skpw.service.AlermSettingSpecs;

@Controller
public class AlermSettingController {
	@Resource
	private AlermSettingService alermSettingService;

	// 跳转到报警参数设置界面
	@RequestMapping("alerm/initAlermSettingToList")
	public String initAlermSettingToList() {

		return "alerm/alermsetting_list";

	}

	// 显示所有
	@RequestMapping("alerm/showAlermSetting")
	@ResponseBody
	public List showAlermSetting() {
		List list = alermSettingService.showAlermSetting();
		return list;
	}

	// 条件查询显示所有
	@RequestMapping("alerm/showAlermSettingByCondition")
	@ResponseBody
	public Map showAlermSettingByCondition(AlermSetting alermSetting,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = alermSettingService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<AlermSetting> alermSettings = alermSettingService
				.showAlermSettingByCondition(null, pageRequest,
						AlermSettingSpecs.queryCondition(alermSetting))
				.getContent();

		map.put("total", count);
		map.put("rows", alermSettings);
		return map;

	}

	// 保存和修改
	@RequestMapping("/alerm/saveAlermSetting")
	@ResponseBody
	public String saveAlermSetting(AlermSetting alermSetting) {

		String result = "";
		try {
			alermSettingService.saveAlermSetting(alermSetting);

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除参数设置
	@RequestMapping("/alerm/deleteAlermSetting")
	@ResponseBody
	public Map<String, Boolean> deleteAlermSetting(String id) {

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			alermSettingService.delAlermSetting(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}
}
