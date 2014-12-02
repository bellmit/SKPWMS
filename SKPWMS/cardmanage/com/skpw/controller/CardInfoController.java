package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.CardInfo;
import com.skpw.bean.ICCard;
import com.skpw.bean.ICCardControler;
import com.skpw.bean.TTcControler;
import com.skpw.common.Page;
import com.skpw.common.UUIDGenerate;
import com.skpw.service.CardInfoService;
import com.skpw.service.CardinfoSpec;
import com.skpw.service.ICCardService;
import com.skpw.service.PolltantYearDetailService;
import com.skpw.service.TPsOutSewageService;
import com.skpw.service.TTcControlerService;

/**
 * @author hjy IC卡信息控制层
 */

@Controller
public class CardInfoController {

	@Resource
	private CardInfoService cardInfoService;

	@Resource
	private TTcControlerService tTcControlerService;

	@Resource
	private PolltantYearDetailService polltantYearDetailService;

	@Autowired
	private TPsOutSewageService tPsOutSewageService;

	@Resource
	private ICCardService icCardService;

	// 跳转到卡信息界面
	@RequestMapping("/card/initToCard")
	public String initToCard() {

		return "card/iccardinfo";

	}

	// 保存卡与总量控制器之间的关系
	@RequestMapping("/card/saveCardInfo")
	@ResponseBody
	public String saveCardInfo(CardInfo cardInfo, HttpServletRequest request) {

		String message = "";
		ICCard icCard = new ICCard();
		
		try {
			if(cardInfo != null && cardInfo.getCardinfoid() != null && !cardInfo.getCardinfoid().trim().equals("")) {
				String fcardphyno = request.getParameter("FCardPhyNo");
				CardInfo _cInfo = cardInfoService.findCardById(cardInfo.getCardinfoid());
				ICCard _iCard = new ICCard();
				ICCardControler icCardControler = new ICCardControler();
				
				if(_cInfo != null) {
					_iCard = icCardService.findOne(_cInfo.getCardid());
					cardInfo.setCardinfoid(_cInfo.getCardinfoid());
					if(_cInfo.getIcCardControler() != null) {
						icCardControler.setId(_cInfo.getIcCardControler().getId());
					}
				}
				if(_iCard != null) {
					icCard.setId(_iCard.getId());
				} else {
					icCard.setId(UUIDGenerate.getUUID());
				}
				icCard.setCardPhyNo(fcardphyno);
				String cardid = icCardService.saveICCard(icCard).getId();
				String controlerid = request.getParameter("fcid");
				TTcControler ttcController = tTcControlerService
						.findtccontrollerById(controlerid);
				

				icCardControler.setCardInfo(cardInfo);

				icCardControler.setTcControler(ttcController);
				cardInfo.setCardid(cardid);
				cardInfo.setIcCardControler(icCardControler);
				cardInfoService.saveCard(cardInfo);
				message = "true";
			} else {
				String fcardphyno = request.getParameter("FCardPhyNo");
				ICCard _iCard = icCardService.findIcCardByphyNo(fcardphyno);
				CardInfo _cInfo = cardInfoService.findCardByCardPhyNo(fcardphyno);
				ICCardControler icCardControler = new ICCardControler();
				if(_iCard != null) {
					icCard.setId(_iCard.getId());
				} else {
					icCard.setId(UUIDGenerate.getUUID());
				}
				
				if(_cInfo != null) {
				/*	cardInfo.setCardinfoid(_cInfo.getCardinfoid());
					if(_cInfo.getIcCardControler() != null) {
						icCardControler.setId(_cInfo.getIcCardControler().getId());
					}*/
					message = "yjh";
				}
				else {
					icCard.setCardPhyNo(fcardphyno);
					String cardid = icCardService.saveICCard(icCard).getId();
					String controlerid = request.getParameter("controllerids");
					TTcControler ttcController = tTcControlerService
							.findtccontrollerById(controlerid);
					icCardControler.setCardInfo(cardInfo);
					icCardControler.setTcControler(ttcController);
					cardInfo.setCardid(cardid);
					cardInfo.setIcCardControler(icCardControler);
					cardInfoService.saveCard(cardInfo);
					message = "true";
				}
			}
		} catch (Exception e) {

			message = "false";
		}

		return message;
	}

