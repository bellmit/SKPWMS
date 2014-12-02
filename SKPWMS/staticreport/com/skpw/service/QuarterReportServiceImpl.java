package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.NameAndData;
import com.skpw.bean.QuarterReport;
import com.skpw.dao.QuarterReportDao;

@Service("quarterReportService")
public class QuarterReportServiceImpl implements QuarterReportService {

	@Resource
	private QuarterReportDao quarterReportDao;

	public List statisticQuarterReport(Integer ttcid, String time,
			String paramtype) {

		List<NameAndData> dataList = new ArrayList<NameAndData>();

		NameAndData nameAndDataCod = new NameAndData();// COD排放量
		NameAndData nameAndDataNh3 = new NameAndData();// NH3排放量
		NameAndData nameAndDatacoda = new NameAndData();// cod核准排放量
		NameAndData nameAndDataNh3a = new NameAndData();// nh3核准排放量

		List<Float> codlist = new ArrayList<Float>();// COD排放量
		List<Float> nh3list = new ArrayList<Float>();// NH3排放量
		List<Float> codalist = new ArrayList<Float>();// COD核准排放量
		List<Float> nh3alist = new ArrayList<Float>();// nh3核准排放量

		@SuppressWarnings("unchecked")
		List<QuarterReport> chartlist = quarterReportDao
				.statisticQuarterReport(ttcid, time);
		for (QuarterReport quarterReport : chartlist) {
			codlist.add(quarterReport.getCodf());
			nh3list.add(quarterReport.getNh3f());
			codalist.add(quarterReport.getCoda());
			nh3alist.add(quarterReport.getNh3a());
		}

		if (paramtype.equals("COD")) {
			nameAndDataCod.setName("COD排放量");
			nameAndDataCod.setData(codlist);
			nameAndDatacoda.setName("COD核准排放量");
			nameAndDatacoda.setData(codalist);
			dataList.add(nameAndDataCod);
			dataList.add(nameAndDatacoda);
		}
		if (paramtype.equals("NH3-N")) {
			nameAndDataNh3.setData(nh3list);
			nameAndDataNh3.setName("NH3-N");
			nameAndDataNh3a.setName("NH3-N核准排放量");
			nameAndDataNh3a.setData(nh3alist);
			dataList.add(nameAndDataNh3);
			dataList.add(nameAndDataNh3a);
		}

		return dataList;
	}

}
