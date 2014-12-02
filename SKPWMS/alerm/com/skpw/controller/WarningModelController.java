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

import com.skpw.bean.EwsWarningModel;
import com.skpw.common.Page;
import com.skpw.service.EwsWarningModelService;
import com.skpw.service.WarningModelService;

/**
 * @author hjy 预警方式
 */
@Controller
public class WarningModelController {
	@Resource
	private EwsWarningModelService ewsWarningModelService;

	@Resource
	private WarningModelService warningModelService;

	// 显示报警方式
	 @RequestMapping("alerm/showwarningModel")
	 @ResponseBody
	 public List showwarningModel(){
	
	 List list=warningModelService.showWarningModel();
	
	 return list;
	 }
	 
	// 显示报警方式
	@RequestMapping("alerm/showwarningModelByWarnid")
	@ResponseBody
	public List showwarningModelByWarnid(HttpServletRequest request) {
		String warningid = request.getParameter("warningid");
		List list = ewsWarningModelService
				.showEwsWarningModelByWarnid(warningid);

		return list;
	}

	// 显示所有
	@RequestMapping("alerm/showEwsWarningModel")
	@ResponseBody
	public List showEwsWarningModel() {
		List list = ewsWarningModelService.showEwsWarningModel();
		return list;
	}

	// 条件查询显示所有
	@RequestMapping("alerm/showEwsWarningModelByCondition")
	@ResponseBody
	public Map showEwsWarningModelByCondition(EwsWarningModel ewsWarningModel,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = ewsWarningModelService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<EwsWarningModel> WarningModels = ewsWarningModelService
				.showEwsWarningModelByPage(pageRequest).getContent();
		map.put("total", count);
		map.put("rows", WarningModels);
		return map;

	}

	// 保存和修改
	@RequestMapping("/alerm/saveEwsWarningModel")
	@ResponseBody
	public String saveEwsWarningModel(EwsWarningModel ewsWarningModel) {

		String result = "";
		try {
			ewsWarningModelService.saveEwsWarningModel(ewsWarningModel);

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除参数设置
	@RequestMapping("/alerm/deleteEwsWarningModel")
	@ResponseBody
	public Map<String, Boolean> deleteEwsWarningModel(String id) {

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			ewsWarningModelService.delEwsWarningModel(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}
}
