package com.skpw.dao;

import java.util.List;

import com.skpw.bean.QuarterReportBean;

public interface GasQuarterStatisticDao {
	
	public List<QuarterReportBean> quarterStatisticReport(int year,int ffacilityid,int controlid);

	// 月数据的最大值，最小值，平均值、总和
	public List<QuarterReportBean> findMMAS(int year,int ffacilityid,int controlid);

}
