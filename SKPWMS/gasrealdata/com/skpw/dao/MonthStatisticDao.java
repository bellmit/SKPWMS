package com.skpw.dao;

import java.util.List;

import com.skpw.bean.MonthReportBean;

public interface MonthStatisticDao {

	public List<MonthReportBean> monthStatisticReport(int year,int month,int ffacilityid,int controlid);

	// 月数据的最大值，最小值，平均值、总和
	public List<MonthReportBean> findMMAS(int year,int month,int ffacilityid,int controlid);
}
