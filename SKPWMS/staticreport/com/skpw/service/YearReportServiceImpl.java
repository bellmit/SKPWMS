package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.NameAndData;
import com.skpw.bean.YearReport;
import com.skpw.dao.YearReportDao;

@Service("yearReportService")
public class YearReportServiceImpl implements YearReportService {

	@Resource
	private YearReportDao yearReportDao;

	public List statisticYearReport(Integer ttcid, String time, String paramtype) {

		List<NameAndData> dataList = new ArrayList<NameAndData>();

		NameAndData nameAndDataCod = new NameAndData();// COD排放量
		NameAndData nameAndDataNh3 = new NameAndData();// NH3排放量
		NameAndData nameAndDataCoda = new NameAndData();// COD核准排放量
		NameAndData nameAndDataNh3a = new NameAndData();// NH3核准排放量

		List<Float> codlist = new ArrayList<Float>();// COD排放量
		List<Float> nh3list = new ArrayList<Float>();// NH3排放量
		List<Float> codalist = new ArrayList<Float>();// COD核准排放量
		List<Float> nh3alist = new ArrayList<Float>();// NH3核准排放量

		@SuppressWarnings("unchecked")
		List<YearReport> chartlist = yearReportDao.statisticYearReport(ttcid,time);
		for (YearReport yearReport : chartlist) {
			codlist.add(yearReport.getCodf());
			nh3list.add(yearReport.getNh3f());
			codalist.add(yearReport.getCoda());
			nh3alist.add(yearReport.getNh3a());

		}
		if (paramtype.equals("COD")) {
			nameAndDataCod.setName("COD排放量");
			nameAndDataCod.setData(codlist);
			nameAndDataCoda.setName("COD核准排放量");
			nameAndDataCoda.setData(codalist);
			dataList.add(nameAndDataCod);
			dataList.add(nameAndDataCoda);
		}
		if (paramtype.equals("NH3-N")) {
			nameAndDataNh3.setName("NH3排放量");
			nameAndDataNh3.setData(nh3list);
			nameAndDataNh3a.setName("NH3-N核准排放量");
			nameAndDataNh3a.setData(nh3alist);
			dataList.add(nameAndDataNh3);
			dataList.add(nameAndDataNh3a);
		}		
		
		return dataList;
	}

}
