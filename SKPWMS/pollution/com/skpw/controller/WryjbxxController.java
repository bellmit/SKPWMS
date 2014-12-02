package com.skpw.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.CardInfo;
import com.skpw.bean.OrgBean;
import com.skpw.bean.TBasCompanyScale;
import com.skpw.bean.TBasCounty;
import com.skpw.bean.TBasEnterPoll;
import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TBasPollSourceLevel;
import com.skpw.bean.TBasPollSourceType;
import com.skpw.bean.TBasRegisterType;
import com.skpw.bean.TBasRship;
import com.skpw.bean.TBasUnitClass;
import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TSysUserInfo;
import com.skpw.common.CreateUUID;
import com.skpw.common.DateUtil;
import com.skpw.common.JWTransformUtil;
import com.skpw.service.CardInfoService;
import com.skpw.service.OrgUnitService;
import com.skpw.service.TBasCompanyScaleService;
import com.skpw.service.TBasCountyService;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.TBasOutWhereService;
import com.skpw.service.TBasPollSourceLevelService;
import com.skpw.service.TBasPollSourceTypeService;
import com.skpw.service.TBasRegisterTypeService;
import com.skpw.service.TBasRshipService;
import com.skpw.service.TBasUnitClassService;
import com.skpw.service.TBasWaterDisRuleService;
import com.skpw.service.TBasWaterDisStdService;
import com.skpw.service.TPsOutPermitService;
import com.skpw.service.TTcControlerService;
import com.skpw.service.WrySpecs;
import com.skpw.service.WryglsxService;
import com.skpw.service.WryhbsxService;
import com.skpw.service.WryjbxxService;

@Controller
public class WryjbxxController {

	@Resource
	private WryjbxxService wryjbxxService;

	@Resource
	private TBasCompanyScaleService tBasCompanyScaleService;

	@Resource
	private TBasOutWhereService tBasOutWhereService;

	@Resource
	private TBasPollSourceLevelService tBasPollSourceLevelService;

	@Resource
	private TBasPollSourceTypeService tBasPollSourceTypeService;

	@Resource
	private TBasRegisterTypeService tBasRegisterTypeService;

	@Resource
	private TBasRshipService TBasRshipService;

	@Resource
	private TBasUnitClassService tBasUnitClassService;

	@Resource
	private TBasWaterDisRuleService tBasWaterDisRuleService;

	@Resource
	private TBasWaterDisStdService tBasWaterDisStdService;

	@Resource
	private TBasCountyService tBasCountyService;

	@Resource
	private OrgUnitService orgUnitService;

	@Resource
	private TBasEnterPollService tBasEnterPollService;

	@Resource
	private CardInfoService cardInfoService;

	@Resource
	private TPsOutPermitService tPsOutPermitService;

	@Resource
	private WryhbsxService wryhbsxService;

	@Resource
	private WryglsxService wryglsxService;
	
	@Resource
	private TTcControlerService tTcControlerService;

	// 查找所有污染源企业
	@RequestMapping("/wryjbxxController/findAllPollutionBasInfo")
	@ResponseBody
	public List findAllPollutionBasInfo() {

		List<TBasEnterprise> enterprises = wryjbxxService.findAll();

		return enterprises;
	}
	
	// 数据权限过滤后，查询企业列表
		@RequestMapping("/wryjbxxController/findAllPollutionBasInfonew")
		@ResponseBody
		public List findAllPollutionBasInfonew(HttpServletRequest request) {

			List<TBasEnterprise> enterList = new ArrayList<TBasEnterprise>();
			String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
			List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
			if(null != l1 && l1.size() > 0) {
				String enterid = l1.get(0).get("enterid").toString();
				if(enterid != null && !"".equals(enterid.trim())) {
					List<TBasEnterprise> l3 = wryjbxxService.findAll();
					if(null != l3 && l3.size() > 0) {
						for(int i=0; i<l3.size(); i++) {
							if(enterid.equals(l3.get(i).getFenterId().trim())) {
								enterList.add(l3.get(i));
							}
						}
					}
				}
			} else {
				List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
				if(null != longcodelist && longcodelist.size() > 0) {
					List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
					if(null != orgidList && orgidList.size() > 0) {
						enterList = wryjbxxService.findqylistByzzjgid(orgidList);
					}
					
				}
			}

			return enterList;
		}

