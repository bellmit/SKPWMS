package com.skpw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSmsChoiceItem;
import com.skpw.repository.TSmsChoiceItemRepository;

@Service
public class TSmsChoiceItemServiceImpl implements TSmsChoiceItemService{
	
	@Autowired
	private TSmsChoiceItemRepository tSmsChoiceItemRepository;

	@Override
	public Page<TSmsChoiceItem> findAllByPage(TSmsChoiceItem tSmsChoiceItem,
			Pageable pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TSmsChoiceItem findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TSmsChoiceItem> findAll() {
		return tSmsChoiceItemRepository.findAll();
	}

	@Override
	public TSmsChoiceItem save(TSmsChoiceItem tSmsChoiceItem) {
		return tSmsChoiceItemRepository.save(tSmsChoiceItem);
	}

}
