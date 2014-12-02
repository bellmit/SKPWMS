package com.skpw.service;

import java.util.List;

public interface RealtimeService {

	// 实时数据分页显示
	public List showRealtimeByPage(int firstSize, int maxSize);

	// 实时数据记录数
	public long realTimeCount();

	public List showRealtimeByPagenew(int firstSize, int maxSize,List<String> qyids);
	
	public long realTimeCountnew(List<String> qyids);
}
