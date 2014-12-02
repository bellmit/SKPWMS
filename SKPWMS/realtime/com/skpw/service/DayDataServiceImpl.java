package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.ReportData;
import com.skpw.dao.DayDataDao;

@Service("dayDataService")
public class DayDataServiceImpl implements DayDataService{

	@Resource
	private DayDataDao dayDataDao;
	
	@Override
	public List<ReportData> findListByPage(int cId, String time) {
		// TODO Auto-generated method stub
		return dayDataDao.findListByPage(cId, time);
	}

	@Override
	public List<ReportData> findMMAS(int cId, String time) {
		// TODO Auto-generated method stub
		return dayDataDao.findMMAS(cId, time);
	}

}
