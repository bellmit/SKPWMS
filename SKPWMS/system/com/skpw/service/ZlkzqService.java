package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.skpw.bean.TBasZlkzq;

public interface ZlkzqService {

	//查询数据条数
		public int queryCount();
		
		//分页查询
		public List<TBasZlkzq> findByPage(Pageable pageable);
		
		//保存
		public void save(TBasZlkzq pfk);
		
		//删除
		public void del(String id);
		
}
