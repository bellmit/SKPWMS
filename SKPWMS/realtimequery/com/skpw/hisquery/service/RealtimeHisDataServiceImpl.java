package com.skpw.hisquery.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.hisquery.dao.RealtimeHisDataDao;

@Service("realtimeHisDataService")
public class RealtimeHisDataServiceImpl implements RealtimeHisDataService {

	@Resource
	private RealtimeHisDataDao realtimeHisDataDao;

	public List queryRealtimeHis(String starttime, String endTime, int fid,
			int currentPage, int pageSize) {

		return realtimeHisDataDao.queryRealtimeHis(starttime, endTime, fid,
				currentPage, pageSize);
	}

	public long realtimeDataCount(String starttime, String endTime, int fid) {

		return realtimeHisDataDao.realtimeDataCount(starttime, endTime, fid);
	}
}
