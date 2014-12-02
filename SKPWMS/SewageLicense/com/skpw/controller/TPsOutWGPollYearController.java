package com.skpw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutWGPollYear;
import com.skpw.bean.TPsWasteGasOutlet;
import com.skpw.common.CreateUUID;
import com.skpw.service.TPsOutWGPollYearService;
import com.skpw.service.TPsWasteGasOutletService;

@Controller
public class TPsOutWGPollYearController {

	@Autowired
	private TPsOutWGPollYearService tPsOutWGPollYearService;
	
	@Autowired
	private TPsWasteGasOutletService tPsWasteGasOutletService;
	
	@RequestMapping("tPsOutWGPollYear/list")
	public @ResponseBody Map<String, Object> list(int page, int rows, TPsOutWGPollYear tPsOutWGPollYear) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != tPsOutWGPollYear.gettPsWasteGasOutlet() && tPsOutWGPollYear.gettPsWasteGasOutlet().getfWGOutletID() != null && !tPsOutWGPollYear.gettPsWasteGasOutlet().getfWGOutletID().trim().equals("")) {
			Sort sort = new Sort(new Sort.Order(Direction.ASC,"tBasPollutant.fPollutantName"));
			Pageable pageable = new PageRequest(page-1, rows, sort);
			Page<TPsOutWGPollYear> pages = tPsOutWGPollYearService.findAllByPage(tPsOutWGPollYear, pageable);
			map.put("total", pages.getTotalElements());
			map.put("rows", pages.getContent());
		}
		else {
			map.put("total", 0);
			map.put("rows", new ArrayList());
		}
		return map;
	}
	
	@RequestMapping("tPsOutWGPollYear/save")
	public void save(HttpServletResponse res, TPsOutWGPollYear tPsOutWGPollYear) {
		String str = "true";
		try {
			TPsWasteGasOutlet tPsWasteGasOutlet = tPsWasteGasOutletService.findOne(tPsOutWGPollYear.gettPsWasteGasOutlet().getfWGOutletID());
			TPsOutPermit tPsOutPermit = tPsWasteGasOutlet.gettPsOutPermit();
			if(null == tPsOutWGPollYear.getfOutWGPollYearID() || "".equals(tPsOutWGPollYear.getfOutWGPollYearID().trim())) {
				tPsOutWGPollYear.setfOutWGPollYearID(CreateUUID.getUuid());
				tPsOutWGPollYear.settPsOutPermit(tPsOutPermit);
				str = tPsOutWGPollYearService.save(tPsOutWGPollYear, tPsOutPermit).getfOutWGPollYearID();
			}
			else {
				TPsOutWGPollYear _tPsOutWGPollYear = tPsOutWGPollYearService.findOne(tPsOutWGPollYear.getfOutWGPollYearID());
				_tPsOutWGPollYear.setfYearID(tPsOutWGPollYear.getfYearID());
				_tPsOutWGPollYear.setfDischarge(tPsOutWGPollYear.getfDischarge());
				str = tPsOutWGPollYearService.save(_tPsOutWGPollYear, tPsOutPermit).getfOutWGPollYearID();
			}
		} catch (Exception e) {
			str = "false";
		}
		try {
			res.getWriter().print(str);
		} catch (Exception e) {
		}
	}
	
	@RequestMapping("tPsOutWGPollYear/del")
	public @ResponseBody Map<String, Boolean> del(String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {
			tPsOutWGPollYearService.delete(id);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		map.put("success", result);
		return map;
	}
}
