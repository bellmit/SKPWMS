package com.skpw.controller;

import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TSysUserInfo;
import com.skpw.service.DayReportService;
import com.skpw.service.TBasEnterPollService;
import com.skpw.service.TBasPollutantService;
import com.skpw.service.TTcControlerService;
import com.skpw.service.WryjbxxService;

/**
 * @author hjy 日图形报表统计
 */
@Controller
public class DayReportController {
	
	private static final Logger logger = Logger.getLogger(DayReportController.class);

	@Resource
	private DayReportService dayReportService;

	@Resource
	private WryjbxxService wryjbxxService;

	@Resource
	private TTcControlerService tTcControlerService;

	@Resource
	private TBasPollutantService tBasPollutantService;
	
	@Resource
	private TBasEnterPollService tBasEnterPollService;

	@RequestMapping("/report/getTTCList")
	@ResponseBody
	public List getTTCList(String id) {

		List list = tTcControlerService.findByFenterId1(id);

		return list;
	}
	
	@RequestMapping("/report/initDayReportTab")
	public String initDayReportTab(Model model) {
		
		return "report/daycharttab_list";
	}

	@RequestMapping("/report/initDayReportToList")
	public String initDayReportToList(Model model,HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = calendar.getTime();
		String statisticime = sdf.format(date);
		
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

		
		model.addAttribute("statisticime", statisticime);

		model.addAttribute("enterList", enterList);

		return "report/daychart_list";
	}

	@RequestMapping("/report/statisticDayReport")
	@ResponseBody
	@ExceptionHandler(value={ArithmeticException.class,SQLException.class,SQLServerException.class}) 
	public Map statisticDayReport(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ttcid = request.getParameter("ttcid");
		String time_static = request.getParameter("time_static");
		String paramtype = request.getParameter("paramtype");
		List datalist=dayReportService.statisticComplexDayReport(Integer.valueOf(ttcid), time_static, paramtype);	

		map.put("datalist", datalist);
		map.put("paramtype", paramtype);

		return map;
	}
	
	
	@RequestMapping("/report/initDayReportByColumn")
	public String initDayReportByColumn(Model model,HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = calendar.getTime();
		String statisticime = sdf.format(date);

		//List<TBasEnterprise> enterList = wryjbxxService.findAll();
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
		model.addAttribute("statisticime", statisticime);

		model.addAttribute("enterList", enterList);

		return "report/daychartclumn_list";
	}
	
	
	@RequestMapping("/report/statisticDayReportByColumn")
	@ResponseBody
	@ExceptionHandler(value={ArithmeticException.class,SQLException.class,SQLServerException.class}) 
	public Map statisticDayReportByColumn(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ttcid = request.getParameter("ttcid");
		String time_static = request.getParameter("time_static");		
		List datalist=dayReportService.statisticDayReportByColumn(Integer.valueOf(ttcid), time_static);		

		map.put("datalist", datalist);

		return map;
	}
	
	//导出图片
	@RequestMapping("export/exportImage")
	public void  exportImage (HttpServletRequest request,HttpServletResponse response) throws Exception{	
		request.setCharacterEncoding("gb2312");//设置编码，解决乱码问题
		String type = request.getParameter("type");
        String svg = request.getParameter("svg");
        response.setCharacterEncoding("gb2312");//设置编码，解决乱码问题        
        String filename = request.getParameter("filename");
        filename = filename==null?"chart":filename;
        ServletOutputStream out = response.getOutputStream();
        logger.info("type:"+type+"filename:"+filename);
        if (null != type && null != svg) {
            svg = svg.replaceAll(":rect", "rect");
            String ext = "";
            Transcoder t = null;
            if (type.equals("image/png")) {
                ext = "png";
                t = new PNGTranscoder();
            } else if (type.equals("image/jpeg")) {
                ext = "jpg";
                t = new JPEGTranscoder();
            }else if (type.equals("application/pdf")) {  
                ext = "pdf";  
                t =(Transcoder) new PDFTranscoder();  
            }else if(type.equals("image/svg+xml")) 
                ext = "svg";   
            //解决下载文件的文件名的乱码
            response.addHeader("Content-Disposition", "attachment; filename="+ new String (filename.getBytes("gb2312"),"iso-8859-1") + "."+ext);
            response.addHeader("Content-Type", type);
            
            if (null != t) {
                TranscoderInput input = new TranscoderInput(new StringReader(svg));
                TranscoderOutput output = new TranscoderOutput(out);
                
                try {
                    t.transcode(input, output);
                } catch (TranscoderException e) {
                    e.printStackTrace();
                }
            } else if (ext.equals("svg")) {
                OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
                writer.append(svg);
                writer.close();
            } else 
                out.print("Invalid type: " + type);
        } else {
            response.addHeader("Content-Type", "text/html");
        }
        out.flush();
        out.close();
		
	}

}
