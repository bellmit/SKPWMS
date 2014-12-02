package com.skpw.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.skpw.bean.TBasOutWhere;

public interface TBasOutWhereService {
	
	//查询数据条数
	public int queryCount();
				
	//分页查询
	public List<TBasOutWhere> findByPage(Pageable pageable);
				
	//保存
	public void save(TBasOutWhere tem);
				
	//删除
	public void del(String id);
			
	//根据id查询
	public TBasOutWhere findOne(String id);

	//查询全部
	public List<TBasOutWhere> findAll();
}
