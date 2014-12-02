package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.dao.GasRealtimeHistoryDao;

@Service("gasRealtimeHistoryService")
public class GasRealtimeHistoryServiceImpl implements GasRealtimeHistoryService {

	
	@Resource
	private GasRealtimeHistoryDao gasRealtimeHistoryDao;
	
	
	public List queryRealtimeHis(String starttime, String endTime, int fid,
			int facility, int currentPage, int pageSize) {
		
		
		List list=gasRealtimeHistoryDao.queryRealtimeHis(starttime, endTime, fid, facility, currentPage, pageSize);
		
		return list;
	}

	
	public long realtimeDataCount(String starttime, String endTime, int fid,
			int facility) {
		
		return gasRealtimeHistoryDao.realtimeDataCount(starttime, endTime, fid, facility);
	}

	
	

}
