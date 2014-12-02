package com.skpw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutWGPoll;
import com.skpw.service.TPsOutWGPollService;
import com.skpw.service.TPsWasteGasOutletService;

@Controller
public class TPsOutWGPollController {
	
	@Autowired
	private TPsOutWGPollService tPsOutWGPollService;
	
	@Autowired
	private TPsWasteGasOutletService tPsWasteGasOutletService;

	@RequestMapping("tPsOutWGPoll/list")
	public @ResponseBody Map<String, Object> list(int page, int rows, TPsOutWGPoll tPsOutWGPoll) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(tPsOutWGPoll != null && tPsOutWGPoll.gettPsWasteGasOutlet() != null && tPsOutWGPoll.gettPsWasteGasOutlet().getfWGOutletID() != null && !tPsOutWGPoll.gettPsWasteGasOutlet().getfWGOutletID().trim().equals("")) {
			Pageable pager = new PageRequest(page-1, rows);
			Page<TPsOutWGPoll> pages = tPsOutWGPollService.findAllByPage(tPsOutWGPoll, pager);
			map.put("total", pages.getTotalElements());
			map.put("rows", pages.getContent());
		}
		else {
			map.put("total", 0);
			map.put("rows", new ArrayList());
		}
		return map;
	}
	
	@RequestMapping("tPsOutWGPoll/save")
	public @ResponseBody String save(TPsOutWGPoll tPsOutWGPoll) {
		String str = "";
		try {
			if(tPsOutWGPoll.getfOutWGPollID() == null || tPsOutWGPoll.getfOutWGPollID().trim().equals("")) {
				TPsOutPermit tPsOutPermit = tPsWasteGasOutletService.findOne(tPsOutWGPoll.gettPsWasteGasOutlet().getfWGOutletID()).gettPsOutPermit();
				tPsOutWGPoll.settPsOutPermit(tPsOutPermit);
				str = tPsOutWGPollService.save(tPsOutWGPoll).getfOutWGPollID();
			}
			else {
				TPsOutWGPoll _tPsOutWGPoll = tPsOutWGPollService.findOne(tPsOutWGPoll.getfOutWGPollID());
				_tPsOutWGPoll.setfUpperLimit(tPsOutWGPoll.getfUpperLimit());
				str = tPsOutWGPollService.save(_tPsOutWGPoll).getfOutWGPollID();
			}
		} catch (Exception e) {
			str = "false";
		}
		return str;
	}
	
	@RequestMapping("tPsOutWGPoll/del")
	public @ResponseBody Map<String, Boolean> del(String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {
			tPsOutWGPollService.delete(id);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		map.put("success", result);
		return map;
	}
}
