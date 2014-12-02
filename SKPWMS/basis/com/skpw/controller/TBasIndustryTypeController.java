package com.skpw.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.skpw.bean.TBasIndustryType;
import com.skpw.bean.TreeBean;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasIndustryTypeService;
import com.skpw.service.TBasIndustryTypeSpecs;

@Controller
public class TBasIndustryTypeController {
	@Resource
	private TBasIndustryTypeService tBasIndustryTypeService;
	
	//分页查询
	@RequestMapping("/tBasIndustryTypeController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows, String id){
		//用户存储数据，转为json
		Map<String, Object> map = new HashMap<String, Object>();
		//总条数
		int total = tBasIndustryTypeService.queryCount();
		Pageable pageable = new PageRequest(page-1, rows);
		//判断查询条件
		TBasIndustryType tbit = new TBasIndustryType();
		if(id!=null && id!=""){
			tbit.setFparentId(id);
		}
		Specification<TBasIndustryType> spf = TBasIndustryTypeSpecs.getOneForSearch(tbit);
		//查询出当前页面的信息
		List<TBasIndustryType> list = tBasIndustryTypeService.findByPage(spf, pageable);
		
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	//保存
	@RequestMapping("/tBasIndustryTypeController/save")
	public void save(HttpServletResponse res, TBasIndustryType bean){
		String str = "true";
		try {
			
			try{
				String id = bean.getFindustryTypeId();
				//判断是否为新添加的，是则设置创建人和创建时间
				if(id==null || id==""){
					bean.setFcreatorId("creator");
					bean.setFcreatTime(DateUtil.gettimestamp().toString());
				}
				//设置最后修改人和时间
				bean.setFlastEditId("editer");
				bean.setFlastEditTime(DateUtil.gettimestamp());
				tBasIndustryTypeService.save(bean);
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
	
	//删除
	@RequestMapping("/tBasIndustryTypeController/del")
	public @ResponseBody Map<String, Object> del(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		Boolean b = true;
		try{
			tBasIndustryTypeService.del(id);
		}catch(Exception e){
			b = false;
			e.printStackTrace();
		}
		map.put("result", b);
		return map;
	}
	
	/**
	 * 查询全部行业类别，拼接为tree
	 * @return
	 */
	@RequestMapping("/tBasIndustryTypeController/findAllOrderByID")
	public @ResponseBody List<TreeBean> findAllOrderByID() {
		List<TreeBean> list = new ArrayList<TreeBean>();
		List<TBasIndustryType> titlist = tBasIndustryTypeService.findAllOrderByID();
		for(TBasIndustryType tbi:titlist){
			TreeBean tb = new TreeBean();
			tb.setId(tbi.getFindustryTypeId().trim());
			if(tbi.getFparentId() == null){
				tb.setPid("0");
			}else{
				tb.setPid(tbi.getFparentId());
			}
			tb.setName(tbi.getFindustryTypeName());
			list.add(tb);
		}
		return list;
	}
	
}
