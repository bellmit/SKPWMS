package com.skpw.dao;

import java.util.List;

import com.skpw.bean.YearReportBean;

public interface YearStatisticDao {
	
	
	public   List<YearReportBean>   yearStatisticReport(int monitortime,int controlid,int ffacilityid);
	
	// 年数据的最大值，最小值，平均值、总和
	public List<YearReportBean> findMMAS(int monitortime, int controlid,int ffacilityid);
}
