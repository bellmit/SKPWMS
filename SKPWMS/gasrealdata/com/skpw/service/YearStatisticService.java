package com.skpw.service;

import java.util.List;

import com.skpw.bean.YearReportBean;

public interface YearStatisticService {
	
	public   List<YearReportBean>   yearStatisticReport(String monitortime,int controlid,int ffacilityid);
	
	// 年数据的最大值，最小值，平均值、总和
	public List<YearReportBean>    findYearMMAS(int monitortime, int controlid,int ffacilityid);
}
