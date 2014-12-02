package com.skpw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutSPoll;
import com.skpw.bean.TSysLog;
import com.skpw.common.CreateUUID;
import com.skpw.common.DateUtil;
import com.skpw.common.Globals;
import com.skpw.service.TBasOutWhereService;
import com.skpw.service.TBasWaterDisStdService;
import com.skpw.service.TPsOutSPollService;
import com.skpw.service.TPsOutSewageService;
import com.skpw.service.TPsOutWGPollService;

@Controller
public class OutSPollController {
	@Autowired
	private TPsOutSPollService tPsOutSPollService;
	
	@Autowired
	private TPsOutSewageService tPsOutSewageService;
	
	@Autowired
	private TBasOutWhereService tBasOutWhereService;
	
	@Autowired
	private TBasWaterDisStdService tBasWaterDisStdService;
	
	@Autowired
	private TPsOutWGPollService tPsOutWGPollService;
	
	// 跳转到排污许可证显示页面
	@RequestMapping("outSPoll/list_init")
	public String list_init() {

		return "sewagelicense/sewagemange_list";
	}
		
	@RequestMapping("outSPoll/list")
	public @ResponseBody Map<String, Object> list(int page, int rows,TPsOutSPoll tPsOutSPoll){
		Map<String, Object> map = new HashMap<String, Object>();
		Page<TPsOutSPoll> pages;
		if(null!=tPsOutSPoll.gettPsOutSewage() && !"".equals(tPsOutSPoll.gettPsOutSewage().getfOutSewageID())){
			Pageable pageable = new PageRequest(page-1, rows);
			pages= tPsOutSPollService.findAllByPage(tPsOutSPoll,pageable);
			map.put("total", pages.getTotalElements());
			map.put("rows", pages.getContent());
		}else {
			map.put("total", 0);
			map.put("rows", 0);
		}
		return map;
	}
	
	@RequestMapping("outSPoll/add")
	public String add(Model model,TPsOutPermit tPsOutPermit) {
		TPsOutSPoll tPsOutSPoll=new TPsOutSPoll();
		tPsOutSPoll.settPsOutPermit(tPsOutPermit);
//		model.addAttribute("fOutPID", fOutPID);
		model.addAttribute("tBasOutWhereList", tBasOutWhereService.findAll());
		model.addAttribute("tBasWaterDisStdList", tBasWaterDisStdService.findAll());
		model.addAttribute("tPsOutSPoll", tPsOutSPoll);
		return "sewagelicense/outSewage";
	}
	
	@RequestMapping("outSPoll/findOutPermit")
	public ModelAndView findOutPermit(String id){
		ModelAndView mav=new ModelAndView("sewagelicense/outPermitInfo");
		mav.addObject("id", id);
		return mav;
	}
	
	@RequestMapping("outSPoll/find")
	public ModelAndView find(TPsOutSPoll tPsOutSPoll){
		ModelAndView mav=new ModelAndView("sewagelicense/outSewage");
//		TPsOutSPoll tPsOutSPoll=null;
		tPsOutSPoll=tPsOutSPollService.findOne(tPsOutSPoll.getfOutSPollID());
		if (null==tPsOutSPoll) {
			tPsOutSPoll=new TPsOutSPoll();
		}
		mav.addObject("tBasOutWhereList", tBasOutWhereService.findAll());
		mav.addObject("tBasWaterDisStdList", tBasWaterDisStdService.findAll());
		mav.addObject("tPsOutSPoll", tPsOutSPoll);
		return mav;
	}
	
	@RequestMapping("outSPoll/save")
	@ResponseBody
	public String save(TPsOutSPoll tPsOutSPoll){
		String str = "";
			try{

				//新增--设置创建人和创建时间
				if(tPsOutSPoll.getfOutSPollID() == null || "".equals(tPsOutSPoll.getfOutSPollID())){
					tPsOutSPoll.setfOutSPollID(CreateUUID.getUuid());
					
					TPsOutPermit tPsOutPermit=tPsOutSewageService.findOne(tPsOutSPoll.gettPsOutSewage().getfOutSewageID()).gettPsOutPermit();
					tPsOutSPoll.settPsOutPermit(tPsOutPermit);
					
					tPsOutSPoll.setFcreatorId("");
					tPsOutSPoll.setFcreatTime(DateUtil.gettimestamp());
					str=tPsOutSPollService.save(tPsOutSPoll).getfOutSPollID();
				}else {//更新--设置最后修改人和修改时间
				TPsOutSPoll _tPsOutSPoll=tPsOutSPollService.findOne(tPsOutSPoll.getfOutSPollID());
//				_tPsOutSPoll.settBasPollutant(tPsOutSPoll.gettBasPollutant());
				_tPsOutSPoll.setfUpperLimit(tPsOutSPoll.getfUpperLimit());
				
				_tPsOutSPoll.setFlastEditId("");
				_tPsOutSPoll.setFlastEditTime(DateUtil.gettimestamp());
				str=tPsOutSPollService.save(_tPsOutSPoll).getfOutSPollID();
				}
				
			}catch(Exception e){
				str = "false";
				e.printStackTrace();
			}
		return str;
	}
	
	@RequestMapping("outSPoll/update")
	public void update(HttpServletResponse res, TPsOutSPoll tPsOutSPoll){
		String str = "true";
		try {
			try{

				//设置创建人和创建时间
//				if(tPsOutSPoll.getFenterId() !=null && tPsOutSPoll.getFenterId() != ""){
//					tPsOutSPoll.setFcreatorId("");
//					tPsOutSPoll.setFcreatTime(DateUtil.gettimestamp());
//				}
				//设置最后修改人和修改时间
				tPsOutSPoll.setFlastEditId("");
				tPsOutSPoll.setFlastEditTime(DateUtil.gettimestamp());
				
				tPsOutSPollService.save(tPsOutSPoll);
			}catch(Exception e){
				str = "false";
				e.printStackTrace();
			}
			res.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/outSPoll/delete")
	@ResponseBody
	public Map<String, Boolean> delete(String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			tPsOutSPollService.delete(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}
	
	@RequestMapping("/outSPoll/distinctPollutant")
	@ResponseBody
	public List<Map<String, String>> distinctPollutant(String fOutPID) {
		List<Map<String, String>> list1 = tPsOutSPollService.distinct(fOutPID);
		List<Map<String, String>> list2 = tPsOutWGPollService.distinct(fOutPID);
		if(list1 != null && list2 != null && list2.size() > 0) {
			list1.addAll(list2);
		}
		return list1;
	}
}
