package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.skpw.bean.TRtFacility;

public interface TRtFacilityService {

	public Page<TRtFacility> findAllByPage(String ffacilityNo, String ffacilityName, String wry_id, int page, int rows);
	
	public void save(TRtFacility tRtFacility);
	
	public TRtFacility findOne(Integer id);
	
	public void del(String ids);
	
	//通过总量控制器，查找设备
	public List findFaciltityByControlid(int fctrlid);
}
