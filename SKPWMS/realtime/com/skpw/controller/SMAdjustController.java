package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.SMAdjust;
import com.skpw.common.Page;
import com.skpw.common.StringUtil;
import com.skpw.service.SMAdjustService;
import com.skpw.service.SMAdjustSpecs;
import com.skpw.service.TBasPollutantService;
import com.skpw.service.TTcControlerService;
import com.skpw.service.WryjbxxService;

/**
 * @author hjy 月度数据调整
 */

@Controller
public class SMAdjustController {

	@Resource
	private TTcControlerService tTcControlerService;

	@Resource
	private WryjbxxService wryjbxxService;

	@Resource
	private SMAdjustService smAdjustService;

	@Resource
	private TBasPollutantService tBasPollutantService;

	// 跳转到月度数据调整页面
	@RequestMapping("/realtime/initSMAdjustToList")
	public String initSMAdjustToList() {

		return "realtime/smadjust_list";
	}

	// 获取污染源因子
	@RequestMapping("realtime/showPolluntant")
	@ResponseBody
	public List showPolluntant() {

		List list = tBasPollutantService.findAll(null);

		return list;
	}

	// 显示所有
	@RequestMapping("/realtime/showSMAdjust")
	@ResponseBody
	public List showSMAdjust(Model model) {
		List<SMAdjust> smAdjusts = smAdjustService.showSMAdjustInfo();

		return smAdjusts;
	}

	// 分页条件显示所有
	@RequestMapping("/realtime/showSMAdjustbyCondition")
	@ResponseBody
	public Map showSMAdjustbyCondition(Model model, SMAdjust smAdjust,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = smAdjustService.count(SMAdjustSpecs
				.queryCondition(smAdjust));
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<SMAdjust> smAdjusts = smAdjustService.showSMAdjustListByCondition(
				SMAdjustSpecs.queryCondition(smAdjust), pageRequest)
				.getContent();

		map.put("total", count);
		map.put("rows", smAdjusts);
		return map;
	}

	// 添加

	@RequestMapping("/realtime/addSMAdjust")
	@ResponseBody
	public String addSMAdjust(Model model, SMAdjust smAdjust,
			HttpServletRequest request) {
		String result = "";
		try {
			String ctrlerid = request.getParameter("crtllerid");

			if (StringUtil.isNotEmpty(smAdjust.getId())) {
				SMAdjust smAdjust2 = new SMAdjust();
				smAdjust2.setId(smAdjust.getId());
				smAdjust2.setMonth(smAdjust.getMonth());
				smAdjust2.setDischarge(smAdjust.getDischarge());
				smAdjust2.setEnterprise(wryjbxxService.findWry(smAdjust
						.getEnterprise().getFenterId()));
				smAdjust2.setPollutant(tBasPollutantService.findOne(smAdjust
						.getPollutant().getfPollutantID()));
				smAdjust2.setTtcControler(tTcControlerService
						.findcrtlByFid(Integer.valueOf(ctrlerid)));
				smAdjustService.saveSMAdjust(smAdjust2);
				result = "1";
			} else {
				
				int monthid = Integer.valueOf(smAdjust.getMonth());
				int pollutant = Integer.valueOf(smAdjust.getPollutant().getfPollutantID().trim());

				

				if (smAdjustService.isExist(pollutant, monthid) != null) {

					result = "2";

				}else{
				SMAdjust smAdjust2 = new SMAdjust();
				smAdjust2.setMonth(smAdjust.getMonth());
				smAdjust2.setDischarge(smAdjust.getDischarge());
				smAdjust2.setEnterprise(wryjbxxService.findWry(smAdjust
						.getEnterprise().getFenterId()));
				smAdjust2.setPollutant(tBasPollutantService.findOne(smAdjust
						.getPollutant().getfPollutantID()));
				smAdjust2.setTtcControler(tTcControlerService
						.findcrtlByFid(Integer.valueOf(ctrlerid)));
				smAdjustService.saveSMAdjust(smAdjust2);
				result = "1";
			
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除
	@RequestMapping("/realtime/delSMAdjust")
	@ResponseBody
	public Map<String, Boolean> delSMAdjust(Model model, String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			smAdjustService.deleteSMAdjust(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

}
