package com.skpw.service;

import java.util.List;

import com.skpw.bean.ReportData;

public interface DayDataService {
	
	//日时数据详细信息
	public List<ReportData> findListByPage(int cId, String time);
		
	//日时数据的最大值，最小值，平均值、总和
	public List<ReportData> findMMAS(int cId, String time);
}
