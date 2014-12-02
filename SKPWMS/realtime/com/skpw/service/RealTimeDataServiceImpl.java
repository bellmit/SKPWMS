package com.skpw.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.skpw.bean.TBasEnterprise;
import com.skpw.dao.RealTimeDataDao;

@Service("realTimeDataService")
public class RealTimeDataServiceImpl implements RealTimeDataService{
	
	@Resource
	private RealTimeDataDao realTimeDataDao;
	
	@Override
	public Map<String , Object> findRealtimeData(int page, int rows, String id) {
		Map<String , Object> map = realTimeDataDao.findRealtimeData(page, rows, id);
		return map;
	}

	@Override
	public List<TBasEnterprise> findWry() {
		// TODO Auto-generated method stub
		return realTimeDataDao.findWry();
	}
	
	public Map<String , Object> findRealtimeDatanew(int page, int rows, String id,List<String> zzjgids) {
		Map<String , Object> map = realTimeDataDao.findRealtimeDatanew(page, rows, id,zzjgids);
		return map;
	}
	 
}
