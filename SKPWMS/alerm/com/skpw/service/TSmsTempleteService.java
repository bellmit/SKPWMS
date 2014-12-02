package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TSmsTemplete;


public interface TSmsTempleteService {
	public Page<TSmsTemplete> findAllByPage(TSmsTemplete tSmsTemplete,Pageable pager);

	public TSmsTemplete findOne(String id);
	
	public List<TSmsTemplete> findAll();
	
	public TSmsTemplete save(TSmsTemplete tSmsTemplete);
	
	
}