	// 分页查询
	@RequestMapping("/wryjbxxController/findPollutionBasInfo")
	public @ResponseBody
	Map<String, Object> findPollutionBasInfo(int page, int rows,
			TBasEnterprise tbe, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		long total = 0;
		Pageable pageRequest = new PageRequest(page - 1, rows);
		List<TBasEnterprise> list =new ArrayList<TBasEnterprise>();
		String userid = ((TSysUserInfo) request.getSession().getAttribute(
				"userinfo")).getId();

		List<Map<String, Object>> enterList = tBasEnterPollService
				.findEnterByUserid(userid);

		if (null != enterList && enterList.size() > 0) {
			String enterid = enterList.get(0).get("enterid").toString();
			if(null != enterid && !"".equals(enterid.trim())) {
				tbe.setFenterId(enterid);
				Specification<TBasEnterprise> spf = WrySpecs.wryjbxx(tbe);
				Page<TBasEnterprise> p = wryjbxxService.findPollutionBasInfonew(spf,pageRequest);
				total = p.getTotalElements();
				list = p.getContent();
			}
			
		}
		else {
			List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
			if(null != longcodelist && longcodelist.size() > 0) {
				List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
				if(null != orgidList && orgidList.size() > 0) {
					tbe.setFlcList(orgidList);
					Specification<TBasEnterprise> spf = WrySpecs.wryjbxx(tbe);
					Page<TBasEnterprise> p1 = wryjbxxService.findPollutionBasInfonew(spf,pageRequest);
					total = p1.getTotalElements();
					list = p1.getContent();
				}
				
			}
		}
		
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	/**
	 * 跳转到企业列表界面
	 * 
	 * @return
	 */
	@RequestMapping("/wryjbxxController/jumpToWryList")
	public String jumpToWryList(Model model) {
		// 行政区域:深圳的区县，深圳市的FCityID是440300
		List<TBasCounty> tBasCountyList = tBasCountyService
				.findCountyForCity("440300");
		model.addAttribute("tBasCountyList", tBasCountyList);
		return "qyxxgl/wryjbxx/wry_list";
	}

	// 根据Id删除
	@RequestMapping("/wryjbxxController/del")
	@ResponseBody
	public Map<String, Object> del(HttpServletResponse res, String id) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("result", true);
		try {
			// 删除企业管理属性
			if (wryhbsxService.findWry(id) != null)
				wryhbsxService.del(id);

			// 删除企业污染因子
			if (wryglsxService.findWry(id) != null)
				wryglsxService.del(id);

			// 删除企业总量控制器
			if (tBasEnterPollService.getList(id).size() > 0)
				tBasEnterPollService.deleteByEnterId(id);

			if(tTcControlerService.findByFenterId(id) != null && tTcControlerService.findByFenterId(id).size() > 0)
				tTcControlerService.deleteByEnterId(id);
			// 删除企业信息
			wryjbxxService.del(id);

		} catch (Exception e) {
			map.put("result", false);
			e.printStackTrace();
		}
		return map;
	}

