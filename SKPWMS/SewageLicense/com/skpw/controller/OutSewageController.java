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
import com.skpw.bean.TPsOutSewage;
import com.skpw.common.CreateUUID;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasOutWhereService;
import com.skpw.service.TBasPollutantService;
import com.skpw.service.TBasWaterDisStdService;
import com.skpw.service.TPsOutPermitService;
import com.skpw.service.TPsOutSewageService;

@Controller
public class OutSewageController {
	@Autowired
	private TPsOutSewageService tPsOutSewageService;
	
	@Autowired
	private TBasOutWhereService tBasOutWhereService;
	
	@Autowired
	private TBasWaterDisStdService tBasWaterDisStdService;
	
	@Autowired
	private TBasPollutantService tBasPollutantService;
	
	@Autowired
	private TPsOutPermitService tPsOutPermitService;
	
	// 跳转到排污许可证显示页面
	@RequestMapping("outSewage/list_init")
	public String list_init() {

		return "sewagelicense/sewagemange_list";
	}
		
	@RequestMapping("outSewage/list")
	public @ResponseBody Map<String, Object> list(int page, int rows,TPsOutSewage tPsOutSewage){
		Map<String, Object> map = new HashMap<String, Object>();
		Page<TPsOutSewage> pages;
		if(null!=tPsOutSewage.gettPsOutPermit() && !"".equals(tPsOutSewage.gettPsOutPermit().getfOutPID())){
			Pageable pageable = new PageRequest(page-1, rows);
			pages= tPsOutSewageService.findAllByPage(tPsOutSewage,pageable);
			map.put("total", pages.getTotalElements());
			map.put("rows", pages.getContent());
		}else {
			map.put("total", 0);
			map.put("rows", 0);
		}
		return map;
	}
	
	/*@RequestMapping("outSewage/add")
	public String add(Model model) {
		model.addAttribute("id", "new");
		return "sewagelicense/outPermitInfo";
	}*/
	@RequestMapping("outSewage/add")
	public String add(Model model,TPsOutPermit tPsOutPermit) {
		TPsOutSewage tPsOutSewage=new TPsOutSewage();
		tPsOutSewage.settPsOutPermit(tPsOutPermit);
//		model.addAttribute("fOutPID", fOutPID);
		model.addAttribute("tBasOutWhereList", tBasOutWhereService.findAll());
		model.addAttribute("tBasWaterDisStdList", tBasWaterDisStdService.findAll());
//		model.addAttribute("tBasPollutantList", tBasPollutantService.findAll());
		model.addAttribute("tPsOutSewage", tPsOutSewage);
		return "sewagelicense/outSewage";
	}
	
	@RequestMapping("outSewage/findOutPermit")
	public ModelAndView findOutPermit(String id){
		ModelAndView mav=new ModelAndView("sewagelicense/outPermitInfo");
		mav.addObject("id", id);
		return mav;
	}
	
	/*@RequestMapping("outSewage/find")
	public ModelAndView find(String id){
		ModelAndView mav=new ModelAndView("sewagelicense/enterprisejbxx");
		TPsOutSewage tPsOutSewage=null;
		tPsOutSewage=tPsOutSewageService.findOne(id);
		if (null==tPsOutSewage) {
			tPsOutSewage=new TPsOutSewage();
		}
		mav.addObject("tPsOutSewage", tPsOutSewage);
		return mav;
	}*/
	@RequestMapping("outSewage/find")
	public ModelAndView find(TPsOutSewage tPsOutSewage){
		ModelAndView mav=new ModelAndView("sewagelicense/outSewage");
//		TPsOutSewage tPsOutSewage=null;
		tPsOutSewage=tPsOutSewageService.findOne(tPsOutSewage.getfOutSewageID());
		if (null==tPsOutSewage) {
			tPsOutSewage=new TPsOutSewage();
		}
		mav.addObject("tBasOutWhereList", tBasOutWhereService.findAll());
		mav.addObject("tBasWaterDisStdList", tBasWaterDisStdService.findAll());
//		mav.addObject("tBasPollutantList", tBasPollutantService.findAll());
		mav.addObject("tPsOutSewage", tPsOutSewage);
		return mav;
	}
	
	@RequestMapping("outSewage/save")
	public void save(HttpServletResponse res, TPsOutSewage tPsOutSewage){
		String str = "true";
		try {
			try{

				//新增--设置创建人和创建时间
				if(tPsOutSewage.getfOutSewageID() == null || "".equals(tPsOutSewage.getfOutSewageID())){
//					tPsOutSewage.setfOutSewageID(CreateUUID.getUuid());
					
					TPsOutPermit tPsOutPermit=tPsOutPermitService.findOne(tPsOutSewage.gettPsOutPermit().getfOutPID());
					tPsOutSewage.settBasEnterprise(tPsOutPermit.gettBasEnterprise());
					
					tPsOutSewage.setFcreatorId("");
					tPsOutSewage.setFcreatTime(DateUtil.gettimestamp());
				}else {//更新--设置最后修改人和修改时间
				tPsOutSewage.setFlastEditId("");
				tPsOutSewage.setFlastEditTime(DateUtil.gettimestamp());
				}
				
//				tPsOutSewageService.save(tPsOutSewage);
				str=tPsOutSewageService.save(tPsOutSewage).getfOutSewageID();
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
	
	@RequestMapping("outSewage/update")
	public void update(HttpServletResponse res, TPsOutSewage tPsOutSewage){
		String str = "true";
		try {
			try{

				//设置创建人和创建时间
//				if(tPsOutSewage.getFenterId() !=null && tPsOutSewage.getFenterId() != ""){
//					tPsOutSewage.setFcreatorId("");
//					tPsOutSewage.setFcreatTime(DateUtil.gettimestamp());
//				}
				//设置最后修改人和修改时间
				tPsOutSewage.setFlastEditId("");
				tPsOutSewage.setFlastEditTime(DateUtil.gettimestamp());
				
				tPsOutSewageService.save(tPsOutSewage);
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
	
	@RequestMapping("/outSewage/delete")
	@ResponseBody
	public Map<String, Boolean> delete(String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			tPsOutSewageService.delete(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}
}
