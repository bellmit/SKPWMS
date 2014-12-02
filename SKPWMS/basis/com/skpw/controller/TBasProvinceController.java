package com.skpw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.skpw.bean.TBasProvince;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasProvinceService;
import com.skpw.service.TBasProvinceSpecs;

@Controller
public class TBasProvinceController {

	@Resource
	private TBasProvinceService tBasProvinceService;
	
	/**
	 * 查询disable为fasle的city
	 * @return
	 */
	@RequestMapping("/tBasProvinceController/findList")
	public @ResponseBody List<TBasProvince> findList(){
		//设置条件
		TBasProvince tbc = new TBasProvince();
		tbc.setFisDisable(false);
		Specification<TBasProvince> spf = TBasProvinceSpecs.getOneForSearch(tbc);
		//查询
		List<TBasProvince> list = tBasProvinceService.getList(spf);
		return list;
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/tBasProvinceController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows){
		//容器
		Map<String, Object> map = new HashMap<String, Object>();
		//查询数据总条数
		int total = tBasProvinceService.queryCount();
		//分页条件
		Pageable pageable = new PageRequest(page-1, rows);
		List<TBasProvince> list = tBasProvinceService.findByPage(pageable);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 保存
	 * @param res
	 * @param tee
	 */
	@RequestMapping("/tBasProvinceController/save")
	public void save(HttpServletResponse res, TBasProvince tee){
		String str = "true";
		try {
			try{
				//设置创建人和创建时间
				if(tee.getFcreatorId() !=null && tee.getFcreatorId() != ""){
					tee.setFcreatorId("");
					tee.setFcreatTime(DateUtil.gettimestamp());
				}
				//设置最后修改人和修改时间
				tee.setFlastEditId("");
				tee.setFlastEditTime(DateUtil.gettimestamp());
				
				tBasProvinceService.save(tee);
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
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/tBasProvinceController/del")
	public @ResponseBody Map<String, Object> del(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			tBasProvinceService.del(id);
			map.put("result", true);
		}catch(Exception e){
			map.put("result", false);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据id查询
	 * @param model
	 * @param id
	 * @return
	 */
	/*
	@RequestMapping("/tBasProvinceController/findOne")
	public String findOne(Model model, String id){
		TBasProvince tbe = tBasProvinceService.findOne(id);
		if(tbe == null){
			tbe = new TBasProvince();
			tbe.setFenterId(id);
		}
		model.addAttribute("tbe", tbe);
		return "qyxxgl/wryjbxx/wryglsx";
	}*/
}
