package com.skpw.service;

import java.util.List;
import java.util.Map;
import com.skpw.bean.TBasEnterprise;



public interface RealTimeDataService {

	//查询日数据
	public Map<String , Object> findRealtimeData(int page, int rows, String id);
	
	//查询污染源列表
	public List<TBasEnterprise> findWry();
	
	public Map<String , Object> findRealtimeDatanew(int page, int rows, String id,List<String> zzjgids);
}
