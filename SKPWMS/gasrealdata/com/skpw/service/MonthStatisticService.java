package com.skpw.service;

import java.util.List;

import com.skpw.bean.MonthReportBean;

public interface MonthStatisticService {
	
	public   List<MonthReportBean>  monthStatisticReport(String monitortime,int ffacilityid,int controlid);
	
	public   List<MonthReportBean> findMMAS(int cId,int ffacilityid, String time);
}
