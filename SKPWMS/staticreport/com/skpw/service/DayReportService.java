package com.skpw.service;

import java.util.List;

public interface DayReportService {
	
	public List statisticDayReport1(Integer ttcid,String time);
	
	public List statisticDayReportByColumn(Integer ttcid, String time);
	
	public List statisticComplexDayReport(Integer ttcid, String time,String type);
}
