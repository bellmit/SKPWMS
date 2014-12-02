package com.skpw.service;

import java.util.List;

import com.skpw.bean.ReportData;

public interface QuarterDataService {
	
	//季度数据详细信息
	public List<ReportData> statisticQuarter(int cId, String time);
	
	public List<ReportData> findMMAS(int cId, String time);
}
