package com.skpw.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TTcControler;
import com.skpw.common.CreateUUID;
import com.skpw.common.DateUtil;
import com.skpw.common.Page;
import com.skpw.service.TTcControlerService;

@Controller
public class TTcControlerController {

	@Resource
	private TTcControlerService tTcControlerService;

	// 获取某企业的总量控制器列表 json for tree
	@RequestMapping("/tTcControlerController/findTTcControlersOfWry")
	public @ResponseBody
	List<Map<String, Object>> findTTcControlersOfWry(String id) {
		List<Map<String, Object>> list = tTcControlerService
				.findTTcControlersOfWry(id);
		return list;
	}

	@RequestMapping("/tTcControlerController/showTTcControlers")
	@ResponseBody
	public List showTTcControlers() {
		List<TTcControler> tTcControlers = tTcControlerService
				.showControllerInfo();
		return tTcControlers;
	}

	@RequestMapping("/tTcControlerController/showTTcControlersByPage")
	@ResponseBody
	public Map showTTcControlersByPage(Model model, TTcControler tcControler,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		long count = tTcControlerService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<TTcControler> tcControlers = tTcControlerService
				.showControllerByPage(pageRequest).getContent();

		map.put("total", count);
		map.put("rows", tcControlers);
		return map;
	}
	
	
	/**
	 * 保存总量控制器信息
	 * @param ttc
	 * @return
	 */
	@RequestMapping("/tTcControlerController/save")
	public void save(HttpServletResponse res, TTcControler ttc){
		//判断是否保存成功
		String b = "true";
		String id = ttc.getFcontrolerId();
		List<TTcControler> list = tTcControlerService.findByFenterId(ttc.getFenterId());
		if(list != null && list.size() > 0 && list.get(0) != null) {
			id = list.get(0).getFcontrolerId();
			ttc.setFcontrolerId(id);
		}
		try {
			try{
				if(id==null || id==""){
					//获取32位UUID
					id = CreateUUID.getUuid();
					//设置主键
					ttc.setFcontrolerId(id);
				}
				//设置创建人和创建时间
				if(ttc.getFcreatorId() ==null || ttc.getFcreatorId() == ""){
					ttc.setFcreatorId("");
					ttc.setFcreatTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				}
				//设置最后修改人和修改时间
				ttc.setFlastEditId("");
				ttc.setFlastEditTime(DateUtil.gettimestamp());
				
				tTcControlerService.save(ttc);
			}catch(Exception e){
				b = "false";
				e.printStackTrace();
			}
			res.getWriter().print(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据企业id查询总量控制器信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/tTcControlerController/findByFenterId")
	public String findByFenterId(Model model, String wry_id){
		List<TTcControler> list = tTcControlerService.findByFenterId(wry_id);
		TTcControler ttc = new TTcControler();
		if(list.size()>0){
			ttc = list.get(0);
		}else{
			ttc.setFenterId(wry_id);
		}
		model.addAttribute("tbe", ttc);
		return "qyxxgl/wryjbxx/controler";
	}
}
