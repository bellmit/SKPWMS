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

import com.skpw.bean.TBasEnterEnviron;
import com.skpw.common.DateUtil;
import com.skpw.service.WryhbsxService;

@Controller
public class WryhbsxController {

	@Resource
	private WryhbsxService wryhbsxService;
	
	@RequestMapping("/wryhbsxController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		int total = wryhbsxService.queryCount();
		Pageable pageable = new PageRequest(page-1, rows);
		List<TBasEnterEnviron> list = wryhbsxService.findByPage(pageable);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	@RequestMapping("/wryhbsxController/save")
	public void save(HttpServletResponse res, TBasEnterEnviron tee){
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
				
				wryhbsxService.save(tee);
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
	
	@RequestMapping("/wryhbsxController/del")
	public @ResponseBody Map<String, Object> del(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			wryhbsxService.del(id);
			map.put("result", true);
		}catch(Exception e){
			map.put("result", false);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询该污染源的环保属性
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/wryhbsxController/findOne")
	public String findOne(Model model, String id){
		TBasEnterEnviron tbe = wryhbsxService.findWry(id);
		if(tbe == null){
			tbe = new TBasEnterEnviron();
			tbe.setFenterId(id);
		} 
		model.addAttribute("tbe", tbe);
		return "qyxxgl/wryjbxx/wryhbsx";
	}
}
