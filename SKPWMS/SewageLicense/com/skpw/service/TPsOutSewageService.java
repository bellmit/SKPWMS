package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TPsOutSewage;


public interface TPsOutSewageService {
	public Page<TPsOutSewage> findAllByPage(TPsOutSewage tPsOutSewage,Pageable pager);

	public TPsOutSewage findOne(String id);
	
	public TPsOutSewage save(TPsOutSewage tPsOutSewage);
	
	public void update(TPsOutSewage tPsOutSewage);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
}
