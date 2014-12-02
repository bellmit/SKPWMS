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

import com.skpw.bean.TSysorgUnit;
import com.skpw.bean.TreeBean;
import com.skpw.common.Page;
import com.skpw.service.OrgUnitService;
import com.skpw.service.OrgUnitsSpecs;

@Controller
public class OrgUnitController {
	
	@Resource
	private OrgUnitService orgUnitService;
	

	// 跳转到组织结构显示页面
	@RequestMapping("/orgunit/initOrgUnitToList")
	public String initOrgUnitToList() {

		return "sys/orgunit_list";
	}

	// 显示所有
	@RequestMapping("/orgunit/showOrgunit")
	@ResponseBody
	public List showOrgunit(Model model) {
		List<TSysorgUnit> orgUnits = orgUnitService.showTSysorgUnitInfo();

		return orgUnits;
	}
	
	// 显示所有父级下拉列表
		@RequestMapping("/orgunit/showParentOrgunitList")
		@ResponseBody
		public List showParentOrgunitList(Model model,String zzid) {
			List<TSysorgUnit> orgUnits = orgUnitService.showTSysorgUnitInfo();
			if(null != zzid && !"".equals(zzid.trim()) && orgUnits.size() > 0) {
				for(int i=0; i < orgUnits.size(); i++) {
					if(orgUnits.get(i).getId().trim().equals(zzid.trim())) {
						orgUnits.remove(i);
					}
				}
			}
			if(null == zzid || "".equals(zzid.trim())) {
				TSysorgUnit t = new TSysorgUnit();
				t.setId("0");
				orgUnits.add(0, t);
			}
			

			return orgUnits;
		}

	// 分页条件显示所有
	@RequestMapping("/orgunit/showOrgunitbyCondition")
	@ResponseBody
	public Map showOrgunitbyCondition(Model model,
			TSysorgUnit orgUnit,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		long count = orgUnitService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<TSysorgUnit> orgUnits = orgUnitService.showTSysorgUnitListByCondition(OrgUnitsSpecs.queryCondition(orgUnit), pageRequest)
				.getContent();
		model.addAttribute("page", page);
		map.put("total", count);
		map.put("rows", orgUnits);
		return map;
	}
	
	//显示两级组织结构
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/orgunit/getOrgunitTreeData")
	@ResponseBody
	public List getOrgunitTreeData(Model model,TSysorgUnit orgUnit)
	{
		List<TSysorgUnit> firstNode = orgUnitService.findNodesByParentId("0");
		List listNode = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i < firstNode.size(); i++)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", firstNode.get(i).getId());
			map.put("orgUnitCode", firstNode.get(i).getOrgUnitCode());
			map.put("orgUnitName", firstNode.get(i).getOrgUnitName());
			map.put("longCode", firstNode.get(i).getLongCode());
			map.put("property", firstNode.get(i).getProperty());
			map.put("parentId", "0");
			
			List<TSysorgUnit> childNodeList = orgUnitService.findNodesByParentId(firstNode.get(i).getId());
			
