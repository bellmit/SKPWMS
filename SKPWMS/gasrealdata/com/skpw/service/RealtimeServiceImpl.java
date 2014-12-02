package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.dao.RealtimeDao;

@Service("realtimeService")
public class RealtimeServiceImpl implements RealtimeService {

	@Resource
	private RealtimeDao realtimeDao;

	public List showRealtimeByPage(int firstSize, int maxSize) {

		return realtimeDao.showRealtimeByPage(firstSize, maxSize);
	}

	public long realTimeCount() {

		return realtimeDao.realTimeCount();
	}

	public List showRealtimeByPagenew(int firstSize, int maxSize,List<String> qyids) {

		return realtimeDao.showRealtimeByPagenew(firstSize, maxSize,qyids);
	}
	
	public long realTimeCountnew(List<String> qyids) {

		return realtimeDao.realTimeCountnew(qyids);
	}
}
