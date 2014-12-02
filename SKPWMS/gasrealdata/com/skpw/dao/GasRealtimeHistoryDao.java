package com.skpw.dao;

import java.util.List;

public interface GasRealtimeHistoryDao {

	public List queryRealtimeHis(String starttime, String endTime, int fid,
			int facility, int currentPage, int pageSize);

	public long realtimeDataCount(String starttime, String endTime, int fid,
			int facility);
}
