package com.skpw.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.skpw.bean.TBasUnitClass;

public interface TBasUnitClassService {
	
	//查询数据条数
	public int queryCount();
				
	//分页查询
	public List<TBasUnitClass> findByPage(Pageable pageable);
				
	//保存
	public void save(TBasUnitClass tem);
				
	//删除
	public void del(String id);
			
	//根据id查询
	public TBasUnitClass findOne(String id);
	
	//查询全部
	public List<TBasUnitClass> findAll();
}
