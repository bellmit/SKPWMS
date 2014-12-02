package com.skpw.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TIcPrePaidType;
import com.skpw.bean.TIcRecharge;
import com.skpw.bean.TIcRechargeDetails;
import com.skpw.bean.TIcRechargeYear;
import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutSewage;
import com.skpw.bean.TPsPermits;
import com.skpw.common.CreateUUID;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasOutWhereService;
import com.skpw.service.TBasPollutantService;
import com.skpw.service.TBasWaterDisStdService;
import com.skpw.service.TIcRechargeDetailsService;
import com.skpw.service.TIcRechargeService;
import com.skpw.service.TIcRechargeYearService;
import com.skpw.service.TPsOutPermitService;
import com.skpw.service.TPsOutSewageService;
import com.skpw.service.TPsPermitsService;

@Controller
@RequestMapping("card")
public class RechargeController {
	@Autowired
	private TIcRechargeService tIcRechargeService;
	
	@Autowired
	private TIcRechargeDetailsService tIcRechargeDetailsService;
	
	@Autowired
	private TIcRechargeYearService tIcRechargeYearService;
	
	@Autowired
	private TPsPermitsService tPsPermitsService;
	
	@Autowired
	private TPsOutSewageService tPsOutSewageService;
	
	@Autowired
	private TPsOutPermitService tPsOutPermitService;
	
	@Autowired
	private TBasPollutantService tBasPollutantService;
	
	@Resource
	private DataSource dataSource;
	
	// 跳转到排污许可证显示页面
	@RequestMapping("recharge/list_init")
	public String list_init(Model model) {
		model.addAttribute("fDate", DateUtil.getCurrentDate());
		model.addAttribute("tBasPollutantList", tBasPollutantService.findAll(null));
//		return "card/recharge_list";
		return "card/recharge_search";
	}
	
	@RequestMapping("recharge/search")
	public String add(Model model,TBasEnterprise tBasEnterprise) {
		model.addAttribute("tBasEnterprise", tBasEnterprise);
		model.addAttribute("tBasPollutantList", tBasPollutantService.findAll(null));
		return "card/recharge_search";
	}
		
/*	@RequestMapping("recharge/list")
	public @ResponseBody Map<String, Object> list(int page, int rows,TPsOutSewage tPsOutSewage){
		Map<String, Object> map = new HashMap<String, Object>();
		Page<TPsOutSewage> pages;
		if(null!=tPsOutSewage.gettPsOutPermit() && !"".equals(tPsOutSewage.gettPsOutPermit().getfOutPID())){
//			Pageable pageable = new PageRequest(page-1, rows);
//			pages= tPsOutSewageService.findAllByPage(tPsOutSewage,pageable);
//			map.put("total", pages.getTotalElements());
//			map.put("rows", pages.getContent());
		}else {
			map.put("total", 0);
			map.put("rows", 0);
		}
		return map;
	}*/
	
	/*@RequestMapping("recharge/list")
	public @ResponseBody Map<String, Object> list(int page, int rows,TBasEnterprise tBasEnterprise){
		Map<String, Object> map = new HashMap<String, Object>();
		Page<TIcRechargeYear> pages;
		if(null!=tBasEnterprise.getFenterId() && !"".equals(tBasEnterprise.getFenterId())){
			Sort sort=new Sort(new Order(Direction.ASC, "fYearID"),new Order(Direction.ASC, "fAfterQty"));
			Sort sort=new Sort(new Order(Direction.ASC, "tBasPollutant.fPollutantName"),new Order(Direction.ASC, "fYearID"));
			Pageable pageable = new PageRequest(page-1, rows,sort);
			pages= tIcRechargeYearService.findAllByPage(null, tBasEnterprise, pageable);
			map.put("total", pages.getTotalElements());
			map.put("rows", pages.getContent());
			
			List<TIcRechargeYear> content=pages.getContent();
			List<Map<String, Object>> mergeCells=new ArrayList<Map<String, Object>>();
			
			for (TIcRechargeYear tIcRechargeYear : content) {
				
			}
		}else {
			map.put("total", 0);
			map.put("rows", 0);
		}
		return map;
	}*/
	
