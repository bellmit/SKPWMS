package com.stepwell.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.RealtimeBean;
import com.stepwell.dao.MapRealtimeDao;

@Service
public class MapRealtimeServiceImpl implements MapRealTimeService {

	@Resource
	private MapRealtimeDao mapRealtimeDao;

	public List findRealtimeData(String fenterid) {

		return mapRealtimeDao.findRealtimeData(fenterid);
	}
	
	public List findRealtimeDatanew(String id,String name,List<String> zzjgids) {

		return mapRealtimeDao.findRealtimeDatanew(id,name,zzjgids);
	}

	@Override
	public List<RealtimeBean> showRealtimeByPagenew(String qyid) {
		return mapRealtimeDao.showRealtimeByPagenew(qyid);
	}

}
