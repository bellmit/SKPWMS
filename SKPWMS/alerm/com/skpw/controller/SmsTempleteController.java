package com.skpw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TSmsTemplete;
import com.skpw.common.DateUtil;
import com.skpw.service.TSmsChoiceItemService;
import com.skpw.service.TSmsTempleteService;

@Controller
@RequestMapping("sys")
public class SmsTempleteController {
	@Autowired
	private TSmsTempleteService tSmsTempleteService;
	
	@Autowired
	private TSmsChoiceItemService tSmsChoiceItemService;
	
	@RequestMapping("smsTemplete/list_init")
	public String list_init(Model model) {
		model.addAttribute("tSmsChoiceItemList", tSmsChoiceItemService.findAll());
		
		TSmsTemplete tSmsTemplete =null;
		if (0!=tSmsTempleteService.findAll().size()) {
			tSmsTemplete=tSmsTempleteService.findAll().get(0);
		}
		model.addAttribute("tSmsTemplete", tSmsTemplete);
		return "alerm/smsTemplete_list";
	}
		
	@RequestMapping("smsTemplete/save")
	@ResponseBody
	public String save(TSmsTemplete tSmsTemplete){
		String str = "true";
		try{
			List<TSmsTemplete> tSmsTempletes=tSmsTempleteService.findAll();
			//新增--设置创建人和创建时间
			if(0 == tSmsTempletes.size()){
				tSmsTemplete.setFcreatorId("");
				tSmsTemplete.setFcreatTime(DateUtil.gettimestamp());
				tSmsTempleteService.save(tSmsTemplete);
			}else {//更新--设置最后修改人和修改时间
			TSmsTemplete _tSmsTemplete=tSmsTempletes.get(0);
			_tSmsTemplete.setfContent(tSmsTemplete.getfContent());
			
			_tSmsTemplete.setFlastEditId("");
			_tSmsTemplete.setFlastEditTime(DateUtil.gettimestamp());
			tSmsTempleteService.save(_tSmsTemplete);
			}
			
		}catch(Exception e){
			str = "false";
			e.printStackTrace();
		}
		return str;
	}
}
