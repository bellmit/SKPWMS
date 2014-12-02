package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.ComplexBean;
import com.skpw.bean.MonthReport;
import com.skpw.bean.NameAndData;
import com.skpw.bean.XData;
import com.skpw.dao.MonthReportDao;

@Service("monthReportService")
public class MonthReportServiceImpl implements MonthReportService {

	@Resource
	private MonthReportDao monthReportDao;

	public List statisticMonthReport(Integer ttcid, String time) {

		String[] statime = time.split("-");

		Integer year = Integer.valueOf(statime[0]);
		Integer month = Integer.valueOf(statime[1]);

		List<NameAndData> dataList = new ArrayList<NameAndData>();

		NameAndData nameAndDataCod = new NameAndData();// COD
		NameAndData nameAndDataNh3 = new NameAndData();// NH3
		List<Float> codlist = new ArrayList<Float>();// COD
		List<Float> nh3list = new ArrayList<Float>();// NH3

		List<MonthReport> chartlist = monthReportDao.statisticMonthReport(
				ttcid, year, month);

		for (MonthReport monthReport : chartlist) {
			codlist.add(monthReport.getCod());
			nh3list.add(monthReport.getNh3());

		}
		nameAndDataCod.setName("COD");

		nameAndDataCod.setData(codlist);

		nameAndDataNh3.setData(nh3list);
		nameAndDataNh3.setName("NH3-N");

		dataList.add(nameAndDataCod);
		dataList.add(nameAndDataNh3);
		return dataList;
	}

	public List getXData(Integer ttcid, String time) {

		String[] statime = time.split("-");

		Integer year = Integer.valueOf(statime[0]);
		Integer month = Integer.valueOf(statime[1]);
		List<XData> xdatalist = new ArrayList<XData>();

		XData xData = new XData();

		List xlist = new ArrayList();
		List<MonthReport> chartlist = monthReportDao.statisticMonthReport(
				ttcid, year, month);
		for (MonthReport monthReport : chartlist) {
			xlist.add(monthReport.getDate());
		}
		xData.setXdata(xlist);
		xdatalist.add(xData);
		return xdatalist;
	}

	@Override
	public List statisticComplexMonthReport(Integer ttcid, String time,
			String type) {
		String[] statime = time.split("-");
		Integer year = Integer.valueOf(statime[0]);
		Integer month = Integer.valueOf(statime[1]);
		List<ComplexBean> datalist = new ArrayList<ComplexBean>();

		ComplexBean codcomplexBean = new ComplexBean();
		ComplexBean nh3complexBean = new ComplexBean();
		ComplexBean codfcomplexBean = new ComplexBean();
		ComplexBean nh3fcomplexBean = new ComplexBean();

		List codlist = new ArrayList();
		List nh3list = new ArrayList();
		List codflist = new ArrayList();
		List nh3flist = new ArrayList();

		List<MonthReport> chartlist = monthReportDao.statisticMonthReport(
				ttcid, year, month);
		for (MonthReport monthReport : chartlist) {
			codlist.add(monthReport.getCod());
			nh3list.add(monthReport.getNh3());
			codflist.add(monthReport.getCodf());
			nh3flist.add(monthReport.getNh3f());
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
