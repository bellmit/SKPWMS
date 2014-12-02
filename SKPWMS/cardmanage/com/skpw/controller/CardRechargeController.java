package com.skpw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hjy ic卡充值管理
 */

@Controller
public class CardRechargeController {

	// 跳转到充值界面

	@RequestMapping("/card/initToCardRechange")
	public String initToCardRechange() {

		return "/card/cardrechange";

	}
	
	
	//保存充值信息到数据库
	@RequestMapping("/card/saveToCardRechange")
	@ResponseBody
	public String saveToCardRechange() {

		String result = "";
		try {
			

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		
		
	}
}
