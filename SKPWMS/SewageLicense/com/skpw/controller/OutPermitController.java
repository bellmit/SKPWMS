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

import com.skpw.bean.TBasCounty;
import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TPsOutPermit;
import com.skpw.common.CreateUUID;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasCountyService;
import com.skpw.service.TPsOutPermitService;
import com.skpw.service.WryjbxxService;

@Controller
public class OutPermitController {
	@Autowired
	private TPsOutPermitService tPsOutPermitService;
	@Autowired
	private TBasCountyService tBasCountyService;
	@Autowired
	private WryjbxxService wryjbxxService;
	
	// 跳转到排污许可证显示页面
	@RequestMapping("outPermit/list_init")
	public String list_init(Model model) {
		model.addAttribute("tPsOutPermit", new TPsOutPermit());
		//行政区域:深圳的区县，深圳市的FCityID是440300
		List<TBasCounty> tBasCountyList = tBasCountyService.findCountyForCity("440300");
		model.addAttribute("tBasCountyList", tBasCountyList);
		return "sewagelicense/sewagemange_list";
	}
	
	// 跳转到排污许可证list页面
		@RequestMapping("outPermit/pwxkzList")
		public String pwxkzList(Model model,String wry_id) {
			model.addAttribute("tPsOutPermit", new TPsOutPermit());
			//行政区域:深圳的区县，深圳市的FCityID是440300
			List<TBasCounty> tBasCountyList = tBasCountyService.findCountyForCity("440300");
			model.addAttribute("tBasCountyList", tBasCountyList);
			return "sewagelicense/sewagemange_list";
		}
		
	@RequestMapping("outPermit/list")
	public @ResponseBody Map<String, Object> list(int page, int rows,TPsOutPermit tPsOutPermit){
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page-1, rows);
		Page<TPsOutPermit> pages= tPsOutPermitService.findAllByPage(tPsOutPermit,pageable);
		map.put("total", pages.getTotalElements());
		map.put("rows", pages.getContent());
		return map;
	}
	
	/*@RequestMapping("outPermit/add")
	public String add(Model model) {
		model.addAttribute("id", "new");
		return "sewagelicense/outPermitInfo";
	}*/
	@RequestMapping("outPermit/add")
	public String add(Model model,String wrybh) {
		TBasEnterprise tbe = wryjbxxService.findWry(wrybh);
		TPsOutPermit tpsop = new TPsOutPermit();
		tpsop.settBasEnterprise(tbe);
		model.addAttribute("id", "new");
		model.addAttribute("tPsOutPermit", tpsop);
		return "sewagelicense/sewagelicense";
	}
	
	@RequestMapping("outPermit/findOutPermit")
	public ModelAndView findOutPermit(String id){
		ModelAndView mav=new ModelAndView("sewagelicense/outPermitInfo");
		mav.addObject("id", id);
		return mav;
	}
	
	/*@RequestMapping("outPermit/find")
	public ModelAndView find(String id){
		ModelAndView mav=new ModelAndView("sewagelicense/enterprisejbxx");
		TPsOutPermit tPsOutPermit=null;
		tPsOutPermit=tPsOutPermitService.findOne(id);
		if (null==tPsOutPermit) {
			tPsOutPermit=new TPsOutPermit();
		}
		mav.addObject("tPsOutPermit", tPsOutPermit);
		return mav;
	}*/
	@RequestMapping("outPermit/find")
	public ModelAndView find(String id){
		ModelAndView mav=new ModelAndView("sewagelicense/sewagelicense");
		TPsOutPermit tPsOutPermit=null;
		tPsOutPermit=tPsOutPermitService.findOne(id);
		if (null==tPsOutPermit) {
			tPsOutPermit=new TPsOutPermit();
		}
		mav.addObject("tPsOutPermit", tPsOutPermit);
		return mav;
	}
	
	@RequestMapping("/outPermit/save")
	public void save(HttpServletResponse res, TPsOutPermit tPsOutPermit){
		String str = "true";
		try {
			try{

				//新增--设置创建人和创建时间
				if(tPsOutPermit.getfOutPID() == null || "".equals(tPsOutPermit.getfOutPID())){
					tPsOutPermit.setfOutPID(CreateUUID.getUuid());
					tPsOutPermit.setFcreatorId("");
					tPsOutPermit.setFcreatTime(DateUtil.gettimestamp());
					str=tPsOutPermitService.save(tPsOutPermit).getfOutPID();
				}else {//更新--设置最后修改人和修改时间
				TPsOutPermit _tPsOutPermit=tPsOutPermitService.findOne(tPsOutPermit.getfOutPID());
				_tPsOutPermit.setfOutPCode(tPsOutPermit.getfOutPCode());
				_tPsOutPermit.setfVaildDate(tPsOutPermit.getfVaildDate());
				_tPsOutPermit.setfEndDate(tPsOutPermit.getfEndDate());
				_tPsOutPermit.setfIsSewage(tPsOutPermit.getfIsSewage());
				_tPsOutPermit.setfIsWasteGas(tPsOutPermit.getfIsWasteGas());
				_tPsOutPermit.setfIsNoise(tPsOutPermit.getfIsNoise());
				_tPsOutPermit.setfIssuingDate(tPsOutPermit.getfIssuingDate());
				_tPsOutPermit.setfEmpID(tPsOutPermit.getfEmpID());
				
				_tPsOutPermit.setFlastEditId("");
				_tPsOutPermit.setFlastEditTime(DateUtil.gettimestamp());
				str=tPsOutPermitService.save(_tPsOutPermit).getfOutPID();
				}
				
//				tPsOutPermitService.save(tPsOutPermit);
//				str=tPsOutPermitService.save(tPsOutPermit).getfOutPID();
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
	
	@RequestMapping("/outPermit/update")
	public void update(HttpServletResponse res, TPsOutPermit tPsOutPermit){
		String str = "true";
		try {
			try{

				//设置创建人和创建时间
//				if(tPsOutPermit.getFenterId() !=null && tPsOutPermit.getFenterId() != ""){
//					tPsOutPermit.setFcreatorId("");
//					tPsOutPermit.setFcreatTime(DateUtil.gettimestamp());
//				}
				//设置最后修改人和修改时间
				tPsOutPermit.setFlastEditId("");
				tPsOutPermit.setFlastEditTime(DateUtil.gettimestamp());
				
				tPsOutPermitService.save(tPsOutPermit);
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
	
	@RequestMapping("/outPermit/delete")
	@ResponseBody
	public Map<String, Boolean> delete(String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			tPsOutPermitService.delete(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}
	
	@RequestMapping("/outPermit/findByEnterpriseId")
	@ResponseBody
	public Map<String, Object> findByEnterId(String enterpriseId){
		Map<String, Object> map=new HashMap<String,Object>();
		TPsOutPermit tPsOutPermit= tPsOutPermitService.findByEnterpriseId(enterpriseId);
		map.put("tPsOutPermit", tPsOutPermit);
		return map;
	}
}
