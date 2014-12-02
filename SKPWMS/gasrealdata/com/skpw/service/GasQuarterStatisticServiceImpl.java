package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.QuarterReportBean;
import com.skpw.dao.GasQuarterStatisticDao;

@Service("gasQuarterStatisticService")
public class GasQuarterStatisticServiceImpl implements  GasQuarterStatisticService {
	
	@Resource
	private GasQuarterStatisticDao gasQuarterStatisticDao;
	
	public List<QuarterReportBean> quarterStatisticReport(int year,
			int ffacilityid, int controlid) {
		
		return gasQuarterStatisticDao.quarterStatisticReport(year, ffacilityid, controlid);
	}

	
	public List<QuarterReportBean> findMMAS(int year, int ffacilityid,
			int controlid) {
		
		return gasQuarterStatisticDao.findMMAS(year, ffacilityid, controlid);
	}

}