	@RequestMapping("recharge/list")
	public @ResponseBody Map<String, Object> orderList(
			int page, int rows,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date rechargeStart,@DateTimeFormat(pattern="yyyy-MM-dd") Date rechargeEnd,
			Long rechargeYearStart,Long rechargeYearEnd,
			String listType,
			TBasEnterprise tBasEnterprise,TIcRechargeYear tIcRechargeYear){
		Map<String, Object> map = new HashMap<String, Object>();
		Page<TIcRechargeYear> pages;
		/*if(null!=tBasEnterprise.getFenterId() && !"".equals(tBasEnterprise.getFenterId())){*/
			Sort sort=null;
			if ("search".equals(listType)) {
				sort=new Sort(new Order(Direction.ASC, "tBasPollutant.fPollutantName"),new Order(Direction.ASC, "fYearID"),new Order(Direction.ASC, "fBeforeQty"));
			} else {
				sort=new Sort(new Order(Direction.DESC, "tIcRecharge.fDate"));
			}
			
			Pageable pageable = new PageRequest(page-1, rows,sort);
			pages= tIcRechargeYearService.findAllByPage(tIcRechargeYear, tBasEnterprise, rechargeStart,rechargeEnd,rechargeYearStart,rechargeYearEnd,pageable);
			map.put("total", pages.getTotalElements());
			map.put("rows", pages.getContent());
			
			/*List<TIcRechargeYear> content=pages.getContent();
			List<Map<String, Object>> mergeCells=new ArrayList<Map<String, Object>>();
			
			for (TIcRechargeYear tIcRechargeYear : content) {
				
			}*/
		/*}else {
			map.put("total", 0);
			map.put("rows", 0);
		}*/
		return map;
	}
	
	@RequestMapping("recharge/add")
	public String add(Model model) {
		return "card/recharge_list";
	}
	
	@RequestMapping("recharge/findOutPermit")
	public ModelAndView findOutPermit(String id){
		ModelAndView mav=new ModelAndView("sewagelicense/outPermitInfo");
		mav.addObject("id", id);
		return mav;
	}
	
	
	@RequestMapping("recharge/save")
	@ResponseBody
	public String save(TIcRecharge tIcRecharge,TIcRechargeDetails tIcRechargeDetails){
		String str = "";
		try{
			
			//IC卡充值
			TIcRecharge _tIcRecharge=tIcRechargeService.findOneByFRechargeNo(tIcRecharge.getfRechargeNo());
			if (null!=_tIcRecharge) {
				tIcRecharge=_tIcRecharge;
			}else {
				//新增--设置创建人和创建时间
				tIcRecharge.setFcreatorId("");
				tIcRecharge.setFcreatTime(DateUtil.gettimestamp());
			}
			
			//IC卡充值明细
			tIcRechargeDetails.settIcRecharge(tIcRecharge);
			
			//IC卡年度充值明细
			if (TIcPrePaidType.按周期充值.equals(tIcRechargeDetails.getfPrePaid())) {
				//计算周期
				TPsOutPermit tPsOutPermit=tPsOutPermitService.findOne(tIcRecharge.gettPsOutPermit().getfOutPID());
				List<Integer> years=getYear(tPsOutPermit);
				
				
				List<TIcRechargeYear> tIcRechargeYears=new ArrayList<TIcRechargeYear>();
				List<TPsPermits> tPsPermitss=new ArrayList<TPsPermits>();
				for (Integer year : years) {
					long yearL=year;
					
					TPsPermits tPsPermits=tPsPermitsService.findByDetails(tIcRecharge.gettBasEnterprise().getFenterId(),
							tIcRechargeDetails.gettBasPollutant().getfPollutantID(), yearL);
					
					if(null != tPsPermits) {
						TIcRechargeYear tIcRechargeYear=new TIcRechargeYear();
						tIcRechargeYear.settIcRechargeDetails(tIcRechargeDetails);
						tIcRechargeYear.settIcRecharge(tIcRecharge);
						tIcRechargeYear.settBasPollutant(tIcRechargeDetails.gettBasPollutant());
						tIcRechargeYear.setfQuantity(tIcRechargeDetails.getfQuantity());
						tIcRechargeYear.setfYearID(yearL);
						tIcRechargeYear.setfPrePaid(tIcRechargeDetails.getfPrePaid());
						
//						tIcRechargeYear.setfBeforeQty(tPsPermits.getfInitPermitValue());
						tIcRechargeYear.setfBeforeQty(tPsPermits.getfPermitValue());
						
						//维护数据
						double addPermit=tPsPermits.getfAddPermit()+tIcRechargeYear.getfQuantity();
						tPsPermits.setfAddPermit(addPermit);
						double afterQty=tPsPermits.getfPermitValue()+tIcRechargeYear.getfQuantity();
						tIcRechargeYear.setfAfterQty(afterQty);
						tPsPermits.setfPermitValue(afterQty);
						
						tIcRechargeYears.add(tIcRechargeYear);
						tPsPermitss.add(tPsPermits);
					}
				}
//				//维护数据
//				double addPermit=tPsPermits.getfAddPermit()+tIcRechargeDetails.getfQuantity();
//				tPsPermits.setfAddPermit(addPermit);
//				double afterQty=tPsPermits.getfPermitValue()+tIcRechargeDetails.getfQuantity();
////				tIcRechargeYear.setfAfterQty(afterQty);
//				tPsPermits.setfPermitValue(afterQty);
				
				tIcRechargeYearService.save(tIcRechargeYears, tPsPermitss);
				
			} else {//按年度充值
				
				TPsPermits tPsPermits=tPsPermitsService.findByDetails(tIcRecharge.gettBasEnterprise().getFenterId(),
						tIcRechargeDetails.gettBasPollutant().getfPollutantID(), tIcRechargeDetails.getfYearID());
				
				TIcRechargeYear tIcRechargeYear=new TIcRechargeYear();
				tIcRechargeYear.settIcRechargeDetails(tIcRechargeDetails);
				tIcRechargeYear.settIcRecharge(tIcRecharge);
				tIcRechargeYear.settBasPollutant(tIcRechargeDetails.gettBasPollutant());
				tIcRechargeYear.setfQuantity(tIcRechargeDetails.getfQuantity());
				tIcRechargeYear.setfYearID(tIcRechargeDetails.getfYearID());
				tIcRechargeYear.setfPrePaid(tIcRechargeDetails.getfPrePaid());
				
//				tIcRechargeYear.setfBeforeQty(tPsPermits.getfInitPermitValue());
				tIcRechargeYear.setfBeforeQty(tPsPermits.getfPermitValue());
				
				//维护数据
				double addPermit=tPsPermits.getfAddPermit()+tIcRechargeYear.getfQuantity();
				tPsPermits.setfAddPermit(addPermit);
				double afterQty=tPsPermits.getfPermitValue()+tIcRechargeYear.getfQuantity();
				tIcRechargeYear.setfAfterQty(afterQty);
				tPsPermits.setfPermitValue(afterQty);
				
				tIcRechargeYearService.save(tIcRechargeYear,tPsPermits);
				
			}
			tIcRecharge=tIcRechargeService.findOne(tIcRecharge.getfRechargeID());
//			str=String.format("%032d", tIcRecharge.getfSn());
			str=tIcRecharge.getfSn().toString();
		}catch(Exception e){
			str = "false";
			e.printStackTrace();
		}
		return str;
	}
	
