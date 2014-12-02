package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.ReportData;
import com.skpw.dao.QuarterDataDao;

@Service("quarterDataService")
public class QuarterDataServiceImpl implements QuarterDataService {
	@Resource
	private QuarterDataDao quarterDataDao;

	public List<ReportData> statisticQuarter(int cId, String time) {
		return quarterDataDao.statisticQuarter(cId, time);
	}

	public List<ReportData> findMMAS(int cId, String time) {
		
		return quarterDataDao.findMMAS(cId, time);
	}

}
