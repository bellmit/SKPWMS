package com.skpw.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.skpw.bean.TBasVillage;

public interface TBasVillageService {

	//查询fisDisable为false的列表
	public List<TBasVillage> getList(Specification<TBasVillage> spf);
	
	//查询数据条数
	public int queryCount();
			
	//分页查询
	public List<TBasVillage> findByPage(Pageable pageable);
			
	//保存
	public void save(TBasVillage tem);
			
	//删除
	public void del(String id);
		
	//根据id查询
	public TBasVillage findOne(String id);
}
