package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.skpw.bean.TBasCompanyScale;

public interface TBasCompanyScaleService {
	
	//查询数据条数
	public int queryCount();
				
	//分页查询
	public List<TBasCompanyScale> findByPage(Pageable pageable);
				
	//保存
	public void save(TBasCompanyScale tem);
				
	//删除
	public void del(String id);
			
	//根据id查询
	public TBasCompanyScale findOne(String id);
	
	//查询全部
	public List<TBasCompanyScale> findAll();
	
}
