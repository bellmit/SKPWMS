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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TPsOutSewagebak;
import com.skpw.service.PfkxxService;

@Controller
public class PfkxxController {
	
	@Resource
	private PfkxxService pfkxxService;
	
	@RequestMapping("/pfkxxController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows){
		//用户存储数据，转为json
		Map<String, Object> map = new HashMap<String, Object>();
		//总条数
		int total = pfkxxService.queryCount();
		Pageable pageable = new PageRequest(page-1, rows);
		//查询出当前页面的信息
		List<TPsOutSewagebak> list = pfkxxService.findByPage(pageable);
		
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	//保存
	@RequestMapping("/pfkxxController/save")
	public void save(HttpServletResponse res, TPsOutSewagebak pfk){
		String str = "true";
		try {
			
			try{
				pfkxxService.save(pfk);
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
	@RequestMapping("/pfkxxController/del")
	public @ResponseBody Map<String, Object> del(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		Boolean b = true;
		try{
			pfkxxService.del(id);
		}catch(Exception e){
			b = false;
			e.printStackTrace();
		}
		map.put("result", b);
		return map;
	}
}
