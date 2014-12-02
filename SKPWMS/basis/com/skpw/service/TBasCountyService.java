package com.skpw.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasCounty;

public interface TBasCountyService {
	
	//查询fisDisable为false的列表
	public List<TBasCounty> getList(Specification<TBasCounty> spf);
	
	//查询数据条数
	public int queryCount();
			
	//分页查询
	public List<TBasCounty> findByPage(Pageable pageable);
			
	//保存
	public void save(TBasCounty tem);
			
	//删除
	public void del(String id);
		
	//根据id查询
	public TBasCounty findOne(String id);
	
	//查询某市的区
	public List<TBasCounty> findCountyForCity(String id);
	
	//查找深圳市市辖区
	public   List<TBasCounty>   findCountryByAll();
}
