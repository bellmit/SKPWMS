package com.skpw.hisquery.service;

import java.util.List;

public interface RealtimeHisDataService {

	public List queryRealtimeHis(String starttime, String endTime, int fid,
			int currentPage, int pageSize);
	
	public long realtimeDataCount(String starttime, String endTime, int fid);
}
