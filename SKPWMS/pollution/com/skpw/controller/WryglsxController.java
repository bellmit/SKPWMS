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
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasCreditGrade;
import com.skpw.bean.TBasCtrlLevel;
import com.skpw.bean.TBasEnterManagement;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasCreditGradeService;
import com.skpw.service.TBasCtrlLevelService;
import com.skpw.service.WryglsxService;

@Controller
public class WryglsxController {

	@Resource
	private WryglsxService wryglsxService;
	
	@Resource
	private TBasCreditGradeService tBasCreditGradeService;
	
	@Resource
	private TBasCtrlLevelService tBasCtrlLevelService;
	
	@RequestMapping("/wryglsxController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		int total = wryglsxService.queryCount();
		Pageable pageable = new PageRequest(page-1, rows);
		List<TBasEnterManagement> list = wryglsxService.findByPage(pageable);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	@RequestMapping("/wryglsxController/save")
	public void save(HttpServletResponse res, TBasEnterManagement tee){
		String str = "true";
		try {
			try{
				//设置创建人和创建时间
				if(tee.getFenterId() !=null && tee.getFenterId() != ""){
					tee.setFcreatorId("");
					tee.setFcreatTime(DateUtil.gettimestamp());
				}
				//设置最后修改人和修改时间
				tee.setFlastEditId("");
				tee.setFlastEditTime(DateUtil.gettimestamp());
				
				wryglsxService.save(tee);
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
	
	@RequestMapping("/wryglsxController/del")
	public @ResponseBody Map<String, Object> del(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			wryglsxService.del(id);
			map.put("result", true);
		}catch(Exception e){
			map.put("result", false);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询该wry的管理属性
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/wryglsxController/findOne")
	public String findOne(Model model, String id){
		//监管级别
		List<TBasCtrlLevel> tBasCtrlLevelList = tBasCtrlLevelService.findAll();
		//信用等级
		List<TBasCreditGrade> tBasCreditGradeList = tBasCreditGradeService.findAll();
		
		TBasEnterManagement tbe = wryglsxService.findWry(id);
		if(tbe == null){
			tbe = new TBasEnterManagement();
			tbe.setFenterId(id);
		}
		model.addAttribute("tbe", tbe);
		model.addAttribute("tBasCtrlLevelList", tBasCtrlLevelList);
		model.addAttribute("tBasCreditGradeList", tBasCreditGradeList);
		return "qyxxgl/wryjbxx/wryglsx";
	}
}
