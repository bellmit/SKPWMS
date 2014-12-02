package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.Alerminfo;
import com.skpw.common.Page;
import com.skpw.service.AlermInfoService;
import com.skpw.service.AlermInfoSpecs;

@Controller
public class AlermInfoController {

	@Resource
	private AlermInfoService alermInfoService;

	// 跳转到报警实时界面
	@RequestMapping("alerm/initAlerminfoToList")
	public String initAlermInfoToList() {

		return "alerm/alerminfo_list";

	}

	@RequestMapping("alerm/showAlermInfo")
	@ResponseBody
	public List showAlermInfo() {

		List list = alermInfoService.showAlermInfo();

		return list;
	}

	// 条件查询显示所有
	@RequestMapping("alerm/showAlermInfoByCondition")
	@ResponseBody
	public Map showAlermInfoByCondition(Alerminfo alerminfo,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage,
			HttpServletRequest request) {

		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = alermInfoService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<Alerminfo> alerminfos = alermInfoService
				.showAlermInfoByCondition(
						pageRequest,
						AlermInfoSpecs.queryCondition(alerminfo, start_time,
								end_time)).getContent();

		map.put("total", count);
		map.put("rows", alerminfos);
		return map;

	}

}
