package com.skpw.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.skpw.bean.TBasTown;

public interface TBasTownService {

	//查询fisDisable为false的列表
	public List<TBasTown> getList(Specification<TBasTown> spf);
	
	//查询数据条数
	public int queryCount();
			
	//分页查询
	public List<TBasTown> findByPage(Pageable pageable);
			
	//保存
	public void save(TBasTown tem);
			
	//删除
	public void del(String id);
		
	//根据id查询
	public TBasTown findOne(String id);
}
