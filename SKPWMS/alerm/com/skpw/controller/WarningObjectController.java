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

import com.skpw.bean.EwsWarningObject;
import com.skpw.common.Page;
import com.skpw.service.EwsWarningObjectService;
import com.skpw.service.WarningObjectService;

/**
 * @author hjy 预警对象
 */
@Controller
public class WarningObjectController {
	@Resource
	private EwsWarningObjectService ewsWarningObjectService;

	@Resource
	private WarningObjectService warningObjectService;

	// 显示报警对象信息
	@RequestMapping("alerm/showWarningObject")
	@ResponseBody
	public List showWarningObject() {

		List list = warningObjectService.showWarningObject();

		return list;
	}

	@RequestMapping("alerm/showEwsWarningObjectByWarnId")
	@ResponseBody
	public List showEwsWarningObjectByWarnId(HttpServletRequest request) {
		String warningid = request.getParameter("warningid");
		List list = ewsWarningObjectService
				.showEwsWarningObjectByWarnid(warningid);
		return list;
	}

	// 显示所有
	@RequestMapping("alerm/showEwsWarningObject")
	@ResponseBody
	public List showEwsWarningObject() {
		List list = ewsWarningObjectService.showEwsWarningObjectModel();
		return list;
	}

	// 条件查询显示所有
	@RequestMapping("alerm/showEwsWarningObjectByCondition")
	@ResponseBody
	public Map showEwsWarningObjectByCondition(
			EwsWarningObject ewsWarningObject,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = ewsWarningObjectService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<EwsWarningObject> EwsWarningObjects = ewsWarningObjectService
				.showEwsWarningObjectModelByPage(pageRequest).getContent();

		map.put("total", count);
		map.put("rows", EwsWarningObjects);
		return map;

	}

	// 保存和修改
	@RequestMapping("/alerm/saveEwsWarningObject")
	@ResponseBody
	public String saveEwsWarningObject(EwsWarningObject ewsWarningObject) {

		String result = "";
		try {
			ewsWarningObjectService.saveEwsWarningObjectModel(ewsWarningObject);

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除参数设置
	@RequestMapping("/alerm/deleteEwsWarningObject")
	@ResponseBody
	public Map<String, Boolean> deleteEwsWarningObject(String id) {

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			ewsWarningObjectService.delEwsWarningObjectModel(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

}
