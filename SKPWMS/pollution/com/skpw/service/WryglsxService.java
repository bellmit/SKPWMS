package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.skpw.bean.TBasEnterManagement;

public interface WryglsxService {

	//查询数据条数
	public int queryCount();
		
	//分页查询
	public List<TBasEnterManagement> findByPage(Pageable pageable);
		
	//保存
	public void save(TBasEnterManagement tem);
		
	//删除
	public void del(String id);
	
	//根据id查询污染源基本信息
	public TBasEnterManagement findWry(String id);
}
