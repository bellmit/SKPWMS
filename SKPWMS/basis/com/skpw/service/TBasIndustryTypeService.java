package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasIndustryType;
import com.skpw.bean.TreeBean;

public interface TBasIndustryTypeService {

	//查询数据条数
	public int queryCount();
				
	//分页查询
	public List<TBasIndustryType> findByPage(Specification<TBasIndustryType> spf, Pageable pageable);
				
	//保存
	public void save(TBasIndustryType tem);
				
	//删除
	public void del(String id);
	
	//查询全部
	public List<TBasIndustryType> findAllOrderByID();
			
}
