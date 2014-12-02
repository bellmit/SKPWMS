package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasZlkzq;
import com.skpw.service.ZlkzqService;

@Controller
public class ZlkzqController {

	@Resource
	private ZlkzqService zlkzqService;
	
	//分页查询
	@RequestMapping("/zlkzqController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows){
		//用户存储数据，转为json
		Map<String, Object> map =  new HashMap<String, Object>();
		//总条数
		int total = zlkzqService.queryCount();
		Pageable pageable = new PageRequest(page-1, rows);
		//查询出当前页面的信息
		List<TBasZlkzq> list = zlkzqService.findByPage(pageable);
				
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	//保存
	@RequestMapping("/zlkzqController/save")
	public @ResponseBody Map<String, Object> save(TBasZlkzq zlkzq){
		//用户存储数据，转为json
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			zlkzqService.save(zlkzq);;
			map.put("result", true);
		}catch(Exception e){
			map.put("result", false);
			e.printStackTrace();
		}
		return map;
	}
	
	//删除
	@RequestMapping("/zlkzqController/del")
	public @ResponseBody Map<String, Object> del(String id){
		//用户存储数据，转为json
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			zlkzqService.del(id);;
			map.put("result", true);
		}catch(Exception e){
			map.put("result", false);
			e.printStackTrace();
		}
		return map;
	}
}
