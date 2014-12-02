package com.skpw.service;

import java.util.List;

public interface GasRealtimeHistoryService {

	public List queryRealtimeHis(String starttime, String endTime, int fid,
			int facility, int currentPage, int pageSize);

	public long realtimeDataCount(String starttime, String endTime, int fid,
			int facility);
}
