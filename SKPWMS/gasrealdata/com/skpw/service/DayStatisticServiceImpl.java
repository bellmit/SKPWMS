package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.DayReportBean;
import com.skpw.dao.DayStatisticDao;

@Service("dayStatisticService")
public class DayStatisticServiceImpl implements  DayStatisticService {
	
	@Resource
	private   DayStatisticDao dayStatisticDao;
	
	public List<DayReportBean> dayStatisticReport(String monitortime,int ffacilityidint,int controlid) {
		
		return dayStatisticDao.dayStatisticReport(monitortime,ffacilityidint, controlid);
	}

	
	public List<DayReportBean> findMMAS(String monitortime,int ffacilityid, int controlid) {
		
		return dayStatisticDao.findMMAS(monitortime, ffacilityid,controlid);
	}
	
	
	
}
