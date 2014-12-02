package com.skpw.service;

import java.util.List;

import com.skpw.bean.QuarterReportBean;

public interface GasQuarterStatisticService {
	
	public List<QuarterReportBean> quarterStatisticReport(int year,int ffacilityid,int controlid);

	// 月数据的最大值，最小值，平均值、总和
	public List<QuarterReportBean> findMMAS(int year,int ffacilityid,int controlid);
}
