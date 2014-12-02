package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.YearReportBean;
import com.skpw.dao.YearStatisticDao;

@Service("yearStatisticService")
public class YearStatisticServiceImpl implements YearStatisticService {

	@Resource
	private YearStatisticDao yearStatisticDao;

	public List<YearReportBean> yearStatisticReport(String monitortime,
			int controlid,int ffacilityid) {

		return yearStatisticDao.yearStatisticReport(
				Integer.valueOf(monitortime), controlid,ffacilityid);
	}

	public List<YearReportBean> findYearMMAS(int monitortime, int controlid,int ffacilityid) {

		return yearStatisticDao.findMMAS(monitortime, controlid,ffacilityid);
	}

}
