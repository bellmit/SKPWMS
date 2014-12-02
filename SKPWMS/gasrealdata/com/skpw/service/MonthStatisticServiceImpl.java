package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.MonthReportBean;
import com.skpw.dao.MonthStatisticDao;

@Service("monthStatisticService")
public class MonthStatisticServiceImpl implements MonthStatisticService {

	@Resource
	private MonthStatisticDao monthStatisticDao;

	public List<MonthReportBean> monthStatisticReport(String monitortime,int ffacilityid,
			int controlid) {

		String[] str = monitortime.split("-");
		int year = Integer.valueOf(str[0]);
		int month = Integer.valueOf(str[1]);

		List<MonthReportBean> list = monthStatisticDao.monthStatisticReport(
				year, month,ffacilityid, controlid);
		return list;
	}

	public List<MonthReportBean> findMMAS(int controlid,int ffacilityid, String monitortime) {
		String[] str = monitortime.split("-");
		int year = Integer.valueOf(str[0]);
		int month = Integer.valueOf(str[1]);
		List<MonthReportBean> list = monthStatisticDao.findMMAS(year, month, ffacilityid, controlid);
		return list;
	}

}
