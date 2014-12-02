package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.skpw.bean.TBasEnterEnviron;
import com.skpw.bean.TBasEnterprise;

public interface WryhbsxService {

	//查询数据条数
	public int queryCount();
		
	//分页查询
	public List<TBasEnterEnviron> findByPage(Pageable pageable);
		
	//保存
	public void save(TBasEnterEnviron tee);
		
	//删除
	public void del(String id);
	
	//根据id查询污染源基本信息
	public TBasEnterEnviron findWry(String id);
}
