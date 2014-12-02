package com.skpw.service;

import java.util.List;

import com.skpw.bean.DayReportBean;

public interface DayStatisticService {
	
	public   List<DayReportBean>   dayStatisticReport(String monitortime,int ffacilityid,int controlid);
	
	//小时数据的最大值，最小值，平均值、总和
	public List<DayReportBean> findMMAS(String monitortime,int ffacilityid,int controlid);
}
