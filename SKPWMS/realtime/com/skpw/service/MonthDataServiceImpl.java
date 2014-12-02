package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.ReportData;
import com.skpw.dao.MonthDataDao;

@Service("monthDataService")
public class MonthDataServiceImpl implements MonthDataService{

	@Resource
	private MonthDataDao monthDataDao;
	
	@Override
	public List<ReportData> findListByPage(int cId, String time) {
		// TODO Auto-generated method stub
		return monthDataDao.findListByPage(cId, time);
	}

	@Override
	public List<ReportData> findMMAS(int cId, String time) {
		// TODO Auto-generated method stub
		return monthDataDao.findMMAS(cId, time);
	}

}
