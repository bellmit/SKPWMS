package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TSmsChoiceItem;


public interface TSmsChoiceItemService {
	public Page<TSmsChoiceItem> findAllByPage(TSmsChoiceItem tSmsChoiceItem,Pageable pager);

	public TSmsChoiceItem findOne(String id);
	
	public List<TSmsChoiceItem> findAll();
	
	public TSmsChoiceItem save(TSmsChoiceItem tSmsChoiceItem);
	
	
}
