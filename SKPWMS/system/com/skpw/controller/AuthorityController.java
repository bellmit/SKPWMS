package com.skpw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.ComboTreeModel;
import com.skpw.bean.TSysAuthority;
import com.skpw.bean.TSysMenu;
import com.skpw.common.Page;
import com.skpw.service.AuthorityService;
import com.skpw.service.AuthoritySpecs;
import com.skpw.service.EasyuiTreeService;
import com.skpw.service.MenuItemService;

@Controller
public class AuthorityController {

	@Resource
	private AuthorityService authorityService;

	@Resource
	private MenuItemService menuItemService;

	// @Resource
	// private SewageLicenceService authorityService;

	// 跳转到权限显示页面
	@RequestMapping("/authority/initAuthorityToList")
	public String initAuthorityToList() {

		return "sys/authority_list";
	}

	// 显示所有
	@RequestMapping("/authority/showSewagelicense")
	@ResponseBody
	public List showAuthoritys(Model model) {
		List<TSysAuthority> authorities = authorityService.showAuthorityInfo();

		return authorities;
	}

	// 分页条件显示所有
	@RequestMapping("/authority/showAuthoritysbyCondition")
	@ResponseBody
	public Map showAuthoritysbyCondition(Model model, TSysAuthority authority,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		long count = authorityService.count(AuthoritySpecs
				.queryCondition(authority));
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<TSysAuthority> authorities = authorityService
				.showAuthorityListByCondition(
						AuthoritySpecs.queryCondition(authority), pageRequest)
				.getContent();
		map.put("total", count);
		map.put("rows", authorities);
		
		return map;
	}
	
	
	// 显示所有
		@RequestMapping("/authority/showAuthoritysbyConditionall")
		@ResponseBody
		public Map showAuthoritysbyConditionall(Model model, TSysAuthority authority,
				@RequestParam(value = "page", required = false) Integer pageNum,
				@RequestParam(value = "rows", required = false) Integer numPerPage) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (pageNum == null || pageNum <= 0) {
				pageNum = new Integer(1);
			}
			if (numPerPage == null || numPerPage <= 0) {
				numPerPage = new Integer(20);
			}
			long count = authorityService.count(AuthoritySpecs
					.queryCondition(authority));
			Page page = new Page(pageNum, numPerPage, count);
			Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
					page.getPageSize());
			List<TSysAuthority> authorities = authorityService
					.showAuthorityListByCondition(
							AuthoritySpecs.queryCondition(authority), null)
					.getContent();
			//map.put("total", count);
			map.put("rows", authorities);
			
			return map;
		}

	// 添加和修改

	@RequestMapping("/authority/addAuthority")
	@ResponseBody
	public String addAuthority(Model model, TSysAuthority authority) {
		String result = "";

		try {
			authorityService.saveAuthority(authority);

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除
	@RequestMapping("/authority/delAuthority")
	@ResponseBody
	public Map<String, Boolean> delAuthority(Model model, String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			authorityService.deleteAuthority(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

	@RequestMapping("/authority/menuComboxTree")
	@ResponseBody
	public Object menuComboxTree(HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {

		List<TSysMenu> list = menuItemService.showTSysMenuInfo();

		List<ComboTreeModel> comboTreeList = new ArrayList<ComboTreeModel>();

		for (TSysMenu tSysMenu : list) {

			ComboTreeModel comboTreeModel = new ComboTreeModel();
			comboTreeModel.setId(tSysMenu.getId());
			comboTreeModel.setText(tSysMenu.getMenuName());
			if (tSysMenu.getParentMenu() == null) {
				comboTreeModel.setPid("");
			} else {
				comboTreeModel.setPid(tSysMenu.getParentMenu().getId());
			}
			comboTreeList.add(comboTreeModel);
		}

		String treejsonstr = EasyuiTreeService.getJsonData(comboTreeList);

		return treejsonstr;

	}

}
