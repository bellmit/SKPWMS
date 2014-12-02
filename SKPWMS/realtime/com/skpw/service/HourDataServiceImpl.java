package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.ReportData;
import com.skpw.dao.HourDataDao;

@Service("hourDataService")
public class HourDataServiceImpl implements HourDataService{

	@Resource
	private HourDataDao hourDataDao;
	
	@Override
	public List<ReportData> findListByPage(int cId, String time) {
		return hourDataDao.findListByPage(cId, time);
	}

	@Override
	public List<ReportData> findMMAS(int cId, String time) {
		// TODO Auto-generated method stub
		return hourDataDao.findMMAS(cId, time);
	}
	
}
