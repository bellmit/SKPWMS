package com.skpw.service;

import java.util.List;

public interface MonthReportService {
	
	public List statisticMonthReport(Integer ttcid,String time);
	
	public List getXData(Integer ttcid, String time);
	
	public List statisticComplexMonthReport(Integer ttcid, String time,String type);
}
