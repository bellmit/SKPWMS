package com.skpw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TSysMenu;
import com.skpw.bean.TSysorgUnit;
import com.skpw.bean.TreeBean;
import com.skpw.common.Page;
import com.skpw.service.MenuItemService;
import com.skpw.service.MenuItemSpecs;

@Controller
public class MenuItemController {

	@Resource
	private MenuItemService menuItemService;

	// 跳转到菜单页面
	@RequestMapping("/menu/initMenuToList")
	public String initMenuToList() {

		return "sys/menuitem_list";
	}

	// 显示所有
	@RequestMapping("/menu/showMenu")
	@ResponseBody
	public List showMenu(Model model) {
		List<TSysMenu> menus = menuItemService.showTSysMenuInfo();

		return menus;
	}

	// 分页条件显示所有
	@RequestMapping("/menu/showMenubyCondition")
	@ResponseBody
	public Map showMenubyCondition(Model model, TSysMenu menu,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = menuItemService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<TSysMenu> menus = menuItemService.showTSysMenuListByCondition(
				MenuItemSpecs.queryCondition(menu), pageRequest).getContent();

		map.put("total", count);
		map.put("rows", menus);
		return map;
	}

	// 添加初始化
	@RequestMapping("/menu/initAddMenu")
	public String initAddMenu(Model model) {

		return "";
	}

	// 添加

	@RequestMapping("/menu/addMenu")
	@ResponseBody
	public String addMenu(Model model, TSysMenu menu) {
		String result = "";
		try {
			menuItemService.saveTSysMenu(menu);

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除
	@RequestMapping("/menu/delMenu")
	@ResponseBody
	public Map<String, Boolean> delMenu(Model model, String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			menuItemService.deleteTSysMenu(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

	// 显示两级组织结构
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/menu/getMenuItemTreeData")
	@ResponseBody
	public List getMenuItemTreeData(Model model, TSysMenu menu) {
		List<TSysMenu> firstNode = menuItemService.findNodesByParentId("");
		List listNode = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < firstNode.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", firstNode.get(i).getId());
			map.put("menuCode", firstNode.get(i).getMenuCode());
			map.put("menuName", firstNode.get(i).getMenuName());
			map.put("url", firstNode.get(i).getUrl());
			map.put("parentId", "");

			List<TSysMenu> childNodeList = menuItemService
					.findNodesByParentId(firstNode.get(i).getId());

			List childList = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < childNodeList.size(); j++) {
				Map<String, Object> childMap = new HashMap<String, Object>();
				childMap.put("id", childNodeList.get(j).getId());
				childMap.put("menuCode", childNodeList.get(j).getMenuCode());
				childMap.put("menuName", childNodeList.get(j).getMenuName());
				childMap.put("url", childNodeList.get(j).getUrl());
				childMap.put("parentId", firstNode.get(i).getId());
				childMap.put("parentName", firstNode.get(i).getMenuName());

				if (menuItemService.findNodesByParentId(
						childNodeList.get(j).getId()).size() > 0) {
					childMap.put("state", "closed");
				}

				childList.add(childMap);
			}
			map.put("children", childList);
			listNode.add(map);
		}

		return listNode;
	}
	
	//获取子菜单
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/menu/findMenuItemNodeListByParentId")
	@ResponseBody
	public List findMenuItemNodeListByParentId(String nodeid) {
		List listNode = new ArrayList<Map<String, Object>>();

		List<TSysMenu> nodeList = menuItemService.findNodesByParentId(nodeid);
		for (int i = 0; i < nodeList.size(); i++) {
			Map<String, Object> childMap = new HashMap<String, Object>();
			childMap.put("id", nodeList.get(i).getId());
			childMap.put("menuCode", nodeList.get(i).getMenuCode());
			childMap.put("menuName", nodeList.get(i).getMenuName());
			childMap.put("url", nodeList.get(i).getUrl());
			childMap.put("parentId", nodeid);

			if (menuItemService.findNodesByParentId(nodeList.get(i).getId())
					.size() > 0) {
				childMap.put("state", "closed");
			}
			listNode.add(childMap);
		}
		return listNode;
	}
	
	/**
	 * 查询菜单树，for Ztree
	 * @return
	 */
	@RequestMapping("/menu/findAllMenuItemForZtree")
	@ResponseBody
	public List<TreeBean> findAllMenuItemForZtree(){
		List<TreeBean> list = new ArrayList<TreeBean>();
		List<TSysMenu> menulist = menuItemService.showTSysMenuInfo();
		for(TSysMenu menu:menulist){
			TreeBean tb = new TreeBean();
			tb.setId(menu.getId().trim());
			if(menu.getParentMenu() == null){
				tb.setPid("0");
			}else{
				tb.setPid(menu.getParentMenu().getId());
			}
			tb.setName(menu.getMenuName());
			list.add(tb);
		}
		return list;
	}
}