	//当前年份到排污许可证有效期终止年份
	public List<Integer> getYear(TPsOutPermit tPsOutPermit) {
		List<Integer> years=new ArrayList<Integer>();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(tPsOutPermit.getfEndDate());
		int endYear=calendar.get(Calendar.YEAR);
		
		calendar.setTime(new Date());
		int currentYear=calendar.get(Calendar.YEAR);;
		while (currentYear<=endYear) {
			years.add(currentYear++);
		}
		
		return years;
	}
	
	@RequestMapping("recharge/download")
	public void downLoadFile(String fRechargeNo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//查找充值单
		TIcRecharge tIcRecharge = tIcRechargeService.findOneByFRechargeNo(fRechargeNo);
		
		if (null != tIcRecharge) {
			// 报表模板 编译之后生成的.jasper文件的存放位置
			File reportFile = new File(request.getServletContext().getRealPath(
					"/views/report/IcRecharge.jasper"));
			
			Map parameters = new HashMap();//报表参数
			parameters.put("czid", tIcRecharge.getfRechargeID());
			parameters.put("czdh", fRechargeNo);
			parameters.put("czrq", "" + tIcRecharge.getfDate());
			parameters.put("qymc", tIcRecharge.gettBasEnterprise().getFenterName());
			parameters.put("jbr", tIcRecharge.getfEmp().getUsername());

			Connection conn = dataSource.getConnection();

			byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);//生成报表

			if (null != conn) {
				conn.close();
			}
		
			//输出pdf格式报表
			java.io.BufferedOutputStream bos = null;
			String fileName = fRechargeNo+".pdf";//报表文件名
			try {
				response.setContentType("application/pdf;charset=utf-8");
				request.setCharacterEncoding("UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(bytes.length));
				bos = new BufferedOutputStream(response.getOutputStream());
				bos.write(bytes, 0, bytes.length);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bos != null)
					bos.close();
			}
		} else {//查找不到充值单号对应的充值记录
			
		}
		
		
		
	}
	
	@RequestMapping("recharge/chaxun")
	public String chaxun() {
		return "card/card_chaxun";
	}
	
	@RequestMapping("recharge/findczxxByqyid")
	public @ResponseBody Map<String, Object> findczxxByqyid(int page, int rows,TBasEnterprise tBasEnterprise) {
		Map<String, Object> map = new HashMap<String, Object>();
		long total = 0;
		Pageable pageable = new PageRequest(page-1, rows);
		Page<TIcRechargeYear> pages = tIcRechargeYearService.findczxxByqyid(tBasEnterprise, pageable);
		map.put("total", pages.getTotalElements());
		map.put("rows", pages.getContent());
		return map;
	}
	
	@RequestMapping("recharge/findlastxkpflByqyid")
	public @ResponseBody List<Map<String, Object>> findlastxkpflByqyid(String fenterId) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(null != fenterId && !"".equals(fenterId.trim())) {
			list = tIcRechargeYearService.findlastxkpflByqyid(0,0,fenterId);
		}
		return list;
	}
	
	public static void main(String[] args) {
		Long long1=123L;
		String string=String.format("%032d", long1); 
		System.out.print(string);
	}
}
