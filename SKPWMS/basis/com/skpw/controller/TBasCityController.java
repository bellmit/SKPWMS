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

import com.skpw.bean.TBasCity;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasCityService;
import com.skpw.service.TBasCitySpecs;

@Controller
public class TBasCityController {

	@Resource
	private TBasCityService tBasCityService;
	
	/**
	 * 查询disable为fasle的city
	 * @return
	 */
	/*@RequestMapping("/tBasCityController/getList")
	public @ResponseBody List<TreeUtilBean> getList(){
		//设置条件
		TBasCity tbc = new TBasCity();
		//tbc.setFisDisable(false);
		Specification<TBasCity> spf = TBasCitySpecs.getCities(tbc);
		List<TBasCity> list = tBasCityService.findTBasCityList(spf);
		//转换实体类，用户tree显示
		List<TreeUtilBean> tList = new ArrayList<TreeUtilBean>();
		for(TBasCity tb:list){
			TreeUtilBean tub = new TreeUtilBean();
			tub.setId(tb.getFcityCode());
			tub.setText(tb.getFcityName());
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", "<%=path%>/tBasCityController/getList?aaaaaaaa");
			map.put("text", "<%=path%>/tBasCityController/getList");
			map = new HashMap<String, String>();
			map.put("id", "111111");
			map.put("text", "2222");
			tList.add(tub);
		}
		return tList;
	}*/
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/tBasCityController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows){
		//容器
		Map<String, Object> map = new HashMap<String, Object>();
		//查询数据总条数
		int total = tBasCityService.queryCount();
		//分页条件
		Pageable pageable = new PageRequest(page-1, rows);
		List<TBasCity> list = tBasCityService.findByPage(pageable);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 保存
	 * @param res
	 * @param tee
	 */
	@RequestMapping("/tBasCityController/save")
	public void save(HttpServletResponse res, TBasCity tee){
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
				
				tBasCityService.save(tee);
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
	@RequestMapping("/tBasCityController/del")
	public @ResponseBody Map<String, Object> del(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			tBasCityService.del(id);
			map.put("result", true);
		}catch(Exception e){
			map.put("result", false);
			e.printStackTrace();
		}
		return map;
	}
}
