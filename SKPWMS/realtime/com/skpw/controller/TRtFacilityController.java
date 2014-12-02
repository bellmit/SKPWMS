package com.skpw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TRtFacility;
import com.skpw.service.TRtFacilityService;

@Controller
@RequestMapping("/wry")
public class TRtFacilityController {

	@Resource
	private TRtFacilityService tRtFacilityService;
	
	@RequestMapping("/tRtFacility/list")
	public @ResponseBody Map<String, Object> tRtFacilityList(String _ffacilityNo, String _ffacilityName, String wry_id, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<TRtFacility> pages = tRtFacilityService.findAllByPage(_ffacilityNo, _ffacilityName, wry_id, page, rows);
		map.put("total", pages.getTotalElements());
		map.put("rows", pages.getContent());
		return map;
	}
	
	@RequestMapping("/tRtFacility/save")
	public void save(TRtFacility tRtFacility,HttpServletResponse res) {
		String str = "";
//		String id = UUID.randomUUID().toString().replace("-", "");
//		if( null == tRtFacility.getFfacilityId() || "" == tRtFacility.getFfacilityId().trim() || tRtFacility.getFfacilityId().equals("")) {
//			tRtFacility.setFfacilityId(id);
//		}
		try {
			tRtFacilityService.save(tRtFacility);
			str = "{\"success\":\"true\",\"msg\":\"保存成功！\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"msg\":\"保存失败！\"}";
		}
		try {
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.getWriter().print(str);
			res.getWriter().flush();
			res.getWriter().close();
		} catch (IOException e) {
		}
	}
	
	@RequestMapping("/tRtFacility/edit")
	public @ResponseBody TRtFacility findOne(String id) {
		return tRtFacilityService.findOne(Integer.valueOf(id));
	}
	
	@RequestMapping("/tRtFacility/del")
	public void del(String ids,HttpServletResponse res) {
		String str = "";
		try {
			tRtFacilityService.del(ids);
			str = "{\"success\":\"true\",\"msg\":\"删除成功！\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"msg\":\"删除失败！\"}";
		}
		try {
			res.setHeader("Content-type", "text/html;charset=UTF-8");
			res.getWriter().print(str);
			res.getWriter().flush();
			res.getWriter().close();
		} catch (IOException e) {
		}
	}
}
