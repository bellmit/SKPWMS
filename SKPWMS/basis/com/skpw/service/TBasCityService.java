package com.skpw.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.skpw.bean.TBasCity;

public interface TBasCityService {

	public List<Map<String, Object>> findList();
	
	//查询fisDisable为false的列表
	public List<TBasCity> findTBasCityList(Specification<TBasCity> spf);
	
	//查询数据条数
	public int queryCount();
			
	//分页查询
	public List<TBasCity> findByPage(Pageable pageable);
			
	//保存
	public void save(TBasCity tem);
			
	//删除
	public void del(String id);
		
	//根据id查询
	public TBasCity findOne(String id);
}
