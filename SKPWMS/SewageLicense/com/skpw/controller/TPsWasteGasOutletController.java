package com.skpw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsWasteGasOutlet;
import com.skpw.service.TBasWaterDisStdService;
import com.skpw.service.TPsOutPermitService;
import com.skpw.service.TPsWasteGasOutletService;

@Controller
public class TPsWasteGasOutletController {
	
	@Autowired
	private TPsWasteGasOutletService tPsWasteGasOutletService;
	
	@Autowired
	private TBasWaterDisStdService tBasWaterDisStdService;
	
	@Autowired
	private TPsOutPermitService tPsOutPermitService;
	
	@RequestMapping("tPsWasteGasOutlet/list")
	public @ResponseBody Map<String, Object> list(int rows, int page, TPsWasteGasOutlet tPsWasteGasOutlet) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(tPsWasteGasOutlet != null && tPsWasteGasOutlet.gettPsOutPermit() != null && tPsWasteGasOutlet.gettPsOutPermit().getfOutPID() != null && !tPsWasteGasOutlet.gettPsOutPermit().getfOutPID().trim().equals("")) {
			Pageable pageable = new PageRequest(page-1, rows);
			Page<TPsWasteGasOutlet> pages = tPsWasteGasOutletService.findAllByPage(tPsWasteGasOutlet, pageable);
			map.put("total", pages.getTotalElements());
			map.put("rows", pages.getContent());
		} else {
			map.put("total", 0);
			map.put("rows", null);
		}
		return map;
	}
	
	@RequestMapping("tPsWasteGasOutlet/add")
	public String add(Model model, TPsOutPermit tPsOutPermit) {
		TPsWasteGasOutlet tPsWasteGasOutlet = new TPsWasteGasOutlet();
		tPsWasteGasOutlet.settPsOutPermit(tPsOutPermit);
		model.addAttribute("tBasWaterDisStdList", tBasWaterDisStdService.findAll());
		model.addAttribute("tPsWasteGasOutlet", tPsWasteGasOutlet);
		return "sewagelicense/tPsWasteGasOutlet";
	}
	
	@RequestMapping("tPsWasteGasOutlet/save")
	public void save(HttpServletResponse res, TPsWasteGasOutlet tPsWasteGasOutlet) {
		String str = "true";
		try {
			if(tPsWasteGasOutlet.getfWGOutletID() == null || tPsWasteGasOutlet.getfWGOutletID().trim().equals("")) {
				TPsOutPermit tPsOutPermit = tPsOutPermitService.findOne(tPsWasteGasOutlet.gettPsOutPermit().getfOutPID());
				tPsWasteGasOutlet.settBasEnterprise(tPsOutPermit.gettBasEnterprise());
			}
			str = tPsWasteGasOutletService.save(tPsWasteGasOutlet).getfWGOutletID();
		} catch (Exception e) {
			str = "false";
		}
		try {
			res.getWriter().print(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@RequestMapping("tPsWasteGasOutlet/del")
	public @ResponseBody Map<String, Boolean> del(String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {
			tPsWasteGasOutletService.delete(id);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		map.put("success", result);
		return map;
	}
	
	@RequestMapping("tPsWasteGasOutlet/find")
	public ModelAndView find(TPsWasteGasOutlet tPsWasteGasOutlet) {
		ModelAndView mav = new ModelAndView("sewagelicense/tPsWasteGasOutlet");
		tPsWasteGasOutlet = tPsWasteGasOutletService.findOne(tPsWasteGasOutlet.getfWGOutletID());
		if(tPsWasteGasOutlet == null) {
			tPsWasteGasOutlet = new TPsWasteGasOutlet();
		}
		mav.addObject("tBasWaterDisStdList", tBasWaterDisStdService.findAll());
		mav.addObject("tPsWasteGasOutlet", tPsWasteGasOutlet);
		return mav;
	}
}
