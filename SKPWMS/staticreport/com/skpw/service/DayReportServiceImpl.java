package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.ComplexBean;
import com.skpw.bean.DayReport;
import com.skpw.bean.NameAndData;
import com.skpw.dao.DayReportDao;
import com.skpw.dao.DayReportDaoImpl;

@Service("dayReportService")
public class DayReportServiceImpl implements DayReportService {

	@Resource
	private DayReportDao dayReportDao;

	public List statisticDayReport1(Integer ttcid, String time) {

		List<NameAndData> dataList = new ArrayList<NameAndData>();

		NameAndData nameAndDataCod = new NameAndData();// COD
		NameAndData nameAndDataNh3 = new NameAndData();// NH3

		List<Float> codlist = new ArrayList<Float>();// COD
		List<Float> nh3list = new ArrayList<Float>();// NH3

		@SuppressWarnings("unchecked")
		List<DayReport> chartlist = dayReportDao.statisticDayReport1(ttcid,
				time);

		for (DayReport dayReport : chartlist) {
			codlist.add(dayReport.getCod());
			nh3list.add(dayReport.getNh3());

		}

		nameAndDataCod.setName("COD浓度");
		nameAndDataNh3.setName("NH3-N浓度");

		nameAndDataCod.setData(codlist);
		nameAndDataNh3.setData(nh3list);

		dataList.add(nameAndDataCod);
		dataList.add(nameAndDataNh3);

		return dataList;
	}

	public List statisticDayReportByColumn(Integer ttcid, String time) {

		List<NameAndData> dataList = new ArrayList<NameAndData>();

		NameAndData nameAndDataCod = new NameAndData();// CODpfl
		NameAndData nameAndDataNh3 = new NameAndData();// NH3pfl

		List<Float> codlist = new ArrayList<Float>();// COD
		List<Float> nh3list = new ArrayList<Float>();// NH3

		@SuppressWarnings("unchecked")
		List<DayReport> chartlist = dayReportDao.statisticDayReport1(ttcid,
				time);

		for (DayReport dayReport : chartlist) {
			codlist.add(dayReport.getCodpfl());
			nh3list.add(dayReport.getNh3pfl());
		}

		nameAndDataCod.setName("COD排放量");
		nameAndDataNh3.setName("NH3-N排放量");

		nameAndDataCod.setData(codlist);
		nameAndDataNh3.setData(nh3list);

		dataList.add(nameAndDataCod);
		dataList.add(nameAndDataNh3);

		return dataList;
	}

	public List statisticComplexDayReport(Integer ttcid, String time,
			String type) {

		List<ComplexBean> datalist = new ArrayList<ComplexBean>();

		ComplexBean codcomplexBean = new ComplexBean();
		ComplexBean nh3complexBean = new ComplexBean();
		ComplexBean codfcomplexBean = new ComplexBean();
		ComplexBean nh3fcomplexBean = new ComplexBean();

		List<Float> codlist = new ArrayList<Float>();
		List<Float> nh3list = new ArrayList<Float>();
		List<Float> codflist = new ArrayList<Float>();
		List<Float> nh3flist = new ArrayList<Float>();

		List<DayReport> chartlist = dayReportDao.statisticDayReport1(ttcid,time);

		for (DayReport dayReport : chartlist) {

			codlist.add(dayReport.getCod());

			nh3list.add(dayReport.getNh3());

			codflist.add(dayReport.getCodpfl());

			nh3flist.add(dayReport.getNh3pfl());

		}
		if (type.equals("COD")) {
			codcomplexBean.setDatalist(codlist);
			codfcomplexBean.setDatalist(codflist);
			datalist.add(codcomplexBean);
			datalist.add(codfcomplexBean);

		}
		if (type.equals("NH3-N")) {
			nh3complexBean.setDatalist(nh3list);
			nh3fcomplexBean.setDatalist(nh3flist);
			datalist.add(nh3complexBean);
			datalist.add(nh3fcomplexBean);
		}

		return datalist;
	}

}
