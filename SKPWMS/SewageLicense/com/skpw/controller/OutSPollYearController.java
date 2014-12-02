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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutSPollYear;
import com.skpw.bean.TPsOutSewage;
import com.skpw.common.CreateUUID;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasOutWhereService;
import com.skpw.service.TBasWaterDisStdService;
import com.skpw.service.TPsOutSPollYearService;
import com.skpw.service.TPsOutSewageService;

@Controller
public class OutSPollYearController {
	@Autowired
	private TPsOutSPollYearService tPsOutSPollYearService;
	
	@Autowired
	private TPsOutSewageService tPsOutSewageService;
	
	@Autowired
	private TBasOutWhereService tBasOutWhereService;
	
	@Autowired
	private TBasWaterDisStdService tBasWaterDisStdService;
	
	// 跳转到排污许可证显示页面
	@RequestMapping("outSPollYear/list_init")
	public String list_init() {

		return "sewagelicense/sewagemange_list";
	}
		
	@RequestMapping("outSPollYear/list")
	public @ResponseBody Map<String, Object> list(int page, int rows,TPsOutSPollYear tPsOutSPollYear){
		Map<String, Object> map = new HashMap<String, Object>();
		Page<TPsOutSPollYear> pages;
		if(null!=tPsOutSPollYear.gettPsOutSewage() && !"".equals(tPsOutSPollYear.gettPsOutSewage().getfOutSewageID())){
			Sort sort=new Sort(new Sort.Order(Direction.ASC, "tBasPollutant.fPollutantName"),new Sort.Order(Direction.ASC, "fYearID"));
			Pageable pageable = new PageRequest(page-1, rows,sort);
			pages= tPsOutSPollYearService.findAllByPage(tPsOutSPollYear,pageable);
			map.put("total", pages.getTotalElements());
			map.put("rows", pages.getContent());
		}else {
			map.put("total", 0);
			map.put("rows", 0);
		}
		return map;
	}
	
	@RequestMapping("outSPollYear/add")
	public String add(Model model,TPsOutPermit tPsOutPermit) {
		TPsOutSPollYear tPsOutSPollYear=new TPsOutSPollYear();
		tPsOutSPollYear.settPsOutPermit(tPsOutPermit);
//		model.addAttribute("fOutPID", fOutPID);
		model.addAttribute("tBasOutWhereList", tBasOutWhereService.findAll());
		model.addAttribute("tBasWaterDisStdList", tBasWaterDisStdService.findAll());
		model.addAttribute("tPsOutSPollYear", tPsOutSPollYear);
		return "sewagelicense/outSewage";
	}
	
	@RequestMapping("outSPollYear/findOutPermit")
	public ModelAndView findOutPermit(String id){
		ModelAndView mav=new ModelAndView("sewagelicense/outPermitInfo");
		mav.addObject("id", id);
		return mav;
	}
	
	@RequestMapping("outSPollYear/find")
	public ModelAndView find(TPsOutSPollYear tPsOutSPollYear){
		ModelAndView mav=new ModelAndView("sewagelicense/outSewage");
//		TPsOutSPollYear tPsOutSPollYear=null;
		tPsOutSPollYear=tPsOutSPollYearService.findOne(tPsOutSPollYear.getfOutSPollYearID());
		if (null==tPsOutSPollYear) {
			tPsOutSPollYear=new TPsOutSPollYear();
		}
		mav.addObject("tBasOutWhereList", tBasOutWhereService.findAll());
		mav.addObject("tBasWaterDisStdList", tBasWaterDisStdService.findAll());
		mav.addObject("tPsOutSPollYear", tPsOutSPollYear);
		return mav;
	}
	
	@RequestMapping("outSPollYear/save")
	public void save(HttpServletResponse res, TPsOutSPollYear tPsOutSPollYear){
		String str = "true";
		try {
			try{

				TPsOutSewage tPsOutSewage=tPsOutSewageService.findOne(tPsOutSPollYear.gettPsOutSewage().getfOutSewageID());
				TPsOutPermit tPsOutPermit=tPsOutSewage.gettPsOutPermit();
				//新增--设置创建人和创建时间
				if(tPsOutSPollYear.getfOutSPollYearID() == null || "".equals(tPsOutSPollYear.getfOutSPollYearID())){
					tPsOutSPollYear.setfOutSPollYearID(CreateUUID.getUuid());
					tPsOutSPollYear.settPsOutPermit(tPsOutPermit);
					
					tPsOutSPollYear.setFcreatorId("");
					tPsOutSPollYear.setFcreatTime(DateUtil.gettimestamp());
					str=tPsOutSPollYearService.save(tPsOutSPollYear,tPsOutPermit).getfOutSPollYearID();
				}else {//更新--设置最后修改人和修改时间
				TPsOutSPollYear _tPsOutSPollYear=tPsOutSPollYearService.findOne(tPsOutSPollYear.getfOutSPollYearID());
				_tPsOutSPollYear.setfYearID(tPsOutSPollYear.getfYearID());
//				_tPsOutSPollYear.settBasPollutant(tPsOutSPollYear.gettBasPollutant());
				_tPsOutSPollYear.setfDischarge(tPsOutSPollYear.getfDischarge());
				
				_tPsOutSPollYear.setFlastEditId("");
				_tPsOutSPollYear.setFlastEditTime(DateUtil.gettimestamp());
				str=tPsOutSPollYearService.save(_tPsOutSPollYear,tPsOutPermit).getfOutSPollYearID();
				}
				
			}catch(Exception e){
				str = "false";
				e.printStackTrace();
			}
			res.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("outSPollYear/update")
	public void update(HttpServletResponse res, TPsOutSPollYear tPsOutSPollYear){
		/*String str = "true";
		try {
			try{

				//设置创建人和创建时间
//				if(tPsOutSPollYear.getFenterId() !=null && tPsOutSPollYear.getFenterId() != ""){
//					tPsOutSPollYear.setFcreatorId("");
//					tPsOutSPollYear.setFcreatTime(DateUtil.gettimestamp());
//				}
				//设置最后修改人和修改时间
				tPsOutSPollYear.setFlastEditId("");
				tPsOutSPollYear.setFlastEditTime(DateUtil.gettimestamp());
				
				tPsOutSPollYearService.save(tPsOutSPollYear);
			}catch(Exception e){
				str = "false";
				e.printStackTrace();
			}
			res.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@RequestMapping("/outSPollYear/delete")
	@ResponseBody
	public Map<String, Boolean> delete(String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			tPsOutSPollYearService.delete(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}
}
