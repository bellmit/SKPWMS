package com.stepwell.service;

import java.util.List;

import com.skpw.bean.RealtimeBean;

public interface MapRealTimeService {
	
	public List findRealtimeData(String fenterid);
	
	public List findRealtimeDatanew(String id,String name,List<String> zzjgids);
	
	public List<RealtimeBean> showRealtimeByPagenew(String qyid);
}
