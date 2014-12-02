package com.skpw.dao;

import java.util.List;

import com.skpw.bean.ReportData;

public interface HourDataDao {

	//小时数据详细信息
	public List<ReportData> findListByPage(int cId, String time);
	
	//小时数据的最大值，最小值，平均值、总和
	public List<ReportData> findMMAS(int cId, String time);
}
