package com.skpw.hisquery.dao;

import java.util.List;

public interface RealtimeHisDataDao {

	public List queryRealtimeHis(String starttime,String endTime,int fid,int currentPage, int pageSize);
	
	
	public long realtimeDataCount(String starttime, String endTime, int fid);
}
