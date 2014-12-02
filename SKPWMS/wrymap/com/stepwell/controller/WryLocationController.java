package com.stepwell.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skpw.bean.RealtimeBean;
import com.skpw.bean.TSysUserInfo;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.WryjbxxService;
import com.stepwell.bean.MapRealTimeData;
import com.stepwell.service.MapRealTimeService;

/**
 * @author Administrator
 * 
 *         污染源定位
 */
@Controller
public class WryLocationController {

	@Resource
	private WryjbxxService wryjbxxService;
	@Resource
	private TBasEnterPollService tBasEnterPollService;
	@Resource
	private MapRealTimeService mapRealTimeService;

	@RequestMapping("map/initToMapForWry")
	public String initToMapForWry(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String fentername = request.getParameter("fenterName");
		//List wryList = mapRealTimeService.findRealtimeData(fentername);
		List wryList = new ArrayList();
		String userid = ((TSysUserInfo) request.getSession().getAttribute("userinfo")).getId();
		List<Map<String, Object>> l1 = tBasEnterPollService.findEnterByUserid(userid);
		if(null != l1 && l1.size() > 0) {
			String enterid = l1.get(0).get("enterid").toString();
			if(enterid != null && !"".equals(enterid.trim())) {
				wryList = mapRealTimeService.findRealtimeDatanew(enterid, fentername, null);
			}
		} else {
			List<String> longcodelist = tBasEnterPollService.findOrgIdsByUserid(userid);
			if(null != longcodelist && longcodelist.size() > 0) {
				List<String> orgidList = tBasEnterPollService.findOrgIdsByOrglongcode(longcodelist);
				if(null != orgidList && orgidList.size() > 0) {
					wryList = mapRealTimeService.findRealtimeDatanew(null, fentername, orgidList);
				}
				
			}
		}
		
		List resultList = new ArrayList();
		if(null != wryList && wryList.size() > 0) {
			for(int i=0; i<wryList.size(); i++) {
				MapRealTimeData mtd = (MapRealTimeData)wryList.get(i);
				if(null != mtd && null != mtd.getFpollTypeCode() && "B02".equals(mtd.getFpollTypeCode())) {
					List<RealtimeBean> fqlist = mapRealTimeService.showRealtimeByPagenew(mtd.getWryid());
					if(null != fqlist && fqlist.size() > 0) {
						mtd.setFqlist(fqlist);
					}
				}
				resultList.add(mtd);
			}
		}
		
		model.addAttribute("wryList", resultList);
		model.addAttribute("fentername",fentername);
		return "map/wrylocation";
	}

}