			List childList = new ArrayList<Map<String, Object>>();
			for(int j = 0; j < childNodeList.size(); j++)
			{
				Map<String, Object> childMap = new HashMap<String, Object>();
				childMap.put("id", childNodeList.get(j).getId());
				childMap.put("orgUnitCode", childNodeList.get(j).getOrgUnitCode());
				childMap.put("orgUnitName", childNodeList.get(j).getOrgUnitName());
				childMap.put("property", childNodeList.get(j).getProperty());
				childMap.put("parentId", firstNode.get(i).getId());
				
				if(orgUnitService.findNodesByParentId(childNodeList.get(j).getId()).size() > 0)
				{
					childMap.put("state", "closed");
				}
				
				childList.add(childMap);
			}
			map.put("children", childList);
			listNode.add(map);
		}
		
		return listNode;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/orgunit/findOrgunitTreeNodeList")
	@ResponseBody
	public List findOrgNodeListByParentId(String nodeid)
	{
		List listNode = new ArrayList<Map<String, Object>>();
		
		List<TSysorgUnit> nodeList = orgUnitService.findNodesByParentId(nodeid);
		for(int i = 0; i < nodeList.size(); i++)
		{
			Map<String, Object> childMap = new HashMap<String, Object>();
			childMap.put("id", nodeList.get(i).getId());
			childMap.put("orgUnitCode", nodeList.get(i).getOrgUnitCode());
			childMap.put("orgUnitName", nodeList.get(i).getOrgUnitName());
			childMap.put("property", nodeList.get(i).getProperty());
			childMap.put("parentId", nodeid);
			
			if(orgUnitService.findNodesByParentId(nodeList.get(i).getId()).size() > 0)
			{
				childMap.put("state", "closed");
			}
			listNode.add(childMap);
		}
		return listNode;
	}
	
	
	 //添加初始化
	@RequestMapping("/orgunit/initAddSewage")
	 public String initAddOrgunit(Model model){
	
	
		 return "orgunit/orgunitmange_add";
	 }

	// 添加

	@RequestMapping("/orgunit/addOrgunit")
	@ResponseBody
	public String addOrgunit(Model model, TSysorgUnit orgUnit) {
		String result = "";
		try {
			
			if("0".equals(orgUnit.getParentOrgUnit().getId().trim())) {
				orgUnit.setLongCode(orgUnit.getOrgUnitCode());
			} else {
				TSysorgUnit _orgUnit = orgUnitService.findOne(orgUnit.getParentOrgUnit().getId());
				orgUnit.setLongCode(_orgUnit.getLongCode()+"$"+orgUnit.getOrgUnitCode());
			}
			orgUnitService.saveTSysorgUnit(orgUnit);

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除
	@RequestMapping("/orgunit/delOrgunit")
	@ResponseBody
	public Map<String, Boolean> delOrgunit(Model model, String id) {
		Map<String, Boolean> map=new HashMap<String, Boolean>();
		boolean result=false ;
		try {
			TSysorgUnit t0 = orgUnitService.findOne(id);
			if(t0 != null && t0.getLongCode() != null && !"".equals(t0.getLongCode().trim())) {
				List<TSysorgUnit> l = orgUnitService.findByLongCode(t0.getLongCode()+"$%");
				if(null != l && l.size() > 0) {
					for (int i=0; i<l.size(); i++) {
						orgUnitService.deleteTSysorgUnit(l.get(i).getId());
					}
				}
			}
			orgUnitService.deleteTSysorgUnit(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		map.put("success", result);
		
		return map;
	}

	/**
	 * 查询所有的管辖归属，for Ztree
	 * @return
	 */
	@RequestMapping("/orgunit/findAllOrgunitForZtree")
	@ResponseBody
	public List<TreeBean> findAllOrgunitForZtree(){
		List<TreeBean> list = new ArrayList<TreeBean>();
		List<TSysorgUnit> titlist = orgUnitService.showTSysorgUnitInfo();
		for(TSysorgUnit tbi:titlist){
			TreeBean tb = new TreeBean();
			tb.setId(tbi.getId().trim());
			if(tbi.getParentOrgUnit() == null){
				tb.setPid("0");
			}else{
				tb.setPid(tbi.getParentOrgUnit().getId());
			}
			tb.setName(tbi.getOrgUnitName());
			list.add(tb);
		}
		return list;
	}
	
	@RequestMapping("/orgunit/findByOrgUnitCode")
	public @ResponseBody String findByOrgUnitCode(String orgUnitCode) {
		String str = "true";
		List<TSysorgUnit> list = orgUnitService.findByOrgUnitCode(orgUnitCode);
		if(null != list && list.size() > 0) {
			str = "false";
		}
		return str;
	}
}
