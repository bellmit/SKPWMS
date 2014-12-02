package com.skpw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TBasSewageLicence;
import com.skpw.bean.TPsOutPermit;
import com.skpw.common.Page;
import com.skpw.service.SewageLicenceService;
import com.skpw.service.SewageSpecs;

@Controller
public class SewageLicenseController {

	@Resource
	private SewageLicenceService sewageLicenseService;

	// 跳转到排污许可证显示页面
	@RequestMapping("/sewagelicense/initSweageToList")
	public String initSweageToList() {

		return "sewagelicense/sewagemange_list";
	}

	// 显示所有
	@RequestMapping("/sewagelicense/showSewagelicense")
	@ResponseBody
	public List showsewageLicense(Model model) {
		List<TBasSewageLicence> sewageLicences = sewageLicenseService
				.showTBasSewageLicence();

		return sewageLicences;
	}

	// 分页条件显示所有
	@RequestMapping("/sewagelicense/showSewagelicenseByCondition")
	@ResponseBody
	public Map showsewageLicensebyCondition(Model model,
			TBasSewageLicence sewageLicence,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map=new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		long count = sewageLicenseService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<TBasSewageLicence> tBasSewageLicences = sewageLicenseService
				.showSewageLicenceListByCondition(
						SewageSpecs.queryCondition(sewageLicence), pageRequest)
				.getContent();
		map.put("total", count);
		map.put("rows", tBasSewageLicences);
		return map;
	}

	// 添加初始化
	@RequestMapping("/sewagelicense/initAddSewage")
	public String initAddsewageLicense(Model model) {

		return "sewagelicense/sewagemange_add";
	}

	// 添加

	@RequestMapping("/sewagelicense/saveSewage")
	@ResponseBody
	public String addsewageLicense(Model model,
			TBasSewageLicence tBasSewageLicence) {
		String result = "";
		try {
			sewageLicenseService.saveSewageLicence(tBasSewageLicence);

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除
	@RequestMapping("/sewagelicense/delSewage")
	@ResponseBody
	public Map<String, Boolean> delsewageLicense(Model model, String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			sewageLicenseService.deleteSewageLicence(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

	// 修改初始化
	// public String initEditsewageLicense(Model model, String userid) {
	//
	// sewageLicenseInfo userInfo =
	// sewageLicenseService.initUpdatesewageLicense(userid);
	//
	// model.addAttribute("userinfo", userInfo);
	// return "user/user_edit";
	// }

	// 修改
	// public String editsewageLicense(sewageLicenseInfo userInfo) {
	//
	// sewageLicenseService.updatesewageLicense(userInfo);
	//
	// return "redirect:user/showsewageLicenseByCondition";
	// }

	//从污染源列表跳转到污染源详细信息页面
		@RequestMapping("/sewagelicense/findOne")
		public String findOne(Model model, String id){
//			TBasEnterprise tbe = wryjbxxService.findWry(id);
//			model.addAttribute("tbe", tbe);
			model.addAttribute("tPsOutPermit", new TPsOutPermit());
			return "sewagelicense/enterprisejbxx";
		}
		
		@RequestMapping("/sewagelicense/save")
		public void save(HttpServletResponse res, TBasEnterprise tbe){
			try {
				res.getWriter().print("1000");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping("/sewagelicense/findYj")
		public String findYj(Model model, String id){
//			TBasEnterprise tbe = wryjbxxService.findWry(id);
//			model.addAttribute("tbe", tbe);
			return "sewagelicense/fzdwspyj";
		}
		
		@RequestMapping("/sewagelicense/saveYj")
		public void saveYj(HttpServletResponse res, TBasEnterprise tbe){
			try {
				res.getWriter().print("1000");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping("/sewagelicense/findSwr")
		public String findSwr(Model model, String id){
//			TBasEnterprise tbe = wryjbxxService.findWry(id);
//			model.addAttribute("tbe", tbe);
			return "sewagelicense/swrwpf";
		}
		
		@RequestMapping("/sewagelicense/addSwr")
		public String addSwr(Model model){
//			TBasEnterprise tbe = wryjbxxService.findWry(id);
//			model.addAttribute("tbe", tbe);
			return "sewagelicense/swrwpfDialog";
		}
		
		@RequestMapping("/sewagelicense/findDqwr")
		public String findDqwr(Model model, String id){
//			TBasEnterprise tbe = wryjbxxService.findWry(id);
//			model.addAttribute("tbe", tbe);
			return "sewagelicense/dqwrwpf";
		}
		
		@RequestMapping("/sewagelicense/addDqwr")
		public String addDqwr(Model model){
//			TBasEnterprise tbe = wryjbxxService.findWry(id);
//			model.addAttribute("tbe", tbe);
			return "sewagelicense/dqwrwpfDialog";
		}
}
