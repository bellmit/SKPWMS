package com.skpw.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.skpw.bean.TBasWaterDisStd;

public interface TBasWaterDisStdService {
	
	//查询数据条数
	public int queryCount();
				
	//分页查询
	public List<TBasWaterDisStd> findByPage(Pageable pageable);
				
	//保存
	public void save(TBasWaterDisStd tem);
				
	//删除
	public void del(String id);
			
	//根据id查询
	public TBasWaterDisStd findOne(String id);
	
	//查询全部
	public List<TBasWaterDisStd> findAll();
}