	@RequestMapping("/card/findCardInfo")
	@ResponseBody
	public CardInfo findCardInfo(String cardPhyNO) {
		return cardInfoService.findCardByCardPhyNo(cardPhyNO);
	}

	@RequestMapping("/card/showPolltantYearDetail")
	@ResponseBody
	public List showPolltantYearDetail(
			@RequestParam(value = "enterid", required = false) String enterid) {

		List list = polltantYearDetailService.showPolltantYeayDetail(enterid);
		return list;
	}

	// 显示卡的信息
	@RequestMapping("card/showCardinfo")
	@ResponseBody
	public List showCardinfo() {

		List list = cardInfoService.showCardinfo();

		return list;
	}

	// 分页和条件显示卡的信息
	@RequestMapping("card/showCardinfoByCondition")
	@ResponseBody
	public Map showCardinfoByCondition(Model model, CardInfo cardInfo,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = cardInfoService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<CardInfo> cardInfos = cardInfoService.showCardinfoByCondition(
				CardinfoSpec.queryCondition(cardInfo), pageRequest)
				.getContent();
		List<ICCard> list3 = icCardService.findAll();
		JSONArray jsonArray = new JSONArray();
		for (CardInfo cardInfo2 : cardInfos) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("cardinfoid", cardInfo2.getCardinfoid());
			if (cardInfo2.getEnterprise() != null) {
				jsonObject.put("entername", cardInfo2.getEnterprise()
						.getFenterName());
			}
			jsonObject.put("date", cardInfo2.getDate());
			if (cardInfo2.getUserInfo() != null) {
				jsonObject.put("username", cardInfo2.getUserInfo()
						.getUsername());
			}

			jsonObject.put("flinkMan", cardInfo2.getFLinkMan());
			if (cardInfo2.getEnterprise() != null) {
				jsonObject.put("ttcname", tTcControlerService
						.getTTController(cardInfo2.getEnterprise()
								.getFenterId()));

			}
			if(null != list3 && list3.size() > 0) {
				for(int k=0; k<list3.size(); k++) {
					ICCard icc = list3.get(k);
					if(null != cardInfo2.getCardid() && null != icc && null != icc.getId() && cardInfo2.getCardid().equals(icc.getId()) && icc.getStatus() != null && icc.getStatus() == 1) {
						jsonObject.put("zxStatus", "是");
					}
				}
			}

			jsonArray.add(jsonObject);
		}
		map.put("total", count);
		map.put("rows", jsonArray);
		return map;
	}

	// 删除
	@RequestMapping("/card/delCardinfo")
	@ResponseBody
	public Map<String, Boolean> delCardinfo(Model model, String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			cardInfoService.delCardInfo(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

	// 跳转到卡管理界面
	@RequestMapping("/card/initToCardManage")
	public String initToCardManage(Model model) {
		model.addAttribute("cardInfo", "");
		return "card/cardmanage_list";

	}

	// 跳转到修改界面
	@RequestMapping("/card/initupdateToCard")
	public String initupdateToCard(Model model, String cardid) {

		CardInfo cardInfo = cardInfoService.findCardById(cardid);

		String enterid = cardInfo.getEnterprise().getFenterId();

		List<TTcControler> ttclist = tTcControlerService
				.findByFenterId(enterid);

		String fcontrollerid = "";
		String fcontrollername = "";
		for (TTcControler tTcControler : ttclist) {

			fcontrollerid = tTcControler.getFcontrolerId();
			fcontrollername = tTcControler.getFctrlerName();
		}

		String cardPhyNo = icCardService.findOne(cardInfo.getCardid())
				.getCardPhyNo();

		model.addAttribute("cardInfo", cardInfo);

		model.addAttribute("cardPhyNo", cardPhyNo);
		model.addAttribute("fcontrollerid", fcontrollerid);
		model.addAttribute("fcontrollername", fcontrollername);

		return "card/iccardinfo";
	}

}
