package com.skpw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.ICCard;
import com.skpw.service.ICCardService;

@Controller
public class ICCardController {

	@Resource
	private ICCardService icCardService;

	@RequestMapping("card/saveICCard")
	@ResponseBody
	public String saveICCard(ICCard icCard) {

		String result = "";
		if (icCard != null) {
			icCardService.saveICCard(icCard);

			result = "true";
		}

		return result;

	}
	
	@RequestMapping("/card/cancelCardinfo")
	public @ResponseBody ICCard cancelCardinfo(String cardPhyBh) {
		return icCardService.findIcCardByphyNo(cardPhyBh);
	}
	
	@RequestMapping("/card/updateIccStateByphyNo")
	public @ResponseBody int updateIccStateByphyNo(String cardPhyBh) {
		return icCardService.updateIccStateByphyNo(cardPhyBh);
	}
	
	@RequestMapping("/card/findTicRechargeBycardinfoId")
	public @ResponseBody List<String> findTicRechargeBycardinfoId(String iccid) {
		List<String> list = new ArrayList<String>();
		if(null != iccid && !"".equals(iccid.trim())) {
			list = icCardService.findTicRechargeBycardinfoId(iccid);
		}
		return list;
	}
	
	@RequestMapping("/card/cardsfcunzai")
	public @ResponseBody ICCard cardsfcunzai(String cardPhyBh) {
		return icCardService.findIcCardByphyNo(cardPhyBh);
	}

}