	// 保存
	@RequestMapping(value = "/wryjbxxController/save", method = RequestMethod.POST)
	public void save(HttpServletResponse res, TBasEnterprise tbe,
			@RequestParam(value = "wryz", required = false) String wryz) {
		try {
			String b = "true";
			String id = tbe.getFenterId();
			try {
				if (id == null || id == "") {
					// 获取32位UUID
					id = CreateUUID.getUuid();
					// 设置主键
					tbe.setFenterId(id);
				}
				// 设置经纬度
				Double longitude = JWTransformUtil.transformLongitude((tbe
						.getFlongDegree() != null) ? tbe.getFlongDegree() : 0,
						(tbe.getFlongMinute() != null) ? tbe.getFlongMinute()
								: 0,
						(tbe.getFlongSecond() != null) ? tbe.getFlongSecond()
								: 0);
				Double flatitude = JWTransformUtil
						.transformLongitude(
								(tbe.getFlatDegree() != null) ? tbe
										.getFlatDegree() : 0,
								(tbe.getFlatMinute() != null) ? tbe
										.getFlatMinute() : 0,
								(tbe.getFlatSecond() != null) ? tbe
										.getFlatSecond() : 0);
				tbe.setFlongitude(longitude);
				tbe.setFlatitude(flatitude);

				// 设置创建人和创建时间
				if (tbe.getFcreatorId() == null || tbe.getFcreatorId() == "") {
					tbe.setFcreatorId("");
					tbe.setFcreatTime(new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss").format(new Date()));
				}
				// 设置最后修改人和修改时间
				tbe.setFlastEditId("");
				tbe.setFlastEditTime(DateUtil.gettimestamp());
				// 保存企业信息
				wryjbxxService.save(tbe);

				// 保存污染因子 如果
				List<String> wryz_true = new ArrayList<String>();// 被勾选的污染因子的id
				// 新增的污染因子
				List<TBasEnterPoll> tBasEnterPollList = new ArrayList<TBasEnterPoll>();
				if(wryz != null && wryz != "") {
					wryz = wryz.replace("{", "");
					wryz = wryz.replace("}", "");
				}
				for (String str1 : wryz.split(",")) {
					String str[] = str1.split(";");
					if ("null".equals(str[0])) {
						// id为null，表示原污染因子表不存在，是新加的
						TBasEnterPoll tbep = new TBasEnterPoll();
						tbep.setFenterPollId(CreateUUID.getUuid());
						tbep.setFenterId(id);
						tbep.setFpollutantId(str[1]);
						tbep.setBisChoice(true);
						tBasEnterPollList.add(tbep);
					} else {
						wryz_true.add(str[0]);
					}
				}
				// 更新污染因子打钩
				if (wryz_true.size() > 0) {
					tBasEnterPollService.updateAll(wryz_true, id);
				}
				tBasEnterPollService.save(tBasEnterPollList);

			} catch (Exception e) {
				b = "false";
				e.printStackTrace();
			}

			res.getWriter().print(id);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// 从污染源列表跳转到污染源详细信息页面
	@RequestMapping("/wryjbxxController/findOne")
	public String findOne(Model model, String id) {
		// 企业基本信息
		TBasEnterprise tbe = wryjbxxService.findWry(id);
		// 企业规模-
		List<TBasCompanyScale> tBasCompanyScaleList = tBasCompanyScaleService
				.findAll();
		// 污染源级别-
		List<TBasPollSourceLevel> tBasPollSourceLevelList = tBasPollSourceLevelService
				.findAll();
		// 污染源类别-
		List<TBasPollSourceType> tBasPollSourceTypeList = tBasPollSourceTypeService
				.findAll();
		// 注册类型-
		List<TBasRegisterType> tBasRegisterTypeList = tBasRegisterTypeService
				.findAll();
		// 隶属关系-
		List<TBasRship> tBasRshipList = TBasRshipService.findAll();
		// 单位类型-
		List<TBasUnitClass> tBasUnitClassList = tBasUnitClassService.findAll();
		// 深圳的区县，深圳市的FCityID是440300
		List<TBasCounty> tBasCountyList = tBasCountyService
				.findCountyForCity("440300");
		// 污染因子
		List<TBasEnterPoll> tBasEnterPollList = tBasEnterPollService
				.getList(id);
		// 所有企业的缺省污染因子
		// List<TBasPollutantSet> tBasPollutantSetList =
		// tBasPollutantSetService.findAll();

		/*
		 * //排放去向 List<TBasOutWhere> tBasOutWhereList =
		 * tBasOutWhereService.findAll(); //废水排放规律 List<TBasWaterDisRule>
		 * tBasWaterDisRuleList = tBasWaterDisRuleService.findAll(); //废水排放标准
		 * List<TBasWaterDisStd> tBasWaterDisStdList =
		 * tBasWaterDisStdService.findAll();
		 */
		// 判断污染因子是否被选

		model.addAttribute("tbe", tbe);
		model.addAttribute("tBasCompanyScaleList", tBasCompanyScaleList);
		model.addAttribute("tBasPollSourceLevelList", tBasPollSourceLevelList);
		model.addAttribute("tBasPollSourceTypeList", tBasPollSourceTypeList);
		model.addAttribute("tBasRegisterTypeList", tBasRegisterTypeList);
		model.addAttribute("tBasRshipList", tBasRshipList);
		model.addAttribute("tBasUnitClassList", tBasUnitClassList);
		model.addAttribute("tBasCountyList", tBasCountyList);
		model.addAttribute("tBasEnterPollList", tBasEnterPollList);
		// model.addAttribute("tBasPollutantSetList", tBasPollutantSetList);
		/*
		 * model.addAttribute("tBasOutWhereList", tBasOutWhereList);
		 * model.addAttribute("tBasWaterDisRuleList", tBasWaterDisRuleList);
		 * model.addAttribute("tBasWaterDisStdList", tBasWaterDisStdList);
		 */
		return "qyxxgl/wryjbxx/wryjbxx";
	}

	// 判断当前企业是否可删除
	@RequestMapping("/wryjbxxController/delflag")
	@ResponseBody
	public Map<String, Object> delFlag(@RequestParam("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		// 判断当前企业是否有排污许可证
		List<TPsOutPermit> permits = tPsOutPermitService
				.findPermitByEnterId(id);
		if (permits.size() > 0)
			flag = false;

		// 判断当前企业是否有IC卡
		List<CardInfo> card = cardInfoService.findCardInfoByEnterId(id);
		if (card.size() > 0)
			flag = false;

		map.put("flag", flag);

		return map;
	}

}
