package com.skpw.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.skpw.bean.TBasProvince;

public interface TBasProvinceService {

	//查询fisDisable为false的列表
	public List<TBasProvince> getList(Specification<TBasProvince> spf);
	
	//查询数据条数
	public int queryCount();
			
	//分页查询
	public List<TBasProvince> findByPage(Pageable pageable);
			
	//保存
	public void save(TBasProvince tem);
			
	//删除
	public void del(String id);
		
	//根据id查询
	public TBasProvince findOne(String id);
}
